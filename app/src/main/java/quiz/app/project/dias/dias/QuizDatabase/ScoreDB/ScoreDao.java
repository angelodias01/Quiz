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
    @Insert
    void insertScore(Score score);

    @Update
    void updateScore(Score score);

    @Delete
    void deleteScore(Score score);

    @Query("SELECT * FROM Score")
    List<Score> getAllScores();

    @Query("SELECT * FROM Score WHERE scoreId = :scoreId")
    Score getScoreById(int scoreId);

    @Query("SELECT * FROM Score WHERE userId = :userId")
    List<Score> getScoresByUserId(int userId);
    @Query("SELECT * FROM Score WHERE userId = :userId")
    List<Score> getScores(int userId);

    @Query("delete from Score where userId = :userId")
    void deleteScoresByUserId(int userId);
}
