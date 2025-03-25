package client;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import data.Activity;
import data.DatabaseConnection;
import data.dao.ActivityDAO;
import goal.FitnessGoal;
import goal.GoalType;
import goal.SportType;
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
        FitnessGoal runningDistanceGoal = runningFactory.createGoal(2, 2, GoalType.DISTANCE, 10, SportType.RUNNING);


        // Simulate progress
        System.out.println("Running Distance Goal Achieved? " + runningDistanceGoal.isThisWeeksGoalAchieved()); 
        System.out.println("Running Distance Goal Achieved? " + runningDistanceGoal.wasLastWeeksGoalAchieved()); 



    }

    
}
