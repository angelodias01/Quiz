package quiz.app.project.dias.dias.model.score;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ScoreDao {
    @Insert
    void insertScore(Score score);

    @Update
    void updateScore(Score score);

    @Delete
    void deleteScore(Score score);

    @Query("SELECT * FROM Score")
    LiveData<List<Score>> getAllScores();

    @Query("SELECT * FROM Score WHERE scoreId = :scoreId")
    LiveData<Score> getScoreById(int scoreId);

    @Query("SELECT * FROM Score WHERE userId = :userId")
    LiveData<List<Score>> getScoresByUserId(int userId);

    @Query("delete from Score where userId = :userId")
    void deleteScoresByUserId(int userId);

    @Query("SELECT SUM(score) FROM Score WHERE userId = :userId")
    LiveData<Integer> getTotalScoreByUserId(int userId);

    @Query("SELECT * FROM Score WHERE userId = :userId and themeId = :themeId")
    LiveData<List<Score>> getUserAnswersForQuiz(int userId, int themeId);

    @Query("SELECT COUNT(*) FROM Score WHERE userId = :userId AND themeId = :themeId AND score = 7")
    LiveData<Boolean> hasPerfectScore(int userId, int themeId);

    @Query("SELECT COUNT(*) FROM Score WHERE userId = :userId AND themeId = :themeId AND score = 0")
    LiveData<Boolean> hasZeroScore(int userId, int themeId);

    @Query("SELECT MAX(winningStreak) FROM (SELECT COUNT(*) AS winningStreak FROM Score WHERE userId = :userId AND score = 7 GROUP BY themeId)")
    LiveData<Integer> getMaxWinningStreak(int userId);

    @Query("SELECT COUNT(DISTINCT score) FROM Score WHERE userId = :userId")
    LiveData<Integer> getAnsweredQuestionCount(int userId);

    @Query("SELECT * FROM Score WHERE userId = :userId ORDER BY date ASC")
    LiveData<List<Score>> getQuizHistoryByUserId(int userId);

    //@Query("SELECT COUNT(*) FROM Score WHERE userId = :userId AND assisted = 1")
    //boolean hasAssistedQuestion(int userId);

    // Additional queries for achievements
    //@Query("SELECT COUNT(*) FROM Score WHERE userId = :userId AND weeklyChallenge = 1")
    //boolean hasWonWeeklyChallenge(int userId);
}
