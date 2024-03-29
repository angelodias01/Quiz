/**
 * ThemeRepo.java
 * This class manages data operations related to themes, handling both the local database
 * and a remote API using Retrofit.
 */

package quiz.app.project.dias.dias.model.theme;

import static android.widget.Toast.LENGTH_SHORT;
import android.content.Context;
import android.widget.Toast;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
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

    /**
     * Constructor for the ThemeRepo class.
     * Initializes the ThemeDao and JsonPlaceHolderService.
     *
     * @param context The application context.
     */
    public ThemeRepo(Context context) {
        this.themeDao = QuizDatabase.getInstance(context).getThemeDao();
        this.jsonPlaceHolderService = RetrofitClient.getClient().create(JsonPlaceHolderService.class);
    }

    /**
     * Fetches themes from a remote API using Retrofit.
     * Handles the API response and inserts themes into the local database.
     */
    public void fetchThemes() {
        Call<List<Themes>> call = jsonPlaceHolderService.getThemes();
        call.enqueue(new Callback<List<Themes>>() {
            @Override
            public void onResponse(Call<List<Themes>> call, Response<List<Themes>> response) {
                if (response.isSuccessful()) {
                    List<Themes> themes = response.body();
                    if (themes != null && !themes.isEmpty()) {
                        insertThemes(themes);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Themes>> call, Throwable t) {
                // Handle API failure
            }
        });
    }

    /**
     * Inserts themes into the local database.
     * Checks for existing themes before insertion.
     *
     * @param themes  List of themes to be inserted.
     */
    private void insertThemes(List<Themes> themes) {
        executor.execute(() -> {
            // Obter todos os temas existentes no banco de dados
            List<Themes> existingThemes = themeDao.getAllThemes();

            // Criar uma lista para armazenar os IDs dos temas presentes na API
            List<Integer> apiThemeIds = new ArrayList<>();

            // Iterar sobre os temas da API
            for (Themes theme : themes) {
                apiThemeIds.add(theme.getThemeId());
            }

            // Iterar sobre os temas existentes no banco de dados
            for (Themes existingTheme : existingThemes) {
                // Verificar se o ID do tema não está presente na lista de IDs dos temas da API
                if (!apiThemeIds.contains(existingTheme.getThemeId())) {
                    // Remover o tema do banco de dados
                    themeDao.deleteTheme(existingTheme);
                }
            }

            // Insere os temas recebidos da API
            for (Themes theme : themes) {
                themeDao.insertTheme(theme);
            }
        });
    }


    /**
     * Retrieves all themes from the local database as LiveData.
     *
     * @return LiveData list of themes.
     */
    public LiveData<List<Themes>> getAllThemesLiveData() {
        return this.themeDao.getAllThemesLiveData();
    }
    public LiveData<Themes> getNewThemeLiveData() {
        return themeDao.getNewThemeLiveData();
    }
    /**
     * Inserts a single theme into the local database.
     *
     * @param theme The theme to be inserted.
     */
    public void insertTheme(Themes theme) {
        executor.execute(() -> themeDao.insertTheme(theme));
    }

    /**
     * Deletes a single theme from the local database.
     *
     * @param theme The theme to be deleted.
     */
    public void deleteTheme(Themes theme) {
        executor.execute(() -> themeDao.deleteTheme(theme));
    }

    /**
     * Updates a single theme in the local database.
     *
     * @param theme The theme to be updated.
     */
    public void updateTheme(Themes theme) {
        executor.execute(() -> themeDao.updateTheme(theme));
    }

    /**
     * Retrieves a specific theme by its ID from the local database as LiveData.
     *
     * @param themeId The ID of the theme to be retrieved.
     * @return LiveData containing the theme.
     */
    public LiveData<Themes> getThemeByIdLiveData(int themeId) {
        return this.themeDao.getThemeByIdLiveData(themeId);
    }

    /**
     * Retrieves all themes from the local database as LiveData.
     *
     * @return LiveData list of themes.
     */
    public LiveData<List<Themes>> getThemesLiveData() {
        return this.themeDao.getThemesLiveData();
    }
}