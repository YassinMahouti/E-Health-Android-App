package be.ehb.LoginMockup.ui.movementMonitor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import be.ehb.Ehealth.R;

public class MainActivity extends AppCompatActivity {

    private Button button_to_input_activity;
    private Button button_to_list_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movement_monitor_main);

        // link this and the input activity whit a button
        button_to_input_activity = findViewById(R.id.button_main_activity_burn_some_calories);

        button_to_input_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MonitorActivity.class);
                startActivity(intent);
            }
        });

        // link this and the list activity whit a button
        button_to_list_activity = findViewById(R.id.button_main_activity_choose_an_activity);

        button_to_list_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
    }
}
