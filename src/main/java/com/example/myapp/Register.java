package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    EditText myName, myEmail, myPassword, myPhone, myWeight, myHeight;
    Button myRegisterBtn;
    TextView myLoginBtn;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;
    RadioButton myFemale, myMale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myName = findViewById(R.id.name);
        myEmail = findViewById(R.id.email);
        myPassword = findViewById(R.id.password);
        myPhone = findViewById(R.id.phone);
        myRegisterBtn = findViewById(R.id.registerBtn);
        myLoginBtn = findViewById(R.id.createText);
        myWeight = findViewById(R.id.weight);
        myFemale = findViewById(R.id.female);
        myMale = findViewById(R.id.male);
        myHeight = findViewById(R.id.height);

        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        myRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = myEmail.getText().toString().trim();
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

                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        } else {
                            Toast.makeText(Register.this, "Something went wrong :/" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        myLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
    }
}
