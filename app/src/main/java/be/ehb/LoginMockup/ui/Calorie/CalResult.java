package be.ehb.LoginMockup.ui.Calorie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import be.ehb.Ehealth.R;
import be.ehb.LoginMockup.ui.corona.FirebaseDatabaseHelper;
import be.ehb.LoginMockup.ui.corona.User;

public class CalResult extends AppCompatActivity {

    TextView txtRes;
    public int minCal;
    public int maxCal;
    Button buttonActiviteit;
    Button buttonFoodChecker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_result);


        txtRes = (TextView) findViewById(R.id.txtRes2);
        buttonActiviteit = (Button) findViewById(R.id.button7);
        buttonFoodChecker = (Button) findViewById(R.id.button6);

        buttonActiviteit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (CalResult.this , be.ehb.LoginMockup.ui.movementMonitor.MainActivity.class);
                startActivity(intent);
            }
        });

        buttonFoodChecker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (CalResult.this,be.ehb.LoginMockup.ui.foodchecker.FoodCheckerMain.class);
                startActivity(intent);
            }
        });

        Intent mIntent = getIntent();
        int result = mIntent.getIntExtra("Result", 0);
        minCal = result - 400;
        maxCal = result + 400;

        if (minCal <= 0) {
            minCal = 0;
        }
        if (maxCal <= 0) {
            maxCal = 0;
        }

        txtRes.setText("Your calories per day should be between " + minCal + " and " + maxCal);


    }


}