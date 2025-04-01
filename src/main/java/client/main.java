package client;

import data.DatabaseConnection;
import data.dao.UserDAO;
import user.User;

import java.sql.Connection;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            UserDAO userDAO = new UserDAO(connection);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to Fitness Friends!");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                // Registration flow
                System.out.print("Enter a username: ");
                String username = scanner.nextLine();
                System.out.print("Enter a password: ");
                String password = scanner.nextLine();

                if (userDAO.registerUser(username, password)) {
                    System.out.println("Registration successful! Please authorize your Strava account.");
                    int userId = userDAO.getUserIdByUsername(username); // Retrieve the userId for the new user
                    authorizeStravaAccount(userId); // Call Python script to authorize Strava
                } else {
                    System.out.println("Registration failed. Please try again.");
                }
            } else if (choice == 2) {
                // Login flow
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                User user = userDAO.getUserByUsernameAndPassword(username, password);

                if (user == null) {
                    System.out.println("Invalid username or password. Exiting...");
                    return;
                }

                System.out.println("Login successful! Welcome, " + user.getUsername());

                // Pull activity data from Strava
                pullActivityData(user.getUserId());

                user.getPetBehaviour().updateHealth(user.getProfile());

                // Simulate user interaction with the pet
                System.out.println("Interacting with your pet:");
                user.getPetBehaviour().tryToMove();
                user.getPetBehaviour().tryToEat();
                user.getPetBehaviour().tryToPlay();

                // Display the pet's updated health status
                System.out.println("Your pet's current health status: " + user.getPetBehaviour().getPet().getHealth().getStatus());
            } else {
                System.out.println("Invalid choice. Exiting...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void authorizeStravaAccount(int userId) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Redirecting to Strava authorization page...");
            System.out.println("Open the URL in your browser to authorize:");
            System.out.println("https://www.strava.com/oauth/authorize?client_id=153559&redirect_uri=http://localhost:3000/&response_type=code&scope=read_all,activity:read_all");
            System.out.print("Enter the authorization code from the URL: ");
            String authorizationCode = scanner.nextLine(); // Prompt the user for the authorization code
    
            // Build the command to execute the Python script
            ProcessBuilder pb = new ProcessBuilder(
                "/Library/Frameworks/Python.framework/Versions/3.11/bin/python3",
                "/Users/emmahorton/Library/CloudStorage/OneDrive-Personal/Post University/GarminVirtualPetProject/src/main/java/data/dao/stravaIntegration.py",
                String.valueOf(userId),
                "authorize",
                authorizationCode // Pass the authorization code as an argument
            );
    
            // Start the process
            Process process = pb.start();
    
            // Read the output from the Python script
            Scanner outputScanner = new Scanner(process.getInputStream());
            while (outputScanner.hasNextLine()) {
                System.out.println(outputScanner.nextLine());
            }
    
            // Read errors from the Python script
            Scanner errorScanner = new Scanner(process.getErrorStream());
            while (errorScanner.hasNextLine()) {
                System.err.println(errorScanner.nextLine());
            }
    
            // Wait for the process to complete
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Strava account authorized successfully!");
            } else {
                System.out.println("Strava authorization failed. Please try again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void pullActivityData(int userId) {
        try {
            // Build the command to execute the Python script
            ProcessBuilder pb = new ProcessBuilder(
                "/Library/Frameworks/Python.framework/Versions/3.11/bin/python3",
                "/Users/emmahorton/Library/CloudStorage/OneDrive-Personal/Post University/GarminVirtualPetProject/src/main/java/data/dao/stravaIntegration.py",
                String.valueOf(userId), "pull_activity_data" // Pass the userId as an argument
            );

            // Start the process
            Process process = pb.start();

            // Read the output from the Python script
            Scanner outputScanner = new Scanner(process.getInputStream());
            while (outputScanner.hasNextLine()) {
                System.out.println(outputScanner.nextLine());
            }

            // Wait for the process to complete
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Activity data pulled successfully!");
            } else {
                System.out.println("Failed to pull activity data. Please try again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}