package be.ehb.version1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InputActivity extends AppCompatActivity {

    private Button button_to_list_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        // link this and the list activity whit a button
        button_to_list_activity = (Button) findViewById(R.id.button_input_activity_show_activity_list);

        button_to_list_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InputActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
    }
}
