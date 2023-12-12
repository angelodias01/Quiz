/**
 * AchievementRepo.java
 * This class manages data operations related to the achievements, handling both the local database
 * and a remote API using Retrofit.
 */
package quiz.app.project.dias.dias.model.achievements;

import android.content.Context;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import quiz.app.project.dias.dias.model.QuizDatabase;

public class AchievementRepo {
    private AchievementsDao achievementsDao;
    private Executor executor = Executors.newSingleThreadExecutor();

    /**
     * Constructor for the AchievementRepo class.
     * Initializes the AchievementsDao.
     *
     * @param context The context of the application.
     */
    public AchievementRepo(Context context) {
        this.achievementsDao = QuizDatabase.getInstance(context).getAchievementsDao();
    }

    /**
     * Retrieves achievements by their ID from the database as LiveData.
     *
     * @param achievementId The ID of the achievement to be retrieved.
     * @return LiveData containing a list of achievements.
     */
    public LiveData<List<Achievements>> getAchievements(int achievementId) {
        return this.achievementsDao.getAchievementById(achievementId);
    }

    /**
     * Retrieves all achievements from the database as LiveData.
     *
     * @return LiveData containing a list of all achievements.
     */
    public LiveData<List<Achievements>> getAllAchievements() {
        return this.achievementsDao.getAllAchievements();
    }

    /**
     * Creates a new achievement in the database.
     *
     * @param achievements The achievement to be created.
     */
    public void createAchievements(Achievements achievements) {
        executor.execute(() -> achievementsDao.insertAchievement(achievements));
    }

    /**
     * Deletes an achievement from the database.
     *
     * @param achievement The achievement to be deleted.
     */
    public void deleteAchievements(Achievements achievement) {
        executor.execute(() -> achievementsDao.deleteAchievement(achievement));
    }
}
