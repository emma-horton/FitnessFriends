package goal;

import goal.GoalType;

public class Goal {
    private int goalId;
    private int userId;
    private GoalType goalType;
    private String sport;
    private int targetValue;

    public Goal(int goalId, int userId, GoalType goalType, String sport, int targetValue) {
        this.goalId = goalId;
        this.userId = userId;
        this.goalType = goalType;
        this.sport = sport;
        this.targetValue = targetValue;
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

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
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

}
