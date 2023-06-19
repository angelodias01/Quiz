package quiz.app.project.dias.dias.QuizDatabase.QuestionsDB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Questions")
public class Questions {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "questionsId")
    @NonNull
    private int questionsId;

    @NonNull
    @ColumnInfo(name = "questionsText")
    private String questionsText;

    @NonNull
    @ColumnInfo(name = "correctAnswer")
    private String correctAnswer;

    @NonNull
    @ColumnInfo(name = "wrongAnswer1")
    private String wrongAnswer1;

    @NonNull
    @ColumnInfo(name = "wrongAnswer2")
    private String wrongAnswer2;

    @NonNull
    @ColumnInfo(name = "wrongAnswer3")
    private String wrongAnswer3;
}
