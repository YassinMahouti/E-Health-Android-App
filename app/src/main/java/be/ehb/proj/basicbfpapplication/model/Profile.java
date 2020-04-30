package be.ehb.proj.basicbfpapplication.model;

import android.renderscript.Sampler;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import java.io.ObjectInputStream;
import java.io.Serializable;

import be.ehb.proj.basicbfpapplication.controller.Controller;
import be.ehb.proj.basicbfpapplication.view.MainActivity;

// i will make profile Serialiaseble so i can have info stored of before
public class Profile implements Serializable {
    // properties
            //database
    private int result_id ;
    private int uid =1 ;
            // nodig voor berekening
    private float weight;
    private float height;
    private int age;
    private int sex;
            // nodig voor resultaat
    private float valueBFP;
    private float valueBMI;
    private String message;

    private boolean save;

    public Profile(int result_id, int uid, float weight, float height, int age, int sex, boolean save)
    {
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.sex = sex;
        this.save = save;
        this.calculateBMI();
        this.calculateBFP();
        this.resultBFP();
    }

    // getters


    public boolean isSave() {
        return save;
    }

    public int getResult_id() {
        return result_id;
    }

    public int getUid() {
        return uid;
    }

    public float getWeight() {
   return weight;
    }

    public float getHeight() {
        return height;
    }

    public int getAge() {
        return age;
    }

    public int getSex() {
        return sex;
    }

    public float getValueBFP() {
        return valueBFP;
    }

    public String getMessage() {  return message; }

    public float calculateBMI()
    {
        float heightInM = (float) height/100;
        return this.valueBMI =  weight / (heightInM*heightInM); // weight in kg
    }
    public void calculateBFP()
    {
        float heightInM = (float) height/100;
        calculateBMI();
        if ( age >= 16)
            this.valueBFP = (float)((1.2*valueBMI) +(0.23*age)-(10.8*sex) -5.4);
        else this.valueBFP = (float)((1.51*valueBMI) -(0.7*age)-(3.6*sex) +1.4);
    }
    public void resultBFP(){
        if ( sex == 0)
        { // female
            if (valueBFP < MainActivity.MIN_ESS_W)  message = MainActivity.CAT_1;
            if ( valueBFP> MainActivity.MIN_ESS_W && valueBFP <=MainActivity.MAX_ESS_W ) message =MainActivity.CAT_2;
            if ( valueBFP> MainActivity.MIN_ATH_W && valueBFP <= MainActivity.MAX_ATH_W ) message = MainActivity.CAT_3;
            if ( valueBFP> MainActivity.MIN_FIT_W && valueBFP <=MainActivity.MAX_FIT_W )message =MainActivity.CAT_4;
            if ( valueBFP> MainActivity.MIN_AVE_W && valueBFP <=MainActivity.MAX_AVE_W )message =MainActivity.CAT_5;
            if ( valueBFP> MainActivity.MIN_OBE_W  )message =MainActivity.CAT_6;
        }
        else {
            //male
            if (valueBFP < MainActivity.MIN_ESS_M)  message = MainActivity.CAT_1;
            if ( valueBFP > MainActivity.MIN_ESS_M && valueBFP <= MainActivity.MAX_ESS_M ) message =MainActivity.CAT_2;
            if ( valueBFP > MainActivity.MIN_ATH_M && valueBFP <= MainActivity.MAX_ATH_M ) message = MainActivity.CAT_3;
            if ( valueBFP > MainActivity.MIN_FIT_M && valueBFP <= MainActivity.MAX_FIT_M )message =MainActivity.CAT_4;
            if ( valueBFP > MainActivity.MIN_AVE_M && valueBFP <= MainActivity.MAX_AVE_M )message =MainActivity.CAT_5;
            if ( valueBFP > MainActivity.MIN_OBE_M  )message =MainActivity.CAT_6;
        }
    }


}
