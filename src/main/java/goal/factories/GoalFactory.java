package goal.factories;

import goal.FitnessGoal;
import goal.GoalType;
import goal.SportType;

public abstract class GoalFactory {
    public abstract FitnessGoal createGoal(int goalId, int userId, GoalType goalType, int targetValue, SportType sport);
}
