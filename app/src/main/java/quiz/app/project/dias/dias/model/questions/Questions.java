/*
 * Questions.java
 * This class represents the Questions entity for the Room database.
 * It defines the structure of the "Questions" table.
 */

package quiz.app.project.dias.dias.model.questions;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/*
 * Entity annotation indicates that this class represents an entity in the Room database.
 * The table name is specified as "Questions".
 */
@Entity(tableName = "Questions")
public class Questions {

    /*
     * PrimaryKey annotation specifies that the "questionsId" column is the primary key
     * and autoGenerate is set to true to allow automatic generation of IDs.
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "questionsId")
    @NonNull
    private int questionsId;

    /*
     * ColumnInfo annotation specifies the name of the "themeId" column in the table.
     */
    @ColumnInfo(name = "themeId")
    private int themeId;

    /*
     * NonNull annotation specifies that the "questionsText" field cannot be null.
     * ColumnInfo annotation specifies the name of the "questionsText" column in the table.
     */
    @NonNull
    @ColumnInfo(name = "questionsText")
    private String questionsText;

    /*
     * NonNull annotation specifies that the "correctAnswer" field cannot be null.
     * ColumnInfo annotation specifies the name of the "correctAnswer" column in the table.
     */
    @NonNull
    @ColumnInfo(name = "correctAnswer")
    private String correctAnswer;

    /*
     * NonNull annotation specifies that the "wrongAnswer1" field cannot be null.
     * ColumnInfo annotation specifies the name of the "wrongAnswer1" column in the table.
     */
    @NonNull
    @ColumnInfo(name = "wrongAnswer1")
    private String wrongAnswer1;

    /*
     * NonNull annotation specifies that the "wrongAnswer2" field cannot be null.
     * ColumnInfo annotation specifies the name of the "wrongAnswer2" column in the table.
     */
    @NonNull
    @ColumnInfo(name = "wrongAnswer2")
    private String wrongAnswer2;

    /*
     * NonNull annotation specifies that the "wrongAnswer3" field cannot be null.
     * ColumnInfo annotation specifies the name of the "wrongAnswer3" column in the table.
     */
    @NonNull
    @ColumnInfo(name = "wrongAnswer3")
    private String wrongAnswer3;

    /*
     * ColumnInfo annotation specifies the name of the "selectedAnswer" column in the table.
     */
    @ColumnInfo(name = "selectedAnswer")
    private String selectedAnswer;

    /*
     * Constructor for the Questions class.
     * Parameters:
     *   - themeId: The ID of the theme associated with the question.
     *   - questionsText: The text of the question.
     *   - correctAnswer: The correct answer to the question.
     *   - wrongAnswer1: The first wrong answer option.
     *   - wrongAnswer2: The second wrong answer option.
     *   - wrongAnswer3: The third wrong answer option.
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

    /*
     * Getter method for the "themeId" field.
     */
    public int getThemeId() {
        return themeId;
    }

    /*
     * Setter method for the "themeId" field.
     */
    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    /*
     * Getter method for the "questionsId" field.
     */
    public int getQuestionsId() {
        return questionsId;
    }

    /*
     * Setter method for the "questionsId" field.
     */
    public void setQuestionsId(int questionsId) {
        this.questionsId = questionsId;
    }

    /*
     * Getter method for the "questionsText" field.
     */
    @NonNull
    public String getQuestionsText() {
        return questionsText;
    }

    /*
     * Setter method for the "questionsText" field.
     */
    public void setQuestionsText(@NonNull String questionsText) {
        this.questionsText = questionsText;
    }

    /*
     * Getter method for the "correctAnswer" field.
     */
    @NonNull
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /*
     * Setter method for the "correctAnswer" field.
     */
    public void setCorrectAnswer(@NonNull String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    /*
     * Getter method for the "wrongAnswer1" field.
     */
    @NonNull
    public String getWrongAnswer1() {
        return wrongAnswer1;
    }

    /*
     * Setter method for the "wrongAnswer1" field.
     */
    public void setWrongAnswer1(@NonNull String wrongAnswer1) {
        this.wrongAnswer1 = wrongAnswer1;
    }

    /*
     * Getter method for the "wrongAnswer2" field.
     */
    @NonNull
    public String getWrongAnswer2() {
        return wrongAnswer2;
    }

    /*
     * Setter method for the "wrongAnswer2" field.
     */
    public void setWrongAnswer2(@NonNull String wrongAnswer2) {
        this.wrongAnswer2 = wrongAnswer2;
    }

    /*
     * Getter method for the "wrongAnswer3" field.
     */
    @NonNull
    public String getWrongAnswer3() {
        return wrongAnswer3;
    }

    /*
     * Setter method for the "wrongAnswer3" field.
     */
    public void setWrongAnswer3(@NonNull String wrongAnswer3) {
        this.wrongAnswer3 = wrongAnswer3;
    }

    /*
     * Setter method for the "selectedAnswer" field.
     */
    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    /*
     * Getter method for the "selectedAnswer" field.
     */
    public String getSelectedAnswer() {
        return selectedAnswer;
    }
}
