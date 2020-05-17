package be.ehb.LoginMockup.ui.Calorie;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import be.ehb.Ehealth.R;
import be.ehb.LoginMockup.ui.corona.FirebaseDatabaseHelper;
import be.ehb.LoginMockup.ui.corona.User;

public class CalResult extends AppCompatActivity {
    TextView txtRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_result);


        txtRes = (TextView)findViewById(R.id.txtRes);

        Intent mIntent = getIntent();
        int result = mIntent.getIntExtra("Result", 0);
        int minCal = result -400;
        int maxCal = result + 400;

        txtRes.setText("Your calories per day should be between "+ minCal + " and " + maxCal);




    }


}