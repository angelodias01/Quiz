package quiz.app.project.dias.dias.QuizDatabase.QuestionsDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QuestionsDao {
    @Insert
    void insertQuestion(Questions question);

    @Update
    void updateQuestion(Questions question);

    @Delete
    void deleteQuestion(Questions question);

    @Query("SELECT * FROM Questions")
    List<Questions> getAllQuestions();

    @Query("SELECT * FROM Questions WHERE questionsId = :questionsId")
    Questions getQuestionById(int questionsId);
    @Query("SELECT * FROM Questions WHERE themeId = :themeId")
    List<Questions> getQuestionsByThemeId(int themeId);

    @Query("SELECT QuestionsId FROM questions WHERE QuestionsId < :currentQuestionId ORDER BY QuestionsId DESC LIMIT 1")
    int getPreviousQuestionId(int currentQuestionId);

    @Query("SELECT COUNT(*) FROM questions WHERE QuestionsId > :questionId") boolean isLastQuestion(int questionId);
}
