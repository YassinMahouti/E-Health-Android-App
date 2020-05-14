package be.ehb.LoginMockup.ui.corona;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.Date;

import be.ehb.LoginMockup.ui.corona.CoronaResult;
import be.ehb.Ehealth.R;
import be.ehb.LoginMockup.ui.registratie.FirebaseAuthHelper;


public class CoronaMain extends AppCompatActivity {
        FirebaseAuth gbTest;
    //---------TextView-----------------------
    private TextView lbl_result;
    private TextView lbl_result_accurate_risk;
    //---------RecyclerView-------------------
    private RecyclerView mRecyclerView;
    //---------Buttons------------------------
    private Button btn_saveAndCalculate;
    private Button btn_showAllResults;
    //---------RadioButtons-------------------
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
    //------------accurate symptoms-----------------
    private RadioGroup rd_group1_accurate;
    private RadioGroup rd_group2_accurate;
    private RadioGroup rd_group3_accurate;
    private RadioGroup rd_group4_accurate;
    private RadioButton rd_accurate_1_yes;
    private RadioButton rd_accurate_2_yes;
    private RadioButton rd_accurate_3_yes;
    private RadioButton rd_accurate_4_yes;
    private RadioButton rd_accurate_1_no;
    private RadioButton rd_accurate_2_no;
    private RadioButton rd_accurate_3_no;
    private RadioButton rd_accurate_4_no;
    //----------------------------------------------
    private ProgressBar progressBarAccurateSymptoms;
    public int accurate_risk =0;
    private String accurate_msg="";
    //----------DatabaseReference------------------
    //----------UserCorona: answerSymptoms
    DatabaseReference mRootSymptom;
    DatabaseReference addSymp;
    //----------User: user_id -> user_risk_corona
    DatabaseReference mRootUser;
    DatabaseReference mRootUserID;
    DatabaseReference mRootUserID_risk;
    //----------UserCorona: userResult -> user results AUTOINCREMENT-------
    DatabaseReference mRootUserCorona;
    DatabaseReference mUserResult;
    DatabaseReference mUserResultRisk;
    //----------diseases: disease: name, min&max percentage, number of symptoms--------
    DatabaseReference mRootDiseases;
    DatabaseReference mRootDisease;
    DatabaseReference mRootDiseaseName;
    DatabaseReference mRootDiseaseMinPercentage;
    DatabaseReference mRootDiseaseMaxPercentage;
    DatabaseReference mRootDiseaseNumberOfSymptoms;
    //----------Datamembers----------------------------------

    private int user_id =1;
    public float userRisk =0;
    private int count ;

    public void init()
    {
        //---------setup TextView--------------------------------------------
        lbl_result =(TextView) findViewById(R.id.txt_Result);
        lbl_result_accurate_risk = (TextView) findViewById(R.id.txt_result_accurate);
        //---------setup Buttons---------------------------------------------
        btn_saveAndCalculate =(Button) findViewById(R.id.btn_saveSymptoms);
        btn_showAllResults =(Button) findViewById(R.id.btn_show_results);
        //---------setup RadioGroups & RadioButtons -------------------------
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
        //---Test Accurate Symptoms------------
        rd_group1_accurate =(RadioGroup) findViewById(R.id.rd_group1_accurate);
        rd_group2_accurate =(RadioGroup) findViewById(R.id.rd_group2_accurate);
        rd_group3_accurate =(RadioGroup) findViewById(R.id.rd_group3_accurate);
        rd_group4_accurate =(RadioGroup) findViewById(R.id.rd_group4_accurate);
        rd_accurate_1_yes=(RadioButton) findViewById(R.id.rd_accurate_symp1_ja);
        rd_accurate_2_yes=(RadioButton) findViewById(R.id.rd_accurate_symp2_ja);
        rd_accurate_3_yes=(RadioButton) findViewById(R.id.rd_accurate_symp3_ja);
        rd_accurate_4_yes=(RadioButton) findViewById(R.id.rd_accurate_symp4_ja);
        rd_accurate_1_no=(RadioButton) findViewById(R.id.rd_accurate_symp1_nee);
        rd_accurate_2_no=(RadioButton) findViewById(R.id.rd_accurate_symp2_nee);
        rd_accurate_3_no=(RadioButton) findViewById(R.id.rd_accurate_symp3_nee);
        rd_accurate_4_no=(RadioButton) findViewById(R.id.rd_accurate_symp4_nee);

        progressBarAccurateSymptoms =(ProgressBar) findViewById(R.id.progressBarAccurateSymptoms);
    }

