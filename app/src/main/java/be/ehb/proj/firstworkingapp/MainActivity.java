package be.ehb.proj.firstworkingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView eds;
     EditText height;
    private Button btn;
    EditText weight;
   private float valueBMI;
   private float lengte; private float gewicht;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         this.btn = (Button) findViewById(R.id.button_BMI);
         this.eds = (TextView) findViewById(R.id.textView2);
        height = (EditText) findViewById(R.id.editText_lengte);
        weight = (EditText) findViewById(R.id.editText_gewicht);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 lengte = Float.valueOf(height.getText().toString());
                 gewicht = Float.valueOf(weight.getText().toString());
                valueBMI = gewicht / (float)(Math.pow((lengte/100),2));
                String result = new Float(valueBMI).toString();
                eds.setText(result);

               // finish();
            }
        });
    }

}
