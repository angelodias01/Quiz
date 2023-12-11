/*
 * Achievements.java
 * This class represents the Achievements entity for the Room database.
 * It defines the structure of the "Achievements" table.
 */

package quiz.app.project.dias.dias.model.achievements;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/*
 * Entity annotation indicates that this class represents an entity in the Room database.
 * The table name is specified as "Achievements".
 */
@Entity(tableName = "Achievements")
public class Achievements {

    /*
     * PrimaryKey annotation specifies that the "achievementId" column is the primary key
     * and autoGenerate is set to true to allow automatic generation of IDs.
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "achievementId")
    private int achievementId;

    /*
     * NonNull annotation specifies that the "achievementName" field cannot be null.
     * ColumnInfo annotation specifies the name of the column in the table.
     */
    @NonNull
    @ColumnInfo(name = "achievementName")
    private String achievementName;

    /*
     * ColumnInfo annotation specifies the name of the "description" column in the table.
     */
    @ColumnInfo(name = "description")
    private String description;

    /*
     * Constructor for the Achievements class.
     * Parameters:
     *   - achievementName: The name of the achievement.
     *   - description: The description of the achievement.
     */
    public Achievements(@NonNull String achievementName, String description) {
        this.achievementName = achievementName;
        this.description = description;
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
     * Getter method for the "achievementName" field.
     */
    @NonNull
    public String getAchievementName() {
        return achievementName;
    }

    /*
     * Setter method for the "achievementName" field.
     */
    public void setAchievementName(@NonNull String achievementName) {
        this.achievementName = achievementName;
    }

    /*
     * Getter method for the "description" field.
     */
    public String getDescription() {
        return description;
    }

    /*
     * Setter method for the "description" field.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
