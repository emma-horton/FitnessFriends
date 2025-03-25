package client;
import goal.FitnessGoal;
import goal.GoalType;
import goal.SportType;
import goal.factories.GoalFactory;
import goal.factories.RunningGoalFactory;
import pet.PetHealthStatus;
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
        FitnessGoal runningDistanceGoal = runningFactory.createGoal(2, 2, GoalType.DISTANCE, 10, SportType.RUNNING);


        // Simulate progress
        System.out.println("Running Distance Goal Achieved? " + runningDistanceGoal.isThisWeeksGoalAchieved()); 
        System.out.println("Running Distance Goal Achieved? " + runningDistanceGoal.wasLastWeeksGoalAchieved()); 



    }

    
}
