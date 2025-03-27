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

        // Create goals for the user
        List<FitnessGoal> goals = new ArrayList<>();
        GoalFactory runningFactory = new RunningGoalFactory();
        goals.add(runningFactory.createGoal(1, 2, GoalType.DISTANCE, 1, SportType.RUNNING));

        GoalFactory cyclingFactory = new CyclingGoalFactory();
        goals.add(cyclingFactory.createGoal(2, 2, GoalType.DURATION, 10, SportType.CYCLING));

        GoalFactory swimmingFactory = new SwimmingGoalFactory();
        goals.add(swimmingFactory.createGoal(3, 2, GoalType.FREQUENCY, 1, SportType.SWIMMING));

        // Create a UserProfile with the user's goals
        UserProfile userProfile = new UserProfile(goals);

        // Create a pet for the user
        PetBehaviour petBehaviour = PetFactory.createPet("turtle", 1, PetHealthStatus.HEALTHY);

        // Create a User object
        User user = new User(1, "Alice123", userProfile, petBehaviour);

        // Update the pet's health based on the user's goals
        user.getPetBehaviour().updateHealth(userProfile);

        // Simulate user interaction with the pet
        System.out.println("Interacting with your pet:");
        user.getPetBehaviour().tryToMove();
        user.getPetBehaviour().tryToEat();
        user.getPetBehaviour().tryToPlay();

        // Display the pet's updated health status
        System.out.println("Your pet's current health status: " + user.getPetBehaviour().getPet().getHealth().getStatus());

    }

    
}
