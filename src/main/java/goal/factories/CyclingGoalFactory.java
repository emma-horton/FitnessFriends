package goal.factories;

import goal.FitnessGoal;
import goal.GoalType;
import goal.types.DistanceGoal;
import goal.types.DurationGoal;
import goal.types.FrequencyGoal;
import goal.SportType;

public class CyclingGoalFactory extends GoalFactory {
    @Override
    //public FitnessGoal createGoal(GoalType goalType, int targetValue, String sport) {
    public FitnessGoal createGoal(int goalId, int userId, GoalType goalType, int targetValue, SportType sport) {
        switch (goalType) {
            case DISTANCE:
                return new DistanceGoal(goalId, userId, targetValue, sport);
            case DURATION:
                return new DurationGoal(goalId, userId, targetValue, sport);
            case FREQUENCY:
                return new FrequencyGoal(goalId, userId, targetValue, sport);
            default:
                throw new IllegalArgumentException("Invalid Cycling Goal Type");
        }
    }
}
