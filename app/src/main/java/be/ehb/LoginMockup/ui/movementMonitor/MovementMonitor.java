package be.ehb.LoginMockup.ui.movementMonitor;

import java.text.DecimalFormat;

public class MovementMonitor {
    private int sessionCalories;
    private int sessionDuration;
    private static float duration;
    private static float calories;
    public static float totalSessionCalories;
    public static float totalSessionDuration;  //in min
    public MovementMonitor(int sessionCalories, int sessionDuration) {
        this.sessionCalories = sessionCalories;
        this.sessionDuration = sessionDuration;
    }

    public int getSessionCalories() {
        return sessionCalories;
    }

    public int getSessionDuration() {
        return sessionDuration;
    }

    public static float getDuration() {
        return duration;
    }

    public static void setDuration(float duration) {
        MovementMonitor.duration = duration;
    }

    public static float getCalories() {
        return calories;
    }

    public static void setCalories(float calories) {
        MovementMonitor.calories = calories;
    }

    public static float getTotalSessionCalories() {
        return totalSessionCalories;
    }

    public static void setTotalSessionCalories(float sessionCalories) {
        totalSessionCalories += sessionCalories;
    }

    public static float getTotalSessionDuration() {
        return totalSessionDuration;
    }

    public static void setTotalSessionDuration(float sessionDuration) {
        totalSessionDuration += sessionDuration;
    }

    public static void resetTotal(float totalCalories, float totalDuration){
        totalSessionCalories = totalCalories;
        totalSessionDuration = totalDuration;
    }

    public static float calcCalorie(float duration, float totalDuration){

        float help1, help2;
        float help3;

        help1 = ((totalDuration / 60000) % 60);

        help2 = (duration / 60000) % 60;

        help3 = (calories/help1) * help2;

        return help3;
    }

    public static float calcDuration(float duration, float totalDuration){
        float help1;
        float help2;

        help1 = ((totalDuration / 60000) % 60);

        help2 = (duration / 60000) % 60;

        help1 = (help1 - help2);

        return help1;
    }
}