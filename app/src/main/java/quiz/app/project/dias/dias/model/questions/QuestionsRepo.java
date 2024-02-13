/**
 * QuestionsRepo.java
 * This class manages data operations related to questions, handling both the local database
 * and a remote API using Retrofit.
 */
package quiz.app.project.dias.dias.model.questions;

import android.content.Context;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import quiz.app.project.dias.dias.model.QuizDatabase;
import quiz.app.project.dias.dias.model.retrofit.JsonPlaceHolderService;
import quiz.app.project.dias.dias.model.retrofit.RetrofitClient;
import quiz.app.project.dias.dias.model.theme.Themes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionsRepo {
    private QuestionsDao questionsDao;
    private Executor executor = Executors.newSingleThreadExecutor();
    private JsonPlaceHolderService jsonPlaceHolderService;

    /**
     * Constructor: Initializes the QuestionsDao using the QuizDatabase instance.
     *
     * @param context The application context.
     */
    public QuestionsRepo(Context context) {
        this.questionsDao = QuizDatabase.getInstance(context).getQuestionsDao();
        this.jsonPlaceHolderService = RetrofitClient.getClient().create(JsonPlaceHolderService.class);
    }

    /**
     * Fetches themes from a remote API using Retrofit.
     * Handles the API response and inserts themes into the local database.
     */
    public void fetchQuestions() {
        Call<List<Questions>> call = jsonPlaceHolderService.getQuestions();
        call.enqueue(new Callback<List<Questions>>() {
            @Override
            public void onResponse(Call<List<Questions>> call, Response<List<Questions>> response) {
                if (response.isSuccessful()) {
                    List<Questions> questions = response.body();
                    if (questions != null && !questions.isEmpty()) {
                        insertQuestion((Questions) questions);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Questions>> call, Throwable t) {
                // Handle API failure
            }
        });
    }

    /**
     * Inserts themes into the local database.
     * Checks for existing themes before insertion.
     *
     * @param questions  List of themes to be inserted.
     */
    private void insertQuestions(List<Questions> questions) {
        executor.execute(() -> {
            // Limpa todos os temas existentes no banco de dados
            questionsDao.deleteAllQuestions();

            for (Questions question : questions) {
                questionsDao.insertQuestion(question);
            }
        });
    }

    /**
     * Retrieves all questions using LiveData.
     *
     * @return LiveData containing a list of all questions.
     */
    public LiveData<List<Questions>> getAllQuestions() {
        return this.questionsDao.getAllQuestions();
    }

    /**
     * Retrieves a question by its ID using LiveData.
     *
     * @param questionsId The ID of the question to retrieve.
     * @return LiveData containing the question.
     */
    public LiveData<Questions> getQuestionById(int questionsId) {
        return this.questionsDao.getQuestionById(questionsId);
    }

    /**
     * Retrieves a set number of questions by theme ID from the database, ordered randomly.
     *
     * @param themeId The ID of the theme.
     * @return LiveData containing a list of questions.
     */
    public LiveData<List<Questions>> getQuestionsByThemeId(int themeId) {
        return this.questionsDao.getQuestionsByThemeId(themeId);
    }

    /**
     * Retrieves the ID of the previous question based on the current question's ID.
     *
     * @param currentQuestionId The ID of the current question.
     * @return LiveData containing the ID of the previous question.
     */
    public LiveData<Integer> getPreviousQuestionId(int currentQuestionId) {
        return this.questionsDao.getPreviousQuestionId(currentQuestionId);
    }

    /**
     * Checks if a question is the last one based on its ID.
     *
     * @param questionId The ID of the question.
     * @return LiveData containing a boolean indicating if the question is the last one.
     */
    public LiveData<Boolean> isLastQuestion(int questionId) {
        return this.questionsDao.isLastQuestion(questionId);
    }

    /**
     * Inserts a question into the database.
     *
     * @param question The question to insert.
     */
    public void insertQuestion(Questions question) {
        executor.execute(() -> {
            questionsDao.insertQuestion(question);
        });
    }

    /**
     * Updates a question in the database.
     *
     * @param question The question to update.
     */
    public void updateQuestion(Questions question) {
        executor.execute(() -> {
            questionsDao.updateQuestion(question);
        });
    }

    /**
     * Deletes a question from the database.
     *
     * @param question The question to delete.
     */
    public void deleteQuestion(Questions question) {
        executor.execute(() -> {
            questionsDao.deleteQuestion(question);
        });
    }
}
