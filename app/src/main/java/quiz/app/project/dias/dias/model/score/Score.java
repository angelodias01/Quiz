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

    public int getScoreId() {
        return scoreId;
    }

    public void setScoreId(int scoreId) {
        this.scoreId = scoreId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}