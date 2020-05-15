package be.ehb.LoginMockup.ui.registratie;

import com.google.firebase.auth.FirebaseAuth;

public class UserResults {
    FirebaseAuth firebaseAuth;
    private  String Uid, dailyWaterIntake;
    private float user_bmi, user_bfp;

    public UserResults() {
    }

    public UserResults(String uid, String dailyWaterIntake, float user_bmi, float user_bfp) {
        this.Uid = uid;
        this.dailyWaterIntake = dailyWaterIntake;
        this.user_bmi = user_bmi;
        this.user_bfp = user_bfp;
    }

    public String getUid() {
        return Uid;
    }

    public String getDailyWaterIntake() {
        return dailyWaterIntake;
    }

    public float getUser_bmi() {
        return user_bmi;
    }

    public float getUser_bfp() {
        return user_bfp;
    }
}


