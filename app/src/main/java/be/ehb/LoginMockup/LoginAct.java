package be.ehb.LoginMockup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import be.ehb.Ehealth.R;
import be.ehb.LoginMockup.ui.registratie.RegAct;

public class LoginAct extends AppCompatActivity {

    ImageView backButton;
    Button login;
    EditText myEmail, myPassword;
    Button myLoginBtn;
    TextView myCreateBtn;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        myEmail = findViewById(R.id.emailTxt);
        myPassword = findViewById(R.id.psswdTxt);
        progressBar = findViewById(R.id.progressBar);
        firebaseAuth = FirebaseAuth.getInstance();
        myLoginBtn = findViewById(R.id.loginBtn);
        myCreateBtn = findViewById(R.id.createText);

        myLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = myEmail.getText().toString().trim(); // convert type object to string
                String password = myPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    myEmail.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    myPassword.setError("Password is required");
                    return;
                }

                if (password.length() < 6) {
                    myPassword.setError("Password must be greater than 6 characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginAct.this, "Logged in", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),NavDrawerAct.class));
                        } else {
                            Toast.makeText(LoginAct.this, "Invalid e-mail or password. Try again." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });





        backButton = findViewById(R.id.backBtn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginAct.this,NavDrawerAct.class);
                startActivity(intent);
                //gewoon finish hier om terug te gaan
                //finish();
            }
        });

        myCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(LoginAct.this, RegAct.class);
                startActivity(intent);

            }
        });
    }
}

