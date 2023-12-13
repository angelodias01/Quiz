/**
 * ViewModel class for managing Score data.
 */
package quiz.app.project.dias.dias.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import quiz.app.project.dias.dias.model.score.Score;
import quiz.app.project.dias.dias.model.score.ScoreRepo;

public class ScoreViewModel extends AndroidViewModel {
    private ScoreRepo repository;

    /**
     * Constructor for the ScoreViewModel.
     *
     * @param application The application instance.
     */
    public ScoreViewModel(@NonNull Application application) {
        super(application);
        // Initialize repository
        this.repository = new ScoreRepo(application.getApplicationContext());
    }

    /**
     * Get LiveData containing all scores for a specific user.
     *
     * @param userId The ID of the user for which to retrieve scores.
     * @return LiveData<List < Score>> representing scores for the specified user.
     */
    public LiveData<List<Score>> getAllScoresByUserId(int userId) {
        return repository.getScoresByUserId(userId);
    }

    /**
     * Get LiveData containing scores for a specific user.
     *
     * @param userId The ID of the user for which to retrieve scores.
     * @return LiveData<List < Score>> representing scores for the specified user.
     */
    public LiveData<List<Score>> getScoresByUserId(int userId) {
        return repository.getScoresByUserId(userId);
    }

    /**
     * Get LiveData containing all scores.
     *
     * @param userId The ID of the user for which to retrieve scores.
     * @return LiveData<List < Score>> representing all scores.
     */
    public LiveData<List<Score>> getScores(int userId) {
        return repository.getAllScores();
    }

    /**
     * Delete scores for a specific user.
     *
     * @param userId The ID of the user for which to delete scores.
     */
    public void deleteScoresByUserId(int userId) {
        repository.deleteScoresByUserId(userId);
    }

    /**
     * Get LiveData containing the total score for a specific user.
     *
     * @param userId The ID of the user for which to retrieve the total score.
     * @return LiveData<Integer> representing the total score for the specified user.
     */
    public LiveData<Integer> getTotalScoreByUserId(int userId) {
        return repository.getTotalScoreByUserId(userId);
    }

    /**
     * Get LiveData containing user answers for a specific quiz.
     *
     * @param userId The ID of the user for which to retrieve answers.
     * @param themeId The ID of the theme for which to retrieve answers.
     * @return LiveData<List < Score>> representing user answers for the specified quiz.
     */
    public LiveData<List<Score>> getUserAnswersForQuiz(int userId, int themeId) {
        return repository.getUserAnswersForQuiz(userId, themeId);
    }

    /**
     * Get LiveData indicating whether a user has a perfect score for a specific quiz.
     *
     * @param userId The ID of the user to check.
     * @param themeId The ID of the theme for which to check the perfect score.
     * @return LiveData<Boolean> representing whether the user has a perfect score for the specified quiz.
     */
    public LiveData<Boolean> hasPerfectScore(int userId, int themeId) {
        return repository.hasPerfectScore(userId, themeId);
    }

    /**
     * Get LiveData indicating whether a user has a zero score for a specific quiz.
     *
     * @param userId The ID of the user to check.
     * @param themeId The ID of the theme for which to check the zero score.
     * @return LiveData<Boolean> representing whether the user has a zero score for the specified quiz.
     */
    public LiveData<Boolean> hasZeroScore(int userId, int themeId) {
        return repository.hasZeroScore(userId, themeId);
    }

    /**
     * Get LiveData containing the maximum winning streak for a specific user.
     *
     * @param userId The ID of the user for which to retrieve the maximum winning streak.
     * @return LiveData<Integer> representing the maximum winning streak for the specified user.
     */
    public LiveData<Integer> getMaxWinningStreak(int userId) {
        return repository.getMaxWinningStreak(userId);
    }

    /**
     * Get LiveData containing the count of answered questions for a specific user.
     *
     * @param userId The ID of the user for which to retrieve the count of answered questions.
     * @return LiveData<Integer> representing the count of answered questions for the specified user.
     */
    public LiveData<Integer> getAnsweredQuestionCount(int userId) {
        return repository.getAnsweredQuestionCount(userId);
    }

    /**
     * Get LiveData containing the quiz history for a specific user.
     *
     * @param userId The ID of the user for which to retrieve the quiz history.
     * @return LiveData<List < Score>> representing the quiz history for the specified user.
     */
    public LiveData<List<Score>> getQuizHistoryByUserId(int userId) {
        return repository.getQuizHistoryByUserId(userId);
    }

    /**
     * Insert a new score.
     *
     * @param score The score to insert.
     */
    public void insertScore(Score score) {
        repository.insertScore(score);
    }

    /**
     * Update an existing score.
     *
     * @param score The score to update.
     */
    public void updateScore(Score score) {
        repository.updateScore(score);
    }

    /**
     * Delete a score.
     *
     * @param score The score to delete.
     */
    public void deleteScore(Score score) {
        repository.deleteScore(score);
    }
}