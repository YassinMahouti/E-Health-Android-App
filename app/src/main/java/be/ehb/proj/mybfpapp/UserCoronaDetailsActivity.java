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
    //------TextViews---------------------
    private TextView mDate_tv;
    private TextView mRisk_tv;
    private TextView mNumberOfSymptoms_tv;
    private ImageButton imgBtn;
    private ProgressBar progressBarRisk;
    //--properties for recuperation of the information from intent of activity before
    private String key;
    private String risk;
    private String date;
    private String countSymptoms;
    //--able to support colors in the progressbar: at least LOLLIPOP
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    /**
     * onCreate the information from the user of the activity before will be stored in to strings.
     * Then the information will be passed to the TextViews associated with id of layout.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_corona_details);
        //get data from intent object
        key = getIntent().getStringExtra("key");
        date = getIntent().getStringExtra("date");
        risk = getIntent().getStringExtra("risk");
        countSymptoms = getIntent().getStringExtra("countSymptoms");
        //--associate TextView by id and set information(strings)
        mDate_tv =(TextView) findViewById(R.id.txt_date);
        mDate_tv.setText(date);
        mRisk_tv =(TextView) findViewById(R.id.txt_Risk);
        mRisk_tv.setText(risk);
        mNumberOfSymptoms_tv =(TextView) findViewById(R.id.txt_countSymptoms);
        mNumberOfSymptoms_tv.setText(countSymptoms);
        imgBtn = (ImageButton) findViewById(R.id.imageButton);
        progressBarRisk = (ProgressBar) findViewById(R.id.progressBarRisk);
        /**
         * Able to have some visual support I created a method to color and have a responding progressbar depending on user risk.
         */
        setupPorgressBarRisk(Float.parseFloat(risk));
        /**
         * Able to let the user delete his information from the cloud a created a delete button.
         * Associated with the FirebaseDatabaseHelper class I created. Generate override methods
         */
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
    /**
     * Able to setup the progressbar with the progress of the user.
     * And set some colors corresponding with the user risk.
     * This setup requires at least Lollipop.
     */
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
