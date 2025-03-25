package goal;

public abstract class FitnessGoal implements IGoal {
    protected int goalId;
    protected int userId;   
    protected GoalType goalType;
    protected int targetValue;
    protected SportType sport;

    public FitnessGoal(int goalId, int userId) {
        this.goalId = goalId;
        this.userId = userId;
    }

    public int getGoalId() {
        return goalId;
    }

    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }

    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public GoalType getGoalType() {
        return goalType;
    }

    public void setGoalType(GoalType goalType) {
        this.goalType = goalType;
    }

    public SportType getSport() {
        return sport;
    }

    public void setSport(SportType sport) {
        this.sport = sport;
    }

    public int getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(int targetValue) {
        this.targetValue = targetValue;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "goalId=" + goalId +
                ", userId=" + userId +
                ", goalType='" + goalType + '\'' +
                ", sport='" + sport + '\'' +
                ", targetValue=" + targetValue +
                '}';
    }


    public abstract boolean isThisWeeksGoalAchieved();
    public abstract boolean wasLastWeeksGoalAchieved();
}