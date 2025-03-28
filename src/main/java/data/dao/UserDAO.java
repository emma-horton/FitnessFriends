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

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public User getUserByUsernameAndPassword(String username, String password) throws SQLException {
        String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("userId");
                String userUsername = rs.getString("username");

                // Retrieve goals for the user
                List<goal.FitnessGoal> goals = getGoalsForUser(userId);

                // Retrieve pet for the user
                PetDAO petDAO = new PetDAO(connection);
                PetBehaviour petBehaviour = petDAO.getPetForUser(userId);

                // Create UserProfile and User objects
                UserProfile userProfile = new UserProfile(goals);
                return new User(userId, userUsername, userProfile, petBehaviour);
            }
        }
        return null; // User not found
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
}