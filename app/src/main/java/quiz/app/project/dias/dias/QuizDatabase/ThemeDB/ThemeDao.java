package quiz.app.project.dias.dias.QuizDatabase.ThemeDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ThemeDao {

    @Insert
    void insert(Theme theme);
    @Update
    void update(Theme theme);
    @Delete
    void delete(Theme theme);
}
