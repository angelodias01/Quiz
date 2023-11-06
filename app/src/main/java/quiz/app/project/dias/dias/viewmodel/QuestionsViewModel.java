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

    public QuestionsViewModel(@NonNull Application application) {
        super(application);
        // Initialize repository
        this.repository = new QuestionsRepo(application.getApplicationContext());
    }

    public LiveData<List<Questions>> getAllQuestions() {
        return repository.getAllQuestions();
    }

    public LiveData<Questions> getQuestionById(int questionsId) {
        return repository.getQuestionById(questionsId);
    }

    public LiveData<List<Questions>> getQuestionsByThemeId(int themeId) {
        return repository.getQuestionsByThemeId(themeId);
    }

    public LiveData<Integer> getPreviousQuestionId(int currentQuestionId) {
        return repository.getPreviousQuestionId(currentQuestionId);
    }

    public LiveData<Boolean> isLastQuestion(int questionId) {
        return repository.isLastQuestion(questionId);
    }

    public void insertQuestion(Questions question) {
        repository.insertQuestion(question);
    }

    public void updateQuestion(Questions question) {
        repository.updateQuestion(question);
    }

    public void deleteQuestion(Questions question) {
        repository.deleteQuestion(question);
    }
}
