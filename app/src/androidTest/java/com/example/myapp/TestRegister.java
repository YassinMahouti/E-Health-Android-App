package com.example.myapp;

import android.app.Instrumentation;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class TestRegister {
    private Register mRegister;
    private Button btn_register_user;


    @Rule
    public ActivityTestRule<Register> RegisterTestRule = new ActivityTestRule<Register>(Register.class);
    @Before
    public void setUp() throws Exception{
        //---Setup for the activity: Register
        mRegister =RegisterTestRule.getActivity();

    }
    @Before
    public void setUpButtons(){
        //---Setup for the RadioButton: (find by Id) in MainActivity-----------------
        View btn_register_user = mRegister.findViewById(R.id.registerBtn);

    }

}
