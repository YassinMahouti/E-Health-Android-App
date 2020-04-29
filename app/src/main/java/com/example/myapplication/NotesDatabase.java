package com.example.myapplication;
// hoort bij de version 1.1 met viewnotes
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(version = 1, entities = {Product.class})
public abstract class NotesDatabase extends RoomDatabase {

    private static final String DB_NAME = "product.db";
    private static NotesDatabase instance;

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static NotesDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(
                    context,
                    NotesDatabase.class,
                    DB_NAME).build();
        }
        return instance;
    }

    public abstract ProductDao ProductDao();
}
