/**
 * Questions.java
 * This class represents a question entity in the database.
 * It includes information about the theme, question text, correct answer, and wrong answer options.
 */

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

    /**
     * Constructor for the Questions class.
     *
     * @param themeId       The ID of the theme to which this question belongs.
     * @param questionsText The text of the question.
     * @param correctAnswer The correct answer to the question.
     * @param wrongAnswer1  The first wrong answer option.
     * @param wrongAnswer2  The second wrong answer option.
     * @param wrongAnswer3  The third wrong answer option.
     */
    public Questions(int themeId, @NonNull String questionsText, @NonNull String correctAnswer,
                     @NonNull String wrongAnswer1, @NonNull String wrongAnswer2, @NonNull String wrongAnswer3) {
        this.themeId = themeId;
        this.questionsText = questionsText;
        this.correctAnswer = correctAnswer;
        this.wrongAnswer1 = wrongAnswer1;
        this.wrongAnswer2 = wrongAnswer2;
        this.wrongAnswer3 = wrongAnswer3;
    }
    /**
     * Gets the ID of the theme to which this question belongs.
     *
     * @return The theme ID.
     */
    public int getThemeId() {
        return themeId;
    }

    /**
     * Sets the ID of the theme to which this question belongs.
     *
     * @param themeId The new theme ID.
     */
    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    /**
     * Gets the auto-generated ID of this question.
     *
     * @return The question ID.
     */
    public int getQuestionsId() {
        return questionsId;
    }

    /**
     * Sets the auto-generated ID of this question.
     *
     * @param questionsId The new question ID.
     */
    public void setQuestionsId(int questionsId) {
        this.questionsId = questionsId;
    }

    /**
     * Gets the text of the question.
     *
     * @return The question text.
     */
    @NonNull
    public String getQuestionsText() {
        return questionsText;
    }

    /**
     * Sets the text of the question.
     *
     * @param questionsText The new question text.
     */
    public void setQuestionsText(@NonNull String questionsText) {
        this.questionsText = questionsText;
    }

    /**
     * Gets the correct answer to the question.
     *
     * @return The correct answer.
     */
    @NonNull
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Sets the correct answer to the question.
     *
     * @param correctAnswer The new correct answer.
     */
    public void setCorrectAnswer(@NonNull String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    /**
     * Gets the first wrong answer option.
     *
     * @return The first wrong answer option.
     */
    @NonNull
    public String getWrongAnswer1() {
        return wrongAnswer1;
    }

    /**
     * Sets the first wrong answer option.
     *
     * @param wrongAnswer1 The new first wrong answer option.
     */
    public void setWrongAnswer1(@NonNull String wrongAnswer1) {
        this.wrongAnswer1 = wrongAnswer1;
    }

    /**
     * Gets the second wrong answer option.
     *
     * @return The second wrong answer option.
     */
    @NonNull
    public String getWrongAnswer2() {
        return wrongAnswer2;
    }

    /**
     * Sets the second wrong answer option.
     *
     * @param wrongAnswer2 The new second wrong answer option.
     */
    public void setWrongAnswer2(@NonNull String wrongAnswer2) {
        this.wrongAnswer2 = wrongAnswer2;
    }

    /**
     * Gets the third wrong answer option.
     *
     * @return The third wrong answer option.
     */
    @NonNull
    public String getWrongAnswer3() {
        return wrongAnswer3;
    }

    /**
     * Sets the third wrong answer option.
     *
     * @param wrongAnswer3 The new third wrong answer option.
     */
    public void setWrongAnswer3(@NonNull String wrongAnswer3) {
        this.wrongAnswer3 = wrongAnswer3;
    }

    /**
     * Sets the user-selected answer for the question.
     *
     * @param selectedAnswer The user-selected answer.
     */
    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    /**
     * Gets the user-selected answer for the question.
     *
     * @return The user-selected answer.
     */
    public String getSelectedAnswer() {
        return selectedAnswer;
    }
}
