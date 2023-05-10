package quiz.app.project.dias.dias.QuizDatabase.UsersDB;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UsersDao {

    @Query("SELECT * FROM Users WHERE email = :email AND password = :password")
    Users getUserByEmailAndPassword(String email, String password);

   @Query("Select * from Users")
    List<Users> getAllUsers();

    @Query("SELECT * FROM Users WHERE userId = :userId")
    Users getUserById(int userId);

    @Query("SELECT * FROM Users WHERE email = :email")
    Users getUserByEmail(String email);

    @Query("SELECT * FROM Users WHERE password = :password")
    Users getUserByPassword(String password);

}
