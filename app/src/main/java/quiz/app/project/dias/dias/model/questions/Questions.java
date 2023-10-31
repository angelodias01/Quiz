package quiz.app.project.dias.dias.model.questions;

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

    @ColumnInfo(name = "themeId")
    private int themeId;


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


    @ColumnInfo(name = "selectedAnswer")
    private String selectedAnswer;

    public Questions(int themeId,@NonNull String questionsText, @NonNull String correctAnswer, @NonNull String wrongAnswer1, @NonNull String wrongAnswer2, @NonNull String wrongAnswer3) {
        this.themeId = themeId;
        this.questionsText = questionsText;
        this.correctAnswer = correctAnswer;
        this.wrongAnswer1 = wrongAnswer1;
        this.wrongAnswer2 = wrongAnswer2;
        this.wrongAnswer3 = wrongAnswer3;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public int getQuestionsId() {
        return questionsId;
    }

    public void setQuestionsId(int questionsId) {
        this.questionsId = questionsId;
    }

    @NonNull
    public String getQuestionsText() {
        return questionsText;
    }

    public void setQuestionsText(@NonNull String questionsText) {
        this.questionsText = questionsText;
    }

    @NonNull
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(@NonNull String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @NonNull
    public String getWrongAnswer1() {
        return wrongAnswer1;
    }

    public void setWrongAnswer1(@NonNull String wrongAnswer1) {
        this.wrongAnswer1 = wrongAnswer1;
    }

    @NonNull
    public String getWrongAnswer2() {
        return wrongAnswer2;
    }

    public void setWrongAnswer2(@NonNull String wrongAnswer2) {
        this.wrongAnswer2 = wrongAnswer2;
    }

    @NonNull
    public String getWrongAnswer3() {
        return wrongAnswer3;
    }

    public void setWrongAnswer3(@NonNull String wrongAnswer3) {
        this.wrongAnswer3 = wrongAnswer3;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

}
