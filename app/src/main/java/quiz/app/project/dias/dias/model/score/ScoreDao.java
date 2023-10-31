package quiz.app.project.dias.dias.model.score;

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
    List<Score> getAllScores();

    @Query("SELECT * FROM Score WHERE scoreId = :scoreId")
    Score getScoreById(int scoreId);

    @Query("SELECT * FROM Score WHERE userId = :userId")
    List<Score> getScoresByUserId(int userId);
    @Query("SELECT * FROM Score WHERE userId = :userId")
    List<Score> getScores(int userId);

    @Query("delete from Score where userId = :userId")
    void deleteScoresByUserId(int userId);
    @Query("SELECT SUM(score) FROM Score WHERE userId = :userId")
    int getTotalScoreByUserId(int userId);
    @Query("SELECT * FROM Score WHERE userId = :userId and themeId = :themeId")
    List<Score> getUserAnswersForQuiz(int userId, int themeId);

    @Query("SELECT COUNT(*) FROM Score WHERE userId = :userId AND themeId = :themeId AND score = 7")
    boolean hasPerfectScore(int userId, int themeId);

    @Query("SELECT COUNT(*) FROM Score WHERE userId = :userId AND themeId = :themeId AND score = 0")
    boolean hasZeroScore(int userId, int themeId);

    @Query("SELECT MAX(winningStreak) FROM (SELECT COUNT(*) AS winningStreak FROM Score WHERE userId = :userId AND score = 7 GROUP BY themeId)")
    int getMaxWinningStreak(int userId);

    @Query("SELECT COUNT(DISTINCT score) FROM Score WHERE userId = :userId")
    int getAnsweredQuestionCount(int userId);

    @Query("SELECT * FROM Score WHERE userId = :userId ORDER BY date ASC")
    List<Score> getQuizHistoryByUserId(int userId);

    //@Query("SELECT COUNT(*) FROM Score WHERE userId = :userId AND assisted = 1")
    //boolean hasAssistedQuestion(int userId);

    // Additional queries for achievements
    //@Query("SELECT COUNT(*) FROM Score WHERE userId = :userId AND weeklyChallenge = 1")
    //boolean hasWonWeeklyChallenge(int userId);


}
