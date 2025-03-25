package goal.types;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import data.Activity;
import data.DatabaseConnection;
import data.SQLiteDataRetrieval;
import data.dao.ActivityDAO;
import goal.FitnessGoal;
import goal.GoalType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DistanceGoal extends FitnessGoal {
    public DistanceGoal(int targetValue, String sport) {
        super(GoalType.DISTANCE, targetValue, sport);
    }

    public List<Activity> getAllActivityDataForUser(int userId){
        try {
            Connection connection = DatabaseConnection.getConnection();
            ActivityDAO activityDAO = new ActivityDAO(connection);
            List<Activity> activities = activityDAO.getByUserId(userId);
            return activities;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean isThisWeeksGoalAchieved(int userId, String sport) {
        System.out.println("Pulling this weeks " + sport + " distance data");
        try {
            Connection connection = DatabaseConnection.getConnection();
            ActivityDAO activityDAO = new ActivityDAO(connection);
            List<Activity> activities = activityDAO.getThisWeeksDataByUserId(userId, sport);
            System.out.println(activities);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Checking if " + sport + " distance goal is achieved");
        return true;
    }
    public boolean wasLastWeeksGoalAchieved(int userId, String sport) {
        System.out.println("Pulling last weeks " + sport + " distance data");
        try {
            Connection connection = DatabaseConnection.getConnection();
            ActivityDAO activityDAO = new ActivityDAO(connection);
            List<Activity> activities = activityDAO.getLastWeeksDataByUserId(userId, sport);
            System.out.println(activities);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Checking if " + sport + " distance goal was achieved");
        return true;
    }
}
