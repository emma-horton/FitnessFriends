package client;

import data.DatabaseConnection;
import data.dao.PetDAO;
import data.dao.HabitGoalDAO;
import data.dao.UserDAO;
import goal.GoalType;
import goal.SportType;
import pet.PetBehaviour;
import user.User;

import java.sql.Connection;
import java.sql.SQLException;
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
                    System.out.println("Registration successful!");
            
                    int userId = userDAO.getUserIdByUsername(username); // Retrieve the userId for the new user
                    System.out.println(userId);
                    // Retry mechanism for Strava account connection
                    boolean isStravaConnected = false;
                    while (!isStravaConnected) {
                        System.out.println("Please authorize your Strava account.");
                        int exitCode = authorizeStravaAccount(userId); // Call the Strava authorization method
            
                        if (exitCode == 0) {
                            System.out.println("Strava account authorized successfully!");
                            isStravaConnected = true; // Exit the loop
                        } else {
                            System.out.println("Strava authorization failed. Would you like to try again?");
                            System.out.println("1. Retry");
                            System.out.println("2. Exit Registration");
                            System.out.print("Enter your choice: ");
                            int retryChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
            
                            if (retryChoice == 2) {
                                System.out.println("Exiting registration. Please try again later.");
                                return; // Exit the registration process
                            }
                        }
                    }
            
                    // Create a pet for the user
                    PetDAO petDAO = new PetDAO(connection);
                    HabitGoalDAO habitGoalDAO = new HabitGoalDAO(connection);
                    createPetForUser(scanner, petDAO, userId);
            
                    // Create a habit goal for the user
                    createHabitGoalForUser(scanner, habitGoalDAO, userId);
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

                // Retrieve the pet for the user
                PetDAO petDAO = new PetDAO(connection);
                try {
                    PetBehaviour petBehaviour = petDAO.getPetForUser(user.getUserId());
                    user.setPetBehaviour(petBehaviour); // Set the petBehaviour in the User object
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Failed to retrieve pet for the user. Exiting...");
                    return;
                }

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

    private static int authorizeStravaAccount(int userId) {
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
    
            // Wait for the process to complete and return the exit code
            return process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Return a non-zero value to indicate failure
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

    private static void createPetForUser(Scanner scanner, PetDAO petDAO, int userId) {
        try {
            System.out.println("Let's create a pet for you!");
            System.out.print("Enter a name for your pet: ");
            String petName = scanner.nextLine();

            System.out.println("Choose a pet type:");
            System.out.println("1. Parrot");
            System.out.println("2. Turtle");
            System.out.print("Enter your choice: ");
            int petTypeChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            String petType = petTypeChoice == 1 ? "Parrot" : "Turtle";

            System.out.println("Setting your pet's initial health status to HEALTHY.");
            String healthStatus = "HEALTHY";

            // Insert the pet into the database
            petDAO.createPet(userId, petName, petType, healthStatus);
            System.out.println("Your pet has been created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to create a pet. Please try again.");
        }
    }

    private static void createHabitGoalForUser(Scanner scanner, HabitGoalDAO habitGoalDAO, int userId) {
        try {
            System.out.println("Let's set a habit goal for you!");
            System.out.println("Choose a goal type:");
            System.out.println("1. Distance");
            System.out.println("2. Duration");
            System.out.println("3. Frequency");
            System.out.print("Enter your choice: ");
            int goalTypeChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            GoalType goalType;
            switch (goalTypeChoice) {
                case 1:
                    goalType = GoalType.DISTANCE;
                    break;
                case 2:
                    goalType = GoalType.DURATION;
                    break;
                case 3:
                    goalType = GoalType.FREQUENCY;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid goal type choice.");
            }

            System.out.println("Choose a sport type:");
            System.out.println("1. Run");
            System.out.println("2. Swim");
            System.out.println("3. Ride");
            System.out.print("Enter your choice: ");
            int sportTypeChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            SportType sportType;
            switch (sportTypeChoice) {
                case 1:
                    sportType = SportType.RUN;
                    break;
                case 2:
                    sportType = SportType.SWIM;
                    break;
                case 3:
                    sportType = SportType.RIDE;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid sport type choice.");
            }

            System.out.print("Enter the target value for your goal: ");
            int targetValue = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Insert the habit goal into the database
            habitGoalDAO.createHabitGoal(userId, goalType, sportType, targetValue);
            System.out.println("Your habit goal has been created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to create a habit goal. Please try again.");
        }
    }

}