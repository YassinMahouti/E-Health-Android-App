package be.ehb.proj.basicbfpapplication.model;

import android.renderscript.Sampler;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Date;

// i will make profile Serialiaseble so i can have info stored of before
public class Profile implements Serializable {

    //constants : Ess ,
    private static final int minEssWoman =10;
    private static final int maxEssWoman =14;
    private static final int minEssMan =3;
    private static final int maxEssMan =5;
    //constants : Ath ,
    private static final int minAthWoman =14;
    private static final int maxAthWoman =21;
    private static final int minAthMan =6;
    private static final int maxAthMan =14;
    //constants : Fit ,
    private static final int minFitWoman =21;
    private static final int maxFitWoman =25;
    private static final int minFitMan =14;
    private static final int maxFitMan =18;
    //constants : Ave ,
    private static final int minAveWoman =25;
    private static final int maxAveWoman =32;
    private static final int minAveMan =18;
    private static final int maxAveMan =25;
    //constants : Obe
    private static final int minObeWoman =32;
    private  static final int minObeMan =25;
    //for SQLite
   // private int id;
   // private Date dateMeasure;
    // properties
    private float weight;
    private float height;
    private int age;
    private int sex;
    private float valueBFP;
    private float valueBMI;

    private String message;

    public Profile(float weight, float height, int age, int sex) {
       // this.id = id;
        //this.dateMeasure = dateMeasure;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.sex = sex;
        this.calculateBMI();
        this.calculateBFP();
        this.resultBFP();
    }



    public float getWeight() {
        return weight;
    }

    public float getHeight() {
        return height;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getSex() {
        return sex;
    }

    public float getValueBFP() {
        return valueBFP;
    }

    public String getMessage() {
        return message;
    }
    public void calculateBMI(){
        float heightInM = (float) height/100;
        this.valueBMI =  weight / (heightInM*heightInM); // weight in KG
    }
   /* public Date getDateMeasure() {
        return dateMeasure;
    }

    public void setDateMeasure(Date dateMeasure) {
        this.dateMeasure = dateMeasure;
    }*/
    private void calculateBFP(){
        float heightInM = (float) height/100;
        calculateBMI();
        if ( age >= 16)
            this.valueBFP = (float)((1.2*valueBMI) +(0.23*age)-(10.8*sex) -5.4);
        else this.valueBFP = (float)((1.51*valueBMI) -(0.7*age)-(3.6*sex) +1.4);
    }
    private void resultBFP(){
        if ( sex == 0)
        { // female
            if (valueBFP < minEssWoman)  message =" You are UNDER the category 'Essential Fat' you have a BFP Under the "+ minEssWoman +"%.";
            if ( valueBFP>= minEssWoman && valueBFP <maxEssWoman ) message ="You are in the category 'Essential Fat' you have a BFP between " +minEssWoman +"% -"+maxEssWoman+"%.";
            if ( valueBFP>= minAthWoman && valueBFP <maxAthWoman ) message = "You are in the category 'Athlete' you have a BFP between " +minAthWoman +"% -"+maxAthWoman+"%.";
            if ( valueBFP>= minFitWoman && valueBFP <maxFitWoman )message ="You are in the category 'Fitness' you have a BFP between " +minFitWoman +"% -"+maxFitWoman+"%.";
            if ( valueBFP>= minAveWoman && valueBFP <maxAveWoman )message ="You are in the category 'Average' you have a BFP between " +minAveWoman +"% -"+maxAveWoman+"%.";
            if ( valueBFP>= minObeWoman  )message ="You are in the category 'Obese' you have a BFP  ABOVE"+ minObeWoman +"%.";
        }
        else {
            //male
            if (valueBFP < minEssMan)  message =" You are UNDER the category 'Essential Fat' you have a BFP Under the "+ minEssMan +"%. Make sure you eat in of because you have an actual BFP of" + valueBFP;
            if ( valueBFP>= minEssMan && valueBFP <maxEssMan ) message =" Your BFP is " + valueBFP + "\n"+"You are in the category 'Essential Fat' you have a BFP between " +minEssMan +"% -"+maxEssMan+"%.";
            if ( valueBFP>= minAthMan && valueBFP <maxAthMan ) message = " Your BFP is " + valueBFP + "\n"+"You are in the category 'Athlete' you have a BFP between " +minAthMan +"% -"+maxAthMan+"%.";
            if ( valueBFP>= minFitWoman && valueBFP <maxFitMan )message =" Your BFP is " + valueBFP + "\n"+"You are in the category 'Fitness' you have a BFP between " +minFitMan +"% -"+maxFitMan+"%.";
            if ( valueBFP>= minAveMan && valueBFP <maxAveMan )message =" Your BFP is " + valueBFP + "\n"+"You are in the category 'Average' you have a BFP between " +minAveMan +"% -"+maxAveMan+"%.";
            if ( valueBFP>= minObeMan  )message =" Your BFP is " + valueBFP + "\n"+"You are in the category 'Obese' you have a BFP  ABOVE"+ minObeMan +"%.";
        }
    }


}
