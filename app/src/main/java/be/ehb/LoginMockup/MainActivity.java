package be.ehb.LoginMockup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import be.ehb.Ehealth.R;
import be.ehb.LoginMockup.ui.registratie.RegAct;

public class MainActivity extends AppCompatActivity {

    Button loginButton;
    Button regButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //initialise registration button

        regButton = findViewById(R.id.register);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegAct.class);
                startActivity(intent);
                finish();

            }
        });

        loginButton = findViewById(R.id.loginBtn);




        //go to login screen
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginAct.class);
                startActivity(intent);

            }
        });
    }
}
