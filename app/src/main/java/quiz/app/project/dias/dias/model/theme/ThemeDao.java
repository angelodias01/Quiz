package quiz.app.project.dias.dias.model.theme;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface ThemeDao {
    @Query("SELECT * FROM Theme")
    LiveData<List<Theme>> getAllThemesLiveData();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTheme(Theme theme);


    @Delete
    void deleteTheme(Theme theme);

    @Update
    void updateTheme(Theme theme);

    @Query("SELECT * FROM Theme WHERE themeId = :themeId")
    LiveData<Theme> getThemeByIdLiveData(int themeId);

    @Query("SELECT * FROM Theme ORDER BY themeName ASC")
    LiveData<List<Theme>> getThemesLiveData();
}
