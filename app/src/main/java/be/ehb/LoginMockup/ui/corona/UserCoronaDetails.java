package be.ehb.LoginMockup.ui.corona;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;import be.ehb.Ehealth.R;


public class UserCoronaDetails extends AppCompatActivity {
    //------TextViews---------------------
    private TextView mDate_tv, mRisk_tv, mNumberOfSymptoms_tv, corona_info;
    public static TextView data_api;
    private ImageButton imgBtn;
    private ProgressBar progressBarRisk;
    //--properties for recuperation of the information from intent of activity before
    private String key, risk , date , countSymptoms;
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

        //init
        mDate_tv =(TextView) findViewById(R.id.txt_date);
        mDate_tv.setText(date);
        mRisk_tv =(TextView) findViewById(R.id.txt_Risk);
        mRisk_tv.setText(risk);
        mNumberOfSymptoms_tv =(TextView) findViewById(R.id.txt_countSymptoms);
        mNumberOfSymptoms_tv.setText(countSymptoms);
        imgBtn = (ImageButton) findViewById(R.id.imageButton);
        // het kan zijn dat je hier een error krijgt, waarom weet ik niet omdat het werkt: ik ga dit nog testen
        progressBarRisk = (ProgressBar) findViewById(R.id.progressBarRisk);

        corona_info =(TextView) findViewById(R.id.txt_corona_info);
        String msg_corona_info = new CoronaMain().messageFromUserRisk(Float.parseFloat(risk));
        corona_info.setText(msg_corona_info);

        data_api =(TextView) findViewById(R.id.txt_Result_api_corona);
        Button btn_fetch =(Button) findViewById(R.id.btn_start_fetch);
        btn_fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CoronaStatsAPI api_call = new CoronaStatsAPI();
                api_call.execute();
            }
        });


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
                        Toast.makeText(UserCoronaDetails.this, "Data successfully deleted", Toast.LENGTH_SHORT).show();
                        finish(); //stop activity and stop returning
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
