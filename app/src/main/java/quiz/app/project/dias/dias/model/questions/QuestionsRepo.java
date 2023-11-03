package quiz.app.project.dias.dias.model.questions;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import quiz.app.project.dias.dias.model.QuizDatabase;

public class QuestionsRepo {
    private QuestionsDao questionsDao;
    private Executor executor = Executors.newSingleThreadExecutor();

    public QuestionsRepo(Context context) {
        this.questionsDao = QuizDatabase.getInstance(context).getQuestionsDao();
    }

    public LiveData<List<Questions>> getAllQuestions() {
        return this.questionsDao.getAllQuestions();
    }

    public LiveData<Questions> getQuestionById(int questionsId) {
        return this.questionsDao.getQuestionById(questionsId);
    }

    public LiveData<List<Questions>> getQuestionsByThemeId(int themeId) {
        return this.questionsDao.getQuestionsByThemeId(themeId);
    }

    public LiveData<Integer> getPreviousQuestionId(int currentQuestionId) {
        return this.questionsDao.getPreviousQuestionId(currentQuestionId);
    }

    public LiveData<Boolean> isLastQuestion(int questionId) {
        return this.questionsDao.isLastQuestion(questionId);
    }

    public void insertQuestion(Questions question) {
        executor.execute(() -> {
            questionsDao.insertQuestion(question);
        });
    }

    public void updateQuestion(Questions question) {
        executor.execute(() -> {
            questionsDao.updateQuestion(question);
        });
    }

    public void deleteQuestion(Questions question) {
        executor.execute(() -> {
            questionsDao.deleteQuestion(question);
        });
    }
}