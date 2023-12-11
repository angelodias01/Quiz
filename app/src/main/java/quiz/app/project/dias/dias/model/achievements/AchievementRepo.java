/*
 * AchievementRepo.java
 * This class is responsible for handling data operations related to achievements.
 * It communicates with the database to perform CRUD operations.
 */

package quiz.app.project.dias.dias.model.achievements;

import android.content.Context;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import quiz.app.project.dias.dias.model.QuizDatabase;

/*
 * AchievementRepo class serves as the repository for handling data operations related to achievements.
 */
public class AchievementRepo {
    private AchievementsDao achievementsDao;
    private Executor executor = Executors.newSingleThreadExecutor();

    /*
     * Constructor for the AchievementRepo class.
     * Initializes the AchievementsDao.
     * Parameters:
     *   - context: The context of the application.
     */
    public AchievementRepo(Context context) {
        this.achievementsDao = QuizDatabase.getInstance(context).getAchievementsDao();
    }

    /*
     * Retrieves a specific achievement by its ID from the local database as LiveData.
     * Parameters:
     *   - achievementId: The ID of the achievement to be retrieved.
     */
    public LiveData<List<Achievements>> getAchievements(int achievementId) {
        return this.achievementsDao.getAchievementById(achievementId);
    }

    /*
     * Retrieves all achievements from the local database as LiveData.
     */
    public LiveData<List<Achievements>> getAllAchievements() {
        return this.achievementsDao.getAllAchievements();
    }

    /*
     * Inserts a single achievement into the local database.
     * Parameters:
     *   - achievements: The achievement to be inserted.
     */
    public void createAchievements(Achievements achievements) {
        executor.execute(() -> achievementsDao.insertAchievement(achievements));
    }

    /*
     * Deletes a single achievement from the local database.
     * Parameters:
     *   - achievement: The achievement to be deleted.
     */
    public void deleteAchievements(Achievements achievement) {
        executor.execute(() -> achievementsDao.deleteAchievement(achievement));
    }
}
