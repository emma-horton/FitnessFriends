package goal.types;

import goal.FitnessGoal;
import goal.GoalType;
import goal.SportType;
import goal.Goal;

public class DurationGoal extends FitnessGoal {
    public DurationGoal(int goalId, int userId, int targetValue, SportType sport) {
        super(goalId, userId);

        this.goalType = GoalType.DURATION;
    }

    @Override
    public boolean isThisWeeksGoalAchieved() {
        System.out.println("Pulling this weeks " + sport + " duration data");
        System.out.println("Checking if " + sport + " duration goal is achieved");
        return true;
    }
    public boolean wasLastWeeksGoalAchieved() {
        System.out.println("Pulling last weeks " + sport + " duration data");
        System.out.println("Checking if " + sport + " duration goal was achieved");
        return true;
    }
}
