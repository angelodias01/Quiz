package quiz.app.project.dias.dias.QuizDatabase.AchievementsDB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Achievements")
public class Achievements {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "achievementsId")
    @NonNull
    private int achievementsId;

    @NonNull
    @ColumnInfo(name = "achievementsText")
    private String achievementsText;

}
