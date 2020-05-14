package be.ehb.LoginMockup.ui.bmiAndBfp.model;

public class User {

    //properties same name as in table on Firebase database
    private float user_height;
    private String date;
    private float user_weight;
    private float user_bmi;
    private float user_bfp;
    private String user_sex;
    private int user_age;


    public User() {
    }

    public User(float user_height, String date, float user_weight, String user_sex, int user_age, float user_bmi, float user_bfp) {
        this.user_height = user_height;
        this.date = date;
        this.user_weight = user_weight;
        this.user_sex = user_sex;
        this.user_age = user_age;
        this.user_bmi = user_bmi;
        this.user_bfp = user_bfp;
    }

    public float getUser_bfp() {
        return user_bfp;
    }

    public void setUser_bfp(float user_bfp) {
        this.user_bfp = user_bfp;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public int getUser_age() {
        return user_age;
    }

    public void setUser_age(int user_age) {
        this.user_age = user_age;
    }

    public float getUser_height() {
        return user_height;
    }

    public void setUser_height(float user_height) {
        this.user_height = user_height;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getUser_weight() {
        return user_weight;
    }

    public void setUser_weight(float user_weight) {
        this.user_weight = user_weight;
    }
    public float getUser_bmi() {
        return user_bmi;
    }

    public void setUser_bmi(float user_bmi) {
        this.user_bmi = user_bmi;
    }
}
