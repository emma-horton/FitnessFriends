package goal.types;

import goal.FitnessGoal;
import goal.GoalType;
import goal.SportType;
import goal.Goal;

public class FrequencyGoal extends FitnessGoal {
    public FrequencyGoal(int goalId, int userId, int targetValue, SportType sport) {
        super(goalId, userId);

        this.goalType = GoalType.FREQUENCY;
    }

    @Override
    public boolean isThisWeeksGoalAchieved() {
        System.out.println("Pulling this weeks " + sport + " frequency data");
        System.out.println("Checking if " + sport + " frequency goal is achieved");
        return true;
    }
    public boolean wasLastWeeksGoalAchieved() {
        System.out.println("Pulling last weeks " + sport + " frequency data");
        System.out.println("Checking if " + sport + " frequency goal was achieved");
        return true;
    }
}
