/**
 * QuestionsDao.java
 * This Data Access Object (DAO) interface defines methods to interact with the "Questions" table
 * in the Room database for handling question entities.
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

@Dao
public interface QuestionsDao {

    /**
     * Inserts a new question into the "Questions" table.
     *
     * @param question The question to insert.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertQuestion(Questions question);

    /**
     * Updates an existing question in the "Questions" table.
     *
     * @param question The question to update.
     */
    @Update
    void updateQuestion(Questions question);

    /**
     * Deletes a specific question from the "Questions" table.
     *
     * @param question The question to delete.
     */
    @Delete
    void deleteQuestion(Questions question);

    /**
     * Retrieves all questions from the "Questions" table as LiveData.
     *
     * @return LiveData containing a list of all questions.
     */
    @Query("SELECT * FROM Questions")
    LiveData<List<Questions>> getAllQuestions();

    /**
     * Retrieves a specific question by its ID from the "Questions" table as LiveData.
     *
     * @param questionsId The ID of the question to retrieve.
     * @return LiveData containing the requested question.
     */
    @Query("SELECT * FROM Questions WHERE questionsId = :questionsId")
    LiveData<Questions> getQuestionById(int questionsId);

    /**
     * Retrieves a random set of questions for a specific theme from the "Questions" table as LiveData.
     * The limit parameter controls the number of questions to retrieve.
     *
     * @param themeId The ID of the theme for which to retrieve questions.
     * @return LiveData containing a list of randomly selected questions for the specified theme.
     */
    @Query("SELECT * FROM Questions WHERE themeId = :themeId ORDER BY RANDOM() LIMIT 7")
    LiveData<List<Questions>> getQuestionsByThemeId(int themeId);

    /**
     * Retrieves the ID of the previous question based on the current question ID from the "Questions" table as LiveData.
     *
     * @param currentQuestionId The ID of the current question.
     * @return LiveData containing the ID of the previous question.
     */
    @Query("SELECT questionsId FROM Questions WHERE questionsId < :currentQuestionId ORDER BY questionsId DESC LIMIT 1")
    LiveData<Integer> getPreviousQuestionId(int currentQuestionId);

    /**
     * Checks if a given question is the last one in the "Questions" table based on its ID.
     *
     * @param questionId The ID of the question to check.
     * @return LiveData representing whether the provided question is the last one.
     */
    @Query("SELECT COUNT(*) FROM Questions WHERE questionsId > :questionId")
    LiveData<Boolean> isLastQuestion(int questionId);

    @Query("DELETE FROM Questions")
    void deleteAllQuestions();
}