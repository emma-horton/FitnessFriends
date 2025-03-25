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

public class DistanceGoal extends FitnessGoal {

    public DistanceGoal(int goalId, int userId, int targetValue, SportType sport) {
        super(goalId, userId);

        this.goalType = GoalType.DISTANCE;
        this.sport = sport;
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
        System.out.println("Pulling this week's " + sport + " distance data");
        try {
            Connection connection = DatabaseConnection.getConnection();
            ActivityDAO activityDAO = new ActivityDAO(connection);
            List<Activity> activities = activityDAO.getThisWeeksDataByUserId(userId, sport);
    
            // Calculate the total distance
            double totalDistance = activities.stream()
                                             .mapToDouble(Activity::getActivityDistance)
                                             .sum();
    
            System.out.println("Total distance for this week: " + totalDistance);
            System.out.println("Target distance: " + targetValue);
    
            // Check if the total distance meets or exceeds the target
            return totalDistance >= targetValue;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Checking if " + sport + " distance goal is achieved");
        return false;
    }
    public boolean wasLastWeeksGoalAchieved() {
        System.out.println("Pulling last weeks " + sport + " distance data");
        try {
            Connection connection = DatabaseConnection.getConnection();
            ActivityDAO activityDAO = new ActivityDAO(connection);
            List<Activity> activities = activityDAO.getLastWeeksDataByUserId(userId, sport);
            // Calculate the total distance
            double totalDistance = activities.stream()
                                             .mapToDouble(Activity::getActivityDistance)
                                             .sum();
    
            System.out.println("Total distance for this week: " + totalDistance);
            System.out.println("Target distance: " + targetValue);
    
            // Check if the total distance meets or exceeds the target
            return totalDistance >= targetValue;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Checking if " + sport + " distance goal is achieved");
        return false;
    }
}
