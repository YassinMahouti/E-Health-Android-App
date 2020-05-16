package be.ehb.LoginMockup.ui.bmiAndBfp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import be.ehb.Ehealth.R;
import be.ehb.LoginMockup.LoginAct;
import be.ehb.LoginMockup.ui.bmiAndBfp.controller.Controller;

public class BmiBfpMain extends AppCompatActivity
{
    FirebaseAuth mAuth;
    //   constant for categorisation
    public static final String CAT_1 = "Under Essential Fat";
    public static final String CAT_2 = "Essential Fat";
    public static final String CAT_3 = "Athlete";
    public static final String CAT_4 = "Fitness";
    public static final String CAT_5 = "Average";
    public static final String CAT_6 = "Obese";
    // define message
    //constants : Ess ,
    public static final int MIN_ESS_W =10;
    public static final int MAX_ESS_W =14;
    public static final int MIN_ESS_M =3;
    public static final int MAX_ESS_M =5;
    //constants : Ath ,
    public static final int MIN_ATH_W =14;
    public static final int MAX_ATH_W =21;
    public static final int MIN_ATH_M =6;
    public static final int MAX_ATH_M =14;
    //constants : Fit ,
    public static final int MIN_FIT_W =21;
    public static final int MAX_FIT_W =25;
    public static final int MIN_FIT_M =14;
    public static final int MAX_FIT_M =18;
    //constants : Ave ,
    public static final int MIN_AVE_W =25;
    public static final int MAX_AVE_W =32;
    public static final int MIN_AVE_M =18;
    public static final int MAX_AVE_M =25;
    //constants : Obe
    public static final int MIN_OBE_W =32;
    public static final int MIN_OBE_M =25;

    //properties
    private EditText txtInputWeight;
    private EditText txtInputHeight;
    private EditText txtInputAge;
    private RadioButton radioMan;
    private RadioButton radioWoman;
    private TextView lblResult;
    private TextView lblResultBMI;
    private Controller controle;
    private Switch sw_save;
    private Switch sw_cloud;
    //properties for Firebase
    DatabaseReference mRootUserBB;
    DatabaseReference mRootUserID;
    DatabaseReference mRootUserInfo;

    DatabaseReference mRootUser;
    DatabaseReference mRootUserID_bmi;
    DatabaseReference mRootUserID_bfp;

    DatabaseReference mRootUsers;
    DatabaseReference mRootKey;
    private Thread savingData;
    private Button btn_saveCloud;
    private Button show_results;


    /**
     * initialisation of my links to my graphical objects in a separated funtion so it's easyer to read code
     */
    private void init()
    {
        txtInputWeight =(EditText)  findViewById(R.id.txtInputWeight);
        txtInputHeight =(EditText) findViewById(R.id.txtInputHeight);
        txtInputAge =(EditText) findViewById(R.id.txtInputAge);
        radioMan =(RadioButton) findViewById(R.id.radioMan);
        radioWoman =(RadioButton) findViewById(R.id.radioWoman);
        lblResult = (TextView) findViewById(R.id.lblResult);
        sw_save = (Switch) findViewById(R.id.sw_saveResult);
        sw_cloud=(Switch) findViewById(R.id.sw_cloud);
        btn_saveCloud =(Button) findViewById(R.id.button_saveCloud);
        show_results =(Button) findViewById(R.id.btnViewResults);
        this.controle = Controller.getInstance(this);
        //eventlistener for calculation on btn_calculateBmiBfp
        listenCalculation();
        //Data stored locally: SQLite -> to recuperate user input: call recupProfile;
        recupProfile();
    }

