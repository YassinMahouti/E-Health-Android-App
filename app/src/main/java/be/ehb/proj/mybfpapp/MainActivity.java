package be.ehb.proj.mybfpapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Button btn_saveAndCalculate;
    private Button btn_showAllResults;
    private TextView lbl_result;
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
        btn_saveAndCalculate =(Button) findViewById(R.id.btn_saveSymptoms);
        btn_showAllResults =(Button) findViewById(R.id.btn_calculateRisk);


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
        lbl_result =(TextView) findViewById(R.id.txt_Result);

        btn_saveAndCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String symptomsMessage="";
                String disease;
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
                    symptomsMessage +="koorts";
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
                    symptomsMessage +=", droge hoest";
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
                    symptomsMessage +=", vermoeidheid";
                }
                else
                    addSymp.setValue("0");

                rd_answerFo_no.isChecked();
                addSymp =  mRootSymptom.child("symptom4");
                if(  rd_answerFo_yes.isChecked()) {
                    addSymp.setValue("1");
                    userRisk += 12.1853f;
                    count++;
                    symptomsMessage +=", expectoratie(slijm)";
                }
                else
                    addSymp.setValue("0");

                rd_answerFi_no.isChecked();
                addSymp =  mRootSymptom.child("symptom5");
                if(  rd_answerFi_yes.isChecked()) {
                    addSymp.setValue("1");
                    userRisk += 6.7858f;
                    count++;
                    symptomsMessage +=", buiten adem zijn";
                }
                else
                    addSymp.setValue("0");

                rd_answerSix_no.isChecked();
                addSymp =  mRootSymptom.child("symptom6");
                if(  rd_answerSix_yes.isChecked()) {
                    addSymp.setValue("1");
                    userRisk += 5.3995f;
                    count++;
                    symptomsMessage +=", pijn in de spieren en of articulaties";
                }
                else
                    addSymp.setValue("0");

                rd_answerSeven_no.isChecked();
                addSymp =  mRootSymptom.child("symptom7");
                if(  rd_answerSeven_yes.isChecked()) {
                    addSymp.setValue("1");
                    userRisk += 4.9617f;
                    count++;
                    symptomsMessage +=", hoofdpijn";
                }
                else
                    addSymp.setValue("0");

                if(userRisk>=70)
                    disease = "You have a result that may us think you possibly have Corona or at least symptoms that coincide whit it.";
                else if (userRisk <70 && userRisk>=40)
                    disease= "Corona is a possibility but Griep has also kind of the same symptoms.";
                else if (userRisk <40 && userRisk >=12)
                    disease="You are ill, you maybe have a Griep or maybe just a Verkoudheid.";
                else disease ="You are safe, you maybe just ill and suffering from some headache or a runny nose. ";





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
                Toast.makeText(MainActivity.this, "Successfully added to cloud", Toast.LENGTH_SHORT).show();
                String stdAdvies = " Please daily check your temperature(fever) and stay safe, keep following your symptoms !"+"\n" +"Please respect the rules of distance between people and follow safety precautions.";
                lbl_result.setText("You have a risk percentage of "+userRisk+"%." + "\n "+"You have following symptoms:"+symptomsMessage +"."+ disease + "\n" +stdAdvies);


            }
        });

      btn_showAllResults.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(MainActivity.this , CoronaResult.class);
              startActivity(intent);
              finish();
          }
      });
    }


    }

