/**
 * ViewModel class for managing and coordinating data related to user achievements.
 */

package quiz.app.project.dias.dias.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
import quiz.app.project.dias.dias.model.achievementsuser.AchievementUser;
import quiz.app.project.dias.dias.model.achievementsuser.AchievementUserRepo;

public class AchievementUserViewModel extends AndroidViewModel {
    private AchievementUserRepo repository;

    /**
     * Constructor for the AchievementUserViewModel.
     *
     * @param application The application context.
     */
    public AchievementUserViewModel(@NonNull Application application) {
        super(application);
        // Initialize repository
        this.repository = new AchievementUserRepo(application.getApplicationContext());
    }

    /**
     * Get user achievements based on the achievementUserId.
     *
     * @param achievementUserId The ID of the user achievements.
     * @return LiveData containing a list of AchievementUser objects.
     */
    public LiveData<List<AchievementUser>> getUserAchievements(int achievementUserId) {
        return repository.getUserAchievements(achievementUserId);
    }

    /**
     * Get user achievements based on the userId.
     *
     * @param userId The ID of the user.
     * @return LiveData containing a list of AchievementUser objects.
     */
    public LiveData<List<AchievementUser>> getUserAchievementByUserId(int userId) {
        return repository.UserAchievementByUserId(userId);
    }

    /**
     * Get all user achievements.
     *
     * @return LiveData containing a list of all AchievementUser objects.
     */
    public LiveData<List<AchievementUser>> getAllUserAchievements() {
        return repository.getAllUserAchievements();
    }

    /**
     * Create a new user achievement.
     *
     * @param achievementUser The AchievementUser object to be created.
     */
    public void createAchievements(AchievementUser achievementUser) {
        this.repository.createUserAchievements(achievementUser);
    }

    /**
     * Delete a user achievement.
     *
     * @param achievementUser The AchievementUser object to be deleted.
     */
    public void deleteAchievements(AchievementUser achievementUser){
        this.repository.deleteUserAchievements(achievementUser);
    }

    /**
     * Delete all user achievements by userId.
     *
     * @param userId The ID of the user.
     */
    public void deleteAchievementsByUserId(int userId){
        this.repository.deleteAchievementsByUserId(userId);
    }
}