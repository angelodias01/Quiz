package quiz.app.project.dias.dias.model.user;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User WHERE email = :email AND password = :password")
    LiveData<User> getUserByEmailAndPassword(String email, String password);

    @Query("Select * from User")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT * FROM User WHERE userId = :userId")
    LiveData<User> getUserById(int userId);

    @Query("SELECT * FROM User WHERE email = :email")
    LiveData<User> getUserByEmail(String email);

    @Query("SELECT * FROM User WHERE password = :password")
    LiveData<User> getUserByPassword(String password);

    @Insert
    void insertAll(User... user);

    @Update
    void updateAll(User user);

    @Delete
    void deleteAll(User user);

    @Query("delete from User where userId = :userId")
    void deleteUserById(int userId);

    @Update
    void updateCurrencyAmount(User user);
}
