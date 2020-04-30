package be.ehb.proj.basicbfpapplication.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Switch;

import java.sql.Statement;
import java.util.Date;

import be.ehb.proj.basicbfpapplication.tools.MySQLiteOpenHelper;
import be.ehb.proj.basicbfpapplication.view.MainActivity;

public class AccesLocal {
    //properties
    private String nameBase ="bdEhealth.sqlite";
    private Integer versionBase =1;
    private MySQLiteOpenHelper accesDB;
    // to read or write in db
    private SQLiteDatabase sqLiteDatabase;

    /**
     * Constructor
     * @param context is needed for MySQLiteOpenhelper
     *
     */
    public AccesLocal(Context context){
        accesDB = new MySQLiteOpenHelper(context,nameBase, null , versionBase);
    }

    /**
     * Add a profile in Database
     * @param profile
     */
    public void addProfile(Profile profile)
    {
        sqLiteDatabase = accesDB.getWritableDatabase();
        //statement
        String  req = "insert into profile (uid,weight,height, age, sex, resultBMI , resultBFP) values";
       //concatenation
        req += "("+profile.getUid()+","+profile.getWeight()+","+profile.getHeight()+","+profile.getAge()+","+profile.getSex()+","+profile.calculateBMI()+","+profile.getValueBFP()+")";
        // execute
        sqLiteDatabase.execSQL(req);
    }

    /**
     * recover the last Profile, no param needed
     * @return
     */
    public Profile recoverLastProfile()
    {
        //read
        sqLiteDatabase = accesDB.getReadableDatabase();
        //make local profile -> null
        Profile profile = null;
        // query for recover Profile
        String req = "select * from profile ";
        // need a cursor bcs we use a type select
        Cursor cursor = sqLiteDatabase.rawQuery(req,null);
        // to have last profile
        cursor.moveToLast();
        // check if there is a last profile
        if(!cursor.isAfterLast())
        {
            // if there is a profile -> recover
            // need to be care with DATE !!! here just a new date
            int resultId = (int) cursor.getInt(0);
            int uid = (int) cursor.getInt(1);
            float weight = (float) cursor.getDouble(2);
            float height = (float) cursor.getDouble(3);
            Integer age = cursor.getInt(4);
            Integer sex = cursor.getInt(5);
            profile = new Profile(resultId, 1, weight, height,age,sex,true);
        }
        cursor.close();
        return profile;
    }
}
