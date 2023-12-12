/**
 * ScoreDao.java
 * This Data Access Object (DAO) interface defines methods to interact with the "Score" table
 * in the Room database for handling score entities.
 */

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
    /**
     * Inserts a new score entry into the "Score" table.
     *
     * @param score The score entry to insert.
     */
    @Insert
    void insertScore(Score score);

    /**
     * Updates an existing score entry in the "Score" table.
     *
     * @param score The score entry to update.
     */
    @Update
    void updateScore(Score score);

    /**
     * Deletes a specific score entry from the "Score" table.
     *
     * @param score The score entry to delete.
     */
    @Delete
    void deleteScore(Score score);

    /**
     * Retrieves all score entries from the "Score" table as LiveData.
     *
     * @return LiveData containing a list of all score entries.
     */
    @Query("SELECT * FROM Score")
    LiveData<List<Score>> getAllScores();

    /**
     * Retrieves a specific score entry by its ID from the "Score" table as LiveData.
     *
     * @param scoreId The ID of the score entry to retrieve.
     * @return LiveData containing the requested score entry.
     */
    @Query("SELECT * FROM Score WHERE scoreId = :scoreId")
    LiveData<Score> getScoreById(int scoreId);

    /**
     * Retrieves all score entries for a specific user from the "Score" table as LiveData.
     *
     * @param userId The ID of the user for which to retrieve score entries.
     * @return LiveData containing a list of score entries for the specified user.
     */
    @Query("SELECT * FROM Score WHERE userId = :userId")
    LiveData<List<Score>> getScoresByUserId(int userId);

    /**
     * Deletes all score entries for a specific user from the "Score" table.
     *
     * @param userId The ID of the user for which to delete score entries.
     */
    @Query("delete from Score where userId = :userId")
    void deleteScoresByUserId(int userId);

    /**
     * Retrieves the total score for a specific user from the "Score" table as LiveData.
     *
     * @param userId The ID of the user for which to retrieve the total score.
     * @return LiveData containing the total score for the specified user.
     */
    @Query("SELECT SUM(score) FROM Score WHERE userId = :userId")
    LiveData<Integer> getTotalScoreByUserId(int userId);

    /**
     * Retrieves all score entries for a specific user and theme from the "Score" table as LiveData.
     *
     * @param userId  The ID of the user for which to retrieve score entries.
     * @param themeId The ID of the theme for which to retrieve score entries.
     * @return LiveData containing a list of score entries for the specified user and theme.
     */
    @Query("SELECT * FROM Score WHERE userId = :userId and themeId = :themeId")
    LiveData<List<Score>> getUserAnswersForQuiz(int userId, int themeId);

    /**
     * Checks if a user achieved a perfect score for a specific theme.
     *
     * @param userId  The ID of the user to check.
     * @param themeId The ID of the theme to check.
     * @return LiveData representing whether the user achieved a perfect score for the theme.
     */
    @Query("SELECT COUNT(*) FROM Score WHERE userId = :userId AND themeId = :themeId AND score = 7")
    LiveData<Boolean> hasPerfectScore(int userId, int themeId);

    /**
     * Checks if a user has a zero score for a specific theme.
     *
     * @param userId  The ID of the user to check.
     * @param themeId The ID of the theme to check.
     * @return LiveData representing whether the user has a zero score for the theme.
     */
    @Query("SELECT COUNT(*) FROM Score WHERE userId = :userId AND themeId = :themeId AND score = 0")
    LiveData<Boolean> hasZeroScore(int userId, int themeId);

    /**
     * Retrieves the maximum winning streak for a specific user.
     *
     * @param userId The ID of the user for which to retrieve the maximum winning streak.
     * @return LiveData containing the maximum winning streak for the specified user.
     */
    @Query("SELECT MAX(winningStreak) FROM (SELECT COUNT(*) AS winningStreak FROM Score WHERE userId = :userId AND score = 7 GROUP BY themeId)")
    LiveData<Integer> getMaxWinningStreak(int userId);

    /**
     * Retrieves the count of distinct scores answered by a specific user.
     *
     * @param userId The ID of the user for which to retrieve the count.
     * @return LiveData containing the count of distinct scores answered by the specified user.
     */
    @Query("SELECT COUNT(DISTINCT score) FROM Score WHERE userId = :userId")
    LiveData<Integer> getAnsweredQuestionCount(int userId);

    /**
     * Retrieves the quiz history for a specific user from the "Score" table as LiveData.
     *
     * @param userId The ID of the user for which to retrieve the quiz history.
     * @return LiveData containing a list of score entries representing the quiz history for the specified user.
     */
    @Query("SELECT * FROM Score WHERE userId = :userId ORDER BY date ASC")
    LiveData<List<Score>> getQuizHistoryByUserId(int userId);
}