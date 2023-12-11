/*
 * AchievementUserRepo.java
 * This class is responsible for handling data operations related to user achievements.
 * It communicates with the database to perform CRUD operations.
 */

package quiz.app.project.dias.dias.model.achievementsuser;

import android.content.Context;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import quiz.app.project.dias.dias.model.QuizDatabase;

/*
 * AchievementUserRepo class serves as the repository for handling data operations related to user achievements.
 */
public class AchievementUserRepo {
    private AchievementUserDao achievementUserDao;
    private Executor executor = Executors.newSingleThreadExecutor();

    /*
     * Constructor for the AchievementUserRepo class.
     * Initializes the AchievementUserDao.
     * Parameters:
     *   - context: The context of the application.
     */
    public AchievementUserRepo(Context context) {
        this.achievementUserDao = QuizDatabase.getInstance(context).getAchievementUserDao();
    }

    /*
     * Retrieves user achievements from the local database by user ID as LiveData.
     * Parameters:
     *   - achievementUserId: The ID of the user.
     */
    public LiveData<List<AchievementUser>> getUserAchievements(int achievementUserId) {
        return this.achievementUserDao.getAchievementUsersByUserId(achievementUserId);
    }

    /*
     * Retrieves all user achievements from the local database as LiveData.
     */
    public LiveData<List<AchievementUser>> getAllUserAchievements() {
        return this.achievementUserDao.getAllAchievementUsers();
    }

    /*
     * Inserts a single user achievement into the local database.
     * Parameters:
     *   - achievementUser: The user achievement to be inserted.
     */
    public void createUserAchievements(AchievementUser achievementUser) {
        executor.execute(() -> achievementUserDao.insertAchievementUser(achievementUser));
    }

    /*
     * Deletes a single user achievement from the local database.
     * Parameters:
     *   - achievementUser: The user achievement to be deleted.
     */
    public void deleteUserAchievements(AchievementUser achievementUser) {
        executor.execute(() -> achievementUserDao.deleteAchievementUser(achievementUser));
    }

    /*
     * Retrieves all user achievements from the local database as LiveData.
     */
    public LiveData<List<AchievementUser>> getAllAchievementUsers() {
        return this.achievementUserDao.getAllAchievementUsers();
    }

    /*
     * Retrieves user achievements from the local database by achievement ID as LiveData.
     * Parameters:
     *   - achievementId: The ID of the achievement.
     */
    public LiveData<List<AchievementUser>> getAchievementUsersByAchievementId(int achievementId) {
        return this.achievementUserDao.getAchievementUsersByAchievementId(achievementId);
    }

    /*
     * Retrieves user achievements from the local database by user ID as LiveData.
     * Parameters:
     *   - userId: The ID of the user.
     */
    public LiveData<List<AchievementUser>> UserAchievementByUserId(int userId) {
        return this.achievementUserDao.getUserAchievementByUserId(userId);
    }

    /*
     * Deletes all user achievements from the local database by user ID.
     * Parameters:
     *   - userId: The ID of the user.
     */
    public void deleteAchievementsByUserId(int userId) {
        executor.execute(() -> achievementUserDao.deleteAchievementsByUserId(userId));
    }

    /*
     * Checks if a user has earned a specific achievement.
     * Parameters:
     *   - userId: The ID of the user.
     *   - achievementId: The ID of the achievement.
     */
    public boolean getAchievementUsersByUserIdAndAchId(int userId, int achievementId) {
        return this.achievementUserDao.getAchievementUsersByUserIdAndAchId(userId, achievementId);
    }
}
