package quiz.app.project.dias.dias.QuizDatabase.AchievementsDB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Achievements")
public class Achievements {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "achievementId")
    private int achievementId;

    @NonNull
    @ColumnInfo(name = "achievementName")
    private String achievementName;

    @ColumnInfo(name = "description")
    private String description;

    public Achievements(@NonNull String achievementName, String description) {
        this.achievementName = achievementName;
        this.description = description;
    }

    public int getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(int achievementId) {
        this.achievementId = achievementId;
    }

    @NonNull
    public String getAchievementName() {
        return achievementName;
    }

    public void setAchievementName(@NonNull String achievementName) {
        this.achievementName = achievementName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
