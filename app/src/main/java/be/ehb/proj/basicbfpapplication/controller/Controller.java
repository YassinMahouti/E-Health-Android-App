package be.ehb.proj.basicbfpapplication.controller;

import android.content.Context;
import android.widget.Switch;

import java.util.Date;

import be.ehb.proj.basicbfpapplication.model.AccesLocal;
import be.ehb.proj.basicbfpapplication.model.Profile;
import be.ehb.proj.basicbfpapplication.model.User;
import be.ehb.proj.basicbfpapplication.tools.Serialiser;
import be.ehb.proj.basicbfpapplication.view.BMICalculator;
import be.ehb.proj.basicbfpapplication.view.MainActivity;

public class Controller { // all static for Serialiser that is static
    private static Profile profile; // nieuw object aanmaken > import nodig
    private static Controller instance = null;
    private static String nameFile = "saveprofile";
    private static AccesLocal localAcces;
    private static User user;

    private Controller(){
        super();
    }

    public static final Controller getInstance(Context context){
        if( Controller.instance == null){
            // nog niet gegenereerd => hier kan ik wel een new aanmaken
            Controller.instance = new Controller();
            //To recuperate info from  db we need a Context > context
            localAcces = new AccesLocal(context);
            // call method -> recoverLastProfil
            profile = localAcces.recoverLastProfile();
            user = localAcces.recoverUser();

           /* getSerialise(context); // static method -> localStorage*/
        }
        //anders return bestaande
        return Controller.instance;
    }// zo heb je steeds 1 instance > Singleton




    /**
     * creation of profile with date from SQLite DB -> localAcces
     * @param resultID : This is the ID for each results
     * @param uid : This is the User ID
     * @param weight
     * @param height
     * @param age
     * @param sex
     * @param context need to precise the context
     */
    public void createProfile(int resultID, int uid, float weight, float height, int age, int sex,boolean save, Context context)
    {
        profile = new Profile(resultID, uid,weight, height, age, sex,save);
        if ( save )
        localAcces.addProfile(profile);

      //  Serialiser.serialise(nameFile,profile, context);
    }
    public void createUser( int result_id, int user_id, float height, float weight, boolean save)
    {
        user= new User( result_id, user_id, height, weight , save);
        if ( save )
        localAcces.addBMIresults(user);

    }
    
    public float getBFP()
    { // to recuperate the value of the Body Fat Percentage
        return profile.getValueBFP();
    }

    public String getMessage()
    { //to recuperate the message => the message is for e.g "Fat" or "Normal"
        return profile.getMessage();
    }
    public float getBMI()
    { // to recuperate the value of the Body Fat Percentage
        return profile.calculateBMI();
    }
    public float getBMIOnly()
    { // to recuperate the value of the Body Fat Percentage
        return user.calculateBMI();
    }

    /**
     * Recuperation of Serializble object from the profil
     * @param context:  need a context
     */
    private static void getSerialise(Context context)
    {
        profile = (Profile) Serialiser.deSerialise(nameFile,context);
    }

    /**
     * recuperation of the height => we need to verify if its null (empty)
     * @return  type float
     */
    public float getHeight(){
        if(profile == null)
        {
            return Float.parseFloat(null);
        } else {
            return profile.getHeight();
        }
    }
    public float getHeightBMI(){
        if(user == null)
        {
            return Float.parseFloat(null);
        } else {
            return user.getHeight();
        }
    }

    /**
     * recuperation of the weight => we need to verify if its null (empty)
     * @return  type float
     */
    public float getWeight(){
        if(profile == null)
        {
            return Float.parseFloat(null);
        } else {
            return profile.getWeight();
        }
    }
    public float getWeightBMI(){
        if(user == null)
        {
            return 0;
        } else {
            return user.getWeight();
        }
    }
    public Integer getAge()
    {
        if(profile == null) return null;
        else return profile.getAge();
    }

    public Integer getSex()
    {
        if(profile == null) return null;
        else return profile.getSex();
    }
}
