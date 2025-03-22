package client;

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
        System.out.println("Running Distance Goal Achieved? " + runningDistanceGoal.isThisWeeksGoalAchieved()); 
        System.out.println("Running Distance Goal Achieved? " + runningDistanceGoal.wasLastWeeksGoalAchieved()); 

        // System.err.println("----------------------");

        // System.out.println("Swimming Duration Goal Achieved? " + swimmingDurationGoal.isThisWeeksGoalAchieved());
        // System.out.println("Swimming Duration Goal Achieved? " + swimmingDurationGoal.wasLastWeeksGoalAchieved());

        // System.err.println("----------------------");

        // System.out.println("Cycling Frequency Goal Achieved? " + cyclingFrequencyGoal.isThisWeeksGoalAchieved());
        // System.out.println("Cycling Frequency Goal Achieved? " + cyclingFrequencyGoal.wasLastWeeksGoalAchieved());

    }

    
}