    /**
     * Function return nothing, it create a table for the diseases in the realtime database.
     * @param db : Need to pass a FirebaseDatabase, to have access to the database.
     */
    public void createTableDiseases(FirebaseDatabase db)
    {
        //those are "table names"
        mRootDiseases = db.getReference("diseases");
        mRootDisease = db.getReference("disease");
        //creating child : name & value : value
        //----Root: Start---------------------------------------------
        mRootDiseases  = mRootUserCorona.child("diseases");
        //-----------Corona-------------------------------------------
        mRootDisease = mRootDiseases.child("1");
        mRootDiseaseName = mRootDisease.child("disease_name");
        mRootDiseaseName.setValue("Corona");
        mRootDiseaseMaxPercentage =  mRootDisease.child("maxPercentage");
        mRootDiseaseMaxPercentage.setValue(100);
        mRootDiseaseMinPercentage =  mRootDisease.child("minPercentage");
        mRootDiseaseMinPercentage.setValue(70);
        mRootDiseaseNumberOfSymptoms = mRootDisease.child("numberOf_symptoms");
        mRootDiseaseNumberOfSymptoms.setValue(7);
        //-----------Griep-------------------------------------------
        mRootDisease = mRootDiseases.child("2");
        mRootDiseaseName = mRootDisease.child("disease_name");
        mRootDiseaseName.setValue("Griep");
        mRootDiseaseMaxPercentage =  mRootDisease.child("maxPercentage");
        mRootDiseaseMaxPercentage.setValue(80);
        mRootDiseaseMinPercentage =  mRootDisease.child("minPercentage");
        mRootDiseaseMinPercentage.setValue(40);
        mRootDiseaseNumberOfSymptoms = mRootDisease.child("numberOf_symptoms");
        mRootDiseaseNumberOfSymptoms.setValue(5);
        //-----------Verkoudheid-------------------------------------------
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String test ="test";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona_main);
        //To write to db: instantiate db -> getInstance()
        //-----FirebaseDatabase---------------------------------
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        FirebaseAuthHelper fbAuth = new FirebaseAuthHelper();
        gbTest= FirebaseAuth.getInstance();

        //-----References--------------------------------------
        mRootUserCorona = db.getReference("UserCorona");
        mUserResult = db.getReference("UserResult");
        mRootUser = db.getReference("Users");
        mRootUserID = db.getReference("user_id");
        //-----Create table diseases
        createTableDiseases(db);
        //------------Initialisation of all we need (call own init)------------------------------
        init();
        //------OnClickListener-----------------------------------------------------------------
        //--save realtime input of user: symptoms
        /**
         * OnClickListener to save the symptoms and the user risk percentage to the realtime database
         */
        btn_saveAndCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Local data-member used for creating a message from the user risk percentage.
                 */
                //-- init data-members and reset values to 0
                String symptomsMessage="";
                String disease;
                count =0; userRisk =0;
                //--Check the RadioButtons status and add a percentage to become the "total user risk"
                //----Check symptom 1----------------------------------
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
                //----Check symptom 2----------------------------------
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
                //----Check symptom 3----------------------------------
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
                //----Check symptom 4----------------------------------
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
                //----Check symptom 5----------------------------------
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
                //----Check symptom 6----------------------------------
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
                //----Check symptom 7----------------------------------
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
                //--create  message by using the user_risk obtained
                if(userRisk>=70)
                    disease = "You have a result that may us think you possibly have Corona or at least symptoms that coincide whit it.";
                else if (userRisk <70 && userRisk>=40)
                    disease= "Corona is a possibility but Griep has also kind of the same symptoms.";
                else if (userRisk <40 && userRisk >=12)
                    disease="You are ill, you maybe have a Griep or maybe just a Verkoudheid.";
                else disease ="You are safe, you maybe just ill and suffering from some headache or a runny nose. ";

