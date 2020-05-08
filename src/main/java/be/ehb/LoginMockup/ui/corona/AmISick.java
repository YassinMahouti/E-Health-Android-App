package be.ehb.LoginMockup.ui.corona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import be.ehb.Ehealth.R;

public class AmISick extends AppCompatActivity {

    private Button testBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.am_i_sick);



        testBtn = (Button) findViewById(R.id.link);


        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AmISick.this, "Corona", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AmISick.this, CoronaMain.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
