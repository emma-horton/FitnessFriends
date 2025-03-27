package goal.types;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import data.Activity;
import data.DatabaseConnection;
import data.dao.ActivityDAO;
import goal.FitnessGoal;
import goal.GoalType;
import goal.SportType;

public class DurationGoal extends FitnessGoal {
    public DurationGoal(int goalId, int userId, int targetValue, SportType sport) {
        super(goalId, userId);

        this.goalType = GoalType.DURATION;
        this.sport = sport;
        this.targetValue = targetValue;
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
    public boolean isThisWeeksGoalAchieved() {
        System.out.println("Pulling this week's " + sport + " duration data");
        try {
            Connection connection = DatabaseConnection.getConnection();
            ActivityDAO activityDAO = new ActivityDAO(connection);
            List<Activity> activities = activityDAO.getThisWeeksDataByUserId(userId, sport);
    
            // Calculate the total duration
            double totalDuration = activities.stream()
                                             .mapToDouble(Activity::getActivityDuration)
                                             .sum();
    
            System.out.println("Total duration for this week: " + totalDuration);
            System.out.println("Target duration: " + targetValue);
    
            // Check if the total duration meets or exceeds the target
            return totalDuration >= targetValue;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Checking if " + sport + " duration goal is achieved");
        return false;
    }
    public boolean wasLastWeeksGoalAchieved() {
        System.out.println("Pulling last weeks " + sport + " duration data");
        try {
            Connection connection = DatabaseConnection.getConnection();
            ActivityDAO activityDAO = new ActivityDAO(connection);
            List<Activity> activities = activityDAO.getLastWeeksDataByUserId(userId, sport);
            // Calculate the total duration
            double totalDuration = activities.stream()
                                             .mapToDouble(Activity::getActivityDuration)
                                             .sum();
    
            System.out.println("Total duration for this week: " + totalDuration);
            System.out.println("Target duration: " + targetValue);
    
            // Check if the total duration meets or exceeds the target
            return totalDuration >= targetValue;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Checking if " + sport + " duration goal is achieved");
        return false;
    }
}
