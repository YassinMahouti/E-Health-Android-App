package be.ehb.proj.basicbfpapplication.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import be.ehb.proj.basicbfpapplication.R;
import be.ehb.proj.basicbfpapplication.model.Profile;
import be.ehb.proj.basicbfpapplication.model.User;
import be.ehb.proj.basicbfpapplication.tools.FirebaseDatabaseHelper;

public class UserDetails extends AppCompatActivity {
    private TextView mDate_tv;

    private TextView mHeight_tv;
    private TextView mWeight_tv;
    private TextView mAge_tv;
    private TextView mSex_tv;
    private TextView mResult_bmi;
    private TextView mResult_bfp;

   private Button btn_delete;

    // properties from intent
    private String key;
    private String height;
    private String date;
    private String weight;
    private String age;
    private String gender;
    private String resultBMI;
    private String resultBFP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userbmi_details);
        //get data from intent object
        key = getIntent().getStringExtra("key");
        date = getIntent().getStringExtra("date");
        resultBFP =getIntent().getStringExtra("user_bfp");
        resultBMI =getIntent().getStringExtra("user_bmi");
        height = getIntent().getStringExtra("user_height");
        gender = getIntent().getStringExtra("user_sex");
        age = getIntent().getStringExtra("user_age");
        weight = getIntent().getStringExtra("user_weight");



        //init
        mDate_tv =(TextView) findViewById(R.id.txt_Date);
        mDate_tv.setText(date);
        mResult_bfp= (TextView) findViewById(R.id.txt_BFP);
        mResult_bfp.setText(resultBFP);
        mResult_bmi= (TextView) findViewById(R.id.txt_BMI);
        mResult_bmi.setText(resultBMI);
        mHeight_tv =(TextView) findViewById(R.id.txt_height);
        mHeight_tv.setText(height);
        mSex_tv= (TextView) findViewById(R.id.txt_gender);
        mSex_tv.setText(gender);
        mAge_tv= (TextView) findViewById(R.id.txt_age);
        mAge_tv.setText(age);
        mWeight_tv =(TextView) findViewById(R.id.txt_weight);
        mWeight_tv.setText(weight);




        btn_delete =(Button) findViewById(R.id.btn_delete);

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FirebaseDatabaseHelper().deleteItem(key, new FirebaseDatabaseHelper.DataStatus() {

                    @Override
                    public void DataIsLoaded(List<User> userBMI, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(UserDetails.this, "Data has been deleted", Toast.LENGTH_SHORT).show();
                        finish(); //stop activity and stop returnin
                        return;
                    }
                });
            }
        });
    }
}


