package quiz.app.project.dias.dias.QuizDatabase.ScoreDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import quiz.app.project.dias.dias.QuizDatabase.UserDB.User;

@Dao
public interface ScoreDao {
    @Query("SELECT * FROM Score")
    List<Score> getAllScores();
    @Query("SELECT * FROM Score WHERE userId = :userId")
    List<Score> getAllScoresById(int userId);
    @Query("DELETE FROM Score WHERE userId = :userId")
    void deleteScoreById(int userId);
    @Query("DELETE FROM Score WHERE scoreId = :scoreId")
    void deleteScoreByScoreId(int scoreId);
    @Insert
    void insertAll(Score score);
    @Update
    void updateAll(Score score);
    @Delete
    void deleteAll(Score score);
}
