/**
 * Score.java
 * This class represents a user's score for a specific theme in the quiz game.
 * It includes information about the score value, user ID, theme ID, and the date the score was achieved.
 */

package quiz.app.project.dias.dias.model.score;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import quiz.app.project.dias.dias.model.theme.Theme;
import quiz.app.project.dias.dias.model.user.User;

@Entity(
        tableName = "Score",
        foreignKeys = {
                @ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "userId"),
                @ForeignKey(entity = Theme.class, parentColumns = "themeId", childColumns = "themeId", onDelete = ForeignKey.CASCADE)
        },
        indices = {
                @Index("userId"),
                @Index("themeId")
        }
)
public class Score {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "scoreId")
    @NonNull
    private int scoreId;

    @NonNull
    @ColumnInfo(name = "score")
    private int score;

    @NonNull
    @ColumnInfo(name = "userId")
    private int userId;

    @ColumnInfo(name = "themeId")
    private int themeId;

    private long date;

    public Score(int score, int userId, int themeId, long date) {
        this.score = score;
        this.userId = userId;
        this.themeId = themeId;
        this.date = date;
    }

    /**
     * Gets the ID of the score entry.
     *
     * @return The score ID.
     */
    public int getScoreId() {
        return scoreId;
    }

    /**
     * Sets the ID of the score entry.
     *
     * @param scoreId The new score ID.
     */
    public void setScoreId(int scoreId) {
        this.scoreId = scoreId;
    }

    /**
     * Gets the score value.
     *
     * @return The score value.
     */
    @NonNull
    public int getScore() {
        return score;
    }

    /**
     * Sets the score value.
     *
     * @param score The new score value.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Gets the ID of the user associated with the score.
     *
     * @return The user ID.
     */
    @NonNull
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user associated with the score.
     *
     * @param userId The new user ID.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the ID of the theme for which the score was achieved.
     *
     * @return The theme ID.
     */
    public int getThemeId() {
        return themeId;
    }

    /**
     * Sets the ID of the theme for which the score was achieved.
     *
     * @param themeId The new theme ID.
     */
    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    /**
     * Gets the date the score was achieved.
     *
     * @return The date.
     */
    public long getDate() {
        return date;
    }

    /**
     * Sets the date the score was achieved.
     *
     * @param date The new date.
     */
    public void setDate(long date) {
        this.date = date;
    }
}
