package be.ehb.proj.basicbfpapplication.controller;

import android.content.Context;

import java.util.Date;

import be.ehb.proj.basicbfpapplication.model.AccesLocal;
import be.ehb.proj.basicbfpapplication.model.Profile;
import be.ehb.proj.basicbfpapplication.tools.Serialiser;

public class Controller { // all static for Serialiser that is static
    private static Profile profile; // nieuw object aanmaken > import nodig
    private static Controller instance = null;
    private static String nameFile = "saveprofile";
    private static AccesLocal localAcces;

    private Controller(){
        super();
    }

    public static final Controller getInstance(Context context){
        if( Controller.instance == null){
            // nog niet gegenereerd => hier kan ik wel een new aanmaken
            Controller.instance = new Controller();
            // recup info db, need context
            localAcces = new AccesLocal(context);
            // call method -> recoverLastProfil
            profile = localAcces.recoverLastProfile();
           // getSerialise(context); // static method -> localStorage
        }
        //anders return bestaande
        return Controller.instance;
    }// zo heb je steeds 1 instance > Singleton

    /**
     * creation of profile and use date from DB -> localAcces
     * @param weight
     * @param height
     * @param age
     * @param sex
     * @param context
     */
    public void createProfile(int resultID, int uid, float weight, float height, int age, int sex, Context context){
        profile = new Profile(resultID, uid,weight, height, age, sex);
        localAcces.addProfile(profile);

      //  Serialiser.serialise(nameFile,profile, context);
    }
    
    public float getBFP(){ // bfp gaan inlezen
        return profile.getValueBFP();
    }
    public String getMessage(){ //messag gaan inlezen
        return profile.getMessage();
    }

    /**
     * Recuperation of Serializble object from the profil
     * @param context
     */
    private static void getSerialise(Context context){
        profile = (Profile) Serialiser.deSerialise(nameFile,context);
    }
    public float getHeight(){
        if(profile == null)
        {
            return Float.parseFloat(null);
        } else {
            return profile.getHeight();
        }
    }
    public float getWeight(){
        if(profile == null)
        {
            return Float.parseFloat(null);
        } else {
            return profile.getWeight();
        }
    }
    public Integer getAge(){
        if(profile == null)
        {
            return null;
        } else {
            return profile.getAge();
        }
    }
    public Integer getSex(){
        if(profile == null)
        {
            return null;
        } else {
            return profile.getSex();
        }
    }
}
