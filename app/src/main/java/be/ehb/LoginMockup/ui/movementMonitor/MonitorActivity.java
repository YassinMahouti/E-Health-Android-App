package be.ehb.LoginMockup.ui.movementMonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import be.ehb.Ehealth.R;

//TODO Documentation
public class MonitorActivity extends AppCompatActivity {

    private Button button_to_list_activity;
    private EditText sessionCaloriesString;
    private EditText sessionDurationString;
    private RadioButton choiceOne;
    private RadioButton choiceTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movement_monitor_monitor);

        button_to_list_activity = findViewById(R.id.button_monitor_activity_show_activity_list);
        sessionCaloriesString =  findViewById(R.id.editTextInput_monitor_activity_question_one);
        sessionDurationString = findViewById(R.id.editTextInput_monitor_activity_question_two);
        choiceOne = findViewById(R.id.radioButton_monitor_activity_question_one);
        choiceTwo = findViewById(R.id.radioButton_monitor_activity_question_two);

        button_to_list_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sessionCaloriesInt = safeEntry(sessionCaloriesString);
                int sessionDurationInt = safeEntry(sessionDurationString);

                Intent intent = new Intent(MonitorActivity.this, ListActivity.class);
                if (choiceOne.isChecked() && sessionCaloriesInt != Integer.MAX_VALUE){
                    intent.putExtra("sessionCalories" ,sessionCaloriesInt);
                    intent.putExtra("sessionDuration", 0);
                    startActivity(intent);
                } else if (choiceTwo.isChecked() && sessionDurationInt != Integer.MAX_VALUE){
                    intent.putExtra("sessionCalories" ,0);
                    intent.putExtra("sessionDuration", sessionDurationInt);
                    startActivity(intent);
                } else if (!choiceOne.isChecked() && !choiceTwo.isChecked()){
                    Toast.makeText(MonitorActivity.this, "Make a choice", Toast.LENGTH_SHORT).show();
                } else if (sessionCaloriesInt == Integer.MAX_VALUE || sessionDurationInt == Integer.MAX_VALUE){
                    Toast.makeText(MonitorActivity.this, "Enter a number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Safe entry*/

    private int safeEntry(EditText editText){
        String help = editText.getText().toString();
        if(help.matches("")){
            return Integer.MAX_VALUE;
        } else {
            return Integer.parseInt(help);
        }
    }
}
