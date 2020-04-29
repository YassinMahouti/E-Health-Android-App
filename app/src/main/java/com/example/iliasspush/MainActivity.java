package com.example.iliasspush;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Insert;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import java.net.URL;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ForkJoinPool;

import database.dao.UserDAO;

public class MainActivity {

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                UserDAO dao = INSTANCE.wordDao();
                dao.deleteAll();

                User user;
                user = new User(1,"Iliass");
                dao.insert(user);

            });
        }
    };





    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);     //on Vieuw activity_main.xml openen
    }
    private EditText txtUsername;
    private EditText txtAge;
    private Button btnConfirm;

    public void intit(){
        txtUsername = (EditText) findViewById(R.id.txtNameInput);
        txtAge = (EditText) findViewById(R.id.txtAgeInput);

    }*/
}
