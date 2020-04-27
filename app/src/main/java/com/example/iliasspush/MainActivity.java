package com.example.iliasspush;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Insert;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import java.net.URL;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);     //on Vieuw activity_main.xml openen
    }
    private EditText txtUsername;
    private EditText txtAge;
    private Button btnConfirm;

    





    public void intit(){
        txtUsername = (EditText) findViewById(R.id.txtNameInput);
        txtAge = (EditText) findViewById(R.id.txtAgeInput);

    }
}
