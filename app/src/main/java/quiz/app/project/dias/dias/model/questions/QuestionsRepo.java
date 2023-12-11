/*
 * QuestionsRepo.java
 * This class is responsible for handling data operations related to questions.
 * It serves as an intermediary between the ViewModel and the QuestionsDao.
 */

package quiz.app.project.dias.dias.model.questions;

import android.content.Context;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import quiz.app.project.dias.dias.model.QuizDatabase;

/*
 * QuestionsRepo class serves as the repository for handling data operations related to questions.
 */
public class QuestionsRepo {
    private QuestionsDao questionsDao;
    private Executor executor = Executors.newSingleThreadExecutor();

    /*
     * Constructor for the QuestionsRepo class.
     * Initializes the QuestionsDao.
     * Parameters:
     *   - context: The context of the application.
     */
    public QuestionsRepo(Context context) {
        this.questionsDao = QuizDatabase.getInstance(context).getQuestionsDao();
    }

    /*
     * Retrieves all questions from the local database as LiveData.
     */
    public LiveData<List<Questions>> getAllQuestions() {
        return this.questionsDao.getAllQuestions();
    }

    /*
     * Retrieves a question by its ID from the local database as LiveData.
     * Parameters:
     *   - questionsId: The ID of the question.
     */
    public LiveData<Questions> getQuestionById(int questionsId) {
        return this.questionsDao.getQuestionById(questionsId);
    }

    /*
     * Retrieves questions by theme ID from the local database as LiveData.
     * Parameters:
     *   - themeId: The ID of the theme.
     */
    public LiveData<List<Questions>> getQuestionsByThemeId(int themeId) {
        return this.questionsDao.getQuestionsByThemeId(themeId);
    }

    /*
     * Retrieves the ID of the previous question from the local database as LiveData.
     * Parameters:
     *   - currentQuestionId: The ID of the current question.
     */
    public LiveData<Integer> getPreviousQuestionId(int currentQuestionId) {
        return this.questionsDao.getPreviousQuestionId(currentQuestionId);
    }

    /*
     * Checks if a question is the last one in the sequence from the local database as LiveData.
     * Parameters:
     *   - questionId: The ID of the question.
     */
    public LiveData<Boolean> isLastQuestion(int questionId) {
        return this.questionsDao.isLastQuestion(questionId);
    }

    /*
     * Inserts a single question into the local database.
     * Parameters:
     *   - question: The question to be inserted.
     */
    public void insertQuestion(Questions question) {
        executor.execute(() -> {
            questionsDao.insertQuestion(question);
        });
    }

    /*
     * Updates a single question in the local database.
     * Parameters:
     *   - question: The question to be updated.
     */
    public void updateQuestion(Questions question) {
        executor.execute(() -> {
            questionsDao.updateQuestion(question);
        });
    }

    /*
     * Deletes a single question from the local database.
     * Parameters:
     *   - question: The question to be deleted.
     */
    public void deleteQuestion(Questions question) {
        executor.execute(() -> {
            questionsDao.deleteQuestion(question);
        });
    }
}
