package quiz.app.project.dias.dias.QuizDatabase.AchievementsUserDB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import quiz.app.project.dias.dias.QuizDatabase.UserDB.User;

@Entity(tableName = "Achievements_User",
 foreignKeys = {
        @ForeignKey(entity = User.class,
        parentColumns = "userId",
        childColumns = "userId",
        onDelete = ForeignKey.CASCADE),

         @ForeignKey(entity = User.class,
                 parentColumns = "achievementsUserId",
                 childColumns = "achievementsUserId",
                 onDelete = ForeignKey.CASCADE)
})
public class Achievements_User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "achievementsUserId")
    @NonNull
    private int achievementsUserId;

    @NonNull
    @ColumnInfo(name = "userId")
    private int userId;

    @NonNull
    @ColumnInfo(name = "achievementsId")
    private int achievementsId;
}
