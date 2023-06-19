package quiz.app.project.dias.dias.QuizDatabase.AchievementUserDB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import java.util.Date;

import quiz.app.project.dias.dias.QuizDatabase.AchievementsDB.Achievements;
import quiz.app.project.dias.dias.QuizDatabase.UserDB.User;

@Entity(tableName = "AchievementsUser",
        primaryKeys = {"userId", "achievementId"},
        foreignKeys = {
                @ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "userId"),
                @ForeignKey(entity = Achievements.class, parentColumns = "achievementId", childColumns = "achievementId")
        })
public class AchievementUser {
    @ColumnInfo(name = "userId")
    private int userId;

    @ColumnInfo(name = "achievementId")
    private int achievementId;

    @NonNull
    @ColumnInfo(name = "dateEarned")
    private Date dateEarned;

    public AchievementUser(int userId, int achievementId, @NonNull Date dateEarned) {
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
    public Date getDateEarned() {
        return dateEarned;
    }

    public void setDateEarned(@NonNull Date dateEarned) {
        this.dateEarned = dateEarned;
    }
}