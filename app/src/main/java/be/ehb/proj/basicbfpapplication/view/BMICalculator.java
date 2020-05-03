package be.ehb.proj.basicbfpapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import be.ehb.proj.basicbfpapplication.R;
import be.ehb.proj.basicbfpapplication.controller.Controller;

public class BMICalculator extends AppCompatActivity {
    private EditText txt_height;
    private EditText txt_weight;
    private TextView lbl_resultBMI;

    private Switch sw_save;
    private Button btn_calculate;
    private RecyclerView rv_bmi;
    private Controller controle;


    private void init()
    {
        setContentView(R.layout.activity_b_m_i_calculator);
        txt_height = (EditText) findViewById(R.id.txt_height);
        txt_weight = (EditText) findViewById(R.id.txt_weight);
        lbl_resultBMI =(TextView) findViewById(R.id.lbl_resultBMI);
        sw_save =(Switch) findViewById(R.id.sw_saveBMI);
        btn_calculate =(Button) findViewById(R.id.btn_calcBMI);
        this.controle = Controller.getInstance(this);
        listenCalculationBMI();
        recupUser();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();



    }
    private void listenCalculationBMI()
    {
        ((Button) findViewById(R.id.btn_calcBMI)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                float weight =0;
                float height =0;
                boolean save =false;
                try {
                    height = Float.parseFloat(txt_height.getText().toString());
                    weight = Float.parseFloat(txt_weight.getText().toString());

                } catch (Exception e) {
                    // e.printStackTrace();
                };
                if (sw_save.isChecked())
                {
                    save = true;
                }
                if ( weight ==0 || height ==0  )
                {
                    Toast.makeText(BMICalculator.this, "Invalid Input !", Toast.LENGTH_SHORT).show();
                }
                else viewResultBMI(1,1,height,weight,save);

            }
        });
    }

    private void viewResultBMI( int result_id , int user_id,float height, float weight , boolean save)
    {
        // via controller > aanmaak profiel en data inhalen
        this.controle.createUser(result_id,user_id,height,weight,save); // context = this = Mainactivity => for Serialisable
        float bmi = this.controle.getBMIOnly();
        lbl_resultBMI.setText("Your BMI is "+ String.format(String.valueOf("%.01f"),bmi));
    }

    private void recupUser()
    {

            txt_weight.setText(String.valueOf(controle.getWeightBMI()).toString());
            txt_height.setText(String.valueOf(controle.getHeightBMI()).toString());
            ((Button) findViewById(R.id.btn_calcBMI)).performClick(); // simulate a click



    }







}
