package quiz.app.project.dias.dias.model.theme;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ThemeDao {
    @Insert
    void insertTheme(Theme theme);

    @Update
    void updateTheme(Theme theme);

    @Delete
    void deleteTheme(Theme theme);

    @Query("SELECT * FROM Theme")
    List<Theme> getAllThemes();

    @Query("SELECT * FROM Theme WHERE themeId = :themeId")
    Theme getThemeById(int themeId);

    @Query("SELECT * FROM Theme ORDER BY themeName ASC")
    List<Theme> getThemes();
}
