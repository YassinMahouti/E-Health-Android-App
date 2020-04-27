package database.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.iliasspush.User;

@Database(version = 1,entities = {User.class},exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

}
