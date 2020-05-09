package be.ehb.proj.mybfpapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class UserCoronaDetailsActivity extends AppCompatActivity {
    private TextView mDate_tv;
    private TextView mRisk_tv;
    private TextView mNumberOfSymptoms_tv;
    private ImageButton imgBtn;
    private ProgressBar progressBarRisk;

    // properties from intent
    private String key;
    private String risk;
    private String date;
    private String countSymptoms;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_corona_details);
        //get data from intent object
        key = getIntent().getStringExtra("key");
        date = getIntent().getStringExtra("date");
        risk = getIntent().getStringExtra("risk");
        countSymptoms = getIntent().getStringExtra("countSymptoms");
        //init
        mDate_tv =(TextView) findViewById(R.id.txt_date);
        mDate_tv.setText(date);
        mRisk_tv =(TextView) findViewById(R.id.txt_Risk);
        mRisk_tv.setText(risk);
        mNumberOfSymptoms_tv =(TextView) findViewById(R.id.txt_countSymptoms);
        mNumberOfSymptoms_tv.setText(countSymptoms);
        imgBtn = (ImageButton) findViewById(R.id.imageButton);
        progressBarRisk = (ProgressBar) findViewById(R.id.progressBarRisk);
        setupPorgressBarRisk(Float.parseFloat(risk));
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FirebaseDatabaseHelper().deleteItem(key, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<User> userCorona, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(UserCoronaDetailsActivity.this, "Data successfully deleted", Toast.LENGTH_SHORT).show();
                        finish(); //stop activity and stop returnin
                        return;
                    }
                });
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setupPorgressBarRisk(float risk){
        if(risk < 10)
        {
            progressBarRisk.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
        }
        else
        {
            if (risk >= 10) progressBarRisk.setProgressTintList(ColorStateList.valueOf(Color.YELLOW));
            if (risk > 30) progressBarRisk.setProgressTintList(ColorStateList.valueOf(Color.CYAN));
            if (risk > 45) progressBarRisk.setProgressTintList(ColorStateList.valueOf(Color.RED));
        }
        progressBarRisk.setProgress((int) risk);


    }
}
