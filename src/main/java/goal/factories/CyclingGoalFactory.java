package goal.factories;

import goal.FitnessGoal;
import goal.GoalType;
import goal.types.DistanceGoal;
import goal.types.DurationGoal;
import goal.types.FrequencyGoal;

public class CyclingGoalFactory extends GoalFactory {
    @Override
    //public FitnessGoal createGoal(GoalType goalType, int targetValue, String sport) {
    public FitnessGoal createGoal(GoalType goalType, int targetValue, String sport) {
        switch (goalType) {
            case DISTANCE:
                return new DistanceGoal(targetValue, sport);
            case DURATION:
                return new DurationGoal(targetValue, sport);
            case FREQUENCY:
                return new FrequencyGoal(targetValue, sport);
            default:
                throw new IllegalArgumentException("Invalid Goal Type");
        }
    }
}
