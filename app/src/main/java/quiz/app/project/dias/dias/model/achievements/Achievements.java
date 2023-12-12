/**
 * Achievements.java
 * Entity class representing achievements stored in the database.
 */

package quiz.app.project.dias.dias.model.achievements;

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

    /**
     * Constructs an Achievements object with the given name and description.
     *
     * @param achievementName The name of the achievement.
     * @param description     The description of the achievement.
     */
    public Achievements(@NonNull String achievementName, String description) {
        this.achievementName = achievementName;
        this.description = description;
    }

    /**
     * Gets the ID of the achievement.
     *
     * @return The ID of the achievement.
     */
    public int getAchievementId() {
        return achievementId;
    }

    /**
     * Sets the ID of the achievement.
     *
     * @param achievementId The ID to set for the achievement.
     */
    public void setAchievementId(int achievementId) {
        this.achievementId = achievementId;
    }

    /**
     * Gets the name of the achievement.
     *
     * @return The name of the achievement.
     */
    @NonNull
    public String getAchievementName() {
        return achievementName;
    }

    /**
     * Sets the name of the achievement.
     *
     * @param achievementName The name to set for the achievement.
     */
    public void setAchievementName(@NonNull String achievementName) {
        this.achievementName = achievementName;
    }

    /**
     * Gets the description of the achievement.
     *
     * @return The description of the achievement.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the achievement.
     *
     * @param description The description to set for the achievement.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
