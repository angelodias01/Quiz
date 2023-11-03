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

    public ScoreViewModel(@NonNull Application application) {
        super(application);
        // Initialize repository
        this.repository = new ScoreRepo(application.getApplicationContext());
    }

    public LiveData<List<Score>> getScoresByUserId(int userId) {
        return repository.getScoresByUserId(userId);
    }

    public LiveData<List<Score>> getScores(int userId) {
        return repository.getAllScores();
    }

    public void deleteScoresByUserId(int userId) {
        repository.deleteScoresByUserId(userId);
    }

    public LiveData<Integer> getTotalScoreByUserId(int userId) {
        return repository.getTotalScoreByUserId(userId);
    }

    public LiveData<List<Score>> getUserAnswersForQuiz(int userId, int themeId) {
        return repository.getUserAnswersForQuiz(userId, themeId);
    }

    public LiveData<Boolean> hasPerfectScore(int userId, int themeId) {
        return repository.hasPerfectScore(userId, themeId);
    }

    public LiveData<Boolean> hasZeroScore(int userId, int themeId) {
        return repository.hasZeroScore(userId, themeId);
    }

    public LiveData<Integer> getMaxWinningStreak(int userId) {
        return repository.getMaxWinningStreak(userId);
    }

    public LiveData<Integer> getAnsweredQuestionCount(int userId) {
        return repository.getAnsweredQuestionCount(userId);
    }

    public LiveData<List<Score>> getQuizHistoryByUserId(int userId) {
        return repository.getQuizHistoryByUserId(userId);
    }

    public void insertScore(Score score) {
        repository.insertScore(score);
    }

    public void updateScore(Score score) {
        repository.updateScore(score);
    }

    public void deleteScore(Score score) {
        repository.deleteScore(score);
    }
}
