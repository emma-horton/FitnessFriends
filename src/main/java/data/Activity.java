package data;

public class Activity {
    private int dataId;
    private int userId;
    private String activityDate;
    private String activityType;
    private double activityDuration;
    private double activityDistance;

    // Constructor, getters, setters, and toString() methods
    public Activity(int dataId, int userId, String activityDate, String activityType, double activityDuration, double activityDistance) {
        this.dataId = dataId;
        this.userId = userId;
        this.activityDate = activityDate;
        this.activityType = activityType;
        this.activityDuration = activityDuration;
        this.activityDistance = activityDistance;
    }

    // Getters and Setters
    public int getDataId() { return dataId; }
    public void setDataId(int dataId) { this.dataId = dataId; }
    //... other getters and setters

    @Override
    public String toString() {
        return "Activity{" +
                "dataId=" + dataId +
                ", userId=" + userId +
                ", activityDate='" + activityDate + '\'' +
                ", activityType='" + activityType + '\'' +
                ", activityDuration=" + activityDuration +
                ", activityDistance=" + activityDistance +
                '}';
    }
}
