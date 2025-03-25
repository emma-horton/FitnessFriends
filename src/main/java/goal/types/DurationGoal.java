package goal.types;

import goal.FitnessGoal;
import goal.GoalType;
import goal.Goal;

public class DurationGoal extends FitnessGoal {
    public DurationGoal(int targetValue, String sport) {
        super(GoalType.DURATION, targetValue, sport);
    }

    @Override
    public boolean isThisWeeksGoalAchieved(Goal goal) {
        System.out.println("Pulling this weeks " + sport + " duration data");
        System.out.println("Checking if " + sport + " duration goal is achieved");
        return true;
    }
    public boolean wasLastWeeksGoalAchieved(Goal goal) {
        System.out.println("Pulling last weeks " + sport + " duration data");
        System.out.println("Checking if " + sport + " duration goal was achieved");
        return true;
    }
}
