package database.dao;

import android.app.Application;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.iliasspush.User;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(version = 1,entities = {User.class},exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    private static String DB_NAME = "SqliteTestDatabase.db";
    private static UserDatabase instance;

    public static UserDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static UserDatabase create (final Context context){
        return Room.databaseBuilder(
                context,
                UserDatabase.class,
                DB_NAME).allowMainThreadQueries().build();

    }



    public abstract UserDAO getRepoDao();

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

}
