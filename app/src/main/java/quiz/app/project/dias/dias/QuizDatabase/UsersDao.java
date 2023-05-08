package quiz.app.project.dias.dias.QuizDatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UsersDao {

    @Query("SELECT * FROM Users")
    List<Users> getAll();

    @Query("SELECT * FROM Users WHERE userId = :id")
    Users getById(long userId);

    @Insert
    void insert(Users users);

    @Insert
    void insert(List<Users> Users);

    @Update
    void update(Users user);

}