    /**
     * onCreate the view wil be set and all the properties wil be initialised with calling method init()
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bfp_and_bmi_main);
        init();// initialise all we need

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //auth
        mAuth = FirebaseAuth.getInstance();


        //getReference to Firebase
        mRootUserBB = database.getReference("UserBB");
        mRootUserID = database.getReference("resultsBB");

        mRootUsers = database.getReference("Users");
        mRootUser = database.getReference("Users_Results");
       // user.getIdToken(true);
        mRootUserID = mRootUser.child(mAuth.getCurrentUser().getUid());
        mRootUserID_bmi = mRootUserID.child("user_bmi");
        mRootUserID_bfp = mRootUserID.child("user_bfp");

        mRootUsers.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String values = dataSnapshot.child("age").getValue(String.class);
                    System.out.println(values);
                    Toast.makeText(BmiBfpMain.this, values, Toast.LENGTH_SHORT).show();
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btn_saveCloud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenCloudSave();
            }
        });

        show_results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BmiBfpMain.this , UserResults.class);
                startActivity(intent);

            }
        });



    }
    private void listenCloudSave()
    {
        sw_cloud= ((Switch) findViewById(R.id.sw_cloud));
        float weight = 0;
        float height = 0;
        int age = 0;
        int sex = 0;
        boolean save = false;
        boolean cloud = false;
        try {
            weight = Float.parseFloat(txtInputWeight.getText().toString());
            height = Float.parseFloat(txtInputHeight.getText().toString());
            age = Integer.parseInt(txtInputAge.getText().toString());

        } catch (Exception e) {
            // e.printStackTrace();
        };
        if(radioMan.isChecked())
            sex = 1;
        if (sw_cloud.isChecked())
        {
            cloud = true;
            schrijfResultCloud(1,1,weight, height, age, sex,save,cloud);

            Toast.makeText(this, "Date successfully saved in Cloud", Toast.LENGTH_SHORT).show();

        }
        else Toast.makeText(this, "Date CANT BE saved in the Cloud, please agree the conditions below.", Toast.LENGTH_SHORT).show();

    }


    private void listenCalculation()
    {
        //EventListener
        ((Button) findViewById(R.id.btnCalculateBMI_BFP_Click)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {


                //Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();
                float weight = 0;
                float height = 0;
                int age = 0;
                int sex = 0;
                boolean save = false;
                boolean cloud = false;
                try {
                    weight = Float.parseFloat(txtInputWeight.getText().toString());
                    height = Float.parseFloat(txtInputHeight.getText().toString());
                    age = Integer.parseInt(txtInputAge.getText().toString());

                } catch (Exception e) {
                    // e.printStackTrace();
                };
                if( sw_save.isChecked())
                {
                    save = true;
                }
                if(sw_cloud.isChecked())
                {
                    cloud = true;


                }

                if ( radioMan.isChecked())
                {
                    sex = 1;
                }
                if ( weight ==0 || height ==0 || age ==0 )
                {
                    Toast.makeText(BmiBfpMain.this, "Invalid Input !", Toast.LENGTH_SHORT).show();
                } else viewResult( 1,1,weight, height, age, sex,save,cloud);


            }
        });

    }
    private void schrijfResultCloud(int resultID, int uid, float weight, float height , int age , int sex, boolean save, boolean cloud)
    {this.controle.createProfileLocal(resultID,uid,weight,height,age,sex, save,cloud,this); // context = this = Mainactivity => for Serialisable
        if(cloud){
            float bfp = this.controle.getBFP();
            float bmi = this.controle.getBMI();
            Date date = new Date();
            mRootUserID = mRootUserBB.child("resultsBB").push();
            mRootUserInfo = mRootUserID.child("user_weight");
            mRootUserInfo.setValue(weight);
            mRootUserInfo = mRootUserID.child("user_height");
            mRootUserInfo.setValue(height);
            mRootUserInfo = mRootUserID.child("date");
            mRootUserInfo.setValue(String.valueOf(date));
            mRootUserInfo = mRootUserID.child("user_age");
            mRootUserInfo.setValue(age);
            mRootUserInfo = mRootUserID.child("user_sex");
            if(sex == 1)
            {
                mRootUserInfo.setValue("man");

            }else mRootUserInfo.setValue("woman");
            mRootUserInfo = mRootUserID.child("user_bmi");
            mRootUserInfo.setValue(bmi);
            mRootUserInfo = mRootUserID.child("user_bfp");
            mRootUserInfo.setValue(bfp);
            mRootUserID_bmi.setValue(bmi);
            mRootUserID_bfp.setValue(bfp);

        }

    }
    public String resultBFP(String sex, float bfp ){
        if ( sex =="woman")
        { // female
            if (bfp < MIN_ESS_W)  return CAT_1;
            if ( bfp> MIN_ESS_W && bfp <=MAX_ESS_W ) return CAT_2;
            if ( bfp> MIN_ATH_W && bfp <= MAX_ATH_W ) return CAT_3;
            if ( bfp> MIN_FIT_W && bfp <=MAX_FIT_W ) return CAT_4;
            if ( bfp> MIN_AVE_W && bfp <=MAX_AVE_W ) return CAT_5;
            if ( bfp> MIN_OBE_W) return CAT_6;
        }
        else {
            //male
            if (bfp < MIN_ESS_M) return CAT_1;
            if ( bfp > MIN_ESS_M && bfp <= MAX_ESS_M ) return CAT_2;
            if ( bfp > MIN_ATH_M && bfp <= MAX_ATH_M ) return CAT_3;
            if ( bfp > MIN_FIT_M && bfp <= MAX_FIT_M ) return CAT_4;
            if ( bfp > MIN_AVE_M && bfp <= MAX_AVE_M ) return CAT_5;
            if ( bfp > MIN_OBE_M) return CAT_6;
        }
        return "something went wrong, couldnt give you details";
    }

    public String msgBB(float bmi, float bfp, String sex){
        String message = resultBFP(sex,bfp);
        String cat_bmi ="";
        String msg_bmi;
        String msg_bfp ="";

        if(bmi<18.5)
        {
            cat_bmi ="You have a high risk of developing problems such as nutritional deficiency and osteoporosis";
        }
        else if(bmi>=18.5 && bmi <23)
        {
            cat_bmi ="You have a low risk of developing heart disease, high blood pressure, stroke, diabetes";
        }
        else if(bmi >=23 && bmi <27.5)
        {
            cat_bmi ="You have a moderate risk of developing heart disease, high blood pressure, stroke, diabetes";
        }
        else if(bmi >= 27.5)
        {
            cat_bmi ="You have a high risk of developing heart disease, high blood pressure, stroke, diabetes!";
        }
        msg_bmi = ("Your BMI is "+ String.format(String.valueOf("%.01f"),bmi)+"."+ cat_bmi);

        // underweight <18.5, normal weight 18.5-25 overweight 25-30  obese >30


        // categorisering
        if (sex == "woman") {
            switch (message) {
                case CAT_1:
                    msg_bfp =("You have a BFP of " + String.format(String.valueOf("%.01f"), bfp) + "%." + " You are in the category " + CAT_1 + ". You have a Body Fat Percentage <" + MIN_ESS_W + "%.");
                    break;
                case CAT_2:
                    msg_bfp =("You have a BFP of " + String.format(String.valueOf("%.01f"), bfp) + "%." + "You are in the category " + CAT_2 + ". You have a Body Fat Percentage between " + MIN_ESS_W + "% -" + MAX_ESS_W + "%.");
                    break;
                case CAT_3:
                    msg_bfp =("You have a BFP of " + String.format(String.valueOf("%.01f"), bfp) + "%." + "You are in the category " + CAT_3 + ". You have a Body Fat Percentage between " + MIN_ATH_W + "% -" + MAX_ATH_W + "%.");
                    break;
                case CAT_4:
                    msg_bfp =("You have a BFP of " + String.format(String.valueOf("%.01f"), bfp) + "%." + "You are in the category " + CAT_4 + ". You have a Body Fat Percentage between " + MIN_FIT_W + "% -" + MAX_FIT_W + "%.");
                    break;
                case CAT_5:
                    msg_bfp =("You have a BFP of " + String.format(String.valueOf("%.01f"), bfp) + "%." + "You are in the category " + CAT_5 + ". You have a Body Fat Percentage between " + MIN_AVE_W + "% -" + MAX_AVE_W + "%.");
                    break;
                case CAT_6:
                    msg_bfp =("You have a BFP of " + String.format(String.valueOf("%.01f"), bfp) + "%." + "You are in the category " + CAT_6 + ". You have a Body Fat Percentage >" + MIN_OBE_W + "%.");
                    break;
            }
        }
        else  {
            switch (message) {
                case CAT_1:
                    msg_bfp =("You have a BFP of " + String.format(String.valueOf("%.01f"), bfp) + "%." + " You are in the category " + CAT_1 + ". You have a Body Fat Percentage <" + MIN_ESS_M + "%.");
                    break;
                case CAT_2:
                    msg_bfp =("You have a BFP of " + String.format(String.valueOf("%.01f"), bfp) + "%." + "You are in the category " + CAT_2 + ". You have a Body Fat Percentage between " + MIN_ESS_M + "% -" + MAX_ESS_M + "%.");
                    break;
                case CAT_3:
                    msg_bfp =("You have a BFP of " + String.format(String.valueOf("%.01f"), bfp) + "%." + "You are in the category " + CAT_3 + ". You have a Body Fat Percentage between " + MIN_ATH_M + "% -" + MAX_ATH_M + "%.");
                    break;
                case CAT_4:
                    msg_bfp =("You have a BFP of " + String.format(String.valueOf("%.01f"), bfp) + "%." + "You are in the category " + CAT_4 + ". You have a Body Fat Percentage between " + MIN_FIT_M + "% -" + MAX_FIT_M + "%.");
                    break;
                case CAT_5:
                    msg_bfp =("You have a BFP of " + String.format(String.valueOf("%.01f"), bfp) + "%." + "You are in the category " + CAT_5 + ". You have a Body Fat Percentage between " + MIN_AVE_M+ "% -" + MAX_AVE_M + "%.");
                    break;
                case CAT_6:
                    msg_bfp =("You have a BFP of " + String.format(String.valueOf("%.01f"), bfp) + "%." + "You are in the category " + CAT_6 + ". You have a Body Fat Percentage >" + MIN_OBE_M + "%.");
                    break;
            }

        }
        return (msg_bmi + msg_bfp);
    }

    private void viewResult(int resultID, int uid, float weight, float height , int age , int sex, boolean save, boolean cloud)
    {
        // via controller > aanmaak profiel en data inhalen
        this.controle.createProfileLocal(resultID,uid,weight,height,age,sex, save,cloud,this); // context = this = Mainactivity => for Serialisable
        float bfp = this.controle.getBFP();
        float bmi = this.controle.getBMI();
        String gender;
        if(sex == 0)
        {
            gender = "woman";
        }else gender ="man";
        String result = msgBB(bmi,  bfp, gender);
        lblResult.setText(result);

    }
    // MVC -> controller check info for MainActivity !

    /**
     * recuperation of profile if serializable
     */
    private void recupProfile()
    {
        if ( controle.getAge() != null ) // zolang 1 niet null zal er iets geserialiseerd zijn
        {
            txtInputWeight.setText(String.valueOf(controle.getWeight()).toString());
            txtInputHeight.setText(String.valueOf(controle.getHeight()).toString());
            txtInputAge.setText(controle.getAge().toString());
            radioWoman.setChecked(true);
            if (controle.getSex() ==1)
            {
                radioMan.setChecked(true);
            }
            ((Button) findViewById(R.id.btnCalculateBMI_BFP_Click)).performClick(); // simulate a click


        }
    }
}
