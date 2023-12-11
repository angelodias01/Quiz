/*
 * QuestionsDao.java
 * This interface defines the data access object (DAO) for the Questions entity.
 * It contains methods for performing CRUD operations on the "Questions" table.
 */

package quiz.app.project.dias.dias.model.questions;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/*
 * Dao annotation indicates that this interface is a data access object.
 */
@Dao
public interface QuestionsDao {

    /*
     * Insert method for adding a question to the "Questions" table.
     * If there is a conflict, it ignores the new data.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertQuestion(Questions question);

    /*
     * Update method for modifying an existing question in the "Questions" table.
     */
    @Update
    void updateQuestion(Questions question);

    /*
     * Delete method for removing a question from the "Questions" table.
     */
    @Delete
    void deleteQuestion(Questions question);

    /*
     * Query method to retrieve all questions from the "Questions" table as LiveData.
     */
    @Query("SELECT * FROM Questions")
    LiveData<List<Questions>> getAllQuestions();

    /*
     * Query method to retrieve a question by its ID from the "Questions" table as LiveData.
     * Parameters:
     *   - questionsId: The ID of the question.
     */
    @Query("SELECT * FROM Questions WHERE questionsId = :questionsId")
    LiveData<Questions> getQuestionById(int questionsId);

    /*
     * Query method to retrieve questions by theme ID from the "Questions" table as LiveData.
     * Parameters:
     *   - themeId: The ID of the theme.
     */
    @Query("SELECT * FROM Questions WHERE themeId = :themeId ORDER BY RANDOM() LIMIT 7")
    LiveData<List<Questions>> getQuestionsByThemeId(int themeId);

    /*
     * Query method to retrieve the ID of the previous question from the "Questions" table as LiveData.
     * Parameters:
     *   - currentQuestionId: The ID of the current question.
     */
    @Query("SELECT QuestionsId FROM questions WHERE QuestionsId < :currentQuestionId ORDER BY QuestionsId DESC LIMIT 1")
    LiveData<Integer> getPreviousQuestionId(int currentQuestionId);

    /*
     * Query method to check if a question is the last one in the sequence from the "Questions" table as LiveData.
     * Parameters:
     *   - questionId: The ID of the question.
     */
    @Query("SELECT COUNT(*) FROM questions WHERE QuestionsId > :questionId")
    LiveData<Boolean> isLastQuestion(int questionId);
}
