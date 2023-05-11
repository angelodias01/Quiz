package quiz.app.project.dias.dias.QuizDatabase.UserDB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User WHERE email = :email AND password = :password")
    User getUserByEmailAndPassword(String email, String password);

    @Query("Select * from User")
    List<User> getAllUsers();

    @Query("SELECT * FROM User WHERE userId = :userId")
    User getUserById(int userId);

    @Query("SELECT * FROM User WHERE email = :email")
    User getUserByEmail(String email);

    @Query("SELECT * FROM User WHERE password = :password")
    User getUserByPassword(String password);

    @Insert
    void insertAll(User... users);
}
