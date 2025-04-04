package data.dao;
import data.Activity;
import data.DatabaseConnection;
import goal.SportType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivityDAO{
    private Connection connection;

    public ActivityDAO(Connection connection) {
        this.connection = connection;
    }

    public void disconnect() {
        DatabaseConnection.disconnect();
    }

    public List<Activity> getByUserId(int userId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Activity> activityList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM activitydata WHERE userId = ?;";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int dataId = rs.getInt("dataId");
                String activityDate = rs.getString("activityDate");
                String activityType = rs.getString("activityType");
                double activityDuration = rs.getDouble("activityDuration");
                double activityDistance = rs.getDouble("activityDistance");

                Activity activity = new Activity(dataId, userId, activityDate, activityType, activityDuration, activityDistance);
                activityList.add(activity);
            }

        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
        return activityList;
    }

    public List<Activity> getThisWeeksDataByUserId(int userId, SportType sport) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Activity> activityList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM activitydata WHERE activityDate >= DATE('now', '-7 days') AND userId = ? AND activityType = ?;";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, userId);
            pstmt.setString(2, sport.toString());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int dataId = rs.getInt("dataId");
                String activityDate = rs.getString("activityDate");
                String activityType = rs.getString("activityType");
                double activityDuration = rs.getDouble("activityDuration");
                double activityDistance = rs.getDouble("activityDistance");

                Activity activity = new Activity(dataId, userId, activityDate, activityType, activityDuration, activityDistance);
                activityList.add(activity);
            }

        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
        return activityList;
    }

    public List<Activity> getLastWeeksDataByUserId(int userId, SportType sport) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Activity> activityList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM activitydata WHERE activityDate BETWEEN DATE('now', '-14 days') AND DATE('now', '-7 days') AND userId = ? AND activityType = ?;";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, userId);
            pstmt.setString(2, sport.toString());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int dataId = rs.getInt("dataId");
                String activityDate = rs.getString("activityDate");
                String activityType = rs.getString("activityType");
                double activityDuration = rs.getDouble("activityDuration");
                double activityDistance = rs.getDouble("activityDistance");

                Activity activity = new Activity(dataId, userId, activityDate, activityType, activityDuration, activityDistance);
                activityList.add(activity);
            }

        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
        return activityList;
    }

}
