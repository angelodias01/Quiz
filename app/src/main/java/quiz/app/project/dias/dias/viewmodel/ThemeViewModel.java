/**
 * ViewModel class for managing Theme data.
 */
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

    /**
     * Constructor for the ThemeViewModel.
     *
     * @param application The application instance.
     */
    public ThemeViewModel(@NonNull Application application) {
        super(application);
        // Initialize repository
        this.repository = new ThemeRepo(application.getApplicationContext());
        themesLiveData = repository.getAllThemesLiveData();
    }

    /**
     * Get LiveData containing a list of themes.
     *
     * @return LiveData<List<Theme>> representing a list of themes.
     */
    public LiveData<List<Theme>> getThemesLiveData() {
        return themesLiveData;
    }

    /**
     * Get LiveData containing a specific theme by its ID.
     *
     * @param themeId ID of the theme to retrieve.
     * @return LiveData<Theme> representing the theme with the given ID.
     */
    public LiveData<Theme> getThemeById(int themeId) {
        return repository.getThemeByIdLiveData(themeId);
    }

    /**
     * Get LiveData containing a list of all themes.
     *
     * @return LiveData<List<Theme>> representing all themes.
     */
    public LiveData<List<Theme>> getThemes() {
        return repository.getThemesLiveData();
    }

    /**
     * Insert a new theme.
     *
     * @param theme Theme object to be inserted.
     */
    public void insertTheme(Theme theme) {
        repository.insertTheme(theme);
    }

    /**
     * Update an existing theme.
     *
     * @param theme Theme object to be updated.
     */
    public void updateTheme(Theme theme) {
        repository.updateTheme(theme);
    }

    /**
     * Delete a theme.
     *
     * @param theme Theme object to be deleted.
     */
    public void deleteTheme(Theme theme) {
        repository.deleteTheme(theme);
    }
}
