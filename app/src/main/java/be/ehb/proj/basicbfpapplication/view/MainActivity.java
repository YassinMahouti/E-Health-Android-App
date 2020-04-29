package be.ehb.proj.basicbfpapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import be.ehb.proj.basicbfpapplication.R;
import be.ehb.proj.basicbfpapplication.controller.Controller;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

}
//properties
    private EditText txtInputWeight;
    private EditText txtInputHeight;
    private EditText txtInputAge;
    private RadioButton radioMan;
    private RadioButton radioWoman;
    private TextView lblResultBFP;
    private ProgressBar progressBarBFP;
    private Controller controle;
     //link to data >



    /**
     * initialisation of my links to my graphical objects in a separated funtion so it's easyer to read code
     */
    private void init(){
        txtInputWeight =(EditText)  findViewById(R.id.txtInputWeight);
        txtInputHeight =(EditText) findViewById(R.id.txtInputHeight);
        txtInputAge =(EditText) findViewById(R.id.txtInputAge);
        radioMan =(RadioButton) findViewById(R.id.radioMan);
        radioWoman =(RadioButton) findViewById(R.id.radioWoman);
        lblResultBFP = (TextView) findViewById(R.id.lblResultBFP);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBarBFP);
        this.controle = Controller.getInstance(this);
        listenCalculation();
        recupProfile();


    }
    private void listenCalculation(){
        //eventlistener
        ((Button) findViewById(R.id.btnCalculateBFP_Click)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();
                float weight = 0;
                float height = 0;
                int age = 0;
                int sex = 0;

                try {
                    weight = Float.parseFloat(txtInputWeight.getText().toString());
                    height = Float.parseFloat(txtInputHeight.getText().toString());
                    age = Integer.parseInt(txtInputAge.getText().toString());

                } catch (Exception e) {
                    // e.printStackTrace();
                };
                if ( radioMan.isChecked()) {
                    sex = 1;

                }
                if ( weight ==0 || height ==0 || age ==0 )
                {
                    Toast.makeText(MainActivity.this, "Invalid Input !", Toast.LENGTH_SHORT).show();
                } else viewResult( 1,1,weight, height, age, sex);
            }
        });
    }
    //constants : Ess ,
    private final int minEssWoman =10;
    private final int maxEssWoman =14;
    private final int minEssMan =3;
    private final int maxEssMan =5;
    //constants : Ath ,
    private final int minAthWoman =14;
    private final int maxAthWoman =21;
    private final int minAthMan =6;
    private final int maxAthMan =14;
    //constants : Fit ,
    private final int minFitWoman =21;
    private final int maxFitWoman =25;
    private final int minFitMan =14;
    private final int maxFitMan =18;
    //constants : Ave ,
    private final int minAveWoman =25;
    private final int maxAveWoman =32;
    private final int minAveMan =18;
    private final int maxAveMan =25;
    //constants : Obe
    private final int minObeWoman =32;
    private final int minObeMan =25;
    private void viewResult(int resultID, int uid, float weight, float height , int age , int sex){
        // via controller > aanmaak profiel en data inhalen
        this.controle.createProfile(resultID,uid,weight,height,age,sex, this); // context = this = Mainactivity => for Serialisable
        float bfp = this.controle.getBFP();
        String message = this.controle.getMessage();
        // categorisering
        if ( message == " You are UNDER the category 'Essential Fat' you have a BFP Under the "+ minEssWoman +"%.")
        {
            lblResultBFP.setText(message.toString());

        }

        else {
            lblResultBFP.setText("You have a BFP of "+ String.format(String.valueOf("%.01f"),bfp)+ "%");

        }

    }
    // MVC -> controller check info for MainActivity !

    /**
     * recuperation of profile if serializable
     */
    private void recupProfile(){
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
        }
    }
}
