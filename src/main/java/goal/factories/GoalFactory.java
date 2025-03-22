package goal.factories;

import goal.FitnessGoal;
import goal.GoalType;

public abstract class GoalFactory {
    public abstract FitnessGoal createGoal(GoalType goalType, int targetValu, String sport);
}
