package be.ehb.proj.basicbfpapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import be.ehb.proj.basicbfpapplication.R;

public class MainMenuCalculator extends AppCompatActivity {
    // properties
    private ImageButton imgBtn_bmi;
    private ImageButton imgBtn_bfp;
    private ImageButton imgBtn_both;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_calculator);

        imgBtn_bmi =(ImageButton) findViewById(R.id.imageButtonBMI);
        imgBtn_bfp =(ImageButton) findViewById(R.id.imageButtonBFP);
        imgBtn_both =(ImageButton) findViewById(R.id.imageButtonBoth);

        imgBtn_bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),BMICalculator.class);
                startActivity(intent);
                finish();
            }
        });
        imgBtn_both.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
