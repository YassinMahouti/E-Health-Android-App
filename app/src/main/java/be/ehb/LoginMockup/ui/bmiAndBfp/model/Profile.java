package be.ehb.LoginMockup.ui.bmiAndBfp.model;

import android.renderscript.Sampler;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Date;


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
    private boolean cloud;
    private Date dateMeasure;

    public Profile(int result_id, Date dateMeasure, int uid, float weight, float height, int age, int sex, boolean save, boolean cloud)
    {
        this.dateMeasure =dateMeasure;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.sex = sex;
        this.save = save;
        this.cloud = cloud;
        this.calculateBMI();
        this.calculateBFP();
        //this.resultBFP();
    }

    // getters


    public boolean isCloud() {
        return cloud;
    }

    public Date getDateMeasure() {
        return dateMeasure;
    }

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



}
