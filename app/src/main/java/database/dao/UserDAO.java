package database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.iliasspush.User;

@Dao
public interface UserDAO {

    @Insert
    void insertUser(User iliass);
}
