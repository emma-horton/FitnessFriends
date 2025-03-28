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

            // Login
            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to the Virtual Pet App!");
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            User user = userDAO.getUserByUsernameAndPassword(username, password);

            if (user == null) {
                System.out.println("Invalid username or password. Exiting...");
                return;
            }

            System.out.println("Login successful! Welcome, " + user.getPetBehaviour().getPet().getPetId());

            // Update the pet's health based on the user's goals
            user.getPetBehaviour().updateHealth(user.getProfile());

            // Simulate user interaction with the pet
            System.out.println("Interacting with your pet:");
            user.getPetBehaviour().tryToMove();
            user.getPetBehaviour().tryToEat();
            user.getPetBehaviour().tryToPlay();

            // Display the pet's updated health status
            System.out.println("Your pet's current health status: " + user.getPetBehaviour().getPet().getHealth().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}