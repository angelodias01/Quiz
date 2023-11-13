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
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertQuestion(Questions question);

    @Update
    void updateQuestion(Questions question);

    @Delete
    void deleteQuestion(Questions question);

    @Query("SELECT * FROM Questions")
    LiveData<List<Questions>> getAllQuestions();

    @Query("SELECT * FROM Questions WHERE questionsId = :questionsId")
    LiveData<Questions> getQuestionById(int questionsId);

    @Query("SELECT * FROM Questions WHERE themeId = :themeId ORDER BY RANDOM() LIMIT 7")
    LiveData<List<Questions>> getQuestionsByThemeId(int themeId);

    @Query("SELECT QuestionsId FROM questions WHERE QuestionsId < :currentQuestionId ORDER BY QuestionsId DESC LIMIT 1")
    LiveData<Integer> getPreviousQuestionId(int currentQuestionId);

    @Query("SELECT COUNT(*) FROM questions WHERE QuestionsId > :questionId")
    LiveData<Boolean> isLastQuestion(int questionId);
}