package be.ehb.LoginMockup.ui.bmiAndBfp.view;

import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import be.ehb.LoginMockup.ui.bmiAndBfp.model.User;
import be.ehb.LoginMockup.ui.bmiAndBfp.tools.FirebaseDatabaseHelper;
import be.ehb.Ehealth.R;
public class UserDetails extends AppCompatActivity {

    private TextView mDate_tv;

    private TextView mHeight_tv;
    private TextView mWeight_tv;
    private TextView mAge_tv;
    private TextView mSex_tv;
    private TextView mResult_bmi;
    private TextView mResult_bfp;
    private ProgressBar progressBarBMI;
    private ProgressBar progressBarBFP;

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



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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

        // underweight <18.5, normal weight 18.5-25 overweight 25-30  obese >30

        float bmi = Float.parseFloat(resultBMI);
        float bfp =  Float.parseFloat(resultBFP);
//.msgBB(bmi,bfp, gender);

        String result_msg = new BmiBfpMain().msgBB(bmi,bfp,gender);
        //---------initialisation-------------------------------------------------------------------
        mDate_tv =(TextView) findViewById(R.id.txt_Date);
        mDate_tv.setText(date);
        mResult_bfp= (TextView) findViewById(R.id.txt_BFP);
        mResult_bfp.setText(result_msg);
        //---------------------------------------------------
        mHeight_tv =(TextView) findViewById(R.id.txt_height);
        mHeight_tv.setText(height);
        mSex_tv= (TextView) findViewById(R.id.txt_gender);
        mSex_tv.setText(gender);
        mAge_tv= (TextView) findViewById(R.id.txt_age);
        mAge_tv.setText(age);
        mWeight_tv =(TextView) findViewById(R.id.txt_weight);
        mWeight_tv.setText(weight);
        //---------------------------------------------------
        progressBarBMI = (ProgressBar) findViewById(R.id.progressBarBMI);
        progressBarBFP =(ProgressBar) findViewById(R.id.progressBarBFP);
        setUpProgressBarBMI(progressBarBMI,bmi);
        setUpProgressBarBFP(progressBarBFP,bfp,gender);
        //-------------------------------------------------------

        //-------------------------------------------------------
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
                            //stop activity and stop return anything
                            finish(); return;
                    }
                });
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public  void setUpProgressBarBMI(ProgressBar progressBar, float bmi)
    {
        //------ ProgressBarColors setup
        if(bmi<=15 || bmi >= 30)
        {
            progressBar.setProgressTintList(ColorStateList.valueOf(Color.RED));
        }
        else if((bmi > 15 && bmi <= 18.5) || bmi >=27.5)
        {
            progressBar.setProgressTintList(ColorStateList.valueOf(Color.YELLOW));
        }
        else progressBar.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
        //--- setup Progress
        progressBar.setProgress((int)bmi);

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public  void setUpProgressBarBFP(ProgressBar progressBar, float bfp, String gender)
    {
        //------ ProgressBarColors setup
        if(gender=="woman")
        {
            if(bfp<10.0 || bfp > 32)
            {
                progressBar.setProgressTintList(ColorStateList.valueOf(Color.RED));
            }
            else if((bfp >= 10.0 && bfp <= 14) || (bfp >=25.0 && bfp <= 32))
            {
                progressBar.setProgressTintList(ColorStateList.valueOf(Color.YELLOW));
            }
            else progressBar.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
        }
        else {
            if(bfp<3 || bfp > 25)
            {
                progressBar.setProgressTintList(ColorStateList.valueOf(Color.RED));
            }
            else if((bfp >= 3.0 && bfp <= 5) || (bfp >=18.0 && bfp <= 25.0))
            {
                progressBar.setProgressTintList(ColorStateList.valueOf(Color.YELLOW));
            }
            else progressBar.setProgressTintList(ColorStateList.valueOf(Color.GREEN));

        }

        //--- setup Progress
        progressBar.setProgress((int)bfp);

    }
}


