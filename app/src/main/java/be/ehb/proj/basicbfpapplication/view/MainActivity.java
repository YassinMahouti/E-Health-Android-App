package be.ehb.proj.basicbfpapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import be.ehb.proj.basicbfpapplication.R;
import be.ehb.proj.basicbfpapplication.controller.Controller;

public class MainActivity extends AppCompatActivity
{
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
    private TextView lblResultBFP;
    private TextView txtResultBMI;
    private ProgressBar progressBarBFP;
    private Controller controle;
    private Switch sw_save;
   // public Switch aSwitch;
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
        lblResultBFP = (TextView) findViewById(R.id.lblResultBFP);
        txtResultBMI = (TextView) findViewById(R.id.txtResultBMI);
        sw_save = (Switch) findViewById(R.id.sw_saveResult);
        this.controle = Controller.getInstance(this);
        listenCalculationBFP();
        listenCalculBMI();
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
        setContentView(R.layout.activity_main);
        init();
    }

    private void listenCalculationBFP()
    {
        //EventListener
        ((Button) findViewById(R.id.btnCalculateBFP_Click)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();
                float weight = 0;
                float height = 0;
                int age = 0;
                int sex = 0;
                boolean save = false;

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

                if ( radioMan.isChecked())
                {
                    sex = 1;
                }
                if ( weight ==0 || height ==0 || age ==0 )
                {
                    Toast.makeText(MainActivity.this, "Invalid Input !", Toast.LENGTH_SHORT).show();
                } else viewResultBFP( 1,1,weight, height, age, sex,save);
            }
        });
    }
    private void listenCalculBMI()
    {
        //EventListener
        ((Button) findViewById(R.id.btnCalculateBMI)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                // Toast for a popup e.g here to know if my listener works when I push on it.
                //Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();
                float weight = 0;
                float height = 0;
                int age = 0;
                int sex = 0;
                boolean save = false;

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

                if ( radioMan.isChecked())
                {
                    sex = 1;
                }
                if ( weight ==0 || height ==0 || age ==0 )
                {
                    Toast.makeText(MainActivity.this, "Invalid Input !", Toast.LENGTH_SHORT).show();
                } else viewResultBMI( 1,1,weight, height, age, sex, save);
            }
        });
    }

    private void viewResultBFP(int resultID, int uid, float weight, float height , int age , int sex, boolean save)
    {
        // via controller > aanmaak profiel en data inhalen
        this.controle.createProfile(resultID,uid,weight,height,age,sex, save,this); // context = this = Mainactivity => for Serialisable
        float bfp = this.controle.getBFP();
        String message = this.controle.getMessage();
        // categorisering
        if (sex ==0) {
            switch (message) {
                case CAT_1:
                    lblResultBFP.setText("You have a BFP of " + String.format(String.valueOf("%.01f"), bfp) + "%." + " You are in the category " + CAT_1 + ". You have a Body Fat Percentage <" + MIN_ESS_W + "%.");
                    break;
                case CAT_2:
                    lblResultBFP.setText("You have a BFP of " + String.format(String.valueOf("%.01f"), bfp) + "%." + "You are in the category " + CAT_2 + ". You have a Body Fat Percentage between " + MIN_ESS_W + "% -" + MAX_ESS_W + "%.");
                    break;
                case CAT_3:
                    lblResultBFP.setText("You have a BFP of " + String.format(String.valueOf("%.01f"), bfp) + "%." + "You are in the category " + CAT_3 + ". You have a Body Fat Percentage between " + MIN_ATH_W + "% -" + MAX_ATH_W + "%.");
                    break;
                case CAT_4:
                    lblResultBFP.setText("You have a BFP of " + String.format(String.valueOf("%.01f"), bfp) + "%." + "You are in the category " + CAT_4 + ". You have a Body Fat Percentage between " + MIN_FIT_W + "% -" + MAX_FIT_W + "%.");
                    break;
                case CAT_5:
                    lblResultBFP.setText("You have a BFP of " + String.format(String.valueOf("%.01f"), bfp) + "%." + "You are in the category " + CAT_5 + ". You have a Body Fat Percentage between " + MIN_AVE_W + "% -" + MAX_AVE_W + "%.");
                    break;
                case CAT_6:
                    lblResultBFP.setText("You have a BFP of " + String.format(String.valueOf("%.01f"), bfp) + "%." + "You are in the category " + CAT_6 + ". You have a Body Fat Percentage >" + MIN_OBE_W + "%.");
                    break;
            }
        }
            else  {
                switch (message) {
                    case CAT_1:
                        lblResultBFP.setText("You have a BFP of " + String.format(String.valueOf("%.01f"), bfp) + "%." + " You are in the category " + CAT_1 + ". You have a Body Fat Percentage <" + MIN_ESS_M + "%.");
                        break;
                    case CAT_2:
                        lblResultBFP.setText("You have a BFP of " + String.format(String.valueOf("%.01f"), bfp) + "%." + "You are in the category " + CAT_2 + ". You have a Body Fat Percentage between " + MIN_ESS_M + "% -" + MAX_ESS_M + "%.");
                        break;
                    case CAT_3:
                        lblResultBFP.setText("You have a BFP of " + String.format(String.valueOf("%.01f"), bfp) + "%." + "You are in the category " + CAT_3 + ". You have a Body Fat Percentage between " + MIN_ATH_M + "% -" + MAX_ATH_M + "%.");
                        break;
                    case CAT_4:
                        lblResultBFP.setText("You have a BFP of " + String.format(String.valueOf("%.01f"), bfp) + "%." + "You are in the category " + CAT_4 + ". You have a Body Fat Percentage between " + MIN_FIT_M + "% -" + MAX_FIT_M + "%.");
                        break;
                    case CAT_5:
                        lblResultBFP.setText("You have a BFP of " + String.format(String.valueOf("%.01f"), bfp) + "%." + "You are in the category " + CAT_5 + ". You have a Body Fat Percentage between " + MIN_AVE_M+ "% -" + MAX_AVE_M + "%.");
                        break;
                    case CAT_6:
                        lblResultBFP.setText("You have a BFP of " + String.format(String.valueOf("%.01f"), bfp) + "%." + "You are in the category " + CAT_6 + ". You have a Body Fat Percentage >" + MIN_OBE_M + "%.");
                        break;
                }

        }
    }
    private void viewResultBMI(int resultID, int uid, float weight, float height , int age , int sex, boolean save)
    {
        // via controller > aanmaak profiel en data inhalen
        this.controle.createProfile(resultID,uid,weight,height,age,sex, save, this); // context = this = Mainactivity => for Serialisable
        float bmi = this.controle.getBMI();
        txtResultBMI.setText("Your BMI is "+ String.format(String.valueOf("%.01f"),bmi));
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
            ((Button) findViewById(R.id.btnCalculateBFP_Click)).performClick(); // simulate a click
            ((Button) findViewById(R.id.btnCalculateBMI)).performClick();
        }
    }
}
