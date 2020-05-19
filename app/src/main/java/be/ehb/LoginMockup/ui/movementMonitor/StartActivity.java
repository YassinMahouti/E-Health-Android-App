package be.ehb.LoginMockup.ui.movementMonitor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import be.ehb.Ehealth.R;
//TODO Documentation
//TODO
public class StartActivity extends AppCompatActivity {

    private Button buttonToStartActivityTime;
    private Button buttonPause;
    private Button buttonReset;

    private int extraKcal;
    private int extraDuration;
    private long resetMillis;
    private long theMillis;
    private float myMillis;
    private CountDownTimer countDownTimer;
    private TextView time;

    private Date lastDate = null;

    String extraNameActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movement_monitor_start);

        Intent intent = getIntent();
        extraNameActivity = intent.getStringExtra("name activity");
        String extraImage = intent.getStringExtra("image");

        extraKcal = intent.getIntExtra("kcal", 0);
        extraDuration = intent.getIntExtra("duration", 0);

        MovementMonitor.setCalories(extraKcal);
        MovementMonitor.setDuration(extraDuration);

        TextView name_activity = findViewById(R.id.textView_start_activity_variable_name_activity);
        ImageView image = findViewById(R.id.imageView_start_activity);
        time = findViewById(R.id.textView_start_activity_variable_duration);

        TextView totalKcal = findViewById(R.id.textView_start_activity_variable_total_kcal);
        TextView totalDuration = findViewById(R.id.textView_start_activity_variable_total_duration);

        name_activity.setText(extraNameActivity);
        Picasso.get().load(String.valueOf(extraImage)).into(image);

        buttonToStartActivityTime = findViewById(R.id.button_start_activity_activity_time);
        buttonPause = findViewById(R.id.button_pause);
        buttonReset = findViewById(R.id.button_reset);

        resetMillis = TimeUnit.MINUTES.toMillis(extraDuration);
        theMillis = resetMillis;
        myMillis = theMillis;

        readDateToFirebase(totalKcal, totalDuration);

        buttonToStartActivityTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeDate(false);
                startTimer(totalKcal, totalDuration);
            }
        });

        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeDate(true);
                pauseTimer();
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
                writeDataToFirebaseStart(totalKcal, totalDuration, true);
            }
        });
        updateTime();
    }

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseAuth authentication = FirebaseAuth.getInstance();

    private void writeDataToFirebaseStart(TextView totalKcal , TextView totalDuration,Boolean isRunning){

        DatabaseReference usersActivityRef = database.getReference().child("Users_Activity");
        DatabaseReference activityRef = usersActivityRef.child(authentication.getCurrentUser().getUid());
        activityRef.push().child("end").setValue(String.valueOf(new Date()));

        DatabaseReference usersResultsRef = database.getReference().child("Users_Results");
        DatabaseReference resultsRef = usersResultsRef.child(authentication.getCurrentUser().getUid());

        String helpString1 = String.valueOf(totalKcal.getText());
        String helpString2 = String.valueOf(totalDuration.getText());

        if (helpString1.matches("null")){
            resultsRef.child("user_total_Calories").setValue(0);
            resultsRef.child("user_total_Duration").setValue(0);
        } else {
            float help1 = Float.parseFloat(helpString1);
            float help2 = Float.parseFloat(helpString2);
            MovementMonitor.resetTotal(help1, help2);
        }

        float help3 = MovementMonitor.calcDuration(myMillis, resetMillis);
        MovementMonitor.setTotalSessionDuration(help3);

        resultsRef.child("user_total_Duration").setValue(MovementMonitor.getTotalSessionDuration());

        if (isRunning){
            MovementMonitor.setTotalSessionCalories(MovementMonitor.calcCalorie((resetMillis - myMillis), resetMillis));
            resultsRef.child("user_total_Calories").setValue(MovementMonitor.getTotalSessionCalories());
        } else {
            resultsRef.child("user_total_Calories").setValue(MovementMonitor.getTotalSessionCalories());
        }
    }

    private void writeDate(boolean running){
        DatabaseReference usersActivityRef = database.getReference().child("Users_Activity");
        DatabaseReference activityRef = usersActivityRef.child(authentication.getCurrentUser().getUid());

        if (running){
            activityRef.push().child("pause").setValue(String.valueOf(new Date()));
        } else {
            activityRef.push().child(extraNameActivity).setValue(String.valueOf(new Date()));
        }

    }

    private void readDateToFirebase(TextView totalKcal , TextView totalDuration){
        DatabaseReference usersResultsRef = database.getReference().child("Users_Results");
        DatabaseReference resultsRef = usersResultsRef.child(authentication.getCurrentUser().getUid());

        resultsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println(dataSnapshot);
                String activityKcal = String.valueOf(dataSnapshot.child("user_total_Calories").getValue());
                String activityDuration = String.valueOf(dataSnapshot.child("user_total_Duration").getValue());

                totalKcal.setText(activityKcal);
                totalDuration.setText(activityDuration);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("detailActivity", "loadActivity :onCancelled",databaseError.toException());
                Toast.makeText(StartActivity.this,
                        "Failed to load Data",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startTimer(TextView totalKcal , TextView totalDuration){
        countDownTimer = new CountDownTimer(theMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                theMillis = millisUntilFinished;
                updateTime();
            }

            @Override
            public void onFinish() {
                //TODO finish data write to firebase
                theMillis = resetMillis;
                updateTime();
                writeDataToFirebaseStart(totalKcal, totalDuration,false);
                buttonToStartActivityTime.setEnabled(true);
                buttonPause.setEnabled(false);
                buttonReset.setEnabled(false);
            }
        }.start();
        buttonToStartActivityTime.setEnabled(false);
        buttonPause.setEnabled(true);
        buttonReset.setEnabled(true);
    }

    private void pauseTimer(){
        countDownTimer.cancel();
        buttonToStartActivityTime.setEnabled(true);
        buttonPause.setEnabled(false);
        buttonReset.setEnabled(true);
    }

    private void resetTimer(){
        countDownTimer.cancel();
        myMillis = theMillis;
        theMillis = resetMillis;
        updateTime();
        buttonToStartActivityTime.setEnabled(true);
        buttonPause.setEnabled(false);
        buttonReset.setEnabled(false);
    }

    private void updateTime(){
        NumberFormat f = new DecimalFormat("00");
        double hour =  (theMillis / 3600000) % 24;
        double min = (theMillis / 60000) % 60;
        double sec = (theMillis / 1000) % 60;
        time.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
    }
}