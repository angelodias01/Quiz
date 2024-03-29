/**
 * AchievementRepo.java
 * This class manages data operations related to the achievements, handling both the local database
 * and a remote API using Retrofit.
 */
package quiz.app.project.dias.dias.model.achievements;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import quiz.app.project.dias.dias.R;
import quiz.app.project.dias.dias.model.QuizDatabase;
import quiz.app.project.dias.dias.model.achievements.Achievements;
import quiz.app.project.dias.dias.model.questions.Questions;
import quiz.app.project.dias.dias.model.retrofit.JsonPlaceHolderService;
import quiz.app.project.dias.dias.model.retrofit.RetrofitClient;
import quiz.app.project.dias.dias.model.theme.Themes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AchievementRepo {
    private AchievementsDao achievementsDao;
    private Executor executor = Executors.newSingleThreadExecutor();
    private JsonPlaceHolderService jsonPlaceHolderService;

    /**
     * Constructor for the AchievementRepo class.
     * Initializes the AchievementsDao.
     *
     * @param context The context of the application.
     */
    public AchievementRepo(Context context) {
        this.achievementsDao = QuizDatabase.getInstance(context).getAchievementsDao();
        this.jsonPlaceHolderService = RetrofitClient.getClient().create(JsonPlaceHolderService.class);
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
    public LiveData<List<Achievements>> getAllAchievementsLiveData() {
        return this.achievementsDao.getAllAchievementsLiveData();
    }

    public void fetchAchievements() {
        Call<List<Achievements>> call = jsonPlaceHolderService.getAchievements();
        call.enqueue(new Callback<List<Achievements>>() {
            @Override
            public void onResponse(Call<List<Achievements>> call, Response<List<Achievements>> response) {
                if (response.isSuccessful()) {
                    List<Achievements> achievements = response.body();
                    if (achievements != null && !achievements.isEmpty()) {
                        insertAchievements(achievements);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Achievements>> call, Throwable t) {
                // Handle API failure
            }
        });
    }

    private void insertAchievements(List<Achievements> achievements) {
        executor.execute(() -> {
            achievementsDao.insertAchievements(achievements);
        });
    }

}
