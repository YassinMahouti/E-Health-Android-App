package be.ehb.LoginMockup.ui.movementMonitor;
/**
 * This is the class of one activity that you can practice
 * activityImage: this variable receive a url in form of a string*/

public class Activity {
    private String activityName;
    private String type;
    private int kcal;
    private int duration;
    private String intensity;
    private String activityImage;

    public Activity(String activityName, String type, int kcal, int duration, String intensity, String activityImage) {
        this.activityName = activityName;
        this.type = type;
        this.kcal = kcal;
        this.duration = duration;
        this.intensity = intensity;
        this.activityImage = activityImage;
    }

    protected String getActivityName() {
        return activityName;
    }

    protected String getType() {
        return type;
    }

    public int getKcal() {
        return kcal;
    }

    public int getDuration() {
        return duration;
    }

    public String getIntensity() {
        return intensity;
    }

    public String getActivityImage() {
        return activityImage;
    }
}
