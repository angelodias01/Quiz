/**
 * ViewModel class for managing Questions data.
 */

package quiz.app.project.dias.dias.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
import quiz.app.project.dias.dias.model.questions.Questions;
import quiz.app.project.dias.dias.model.questions.QuestionsRepo;


public class QuestionsViewModel extends AndroidViewModel {
    private QuestionsRepo repository;

    /**
     * Constructor for the QuestionsViewModel.
     *
     * @param application The application instance.
     */
    public QuestionsViewModel(@NonNull Application application) {
        super(application);
        // Initialize repository
        this.repository = new QuestionsRepo(application.getApplicationContext());
    }

    /**
     * Get LiveData containing all questions.
     *
     * @return LiveData<List<Questions>> representing all questions.
     */
    public LiveData<List<Questions>> getAllQuestions() {
        return repository.getAllQuestions();
    }

    /**
     * Get LiveData containing a specific question by its ID.
     *
     * @param questionsId The ID of the question to retrieve.
     * @return LiveData<Questions> representing the specified question.
     */
    public LiveData<Questions> getQuestionById(int questionsId) {
        return repository.getQuestionById(questionsId);
    }

    /**
     * Get LiveData containing all questions for a specific theme.
     *
     * @param themeId The ID of the theme for which to retrieve questions.
     * @return LiveData<List<Questions>> representing questions for the specified theme.
     */
    public LiveData<List<Questions>> getQuestionsByThemeId(int themeId) {
        return repository.getQuestionsByThemeId(themeId);
    }

    /**
     * Get LiveData containing the ID of the previous question based on the current question ID.
     *
     * @param currentQuestionId The ID of the current question.
     * @return LiveData<Integer> representing the ID of the previous question.
     */
    public LiveData<Integer> getPreviousQuestionId(int currentQuestionId) {
        return repository.getPreviousQuestionId(currentQuestionId);
    }

    /**
     * Get LiveData indicating whether a given question is the last question.
     *
     * @param questionId The ID of the question to check.
     * @return LiveData<Boolean> representing whether the given question is the last question.
     */
    public LiveData<Boolean> isLastQuestion(int questionId) {
        return repository.isLastQuestion(questionId);
    }

    /**
     * Insert a new question.
     *
     * @param question The question to insert.
     */
    public void insertQuestion(Questions question) {
        repository.insertQuestion(question);
    }

    /**
     * Update an existing question.
     *
     * @param question The question to update.
     */
    public void updateQuestion(Questions question) {
        repository.updateQuestion(question);
    }

    /**
     * Delete a question.
     *
     * @param question The question to delete.
     */
    public void deleteQuestion(Questions question) {
        repository.deleteQuestion(question);
    }
}
