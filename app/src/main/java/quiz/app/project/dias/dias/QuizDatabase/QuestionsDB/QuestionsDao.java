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
}
