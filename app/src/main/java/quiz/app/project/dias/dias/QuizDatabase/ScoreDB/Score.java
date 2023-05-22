package quiz.app.project.dias.dias.QuizDatabase.ScoreDB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import quiz.app.project.dias.dias.QuizDatabase.UserDB.User;

@Entity(tableName = "Score", foreignKeys = @ForeignKey(entity=Score.class, parentColumns="userId", childColumns="userId"))
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
    //Foreign key... from users to match score with the user
    private int userid;

    public Score(int scoreId, int score, int userid) {
        this.scoreId = scoreId;
        this.score = score;
        this.userid = userid;
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

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
