package be.ehb.proj.mybfpapp;

public class User {
    //properties same name as in table on Firebase database
    private int countSymptoms;
    private String date;
    private int user_id;
    private float user_risk;


    public User() {
    }

    public User(int countSymptoms, String date, int user_id, float user_risk) {
        this.countSymptoms = countSymptoms;
        this.date = date;
        this.user_id = user_id;
        this.user_risk = user_risk;
    }

    public int getCountSymptoms() {
        return countSymptoms;
    }

    public void setCountSymptoms(int countSymptoms) {
        this.countSymptoms = countSymptoms;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public float getUser_risk() {
        return user_risk;
    }

    public void setUser_risk(float user_risk) {
        this.user_risk = user_risk;
    }


}
