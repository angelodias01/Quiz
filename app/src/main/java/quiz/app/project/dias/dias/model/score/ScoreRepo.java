/**
 * ScoreRepo.java
 * This class acts as a repository for handling data operations related to scores.
 * It manages interactions between the ViewModel and the underlying data source (Room database).
 */


package quiz.app.project.dias.dias.model.score;

import android.content.Context;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import quiz.app.project.dias.dias.model.QuizDatabase;

public class ScoreRepo {
    private ScoreDao scoreDao;
    private Executor executor = Executors.newSingleThreadExecutor();

    /**
     * Constructor for the ScoreRepo class.
     * Initializes the ScoreDao using the Room database instance.
     *
     * @param context The application context.
     */
    public ScoreRepo(Context context) {
        this.scoreDao = QuizDatabase.getInstance(context).getScoreDao();
    }

    /**
     * Retrieves all scores from the data source as LiveData.
     *
     * @return LiveData list of all scores.
     */
    public LiveData<List<Score>> getAllScores() {
        return this.scoreDao.getAllScores();
    }

    /**
     * Retrieves a specific score by its ID from the data source as LiveData.
     *
     * @param scoreId The ID of the score to retrieve.
     * @return LiveData containing the score.
     */
    public LiveData<Score> getScoreById(int scoreId) {
        return this.scoreDao.getScoreById(scoreId);
    }

    /**
     * Retrieves all scores for a specific user from the data source as LiveData.
     *
     * @param userId The ID of the user for which to retrieve scores.
     * @return LiveData list of scores for the specified user.
     */
    public LiveData<List<Score>> getScoresByUserId(int userId) {
        return this.scoreDao.getScoresByUserId(userId);
    }

    /**
     * Inserts a new score into the data source.
     *
     * @param score The score to insert.
     */
    public void insertScore(Score score) {
        executor.execute(() -> {
            scoreDao.insertScore(score);
        });
    }

    /**
     * Updates an existing score in the data source.
     *
     * @param score The score to update.
     */
    public void updateScore(Score score) {
        executor.execute(() -> {
            scoreDao.updateScore(score);
        });
    }

    /**
     * Deletes a specific score from the data source.
     *
     * @param score The score to delete.
     */
    public void deleteScore(Score score) {
        executor.execute(() -> {
            scoreDao.deleteScore(score);
        });
    }

    /**
     * Deletes all scores for a specific user from the data source.
     *
     * @param userId The ID of the user for which to delete scores.
     */
    public void deleteScoresByUserId(int userId) {
        executor.execute(() -> {
            scoreDao.deleteScoresByUserId(userId);
        });
    }

    /**
     * Retrieves the total score for a specific user from the data source as LiveData.
     *
     * @param userId The ID of the user for which to retrieve the total score.
     * @return LiveData containing the total score for the specified user.
     */
    public LiveData<Integer> getTotalScoreByUserId(int userId) {
        return this.scoreDao.getTotalScoreByUserId(userId);
    }

    /**
     * Retrieves all scores for a specific user and theme from the data source as LiveData.
     *
     * @param userId  The ID of the user for which to retrieve scores.
     * @param themeId The ID of the theme for which to retrieve scores.
     * @return LiveData list of scores for the specified user and theme.
     */
    public LiveData<List<Score>> getUserAnswersForQuiz(int userId, int themeId) {
        return this.scoreDao.getUserAnswersForQuiz(userId, themeId);
    }

    /**
     * Checks if a user achieved a perfect score for a specific theme.
     *
     * @param userId  The ID of the user to check.
     * @param themeId The ID of the theme to check.
     * @return LiveData representing whether the user achieved a perfect score for the theme.
     */
    public LiveData<Boolean> hasPerfectScore(int userId, int themeId) {
        return this.scoreDao.hasPerfectScore(userId, themeId);
    }

    /**
     * Checks if a user has a zero score for a specific theme.
     *
     * @param userId  The ID of the user to check.
     * @param themeId The ID of the theme to check.
     * @return LiveData representing whether the user has a zero score for the theme.
     */
    public LiveData<Boolean> hasZeroScore(int userId, int themeId) {
        return this.scoreDao.hasZeroScore(userId, themeId);
    }

    /**
     * Retrieves the maximum winning streak for a specific user.
     *
     * @param userId The ID of the user for which to retrieve the maximum winning streak.
     * @return LiveData containing the maximum winning streak for the specified user.
     */
    public LiveData<Integer> getMaxWinningStreak(int userId) {
        return this.scoreDao.getMaxWinningStreak(userId);
    }

    /**
     * Retrieves the count of distinct scores answered by a specific user.
     *
     * @param userId The ID of the user for which to retrieve the count.
     * @return LiveData containing the count of distinct scores answered by the specified user.
     */
    public LiveData<Integer> getAnsweredQuestionCount(int userId) {
        return this.scoreDao.getAnsweredQuestionCount(userId);
    }

    /**
     * Retrieves the quiz history for a specific user from the data source as LiveData.
     *
     * @param userId The ID of the user for which to retrieve the quiz history.
     * @return LiveData list of score entries representing the quiz history for the specified user.
     */
    public LiveData<List<Score>> getQuizHistoryByUserId(int userId) {
        return this.scoreDao.getQuizHistoryByUserId(userId);
    }
}