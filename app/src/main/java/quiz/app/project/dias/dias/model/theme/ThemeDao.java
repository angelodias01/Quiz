/**
 * ThemeDao.java
 * This interface defines data access operations (DAO) for interacting with the Theme entity.
 * It includes methods for querying, inserting, updating, and deleting quiz themes.
 */

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

    /**
     * Retrieves a list of all quiz themes from the data source as LiveData.
     *
     * @return LiveData containing a list of all quiz themes.
     */
    @Query("SELECT * FROM Themes")
    LiveData<List<Themes>> getAllThemesLiveData();

    /**
     * Inserts a new quiz theme into the data source.
     *
     * @param theme The quiz theme to insert.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTheme(Themes theme);

    /**
     * Deletes a specific quiz theme from the data source.
     *
     * @param theme The quiz theme to delete.
     */
    @Delete
    void deleteTheme(Themes theme);

    /**
     * Updates an existing quiz theme in the data source.
     *
     * @param theme The quiz theme to update.
     */
    @Update
    void updateTheme(Themes theme);

    /**
     * Retrieves a specific quiz theme by its ID from the data source as LiveData.
     *
     * @param themeId The ID of the quiz theme to be retrieved.
     * @return LiveData containing the quiz theme.
     */
    @Query("SELECT * FROM Themes WHERE themeId = :themeId")
    LiveData<Themes> getThemeByIdLiveData(int themeId);

    /**
     * Retrieves a list of all quiz themes from the data source as LiveData, ordered by theme name.
     *
     * @return LiveData list of quiz themes ordered by theme name.
     */
    @Query("SELECT * FROM Themes ORDER BY themeName ASC")
    LiveData<List<Themes>> getThemesLiveData();
}