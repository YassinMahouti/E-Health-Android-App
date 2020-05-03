package be.ehb.proj.basicbfpapplication.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;



public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "profile";
    private static  final String COL0 ="uid";
    private static  final String COL1 ="weight";
    private static  final String COL2 ="height";
    private static  final String COL3 ="age";
    private static  final String COL4 ="sex";
    private static  final String COL5 ="resultBMI";
    private static  final String COL6 ="resultBFP";

// properties :  REAL => floating point value : 8byte = > double
    private String creation = "CREATE TABLE profile ( resultID INTEGER PRIMARY KEY AUTOINCREMENT , "
            +" UID INTEGER NOT NULL ,"
        + "weight REAL NOT NULL,"
        + "height REAL NOT NULL,"
        + "age INTEGER NOT NULL,"
        + "sex INTEGER NOT NULL , "
        + "resultBMI REAL NOT NULL , "
        + "resultBFP REAL NOT NULL);";

    //user (user_id, result_id, height, weight , result_bmi)

    private String cTableBmi = "CREATE TABLE bmiresult ( result_id INTEGER PRIMARY KEY AUTOINCREMENT ,"
            +" user_id INTEGER NOT NULL ,"
            +"height REAL NOT NULL ,"
            +"weight REAL NOT NULL ,"
            +"result_bmi REAL NOT NULL);";

    /**
     * Constructor
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public MySQLiteOpenHelper( Context context,  String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //need 2abstract methods

    /**
     *onCreate -> if databses change
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
      //onCreate execute SQL defined in creation

        db.execSQL(creation);
        db.execSQL(cTableBmi);

    }

    /**
     * if version change
     * @param db
     *
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE profile");
       this.onCreate(db);
       db.execSQL("DROP TABLE bmiresult");
       this.onCreate(db);
    }
}
