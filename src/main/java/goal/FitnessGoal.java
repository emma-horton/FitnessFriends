package goal;

public abstract class FitnessGoal implements IGoal {
    protected GoalType goalType;
    protected int targetValue;
    protected String sport;

    public FitnessGoal(GoalType goalType, int targetValue, String sport) {
        this.goalType = goalType;
        this.targetValue = targetValue;
        this.sport = sport;
    }

    public int getTargetValue() {
        return targetValue;
    }

    @Override
    public abstract boolean isThisWeeksGoalAchieved();
    public abstract boolean wasLastWeeksGoalAchieved();
}