                //----- Write to User: user_id-> user_risk_corona = userRisk
                mRootUserID = mRootUser.child(fbAuth.getUid());
              //  mRootUserID = mRootUser.child(gbTest.getCurrentUser().getUid());
                mRootUserID_risk = mRootUserID.child("user_risk_corona");
                mRootUserID_risk.setValue(userRisk);
                //----Need to create a date each time the user save a result( later for the progress of the user)
                Date date = new Date();
                //------Write to db using push for AUTOINCREMENT: for each result an id
                mUserResult = mRootUserCorona.child("userResult").push();
                //------Write user_risk, user_id, countSymptoms, date
                mUserResultRisk = mUserResult.child("user_risk");
                mUserResultRisk.setValue(userRisk);
                mUserResultRisk = mUserResult.child("user_id");
                mUserResultRisk.setValue(user_id);
                mUserResultRisk = mUserResult.child("countSymptoms");
                mUserResultRisk.setValue(count);
                mUserResultRisk = mUserResult.child("date");
                mUserResultRisk.setValue(String.valueOf(date));
                //---Toast message to show user data is stored successfully in db
                Toast.makeText(CoronaMain.this, "Successfully added to cloud", Toast.LENGTH_SHORT).show();
                //--Show result to user: setText on lbl_result
                String stdAdvies = " Please daily check your temperature(fever) and stay safe, keep following your symptoms !"+"\n" +"Please respect the rules of distance between people and follow safety precautions.";
                lbl_result.setText("You have a risk percentage of "+userRisk+"%." + "\n "+"You have following symptoms:"+symptomsMessage +"."+ disease + "\n" +stdAdvies);
            }
        });
        //---To show all the results of the user: need to start a new activity
        //Onclick listen on button to go on the activity when user decide it
        btn_showAllResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoronaMain.this , CoronaResult.class);
                startActivity(intent);
                finish();
            }
        });


        rd_group1_accurate.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(rd_accurate_1_yes.isChecked())
                {
                    accurate_risk +=25;
                    setupProgressBarAccurateRisk(progressBarAccurateSymptoms, accurate_risk,accurate_msg);

                }


            }
        });
        rd_group2_accurate.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(rd_accurate_2_yes.isChecked())
                {
                    accurate_risk +=25;
                    setupProgressBarAccurateRisk(progressBarAccurateSymptoms, accurate_risk,accurate_msg);

                }

            }
        });
        rd_group3_accurate.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(rd_accurate_3_yes.isChecked())
                {
                    accurate_risk +=25;
                    setupProgressBarAccurateRisk(progressBarAccurateSymptoms, accurate_risk,accurate_msg);

                }

            }
        });
        rd_group4_accurate.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(rd_accurate_4_yes.isChecked())
                {
                    accurate_risk +=25;
                    setupProgressBarAccurateRisk(progressBarAccurateSymptoms, accurate_risk,accurate_msg);

                }

            }
        });


    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setupProgressBarAccurateRisk(ProgressBar pg, int risk, String msg)
    {
        progressBarAccurateSymptoms.setProgress(risk);
        if(risk ==0)
        {
            msg ="You have selected none of the most important risk, it a good thing! You can do the second test but it's not a must since you don't have the most important symptoms.";
            progressBarAccurateSymptoms.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
            lbl_result_accurate_risk.setText(msg);
        }
        else if(risk == 25) {
            progressBarAccurateSymptoms.setProgressTintList(ColorStateList.valueOf(Color.YELLOW));
            msg = "It seams you selected one symptom.You don't have to answer to the next checkup but we still invite you to do it, so you can better evaluate your risk to be sure.";
            lbl_result_accurate_risk.setText(msg);
        }
        else if (risk ==50){
            msg ="Please answer to the next questions below! You selected 2 symptoms that are the most accurate symptoms to evaluate if you have COVID-19.If your result on the next question is greater than 50% Please call 911.";
            progressBarAccurateSymptoms.setProgressTintList(ColorStateList.valueOf(Color.RED));
            lbl_result_accurate_risk.setText(msg);
        }
        else if (risk==75){
            msg ="Please answer to the next questions below! You selected 3 symptoms that are the most accurate symptoms to evaluate if you have COVID-19.If your result on the next question is greater than 25% Please call 911.";
            progressBarAccurateSymptoms.setProgressTintList(ColorStateList.valueOf(Color.RED));
            lbl_result_accurate_risk.setText(msg);
        }
        else if (risk ==100){
            msg ="Please call 911.";
            progressBarAccurateSymptoms.setProgressTintList(ColorStateList.valueOf(Color.RED));
            lbl_result_accurate_risk.setText(msg);

        }

    }
    public String messageFromUserRisk(float userRisk) {
        String disease;
        if (userRisk >= 70)
            disease = "You have a result that may us think you possibly have Corona or at least symptoms that coincide whit it.";
        else if (userRisk < 70 && userRisk >= 40)
            disease = "Corona is a possibility but Griep has also kind of the same symptoms.";
        else if (userRisk < 40 && userRisk >= 12)
            disease = "You are ill, you maybe have a Griep or maybe just a Verkoudheid.";
        else
            disease = "You are safe, you maybe just ill and suffering from some headache or a runny nose. ";

        //String stdAdvies = " Please daily check your temperature(fever) and stay safe, keep following your symptoms !" + "\n" + "Please respect the rules of distance between people and follow safety precautions.";
        return ("You have a risk percentage of " + userRisk + "%." + "\n " +  disease);
    }


}