package be.ehb.LoginMockup.ui.corona;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import be.ehb.Ehealth.R;


public class UserCoronaDetailsActivity extends AppCompatActivity {
    private TextView mDate_tv;
    private TextView mRisk_tv;
    private TextView mNumberOfSymptoms_tv;
    private ImageButton imgBtn;

    // properties from intent
    private String key;
    private String risk;
    private String date;
    private String countSymptoms;

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
                        Toast.makeText(UserCoronaDetailsActivity.this, "Data has been deleted", Toast.LENGTH_SHORT).show();
                        finish(); //stop activity and stop returnin
                        return;
                    }
                });
            }
        });
    }
}
