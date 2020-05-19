package be.ehb.LoginMockup.ui.movementMonitor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

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
        buildRecycleView();
    }

    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    /**
     * this function read the data available in the firebase*/

    public void readDataChangeFromFirebase() {
        DatabaseReference activitiesRef = database.getReference().child("Activity");

        activitiesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

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
                buildRecycleView();
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

    private void buildRecycleView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        ListAdapter listAdapter = new ListAdapter(activities);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(listAdapter);
    }
}
