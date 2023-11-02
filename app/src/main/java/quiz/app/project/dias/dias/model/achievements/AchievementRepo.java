package quiz.app.project.dias.dias.model.achievements;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import quiz.app.project.dias.dias.model.QuizDatabase;
import quiz.app.project.dias.dias.model.achievements.Achievements;
import quiz.app.project.dias.dias.model.achievements.AchievementsDao;

public class AchievementRepo {
    private AchievementsDao achievementsDao;
    private Executor executor = Executors.newSingleThreadExecutor();

    public AchievementRepo(Context context) {
        this.achievementsDao = QuizDatabase.getInstance(context).getAchievementsDao();
    }
    public LiveData<List<Achievements>> getAchievements(int achievementId) {
        return this.achievementsDao.getAchievementById(achievementId);
    }
    public void createAchievements(Achievements achievements) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                achievementsDao.insertAchievement(achievements);
            }
        });
    }

    public void deleteAchievements(Achievements achievement){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                achievementsDao.deleteAchievement(achievement);
            }
        });
    }
}
