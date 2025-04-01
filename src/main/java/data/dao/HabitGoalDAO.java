package data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}