package be.ehb.LoginMockup.ui.Calorie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import be.ehb.Ehealth.R;

public class CalorieCalc extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_calculator);

        radioGroup = findViewById(R.id.radioGroup);
        Button buttonApply = findViewById(R.id.button_apply);
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                String result;
                radioButton = findViewById(radioId);
                System.out.println(radioButton);
                result = (String) radioButton.getText();
                if (result.equals("Use different credentials")) {
                    Intent intent = new Intent(CalorieCalc.this, CalorieCustom.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(CalorieCalc.this, CalorieOwn.class);
                    startActivity(intent);
                    finish();

                }


            }
        });
    }

    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this, "Selected Radio Button: " + radioButton.getText(),
                Toast.LENGTH_SHORT).show();
    }


}
