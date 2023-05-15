package quiz.app.project.dias.dias.QuizDatabase.ScoreDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface ScoreDao {
    @Insert
    void insertAll(Score score);
    @Update
    void updateAll(Score score);
    @Delete
    void deleteAll(Score score);
}
