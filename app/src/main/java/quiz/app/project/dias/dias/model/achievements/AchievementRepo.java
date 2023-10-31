package quiz.app.project.dias.dias.model.achievements;

import android.content.Context;

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
    public void createTask(Achievements achievements) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                achievementsDao.insertAchievement(achievements);
            }
        });
    }

    public void deleteTask(Achievements achievement){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                achievementsDao.deleteAchievement(achievement);
            }
        });
    }
}
