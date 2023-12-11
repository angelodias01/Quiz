/*
 * AchievementUser.java
 * This class represents the AchievementUser entity for the Room database.
 * It defines the structure of the "AchievementsUser" table with foreign key relationships.
 */

package quiz.app.project.dias.dias.model.achievementsuser;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import quiz.app.project.dias.dias.model.user.User;
import quiz.app.project.dias.dias.model.achievements.Achievements;

/*
 * Entity annotation indicates that this class represents an entity in the Room database.
 * The table name is specified as "AchievementsUser".
 * PrimaryKeys annotation defines the primary keys as "userId" and "achievementId".
 * ForeignKeys annotation defines foreign key relationships with the User and Achievements entities.
 * Indices annotation specifies an index on the "achievementId" column.
 */
@Entity(tableName = "AchievementsUser",
        primaryKeys = {"userId", "achievementId"},
        foreignKeys = {
                @ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "userId"),
                @ForeignKey(entity = Achievements.class, parentColumns = "achievementId", childColumns = "achievementId")
        },
        indices = {@Index("achievementId")})
public class AchievementUser {

    /*
     * The "userId" column represents the foreign key relationship with the User entity.
     */
    @ColumnInfo(name = "userId")
    private int userId;

    /*
     * The "achievementId" column represents the foreign key relationship with the Achievements entity.
     */
    @ColumnInfo(name = "achievementId")
    private int achievementId;

    /*
     * The "dateEarned" column represents the date when the achievement was earned.
     * NonNull annotation specifies that this field cannot be null.
     */
    @NonNull
    @ColumnInfo(name = "dateEarned")
    private long dateEarned;

    /*
     * Constructor for the AchievementUser class.
     * Parameters:
     *   - userId: The ID of the user earning the achievement.
     *   - achievementId: The ID of the earned achievement.
     *   - dateEarned: The date when the achievement was earned.
     */
    public AchievementUser(int userId, int achievementId, @NonNull long dateEarned) {
        this.userId = userId;
        this.achievementId = achievementId;
        this.dateEarned = dateEarned;
    }

    /*
     * Getter method for the "userId" field.
     */
    public int getUserId() {
        return userId;
    }

    /*
     * Setter method for the "userId" field.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /*
     * Getter method for the "achievementId" field.
     */
    public int getAchievementId() {
        return achievementId;
    }

    /*
     * Setter method for the "achievementId" field.
     */
    public void setAchievementId(int achievementId) {
        this.achievementId = achievementId;
    }

    /*
     * Getter method for the "dateEarned" field.
     */
    @NonNull
    public long getDateEarned() {
        return dateEarned;
    }

    /*
     * Setter method for the "dateEarned" field.
     */
    public void setDateEarned(@NonNull long dateEarned) {
        this.dateEarned = dateEarned;
    }
}
