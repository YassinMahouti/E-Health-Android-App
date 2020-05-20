package be.ehb.LoginMockup.ui.Calorie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import be.ehb.Ehealth.R;

public class CalorieCustom extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButtonMale;
    RadioButton radioButtonFemale;

    String mOrFResult;
    int age;

    public int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_custom);

        Button btnCalc = (Button) findViewById(R.id.calc);
        EditText weightStr = (EditText) findViewById(R.id.weight);
        EditText heightStr = (EditText) findViewById(R.id.height);
        EditText ageText = (EditText) findViewById(R.id.age);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup2);
        radioButtonMale = (RadioButton) findViewById(R.id.radioMale);
        radioButtonFemale = (RadioButton) findViewById(R.id.radioFemale);


        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// enkelk als waarden ok =>
                int radioMaleOrFemale = radioGroup.getCheckedRadioButtonId();
                radioButtonMale = findViewById(radioMaleOrFemale);
                mOrFResult = (String) radioButtonMale.getText();
                float weight_int;
                float height_int;

                try {
                     weight_int = Float.parseFloat(String.valueOf(weightStr.getText()));
                     height_int = Float.parseFloat(String.valueOf(heightStr.getText()));
                    age = Integer.parseInt(String.valueOf(ageText.getText()));
                    result = bereken(mOrFResult, weight_int, height_int, age);
                    Intent intent = new Intent(CalorieCustom.this, CalResult.class);
                    intent.putExtra("Result",result);
                    startActivity(intent);
                }
                catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(CalorieCustom.this, "Don't leave the fields blank", Toast.LENGTH_SHORT).show();
                }






            }
        });


    }

    public int bereken(String mOrFResult, float weight_int, float height_int, int age) {

        float uitkomst;

        if (mOrFResult.equals("Male")) {
            uitkomst = (float) ((10 * weight_int) + (6.25 * height_int) - (5 * age) + 5);
        } else {
            uitkomst = (float) ((10 * weight_int) + (6.25 * height_int) - (5 * age) - 161);
        }
        return (int) uitkomst;
    }

}
