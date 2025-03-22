package goal.types;
import java.util.List;

import data.Activity;
import data.SQLiteDataRetrieval;
import goal.FitnessGoal;
import goal.GoalType;

public class DistanceGoal extends FitnessGoal {
    public DistanceGoal(int targetValue, String sport) {
        super(GoalType.DISTANCE, targetValue, sport);
    }

    @Override
    public boolean isThisWeeksGoalAchieved() {
        System.out.println("Pulling this weeks " + sport + " distance data");
        SQLiteDataRetrieval retriever = new SQLiteDataRetrieval(); // Create an instance
        int userIdToRetrieve = 2; // Example user ID
        List<Activity> retrievedActivities = retriever.retrieveActivityDataLastTwoWeeksForUser(userIdToRetrieve);
        System.out.println("Checking if " + sport + " distance goal is achieved");
        return true;
    }
    public boolean wasLastWeeksGoalAchieved() {
        System.out.println("Pulling last weeks " + sport + " distance data");
        System.out.println("Checking if " + sport + " distance goal was achieved");
        return true;
    }
}
