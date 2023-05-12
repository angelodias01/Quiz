package quiz.app.project.dias.dias.QuizDatabase.ThemeDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface ThemeDao {
    @Insert
    void insertAll(Theme theme);
    @Update
    void updateAll(Theme theme);
    @Delete
    void deleteAll(Theme theme);
}
