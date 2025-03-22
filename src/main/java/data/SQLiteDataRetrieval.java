package data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLiteDataRetrieval {

    public static List<Activity> getActivityDataFromPastTwoWeeks(String dbUrl, int userId) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Activity> activityList = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(dbUrl);

            String sql = "SELECT * FROM activitydata WHERE activityDate >= DATE('now', '-14 days') AND userId = ?;";
            pstmt = conn.prepareStatement(sql);
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
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
        return activityList;
    }

    public List<Activity> retrieveActivityDataLastTwoWeeksForUser(int targetUserId) {
        String url = "jdbc:sqlite:fitness-friends.db";
        try {
            List<Activity> activities = getActivityDataFromPastTwoWeeks(url, targetUserId);
                // for (Activity activity : activities) {
                // System.out.println(activity);
            // }
            return activities;
        }catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error during activity data retrieval: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}