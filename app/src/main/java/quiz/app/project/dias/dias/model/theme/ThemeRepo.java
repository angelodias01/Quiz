package quiz.app.project.dias.dias.model.theme;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import quiz.app.project.dias.dias.model.QuizDatabase;
import quiz.app.project.dias.dias.model.retrofit.JsonPlaceHolderService;
import quiz.app.project.dias.dias.model.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ThemeRepo {
    private ThemeDao themeDao;
    private Executor executor = Executors.newSingleThreadExecutor();

    private JsonPlaceHolderService jsonPlaceHolderService;

    public ThemeRepo(Context context) {
        this.themeDao = QuizDatabase.getInstance(context).getThemeDao();

        this.jsonPlaceHolderService = RetrofitClient.getClient().create(JsonPlaceHolderService.class);
    }

    public void fetchThemes(Context context) {
        Call<List<Theme>> call = jsonPlaceHolderService.getThemes();
        call.enqueue(new Callback<List<Theme>>() {
            @Override
            public void onResponse(Call<List<Theme>> call, Response<List<Theme>> response) {
                if (response.isSuccessful()) {
                    List<Theme> themes = response.body();

                    // Check if themes is not null and has data
                    if (themes != null && !themes.isEmpty()) {
                        // Insert themes into the database here
                        insertThemes(themes, context);
                    } else {
                        // Handle the case when the response body is empty
                    }
                } else {
                    // Handle the case when the response is not successful
                    Log.e("API", "Unsuccessful response: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Theme>> call, Throwable t) {
                // Handle failure in making the API call
                Log.e("API", "Failed to make API call: " + t.getMessage());
            }
        });
    }

    private void insertThemes(List<Theme> themes, Context context) {
        // Ensure that executor is thread-safe (e.g., Executors.newSingleThreadExecutor())
        executor.execute(() -> {
            for (Theme theme : themes) {
                Theme existingTheme = themeDao.getThemeByIdLiveData(theme.getThemeId()).getValue();
                if (existingTheme == null) {
                    themeDao.insertTheme(theme);
                }
            }
        });
    }

    public LiveData<List<Theme>> getAllThemesLiveData() {
        return this.themeDao.getAllThemesLiveData();
    }

    public void insertTheme(Theme theme) {
        executor.execute(() -> themeDao.insertTheme(theme));
    }

    public void deleteTheme(Theme theme) {
        executor.execute(() -> themeDao.deleteTheme(theme));
    }

    public void updateTheme(Theme theme) {
        executor.execute(() -> themeDao.updateTheme(theme));
    }

    public LiveData<Theme> getThemeByIdLiveData(int themeId) {
        return this.themeDao.getThemeByIdLiveData(themeId);
    }

    public LiveData<List<Theme>> getThemesLiveData() {
        return this.themeDao.getThemesLiveData();
    }
}
