package be.ehb.proj.basicbfpapplication.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Statement;
import java.util.Date;

import be.ehb.proj.basicbfpapplication.tools.MySQLiteOpenHelper;

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
    public void addProfile(Profile profile){
        sqLiteDatabase = accesDB.getWritableDatabase();
        String  req = "insert into profile (weight,height, age, sex) values";
       //concatenation
        req += "("+profile.getWeight()+","+profile.getHeight()+","+profile.getAge()+","+profile.getSex()+")";
        // execute
        sqLiteDatabase.execSQL(req);

    }

    /**
     * recover the last Profile, no param needed
     * @return
     */
    public Profile recoverLastProfile(){
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
        if(!cursor.isAfterLast()){
            // if there is a profile -> recover
            // need to be care with DATE !!! here just a new date
            float weight = (float) cursor.getDouble(0);
            float height = (float) cursor.getDouble(1);
            Integer age = cursor.getInt(2);
            Integer sex = cursor.getInt(3);
            profile = new Profile(weight, height,age,sex);
        }
        cursor.close();
        return profile;
    }
}
