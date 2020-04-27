package database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.iliasspush.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM user")
    List<User> getAllUsers();

    @Query("SELECT * FROM user WHERE id LIKE :id")
    User findById(int id);

    @Query("SELECT * FROM user WHERE name LIKE :name")
    User findByName(String name);

    @Insert
    void insertUser(User user);

    @Delete
    void delete(User user);
}
