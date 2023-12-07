package quiz.app.project.dias.dias.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
import quiz.app.project.dias.dias.model.theme.Theme;
import quiz.app.project.dias.dias.model.theme.ThemeRepo;

public class ThemeViewModel extends AndroidViewModel {
    private ThemeRepo repository;
    private LiveData<List<Theme>> themesLiveData;
    public ThemeViewModel(@NonNull Application application) {
        super(application);
        // Initialize repository
        this.repository = new ThemeRepo(application.getApplicationContext());
        themesLiveData = repository.getAllThemesLiveData();
    }


    public LiveData<List<Theme>> getThemesLiveData() {
        return themesLiveData;
    }

    public LiveData<Theme> getThemeById(int themeId) {
        return repository.getThemeByIdLiveData(themeId);
    }

    public LiveData<List<Theme>> getThemes() {
        return repository.getThemesLiveData();
    }

    public void insertTheme(Theme theme) {
        repository.insertTheme(theme);
    }

    public void updateTheme(Theme theme) {
        repository.updateTheme(theme);
    }

    public void deleteTheme(Theme theme) {
        repository.deleteTheme(theme);
    }
}