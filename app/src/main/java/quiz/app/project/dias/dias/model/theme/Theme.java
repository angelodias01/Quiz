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


    public Theme (@NonNull String themeName, @NonNull String themeAbreviation) {
        this.themeName = themeName;
        this.themeAbreviation = themeAbreviation;
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

    @NonNull
    public String getThemeAbreviation() {
        return themeAbreviation;
    }

    public void setThemeAbreviation(@NonNull String themeAbreviation) {
        this.themeAbreviation = themeAbreviation;
    }
}
