package goal.types;

import goal.FitnessGoal;
import goal.GoalType;
import goal.SportType;
import data.Activity;
import data.DatabaseConnection;
import data.dao.ActivityDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FrequencyGoal extends FitnessGoal {
    public FrequencyGoal(int goalId, int userId, int targetValue, SportType sport) {
        super(goalId, userId);

        this.goalType = GoalType.FREQUENCY;
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
        System.out.println("Pulling this week's " + sport + " frequency data");
        try {
            Connection connection = DatabaseConnection.getConnection();
            ActivityDAO activityDAO = new ActivityDAO(connection);
            List<Activity> activities = activityDAO.getThisWeeksDataByUserId(userId, sport);
    
            // Calculate the total frequency
            int totalFrequency = activities.size();
    
            System.out.println("Total frequency for this week: " + totalFrequency);
            System.out.println("Target frequency: " + targetValue);
    
            // Check if the total frequency meets or exceeds the target
            return totalFrequency >= targetValue;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Checking if " + sport + " frequency goal is achieved");
        return false;
    }
    public boolean wasLastWeeksGoalAchieved() {
        System.out.println("Pulling last weeks " + sport + " frequency data");
        try {
            Connection connection = DatabaseConnection.getConnection();
            ActivityDAO activityDAO = new ActivityDAO(connection);
            List<Activity> activities = activityDAO.getLastWeeksDataByUserId(userId, sport);
            // Calculate the total frequency
            int totalFrequency = activities.size();
    
            System.out.println("Total frequency for this week: " + totalFrequency);
            System.out.println("Target frequency: " + targetValue);
    
            // Check if the total frequency meets or exceeds the target
            return totalFrequency >= targetValue;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Checking if " + sport + " frequency goal is achieved");
        return false;
    }
}
