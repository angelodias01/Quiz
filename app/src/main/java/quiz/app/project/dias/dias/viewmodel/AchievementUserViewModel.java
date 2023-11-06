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

    public AchievementUserViewModel(@NonNull Application application) {
        super(application);
        // Initialize repository
        this.repository = new AchievementUserRepo(application.getApplicationContext());
    }
    public LiveData<List<AchievementUser>> getUserAchievements(int achievementUserId) {
        return repository.getUserAchievements(achievementUserId);
    }
    public LiveData<List<AchievementUser>> getUserAchievementByUserId(int userId) {
        return this.repository.UserAchievementByUserId(userId);
    }
    public LiveData<List<AchievementUser>> getAllUserAchievements() {
        return repository.getAllUserAchievements();
    }
    public void createAchievements(AchievementUser achievementUser) {
        this.repository.createUserAchievements(achievementUser);
    }

    public void deleteAchievements(AchievementUser achievementUser){
        this.repository.deleteUserAchievements(achievementUser);
    }
    public void deleteAchievementsByUserId(int userId){
        this.repository.deleteAchievementsByUserId(userId);
    }
}