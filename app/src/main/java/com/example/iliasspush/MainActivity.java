package com.example.iliasspush;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.iliasspush.database.dao.UserDatabase;

public class MainActivity extends AppCompatActivity {
    private EditText txtUsername, txtAge;
    private Button btnConfirm;
    User user = new User(1, "Iliass");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        txtUsername = (EditText) findViewById(R.id.txtNameInput);
        txtAge = (EditText) findViewById(R.id.txtAgeInput);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Task().execute();
            }
        });

    }
}
    class Task extends AsyncTask<Void, Void, Void> {
    }








    /*private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
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
    };*/


