package data.dao;

import user.User;
import user.UserProfile;
import pet.PetBehaviour;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM Users WHERE username = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
    
            if (rs.next()) {
                String storedHashedPassword = rs.getString("password");
                if (BCrypt.checkpw(password, storedHashedPassword)) { // Verify the password
                    int userId = rs.getInt("userId");
    
                    // Retrieve the user's goals
                    List<goal.FitnessGoal> goals = getGoalsForUser(userId);
    
                    // Create the UserProfile
                    UserProfile profile = new UserProfile(goals);
    
                    // Return the User object with the profile
                    return new User(userId, username, profile, null); // PetBehaviour will be set later
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if authentication fails
    }

    private List<goal.FitnessGoal> getGoalsForUser(int userId) throws SQLException {
        String sql = "SELECT * FROM HabitGoals WHERE userId = ?";
        List<goal.FitnessGoal> goals = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
    
            while (rs.next()) {
                int goalId = rs.getInt("goalId");
                String goalType = rs.getString("goalType");
                String sport = rs.getString("sport");
                int targetValue = rs.getInt("targetValue");
    
                goal.GoalType goalTypeEnum = goal.GoalType.valueOf(goalType.toUpperCase());
                goal.SportType sportTypeEnum = goal.SportType.valueOf(sport.toUpperCase());
    
                // Create the appropriate goal object
                switch (goalTypeEnum) {
                    case DISTANCE:
                        goals.add(new goal.types.DistanceGoal(goalId, userId, targetValue, sportTypeEnum));
                        break;
                    case DURATION:
                        goals.add(new goal.types.DurationGoal(goalId, userId, targetValue, sportTypeEnum));
                        break;
                    case FREQUENCY:
                        goals.add(new goal.types.FrequencyGoal(goalId, userId, targetValue, sportTypeEnum));
                        break;
                }
            }
        }
        return goals;
    }
   
    public boolean registerUser(String username, String password) {
        // Check if the username already exists
        if (isUsernameTaken(username)) {
            System.out.println("Username already exists. Please choose a different username.");
            return false; // Registration fails if the username is not unique
        }
    
        String sql = "INSERT INTO Users (username, password) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt()); // Hash the password using BCrypt
            pstmt.setString(1, username);
            pstmt.setString(2, hashedPassword);
            pstmt.executeUpdate();
            return true; // Registration successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Registration fails due to an SQL error
        }
    }

    private boolean isUsernameTaken(String username) {
        String sql = "SELECT COUNT(*) FROM Users WHERE username = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // If the count is greater than 0, the username is taken
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Default to false if an error occurs
    }
    public int getUserIdByUsername(String username) throws SQLException {
        String sql = "SELECT userId FROM Users WHERE username = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("userId");
            }
        }
        return -1; // Return -1 if the user is not found
    }
}