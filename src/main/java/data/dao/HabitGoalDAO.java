package data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import goal.GoalType;
import goal.SportType;

public class HabitGoalDAO {
    private Connection connection;

    public HabitGoalDAO(Connection connection) {
        this.connection = connection;
    }

    // Method to create a new habit goal for a user
    public void createHabitGoal(int userId, GoalType goalType, SportType sportType, int targetValue) throws SQLException {
        String sql = "INSERT INTO HabitGoals (userId, goalType, sport, targetValue) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, goalType.toString());
            pstmt.setString(3, sportType.toString());
            pstmt.setInt(4, targetValue);
            pstmt.executeUpdate();
        }
    }

    public void updateHabitGoal(int goalId, int newTargetValue) throws SQLException {
        String sql = "UPDATE HabitGoals SET targetValue = ? WHERE goalId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, newTargetValue);
            pstmt.setInt(2, goalId);
            pstmt.executeUpdate();
        }
    }

    public List<goal.FitnessGoal> getGoalsForUser(int userId) throws SQLException {
        System.out.println("Checking connection state: " + connection.isClosed()); // Debug connection state
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