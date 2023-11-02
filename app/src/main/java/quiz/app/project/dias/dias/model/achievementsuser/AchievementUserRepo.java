package quiz.app.project.dias.dias.model.achievementsuser;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import quiz.app.project.dias.dias.model.QuizDatabase;
import quiz.app.project.dias.dias.model.achievements.Achievements;
import quiz.app.project.dias.dias.model.achievements.AchievementsDao;

public class AchievementUserRepo {
    private AchievementUserDao achievementUserDao;
    private Executor executor = Executors.newSingleThreadExecutor();

    public AchievementUserRepo(Context context) {
        this.achievementUserDao = QuizDatabase.getInstance(context).getAchievementUserDao();
    }

    public LiveData<List<AchievementUser>> getUserAchievements(int achievementUserId) {
        return this.achievementUserDao.getAchievementUsersByUserId(achievementUserId);
    }

    public void createUserAchievements(AchievementUser achievementUser) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                achievementUserDao.insertAchievementUser(achievementUser);
            }
        });
    }

    public void deleteUserAchievements(AchievementUser achievementUser) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                achievementUserDao.deleteAchievementUser(achievementUser);
            }
        });
    }

    public LiveData<List<AchievementUser>> getAllAchievementUsers() {
        return this.achievementUserDao.getAllAchievementUsers();
    }

    public LiveData<List<AchievementUser>> getAchievementUsersByAchievementId(int achievementId) {
        return this.achievementUserDao.getAchievementUsersByAchievementId(achievementId);
    }
    public LiveData<List<AchievementUser>> UserAchievementByUserId(int userId) {
        return this.achievementUserDao.getUserAchievementByUserId(userId);
    }


    public void deleteAchievementsByUserId(int userId) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                achievementUserDao.deleteAchievementsByUserId(userId);
            }
        });
    }

    public boolean getAchievementUsersByUserIdAndAchId(int userId, int achievementId) {
        return this.achievementUserDao.getAchievementUsersByUserIdAndAchId(userId, achievementId);
    }
}