package quiz.app.project.dias.dias.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
import quiz.app.project.dias.dias.model.achievements.AchievementRepo;
import quiz.app.project.dias.dias.model.achievements.Achievements;

public class AchievementViewModel extends AndroidViewModel {
    private AchievementRepo repository;

    public AchievementViewModel(@NonNull Application application) {
        super(application);
        // Initialize repository
        this.repository = new AchievementRepo(application.getApplicationContext());
    }

    public LiveData<List<Achievements>> getAchievements(int achievementId) {
        return repository.getAchievements(achievementId);
    }

    public void createAchievements(Achievements achievement) {
        this.repository.createAchievements(achievement);
    }

    public void deleteAchievements(Achievements achievement){ this.repository.deleteAchievements(achievement);}

    public LiveData<List<Achievements>> getUserAchievementByUserId(int userId){
        return(this.repository.getAchievements(userId));

    }
}