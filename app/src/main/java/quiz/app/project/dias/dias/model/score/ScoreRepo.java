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

    public ScoreRepo(Context context) {
        this.scoreDao = QuizDatabase.getInstance(context).getScoreDao();
    }

    public LiveData<List<Score>> getAllScores() {
        return this.scoreDao.getAllScores();
    }

    public LiveData<Score> getScoreById(int scoreId) {
        return this.scoreDao.getScoreById(scoreId);
    }

    public LiveData<List<Score>> getScoresByUserId(int userId) {
        return this.scoreDao.getScoresByUserId(userId);
    }

    public void insertScore(Score score) {
        executor.execute(() -> {
            scoreDao.insertScore(score);
        });
    }

    public void updateScore(Score score) {
        executor.execute(() -> {
            scoreDao.updateScore(score);
        });
    }

    public void deleteScore(Score score) {
        executor.execute(() -> {
            scoreDao.deleteScore(score);
        });
    }

    public void deleteScoresByUserId(int userId) {
        executor.execute(() -> {
            scoreDao.deleteScoresByUserId(userId);
        });
    }

    public LiveData<Integer> getTotalScoreByUserId(int userId) {
        return this.scoreDao.getTotalScoreByUserId(userId);
    }

    public LiveData<List<Score>> getUserAnswersForQuiz(int userId, int themeId) {
        return this.scoreDao.getUserAnswersForQuiz(userId, themeId);
    }

    public LiveData<Boolean> hasPerfectScore(int userId, int themeId) {
        return this.scoreDao.hasPerfectScore(userId, themeId);
    }

    public LiveData<Boolean> hasZeroScore(int userId, int themeId) {
        return this.scoreDao.hasZeroScore(userId, themeId);
    }

    public LiveData<Integer> getMaxWinningStreak(int userId) {
        return this.scoreDao.getMaxWinningStreak(userId);
    }

    public LiveData<Integer> getAnsweredQuestionCount(int userId) {
        return this.scoreDao.getAnsweredQuestionCount(userId);
    }

    public LiveData<List<Score>> getQuizHistoryByUserId(int userId) {
        return this.scoreDao.getQuizHistoryByUserId(userId);
    }
}
