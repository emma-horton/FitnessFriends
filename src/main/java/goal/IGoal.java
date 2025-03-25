package goal;
public interface IGoal {
    public boolean isThisWeeksGoalAchieved(int userId, String sport);
    public boolean wasLastWeeksGoalAchieved(int userId, String sport);

}