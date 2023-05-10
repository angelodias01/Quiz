package quiz.app.project.dias.dias.QuizDatabase.UsersDB;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import quiz.app.project.dias.dias.QuizDatabase.UsersDB.Users;

@Dao
public interface UsersDao {

    @Query("SELECT * FROM Users WHERE email = :email AND password = :password")
    Users getUser(String email, String password);

   @Query("Select * from Users")
    List<Users> getAllUsers();

    @Query("SELECT * FROM Users WHERE userId = :userId")
    Users getUserById(int userId);

    @Query("SELECT * FROM users WHERE email = :email")
    Users getUserByEmail(String email);

    @Query("SELECT * FROM users WHERE password = :password")
    Users getUserByPassword(String password);

}
