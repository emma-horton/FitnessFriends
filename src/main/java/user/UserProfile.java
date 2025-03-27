package user;
import java.util.List;
import goal.FitnessGoal;

public class UserProfile {
    private List<FitnessGoal> goals;

    public UserProfile(List<FitnessGoal> goals) {
        this.goals = goals;
    }

    public boolean areAllGoalsAchievedThisWeek() {
        return goals.stream().allMatch(FitnessGoal::isThisWeeksGoalAchieved);
    }

    public boolean areSomeGoalsAchievedThisWeek() {
        return goals.stream().anyMatch(FitnessGoal::isThisWeeksGoalAchieved);
    }

    public boolean areAllGoalsMissedForTwoWeeks() {
        return goals.stream().noneMatch(goal -> 
            goal.isThisWeeksGoalAchieved() || goal.wasLastWeeksGoalAchieved()
        );
    }
}
