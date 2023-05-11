package quiz.app.project.dias.dias.QuizDatabase.ThemeDB;

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

    public Theme(int themeId, @NonNull String themeName) {
        this.themeId = themeId;
        this.themeName = themeName;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    @NonNull
    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(@NonNull String themeName) {
        this.themeName = themeName;
    }
}
