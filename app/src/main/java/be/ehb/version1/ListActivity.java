package be.ehb.version1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ListActivity extends AppCompatActivity {

    //for one activity
    private ImageButton button_to_start_activity;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // link this and the input activity whit a button
        button_to_start_activity = (ImageButton) findViewById(R.id.imageButton);

        button_to_start_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, StartActivity.class);
                startActivity(intent);
            }
        });
    }
}
