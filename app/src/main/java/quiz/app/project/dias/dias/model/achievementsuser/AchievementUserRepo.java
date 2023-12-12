/**
 * AchievementsUserRepo.java
 * This class manages data operations related to the user achievements, handling both the local database
 * and a remote API using Retrofit.
 */
package quiz.app.project.dias.dias.model.achievementsuser;

import android.content.Context;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import quiz.app.project.dias.dias.model.QuizDatabase;

public class AchievementUserRepo {
    private AchievementUserDao achievementUserDao;
    private Executor executor = Executors.newSingleThreadExecutor();

    /**
     * Constructor for the AchievementUserRepo class.
     * Initializes the AchievementUserDao and Executor.
     * Parameters:
     *   - context: The context of the application.
     */
    public AchievementUserRepo(Context context) {
        this.achievementUserDao = QuizDatabase.getInstance(context).getAchievementUserDao();
    }

    /**
     * Retrieves user achievements by user ID.
     * Parameters:
     *   - achievementUserId: The ID of the user whose achievements are to be retrieved.
     * Returns:
     *   - LiveData<List<AchievementUser>>: A list of user achievements as LiveData.
     */
    public LiveData<List<AchievementUser>> getUserAchievements(int achievementUserId) {
        return this.achievementUserDao.getAchievementUsersByUserId(achievementUserId);
    }

    /**
     * Retrieves all user achievements.
     * Returns:
     *   - LiveData<List<AchievementUser>>: A list of all user achievements as LiveData.
     */
    public LiveData<List<AchievementUser>> getAllUserAchievements() {
        return this.achievementUserDao.getAllAchievementUsers();
    }

    /**
     * Creates a new user achievement.
     * Parameters:
     *   - achievementUser: The AchievementUser object representing the user achievement.
     */
    public void createUserAchievements(AchievementUser achievementUser) {
        executor.execute(() -> achievementUserDao.insertAchievementUser(achievementUser));
    }

    /**
     * Deletes a user achievement.
     * Parameters:
     *   - achievementUser: The AchievementUser object representing the user achievement to be deleted.
     */
    public void deleteUserAchievements(AchievementUser achievementUser) {
        executor.execute(() -> achievementUserDao.deleteAchievementUser(achievementUser));
    }

    /**
     * Retrieves all user achievements.
     * Returns:
     *   - LiveData<List<AchievementUser>>: A list of all user achievements as LiveData.
     */
    public LiveData<List<AchievementUser>> getAllAchievementUsers() {
        return this.achievementUserDao.getAllAchievementUsers();
    }

    /**
     * Retrieves user achievements by achievement ID.
     * Parameters:
     *   - achievementId: The ID of the achievement for which user achievements are to be retrieved.
     * Returns:
     *   - LiveData<List<AchievementUser>>: A list of user achievements for the specified achievement ID as LiveData.
     */
    public LiveData<List<AchievementUser>> getAchievementUsersByAchievementId(int achievementId) {
        return this.achievementUserDao.getAchievementUsersByAchievementId(achievementId);
    }

    /**
     * Retrieves user achievements by user ID.
     * Parameters:
     *   - userId: The ID of the user for which achievements are to be retrieved.
     * Returns:
     *   - LiveData<List<AchievementUser>>: A list of user achievements for the specified user ID as LiveData.
     */
    public LiveData<List<AchievementUser>> UserAchievementByUserId(int userId) {
        return this.achievementUserDao.getUserAchievementByUserId(userId);
    }

    /**
     * Deletes user achievements by user ID.
     * Parameters:
     *   - userId: The ID of the user for which achievements are to be deleted.
     */
    public void deleteAchievementsByUserId(int userId) {
        executor.execute(() -> achievementUserDao.deleteAchievementsByUserId(userId));
    }

    /**
     * Checks if a user has a specific achievement.
     * Parameters:
     *   - userId: The ID of the user.
     *   - achievementId: The ID of the achievement.
     * Returns:
     *   - boolean: True if the user has the achievement, false otherwise.
     */
    public boolean getAchievementUsersByUserIdAndAchId(int userId, int achievementId) {
        return this.achievementUserDao.getAchievementUsersByUserIdAndAchId(userId, achievementId);
    }
}
