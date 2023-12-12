/**
 * Theme.java
 * This class represents an entity for quiz themes.
 * Each theme has a unique identifier (themeId), a name (themeName), and an abbreviation (themeAbreviation).
 */

package quiz.app.project.dias.dias.model.theme;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Theme")
public class Theme {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "themeId")
    @NonNull
    private int themeId;

    @NonNull
    @ColumnInfo(name = "themeName")
    private String themeName;

    @NonNull
    @ColumnInfo(name = "themeAbreviation")
    private String themeAbreviation;


    /**
     * Constructor for the Theme class.
     *
     * @param themeName         The name of the theme.
     * @param themeAbreviation The abbreviation of the theme.
     */
    public Theme(@NonNull String themeName, @NonNull String themeAbreviation) {
        this.themeName = themeName;
        this.themeAbreviation = themeAbreviation;
    }

    /**
     * Retrieves the unique identifier of the theme.
     *
     * @return The unique identifier (themeId) of the theme.
     */
    public int getThemeId() {
        return themeId;
    }

    /**
     * Sets the unique identifier of the theme.
     *
     * @param themeId The unique identifier to set.
     */
    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    /**
     * Retrieves the name of the theme.
     *
     * @return The name of the theme.
     */
    @NonNull
    public String getThemeName() {
        return themeName;
    }

    /**
     * Sets the name of the theme.
     *
     * @param themeName The name to set.
     */
    public void setThemeName(@NonNull String themeName) {
        this.themeName = themeName;
    }

    /**
     * Retrieves the abbreviation of the theme.
     *
     * @return The abbreviation of the theme.
     */
    @NonNull
    public String getThemeAbreviation() {
        return themeAbreviation;
    }

    /**
     * Sets the abbreviation of the theme.
     *
     * @param themeAbreviation The abbreviation to set.
     */
    public void setThemeAbreviation(@NonNull String themeAbreviation) {
        this.themeAbreviation = themeAbreviation;
    }
}