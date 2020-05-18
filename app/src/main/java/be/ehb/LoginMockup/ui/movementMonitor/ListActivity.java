package be.ehb.LoginMockup.ui.movementMonitor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import be.ehb.Ehealth.R;

/**
 * this class is the activity where you can choice the activity that you will practise*/
//TODO Documentation
public class ListActivity extends AppCompatActivity {

    private MovementMonitor monitor;
    private ArrayList<Activity> activities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movement_monitor_list);

        Intent intent = getIntent();
        int sessionCalories = intent.getIntExtra("sessionCalories", 0);
        int sessionDuration = intent.getIntExtra("sessionDuration", 0);
        monitor = new MovementMonitor(sessionCalories, sessionDuration);

        readDataChangeFromFirebase();
        buildRecycleView(activities);
    }

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
/*
    private void writDataFromFirebase() {
        DatabaseReference myRef = database.getReference().child("Activity");

        myRef.child("activity").child("activity_name").setValue("Swimming");
        myRef.child("activity").child("activity_kcal").setValue(100);
        myRef.child("activity").child("activity_duration").setValue(10);
        myRef.child("activity").child("activity_type").setValue("endurance");
        myRef.child("activity").child("activity_intensity").setValue("hard");
        //myRef.child("activity").child("activity_image").setValue("https://firebasestorage.googleapis.com/v0/b/mybfpapp.appspot.com/o/swimming.jpg?alt=media&token=66bd5194-707b-489e-b4c6-0aa91057b142");
        myRef.child("activity").child("activity_image").setValue("https://firebasestorage.googleapis.com/v0/b/mybfpapp.appspot.com/o/cycling.jpg?alt=media&token=cf5e959f-de86-4d4e-a4d8-144f308f34e0");

        myRef.child("activity1").child("activity_name").setValue("running");
        myRef.child("activity1").child("activity_kcal").setValue(150);
        myRef.child("activity1").child("activity_duration").setValue(120);
        myRef.child("activity1").child("activity_type").setValue("endurance");
        myRef.child("activity1").child("activity_intensity").setValue("hard");
        //myRef.child("activity1").child("activity_image").setValue("https://firebasestorage.googleapis.com/v0/b/mybfpapp.appspot.com/o/running.jpg?alt=media&token=f233d1f7-db6f-4940-a352-167a79e79dd1");
        myRef.child("activity1").child("activity_image").setValue("https://firebasestorage.googleapis.com/v0/b/mybfpapp.appspot.com/o/cycling.jpg?alt=media&token=cf5e959f-de86-4d4e-a4d8-144f308f34e0");

        myRef.child("activity2").child("activity_name").setValue("push-up");
        myRef.child("activity2").child("activity_kcal").setValue(200);
        myRef.child("activity2").child("activity_duration").setValue(60);
        myRef.child("activity2").child("activity_type").setValue("endurance");
        myRef.child("activity2").child("activity_intensity").setValue("hard");
        //myRef.child("activity2").child("activity_image").setValue("https://firebasestorage.googleapis.com/v0/b/mybfpapp.appspot.com/o/push-ups.jpg?alt=media&token=d8325609-1a8b-4aaa-be94-df3fe097ee03");
        myRef.child("activity2").child("activity_image").setValue("https://firebasestorage.googleapis.com/v0/b/mybfpapp.appspot.com/o/cycling.jpg?alt=media&token=cf5e959f-de86-4d4e-a4d8-144f308f34e0");

        myRef.child("activity3").child("activity_name").setValue("cycling");
        myRef.child("activity3").child("activity_kcal").setValue(250);
        myRef.child("activity3").child("activity_duration").setValue(60);
        myRef.child("activity3").child("activity_type").setValue("endurance");
        myRef.child("activity3").child("activity_intensity").setValue("hard");
        myRef.child("activity3").child("activity_image").setValue("https://firebasestorage.googleapis.com/v0/b/mybfpapp.appspot.com/o/cycling.jpg?alt=media&token=cf5e959f-de86-4d4e-a4d8-144f308f34e0");

        /////////////////////////////////////////////////////////////////////
        myRef.child("activity4").child("activity_name").setValue("cycling");
        myRef.child("activity4").child("activity_kcal").setValue(250);
        myRef.child("activity4").child("activity_duration").setValue(60);
        myRef.child("activity4").child("activity_type").setValue("endurance");
        myRef.child("activity4").child("activity_intensity").setValue("hard");
        myRef.child("activity4").child("activity_image").setValue("https://firebasestorage.googleapis.com/v0/b/mybfpapp.appspot.com/o/cycling.jpg?alt=media&token=cf5e959f-de86-4d4e-a4d8-144f308f34e0");

        myRef.child("activity5").child("activity_name").setValue("cycling");
        myRef.child("activity5").child("activity_kcal").setValue(250);
        myRef.child("activity5").child("activity_duration").setValue(60);
        myRef.child("activity5").child("activity_type").setValue("endurance");
        myRef.child("activity5").child("activity_intensity").setValue("hard");
        myRef.child("activity5").child("activity_image").setValue("https://firebasestorage.googleapis.com/v0/b/mybfpapp.appspot.com/o/cycling.jpg?alt=media&token=cf5e959f-de86-4d4e-a4d8-144f308f34e0");

        myRef.child("activity6").child("activity_name").setValue("cycling");
        myRef.child("activity6").child("activity_kcal").setValue(350);
        myRef.child("activity6").child("activity_duration").setValue(60);
        myRef.child("activity6").child("activity_type").setValue("endurance");
        myRef.child("activity6").child("activity_intensity").setValue("hard");
        myRef.child("activity6").child("activity_image").setValue("https://firebasestorage.googleapis.com/v0/b/mybfpapp.appspot.com/o/cycling.jpg?alt=media&token=cf5e959f-de86-4d4e-a4d8-144f308f34e0");

        myRef.child("activity7").child("activity_name").setValue("cycling");
        myRef.child("activity7").child("activity_kcal").setValue(350);
        myRef.child("activity7").child("activity_duration").setValue(60);
        myRef.child("activity7").child("activity_type").setValue("endurance");
        myRef.child("activity7").child("activity_intensity").setValue("hard");
        myRef.child("activity7").child("activity_image").setValue("https://firebasestorage.googleapis.com/v0/b/mybfpapp.appspot.com/o/cycling.jpg?alt=media&token=cf5e959f-de86-4d4e-a4d8-144f308f34e0");

        myRef.child("activity8").child("activity_name").setValue("cycling");
        myRef.child("activity8").child("activity_kcal").setValue(300);
        myRef.child("activity8").child("activity_duration").setValue(60);
        myRef.child("activity8").child("activity_type").setValue("endurance");
        myRef.child("activity8").child("activity_intensity").setValue("hard");
        myRef.child("activity8").child("activity_image").setValue("https://firebasestorage.googleapis.com/v0/b/mybfpapp.appspot.com/o/cycling.jpg?alt=media&token=cf5e959f-de86-4d4e-a4d8-144f308f34e0");

        myRef.child("activity9").child("activity_name").setValue("cycling");
        myRef.child("activity9").child("activity_kcal").setValue(300);
        myRef.child("activity9").child("activity_duration").setValue(60);
        myRef.child("activity9").child("activity_type").setValue("endurance");
        myRef.child("activity9").child("activity_intensity").setValue("hard");
        myRef.child("activity9").child("activity_image").setValue("https://firebasestorage.googleapis.com/v0/b/mybfpapp.appspot.com/o/cycling.jpg?alt=media&token=cf5e959f-de86-4d4e-a4d8-144f308f34e0");

        myRef.child("activity10").child("activity_name").setValue("cycling");
        myRef.child("activity10").child("activity_kcal").setValue(400);
        myRef.child("activity10").child("activity_duration").setValue(60);
        myRef.child("activity10").child("activity_type").setValue("endurance");
        myRef.child("activity10").child("activity_intensity").setValue("hard");
        myRef.child("activity10").child("activity_image").setValue("https://firebasestorage.googleapis.com/v0/b/mybfpapp.appspot.com/o/cycling.jpg?alt=media&token=cf5e959f-de86-4d4e-a4d8-144f308f34e0");
    }
*/
    /**
     * this function read the data available in the firebase*/

    public void readDataChangeFromFirebase() {
        DatabaseReference activitiesRef = database.getReference().child("Activity");

        activitiesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //TODO findViewById(R.id.progressbarWaiting).setVisibility(ViewGroup.GONE);

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    System.out.println(snapshot);
                    String activityName = String.valueOf(snapshot.child("activity_name").getValue());
                    String activityType = String.valueOf(snapshot.child("activity_type").getValue());
                    String activityIntensity = String.valueOf(snapshot.child("activity_intensity").getValue());
                    String activityImage = String.valueOf(snapshot.child("activity_image").getValue());
                    int activityKcal = Integer.parseInt(String.valueOf(snapshot.child("activity_kcal").getValue()));
                    int activityDuration = Integer.parseInt(String.valueOf(snapshot.child("activity_duration").getValue()));

                    if (monitor.getSessionDuration() == 0 && activityKcal >= monitor.getSessionCalories() ){
                        activities.add(new Activity(activityName, activityType,activityKcal, activityDuration, activityIntensity, activityImage));
                    } else if (monitor.getSessionCalories() == 0 && activityDuration >= monitor.getSessionDuration()){
                        activities.add(new Activity(activityName, activityType,activityKcal, activityDuration, activityIntensity, activityImage));
                    }
                }
                buildRecycleView(activities);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("detailActivity", "loadActivity :onCancelled",databaseError.toException());
                Toast.makeText(ListActivity.this,
                        "Failed to load activities",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void buildRecycleView(List activities) {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        ListAdapter listAdapter = new ListAdapter(activities);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //recyclerView.setAdapter(listAdapter);
    }
}
