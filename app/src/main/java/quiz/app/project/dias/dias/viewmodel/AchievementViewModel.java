/**
 * ViewModel class for managing and coordinating data related to achievements.
 */

package quiz.app.project.dias.dias.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
import quiz.app.project.dias.dias.model.achievements.AchievementRepo;
import quiz.app.project.dias.dias.model.achievements.Achievements;
import quiz.app.project.dias.dias.model.theme.ThemeRepo;
import quiz.app.project.dias.dias.model.theme.Themes;

public class AchievementViewModel extends AndroidViewModel {
    private AchievementRepo repository;

    private LiveData<List<Achievements>> achievementsLiveData;

    /**
     * Constructor for the AchievementViewModel.
     *
     * @param application The application context.
     */
    public AchievementViewModel(@NonNull Application application) {
        super(application);
        // Initialize repository
        this.repository = new AchievementRepo(application.getApplicationContext());
        achievementsLiveData = repository.getAllAchievements();
    }

    /**
     * Get achievements based on the achievementId.
     *
     * @param achievementId The ID of the achievements.
     * @return LiveData containing a list of Achievements objects.
     */
    public LiveData<List<Achievements>> getAchievements(int achievementId) {
        return repository.getAchievements(achievementId);
    }
    public LiveData<List<Achievements>> getAchievementsLiveData() {
        return achievementsLiveData;
    }

    public void fetchAchievements() {
        repository.fetchAchievements();
    }
    /**
     * Get all achievements.
     *
     * @return LiveData containing a list of all Achievements objects.
     */
    public LiveData<List<Achievements>> getAllAchievements() {
        return repository.getAllAchievements();
    }

    /**
     * Create a new achievement.
     *
     * @param achievement The Achievements object to be created.
     */
    public void createAchievements(Achievements achievement) {
        this.repository.createAchievements(achievement);
    }

    /**
     * Delete an achievement.
     *
     * @param achievement The Achievements object to be deleted.
     */
    public void deleteAchievements(Achievements achievement){
        this.repository.deleteAchievements(achievement);
    }

    /**
     * Get user achievements by userId.
     *
     * @param userId The ID of the user.
     * @return LiveData containing a list of Achievements objects.
     */
    public LiveData<List<Achievements>> getUserAchievementByUserId(int userId){
        return(this.repository.getAchievements(userId));
    }
}