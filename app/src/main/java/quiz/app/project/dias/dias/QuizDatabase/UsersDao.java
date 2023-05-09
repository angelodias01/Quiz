package quiz.app.project.dias.dias.QuizDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UsersDao {

    @Query("SELECT * FROM Users WHERE email = :email AND Password = :password")
    Users getUser(String email, String password);

   @Query("Select * from Users")
    List<Users> getAllUsers();
}
