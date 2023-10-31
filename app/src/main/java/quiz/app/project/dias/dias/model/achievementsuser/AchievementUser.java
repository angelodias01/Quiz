package quiz.app.project.dias.dias.model.achievementsuser;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import quiz.app.project.dias.dias.model.user.User;
import quiz.app.project.dias.dias.model.achievements.Achievements;

@Entity(tableName = "AchievementsUser",
        primaryKeys = {"userId", "achievementId"},
        foreignKeys = {
                @ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "userId"),
                @ForeignKey(entity = Achievements.class, parentColumns = "achievementId", childColumns = "achievementId")
        },
        indices = {@Index("achievementId")})
public class AchievementUser {
    @ColumnInfo(name = "userId")
    private int userId;

    @ColumnInfo(name = "achievementId")
    private int achievementId;

    @NonNull
    @ColumnInfo(name = "dateEarned")
    private long dateEarned;

    public AchievementUser(int userId, int achievementId, @NonNull long dateEarned) {
        this.userId = userId;
        this.achievementId = achievementId;
        this.dateEarned = dateEarned;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(int achievementId) {
        this.achievementId = achievementId;
    }

    @NonNull
    public long getDateEarned() {
        return dateEarned;
    }

    public void setDateEarned(@NonNull long dateEarned) {
        this.dateEarned = dateEarned;
    }
}