package goal.types;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import data.Activity;
import data.DatabaseConnection;
import data.dao.ActivityDAO;
import goal.FitnessGoal;
import goal.GoalType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import goal.Goal;

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
    public boolean isThisWeeksGoalAchieved(Goal goal) {
        System.out.println("Pulling this week's " + sport + " distance data");
        try {
            Connection connection = DatabaseConnection.getConnection();
            ActivityDAO activityDAO = new ActivityDAO(connection);
            List<Activity> activities = activityDAO.getThisWeeksDataByUserId(goal.getUserId(), goal.getSport());
    
            // Calculate the total distance
            double totalDistance = activities.stream()
                                             .mapToDouble(Activity::getActivityDistance)
                                             .sum();
    
            System.out.println("Total distance for this week: " + totalDistance);
            System.out.println("Target distance: " + goal.getTargetValue());
    
            // Check if the total distance meets or exceeds the target
            return totalDistance >= goal.getTargetValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Checking if " + sport + " distance goal is achieved");
        return false;
    }
    public boolean wasLastWeeksGoalAchieved(Goal goal) {
        System.out.println("Pulling last weeks " + sport + " distance data");
        try {
            Connection connection = DatabaseConnection.getConnection();
            ActivityDAO activityDAO = new ActivityDAO(connection);
            List<Activity> activities = activityDAO.getLastWeeksDataByUserId(goal.getUserId(), goal.getSport());
            // Calculate the total distance
            double totalDistance = activities.stream()
                                             .mapToDouble(Activity::getActivityDistance)
                                             .sum();
    
            System.out.println("Total distance for this week: " + totalDistance);
            System.out.println("Target distance: " + goal.getTargetValue());
    
            // Check if the total distance meets or exceeds the target
            return totalDistance >= goal.getTargetValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Checking if " + sport + " distance goal is achieved");
        return false;
    }
}
