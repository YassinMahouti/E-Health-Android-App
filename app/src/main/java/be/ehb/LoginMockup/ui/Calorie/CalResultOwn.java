package be.ehb.LoginMockup.ui.Calorie;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import be.ehb.Ehealth.R;

public class CalResultOwn extends AppCompatActivity {
    TextView txtRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_result);
        txtRes = (TextView)findViewById(R.id.txtRes2);

        Intent mIntent = getIntent();
        int result = mIntent.getIntExtra("Result", 0);
        int minCal = result -400;
        int maxCal = result + 400;

        if (minCal <= 0) {
            minCal = 0;
        }
        if (maxCal <= 0) {
            maxCal = 0;
        }

        txtRes.setText("Your calories per day should be between "+ minCal + " and " + maxCal);
    }


}