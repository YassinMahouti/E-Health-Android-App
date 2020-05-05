package be.ehb.proj.mybfpapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn_save;
    private Button btn_calculateRisk;
    private RadioGroup rd_groupFirst;
    private RadioButton rd_answerFirst_yes;
    private RadioButton rd_answerFirst_no;
    private RadioGroup rd_groupSecond;
    private RadioButton rd_answerSec_yes;
    private RadioButton rd_answerSec_no;
    private RadioGroup rd_groupThird;
    private RadioButton rd_answerThird_yes;
    private RadioButton rd_answerThird_no;
    private RadioGroup rd_groupFo;
    private RadioButton rd_answerFo_yes;
    private RadioButton rd_answerFo_no;
    private RadioGroup rd_groupFi;
    private RadioButton rd_answerFi_yes;
    private RadioButton rd_answerFi_no;
    private RadioGroup rd_groupSix;
    private RadioButton rd_answerSix_yes;
    private RadioButton rd_answerSix_no;
    private RadioGroup rd_groupSeven;
    private RadioButton rd_answerSeven_yes;
    private RadioButton rd_answerSeven_no;
    DatabaseReference mRootSymptom;

    DatabaseReference addSymp;
    private int user_id =1;
    private float userRisk =0;
    private int count ;

    //-----
    private RecyclerView mRecyclerView;



    //---Table "UserCorona" Database Reference ------
    DatabaseReference mRootUserCorona;
    DatabaseReference mUserResult;
    DatabaseReference mUserResultRisk;

    //---Table diseases Database Reference----------------
    DatabaseReference mRootDiseases;
    DatabaseReference mRootDisease;
    DatabaseReference mRootDiseaseName;
    DatabaseReference mRootDiseaseMinPercentage;
    DatabaseReference mRootDiseaseMaxPercentage;
    DatabaseReference mRootDiseaseNumberOfSymptoms;



    public void init()
    {
        //listSymptoms =(ListView) findViewById(R.id.listSymptoms);
        btn_save =(Button) findViewById(R.id.btn_saveSymptoms);
        btn_calculateRisk =(Button) findViewById(R.id.btn_calculateRisk);

        rd_groupFirst = (RadioGroup) findViewById(R.id.rd_group);
        rd_answerFirst_yes = (RadioButton) findViewById(R.id.rd_First_yes);
        rd_answerFirst_no = (RadioButton) findViewById(R.id.rd_first_no);

        rd_groupSecond =(RadioGroup) findViewById(R.id.rd_group2);
        rd_answerSec_yes = (RadioButton) findViewById(R.id.rd_Sec_yes);
        rd_answerSec_no = (RadioButton) findViewById(R.id.rd_Sec_no);

        rd_groupThird =(RadioGroup) findViewById(R.id.rd_group3);
        rd_answerThird_yes = (RadioButton) findViewById(R.id.rd_Third_yes);
        rd_answerThird_no = (RadioButton) findViewById(R.id.rd_Third_no);

        rd_groupFo =(RadioGroup) findViewById(R.id.rd_group4);
        rd_answerFo_yes = (RadioButton) findViewById(R.id.rd_Fo_yes);
        rd_answerFo_no = (RadioButton) findViewById(R.id.rd_Fo_no);

        rd_groupFi =(RadioGroup) findViewById(R.id.rd_group5);
        rd_answerFi_yes = (RadioButton) findViewById(R.id.rd_Fif_yes);
        rd_answerFi_no = (RadioButton) findViewById(R.id.rd_Fif_no);

        rd_groupSix =(RadioGroup) findViewById(R.id.rd_group6);
        rd_answerSix_yes = (RadioButton) findViewById(R.id.rd_Six_yes);
        rd_answerSix_no = (RadioButton) findViewById(R.id.rd_Six_no);

        rd_groupSeven =(RadioGroup) findViewById(R.id.rd_group7);
        rd_answerSeven_yes = (RadioButton) findViewById(R.id.rd_Seven_yes);
        rd_answerSeven_no = (RadioButton) findViewById(R.id.rd_Seven_no);
    }
    public void createTableDiseases(FirebaseDatabase db)
    {
        //--- those are "table names"
        mRootDiseases = db.getReference("diseases");
        mRootDisease = db.getReference("disease");
        //--- creating child : name & value : value
        mRootDiseases  = mRootUserCorona.child("diseases");
        mRootDisease = mRootDiseases.child("1");
        mRootDiseaseName = mRootDisease.child("disease_name");
        mRootDiseaseName.setValue("Corona");
        mRootDiseaseMaxPercentage =  mRootDisease.child("maxPercentage");
        mRootDiseaseMaxPercentage.setValue(100);
        mRootDiseaseMinPercentage =  mRootDisease.child("minPercentage");
        mRootDiseaseMinPercentage.setValue(70);
        mRootDiseaseNumberOfSymptoms = mRootDisease.child("numberOf_symptoms");
        mRootDiseaseNumberOfSymptoms.setValue(7);
        mRootDisease = mRootDiseases.child("2");
        mRootDiseaseName = mRootDisease.child("disease_name");
        mRootDiseaseName.setValue("Griep");
        mRootDiseaseMaxPercentage =  mRootDisease.child("maxPercentage");
        mRootDiseaseMaxPercentage.setValue(80);
        mRootDiseaseMinPercentage =  mRootDisease.child("minPercentage");
        mRootDiseaseMinPercentage.setValue(40);
        mRootDiseaseNumberOfSymptoms = mRootDisease.child("numberOf_symptoms");
        mRootDiseaseNumberOfSymptoms.setValue(5);
        mRootDisease = mRootDiseases.child("3");
        mRootDiseaseName = mRootDisease.child("disease_name");
        mRootDiseaseName.setValue("Verkouden");
        mRootDiseaseMaxPercentage =  mRootDisease.child("maxPercentage");
        mRootDiseaseMaxPercentage.setValue(60);
        mRootDiseaseMinPercentage =  mRootDisease.child("minPercentage");
        mRootDiseaseMinPercentage.setValue(12);
        mRootDiseaseNumberOfSymptoms = mRootDisease.child("numberOf_symptoms");
        mRootDiseaseNumberOfSymptoms.setValue(3);
    }

    private void statusUser(float userRisk){
       // if()
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //To write a message to db
        // instantiate db -> getInstance()
        FirebaseDatabase db = FirebaseDatabase.getInstance();


        //-------------
        mRootUserCorona = db.getReference("UserCorona");
        mUserResult = db.getReference("UserResult");
        //---------------

        createTableDiseases(db);

        init();


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count =0;
                userRisk =0;
                rd_answerFirst_no.isChecked();
                mRootSymptom = mRootUserCorona.child("answerSymptoms");
                addSymp =  mRootSymptom.child("symptom1");
                if( rd_answerFirst_yes.isChecked())
                {
                    addSymp.setValue("1");
                    userRisk += 32.0685f;
                    count++;
                }
                else
                addSymp.setValue("0");

                rd_answerSec_no.isChecked();
                addSymp =  mRootSymptom.child("symptom2");
                if(  rd_answerSec_yes.isChecked())
                {
                    addSymp.setValue("1");
                    userRisk += 24.6990f;
                    count++;
                }
                else
                    addSymp.setValue("0");

                rd_answerThird_no.isChecked();
                addSymp =  mRootSymptom.child("symptom3");
                if(  rd_answerThird_yes.isChecked())
                {
                    addSymp.setValue("1");
                    userRisk += 13.9000f;
                    count++;
                }
                else
                    addSymp.setValue("0");

                rd_answerFo_no.isChecked();
                addSymp =  mRootSymptom.child("symptom4");
                if(  rd_answerFo_yes.isChecked()) {
                    addSymp.setValue("1");
                    userRisk += 12.1853f;
                    count++;
                }
                else
                    addSymp.setValue("0");

                rd_answerFi_no.isChecked();
                addSymp =  mRootSymptom.child("symptom5");
                if(  rd_answerFi_yes.isChecked()) {
                    addSymp.setValue("1");
                    userRisk += 6.7858f;
                    count++;
                }
                else
                    addSymp.setValue("0");

                rd_answerSix_no.isChecked();
                addSymp =  mRootSymptom.child("symptom6");
                if(  rd_answerSix_yes.isChecked()) {
                    addSymp.setValue("1");
                    userRisk += 5.3995f;
                    count++;
                }
                else
                    addSymp.setValue("0");

                rd_answerSeven_no.isChecked();
                addSymp =  mRootSymptom.child("symptom7");
                if(  rd_answerSeven_yes.isChecked()) {
                    addSymp.setValue("1");
                    userRisk += 4.9617f;
                    count++;
                }
                else
                    addSymp.setValue("0");





              //  t_userRisk.push().setValue(String.valueOf(userRisk));
                //------
                Date date = new Date();
                mUserResult = mRootUserCorona.child("userResult").push();
                mUserResultRisk = mUserResult.child("user_risk");
                mUserResultRisk.setValue(userRisk);
                mUserResultRisk = mUserResult.child("user_id");
                mUserResultRisk.setValue(user_id);
                mUserResultRisk = mUserResult.child("countSymptoms");
                mUserResultRisk.setValue(count);
                mUserResultRisk = mUserResult.child("date");
                mUserResultRisk.setValue(String.valueOf(date));




            }
        });

      btn_calculateRisk.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(MainActivity.this , CoronaResult.class);
              startActivity(intent);
          }
      });
    }


    }

