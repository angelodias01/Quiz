/*
 * ThemeRepo.java
 * This class is responsible for handling data operations related to themes.
 * It communicates with the database and a remote API using Retrofit.
 */

package quiz.app.project.dias.dias.model.theme;

import static android.widget.Toast.LENGTH_SHORT;
import android.content.Context;
import android.widget.Toast;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import quiz.app.project.dias.dias.R;
import quiz.app.project.dias.dias.model.QuizDatabase;
import quiz.app.project.dias.dias.model.retrofit.JsonPlaceHolderService;
import quiz.app.project.dias.dias.model.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemeRepo {
    private ThemeDao themeDao;
    private Executor executor = Executors.newSingleThreadExecutor();
    private JsonPlaceHolderService jsonPlaceHolderService;

    /*
     * Constructor for the ThemeRepo class.
     * Initializes the ThemeDao and JsonPlaceHolderService.
     * Parameters:
     *   - context: The context of the application.
     */
    public ThemeRepo(Context context) {
        this.themeDao = QuizDatabase.getInstance(context).getThemeDao();
        this.jsonPlaceHolderService = RetrofitClient.getClient().create(JsonPlaceHolderService.class);
    }

    /*
     * Fetches themes from a remote API using Retrofit.
     * Handles the API response and inserts themes into the local database.
     * Parameters:
     *   - context: The context of the application.
     * Implementation for Requirement 2.
     */
    public void fetchThemes(Context context) {
        Call<List<Theme>> call = jsonPlaceHolderService.getThemes();
        call.enqueue(new Callback<List<Theme>>() {
            @Override
            public void onResponse(Call<List<Theme>> call, Response<List<Theme>> response) {
                if (response.isSuccessful()) {
                    List<Theme> themes = response.body();

                    if (themes != null && !themes.isEmpty()) {
                        insertThemes(themes, context);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Theme>> call, Throwable t) {
                // Display an error message to the user in case of API failure.
                Toast.makeText(context, R.string.Error, LENGTH_SHORT).show();
            }
        });
    }

    /*
     * Inserts themes into the local database.
     * Checks for existing themes before insertion.
     * Parameters:
     *   - themes: List of themes to be inserted.
     *   - context: The context of the application.
     * Implementation for Requirement 3.
     */
    private void insertThemes(List<Theme> themes, Context context) {
        executor.execute(() -> {
            for (Theme theme : themes) {
                Theme existingTheme = themeDao.getThemeByIdLiveData(theme.getThemeId()).getValue();
                if (existingTheme == null) {
                    themeDao.insertTheme(theme);
                }
            }
        });
    }

    /*
     * Retrieves all themes from the local database as LiveData.
     * Implementation for Requirement 4.
     */
    public LiveData<List<Theme>> getAllThemesLiveData() {
        return this.themeDao.getAllThemesLiveData();
    }

    /*
     * Inserts a single theme into the local database.
     * Parameters:
     *   - theme: The theme to be inserted.
     * Implementation for Requirement 5.
     */
    public void insertTheme(Theme theme) {
        executor.execute(() -> themeDao.insertTheme(theme));
    }

    /*
     * Deletes a single theme from the local database.
     * Parameters:
     *   - theme: The theme to be deleted.
     * Implementation for Requirement 6.
     */
    public void deleteTheme(Theme theme) {
        executor.execute(() -> themeDao.deleteTheme(theme));
    }

    /*
     * Updates a single theme in the local database.
     * Parameters:
     *   - theme: The theme to be updated.
     */
    public void updateTheme(Theme theme) {
        executor.execute(() -> themeDao.updateTheme(theme));
    }

    /*
     * Retrieves a specific theme by its ID from the local database as LiveData.
     * Parameters:
     *   - themeId: The ID of the theme to be retrieved.
     */
    public LiveData<Theme> getThemeByIdLiveData(int themeId) {
        return this.themeDao.getThemeByIdLiveData(themeId);
    }

    /*
     * Retrieves all themes from the local database as LiveData.
     */
    public LiveData<List<Theme>> getThemesLiveData() {
        return this.themeDao.getThemesLiveData();
    }
}
