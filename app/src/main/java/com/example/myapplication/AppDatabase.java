package com.example.myapplication;
// hoort bij de version 1.0
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(version = 1, entities = {Product.class})
public abstract class AppDatabase extends RoomDatabase{

    private static final String DB_NAME = "product.db";
    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context){
        if (instance == null){
            instance = create(context);
        }
        return instance;
    }

    private static AppDatabase create(Context context) {
        return Room.databaseBuilder(
                context,
                AppDatabase.class,
                DB_NAME).allowMainThreadQueries().build();
    }

    public abstract ProductDao getRepoDao();

}
