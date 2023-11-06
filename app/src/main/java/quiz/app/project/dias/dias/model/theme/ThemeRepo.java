package quiz.app.project.dias.dias.model.theme;

import android.content.Context;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import quiz.app.project.dias.dias.model.QuizDatabase;

public class ThemeRepo {
    private ThemeDao themeDao;
    private Executor executor = Executors.newSingleThreadExecutor();

    public ThemeRepo(Context context) {
        this.themeDao = QuizDatabase.getInstance(context).getThemeDao();
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
