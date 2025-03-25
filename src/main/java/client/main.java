package client;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import data.Activity;
import data.DatabaseConnection;
import data.dao.ActivityDAO;
import goal.FitnessGoal;
import goal.GoalType;
import goal.factories.GoalFactory;
import goal.factories.RunningGoalFactory;
import goal.factories.SwimmingGoalFactory;
import pet.Pet;
import pet.PetHealth;
import pet.PetHealthStatus;
import goal.factories.CyclingGoalFactory;

import pet.type.Parrot;
import pet.type.Turtle;
import pet.PetBehaviour;
import pet.PetFactory;

public class main {
    public static void main(String[] args) {

        PetBehaviour parrot5 = PetFactory.createPet("parrot", 1, PetHealthStatus.DEAD);
        parrot5.tryToMove();
        parrot5.tryToEat();
        parrot5.tryToPlay();

        PetBehaviour turtle = PetFactory.createPet("turtle", 1, PetHealthStatus.HEALTHY);
        turtle.tryToMove();
        turtle.tryToEat();
        turtle.tryToPlay();


        // Create a factory for running goals
        GoalFactory runningFactory = new RunningGoalFactory();
        FitnessGoal runningDistanceGoal = runningFactory.createGoal(GoalType.DISTANCE, 10, "Running");

        // // Create a factory for swimming goals
        // GoalFactory swimmingFactory = new SwimmingGoalFactory();
        // FitnessGoal swimmingDurationGoal = swimmingFactory.createGoal(GoalType.DURATION, 30, "Swimming");

        // // Create a factory for cycling goals
        // GoalFactory cyclingFactory = new CyclingGoalFactory();
        // FitnessGoal cyclingFrequencyGoal = cyclingFactory.createGoal(GoalType.FREQUENCY, 3, "Cycling");

        // Simulate progress
        System.out.println("Running Distance Goal Achieved? " + runningDistanceGoal.isThisWeeksGoalAchieved(2, "Running")); 
        System.out.println("Running Distance Goal Achieved? " + runningDistanceGoal.wasLastWeeksGoalAchieved(2, "Running")); 

        // System.err.println("----------------------");

        // System.out.println("Swimming Duration Goal Achieved? " + swimmingDurationGoal.isThisWeeksGoalAchieved());
        // System.out.println("Swimming Duration Goal Achieved? " + swimmingDurationGoal.wasLastWeeksGoalAchieved());

        // System.err.println("----------------------");

        // System.out.println("Cycling Frequency Goal Achieved? " + cyclingFrequencyGoal.isThisWeeksGoalAchieved());
        // System.out.println("Cycling Frequency Goal Achieved? " + cyclingFrequencyGoal.wasLastWeeksGoalAchieved());

        // try {
        //     Connection connection = DatabaseConnection.getConnection();
        //     ActivityDAO activityDAO = new ActivityDAO(connection);

        //     // Call the getByUserId method
        //     int userId = 2; // Replace with the user ID you want to query
        //     List<Activity> activities = activityDAO.getLastWeeksDataByUserId(userId, "Running");

        //     // Print the retrieved activities
        //     if (activities.isEmpty()) {
        //         System.out.println("No activity data found for user ID: " + userId);
        //     } else {
        //         System.out.println("Activity data retrieved successfully:");
        //         for (Activity activity : activities) {
        //             System.out.println("Activity ID: " + activity.getDataId());
        //             System.out.println("Date: " + activity.getActivityDate());
        //             System.out.println("Type: " + activity.getActivityType());
        //             System.out.println("Duration: " + activity.getActivityDuration());
        //             System.out.println("Distance: " + activity.getActivityDistance());
        //             System.out.println("---------------------------");
        //         }
        //     }
        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }


    }

    
}
