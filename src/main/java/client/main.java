package client;
import java.util.ArrayList;
import java.util.List;

import goal.FitnessGoal;
import goal.GoalType;
import goal.SportType;
import goal.factories.CyclingGoalFactory;
import goal.factories.SwimmingGoalFactory;
import goal.factories.GoalFactory;
import goal.factories.RunningGoalFactory;
import pet.PetHealthStatus;
import user.User;
import user.UserProfile;
import pet.PetBehaviour;
import pet.PetFactory;


public class main {
    public static void main(String[] args) {

        // PetBehaviour parrot5 = PetFactory.createPet("parrot", 1, PetHealthStatus.DEAD);
        // parrot5.tryToMove();
        // parrot5.tryToEat();
        // parrot5.tryToPlay();

        // PetBehaviour turtle = PetFactory.createPet("turtle", 1, PetHealthStatus.HEALTHY);
        // turtle.tryToMove();
        // turtle.tryToEat();
        // turtle.tryToPlay();


        // // Create a factory for running goals
        // GoalFactory runningFactory = new RunningGoalFactory();
        // FitnessGoal runningDistanceGoal = runningFactory.createGoal(2, 2, GoalType.DISTANCE, 10, SportType.RUNNING);

        // // Create factory for cycling duration goals
        // GoalFactory cyclingFactory = new CyclingGoalFactory();
        // FitnessGoal cyclingDurationGoal = cyclingFactory.createGoal(1, 2, GoalType.DURATION, 20, SportType.CYCLING);
        
        // // Create factory for swimming frequency goals
        // GoalFactory swimmingFactory = new SwimmingGoalFactory();
        // FitnessGoal swimmingFrequencyGoal = swimmingFactory.createGoal(3, 2, GoalType.FREQUENCY, 3, SportType.SWIMMING);

        // // Simulate progress
        // System.out.println("Running Distance Goal Achieved? " + runningDistanceGoal.isThisWeeksGoalAchieved()); 
        // System.out.println("Running Distance Goal Achieved? " + runningDistanceGoal.wasLastWeeksGoalAchieved()); 
        // System.out.println("Cycling Duration Goal Achieved? " + cyclingDurationGoal.isThisWeeksGoalAchieved());
        // System.out.println("Cycling Duration Goal Achieved? " + cyclingDurationGoal.wasLastWeeksGoalAchieved());
        // System.out.println("Swimming Frequency Goal Achieved? " + swimmingFrequencyGoal.isThisWeeksGoalAchieved());
        // System.out.println("Swimming Frequency Goal Achieved? " + swimmingFrequencyGoal.wasLastWeeksGoalAchieved());

        // Create goals for the user
        List<FitnessGoal> goals = new ArrayList<>();
        GoalFactory runningFactory = new RunningGoalFactory();
        goals.add(runningFactory.createGoal(1, 2, GoalType.DISTANCE, 10, SportType.RUNNING));

        GoalFactory cyclingFactory = new CyclingGoalFactory();
        goals.add(cyclingFactory.createGoal(2, 2, GoalType.DURATION, 20, SportType.CYCLING));

        GoalFactory swimmingFactory = new SwimmingGoalFactory();
        goals.add(swimmingFactory.createGoal(3, 2, GoalType.FREQUENCY, 3, SportType.SWIMMING));

        // Create a UserProfile with the user's goals
        UserProfile userProfile = new UserProfile(goals);

        // Create a pet for the user
        PetBehaviour petBehaviour = PetFactory.createPet("parrot", 1, PetHealthStatus.HEALTHY);

        // Create a User object
        User user = new User(1, "Alice123", userProfile, petBehaviour);

        // Update the pet's health based on the user's goals
        user.getPetBehaviour().updateHealth(user.getProfile());

        // Simulate user interaction with the pet
        System.out.println("Interacting with your pet:");
        user.getPetBehaviour().tryToMove();
        user.getPetBehaviour().tryToEat();
        user.getPetBehaviour().tryToPlay();

        // Display the pet's updated health status
        System.out.println("Your pet's current health status: " + user.getPetBehaviour().getPet().getHealth().getStatus());

    }

    
}
