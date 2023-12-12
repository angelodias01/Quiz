/**
 * AchievementUser.java
 * This class represents the association between users and achievements in the database.
 * It includes foreign keys linking to the User and Achievements tables.
 */

package quiz.app.project.dias.dias.model.achievementsuser;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import quiz.app.project.dias.dias.model.user.User;
import quiz.app.project.dias.dias.model.achievements.Achievements;

@Entity(
        tableName = "AchievementsUser",
        primaryKeys = {"userId", "achievementId"},
        foreignKeys = {
                @ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "userId"),
                @ForeignKey(entity = Achievements.class, parentColumns = "achievementId", childColumns = "achievementId")
        },
        indices = {@Index("achievementId")}
)
public class AchievementUser {

    @ColumnInfo(name = "userId")
    private int userId;

    @ColumnInfo(name = "achievementId")
    private int achievementId;

    @NonNull
    @ColumnInfo(name = "dateEarned")
    private long dateEarned;

    /**
     * Constructor for the AchievementUser class.
     *
     * @param userId       The ID of the user earning the achievement.
     * @param achievementId The ID of the achievement earned.
     * @param dateEarned   The date when the achievement was earned.
     */
    public AchievementUser(int userId, int achievementId, @NonNull long dateEarned) {
        this.userId = userId;
        this.achievementId = achievementId;
        this.dateEarned = dateEarned;
    }

    /**
     * Gets the user ID associated with this achievement.
     *
     * @return The user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID associated with this achievement.
     *
     * @param userId The new user ID.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the achievement ID associated with this record.
     *
     * @return The achievement ID.
     */
    public int getAchievementId() {
        return achievementId;
    }

    /**
     * Sets the achievement ID associated with this record.
     *
     * @param achievementId The new achievement ID.
     */
    public void setAchievementId(int achievementId) {
        this.achievementId = achievementId;
    }

    /**
     * Gets the date when the achievement was earned.
     *
     * @return The date when the achievement was earned.
     */
    @NonNull
    public long getDateEarned() {
        return dateEarned;
    }

    /**
     * Sets the date when the achievement was earned.
     *
     * @param dateEarned The new date when the achievement was earned.
     */
    public void setDateEarned(@NonNull long dateEarned) {
        this.dateEarned = dateEarned;
    }
}