package quiz.app.project.dias.dias.MainMenuUser.QuizPlay;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import quiz.app.project.dias.dias.QuizDatabase.QuestionsDB.Questions;
import quiz.app.project.dias.dias.QuizDatabase.QuestionsDB.QuestionsDao;
import quiz.app.project.dias.dias.QuizDatabase.QuizDatabase;
import quiz.app.project.dias.dias.QuizDatabase.ScoreDB.Score;
import quiz.app.project.dias.dias.QuizDatabase.ScoreDB.ScoreDao;
import quiz.app.project.dias.dias.QuizDatabase.ThemeDB.Theme;
import quiz.app.project.dias.dias.R;

public class QuizActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private ScoreDao scoreDao;
    private int themeId;
    private List<Questions> questionsList;
    private Map<Integer, String> selectedAnswersMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        fragmentManager = getSupportFragmentManager();
        scoreDao = QuizDatabase.getInstance(this).getScoreDao();

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("themeId")) {
            themeId = intent.getIntExtra("themeId", 0);
        } else {
            // Handle error, themeId not found
            finish();
        }

        loadQuestions();
        selectedAnswersMap = new HashMap<>();
    }

    private void loadQuestions() {
        QuizDatabase db = QuizDatabase.getInstance(this);
        QuestionsDao questionsDao = db.getQuestionsDao();
        questionsList = questionsDao.getQuestionsByThemeId(themeId);

        if (!questionsList.isEmpty()) {
            Collections.shuffle(questionsList); // Shuffle the questions list
            startQuiz();
        } else {
            // Handle error, no questions for the selected theme
            finish();
        }
    }

    private void startQuiz() {
        if (currentQuestionIndex < questionsList.size()) {
            int questionId = questionsList.get(currentQuestionIndex).getQuestionsId();
            QuizFragment fragment = QuizFragment.newInstance(questionsList, questionId);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        } else {
            // Quiz finished, insert the score into the database
            insertScoreIntoDatabase(score);
        }
    }

    public void onAnswerSelected(int questionId, String selectedAnswer) {
        selectedAnswersMap.put(questionId, selectedAnswer);
        startQuiz();
    }

    public String getSelectedAnswer(int questionId) {
        return selectedAnswersMap.get(questionId);
    }

    public void onQuizFinished() {
        int correctAnswers = calculateScore();
        insertScoreIntoDatabase(correctAnswers);
    }

    private int calculateScore() {
        int correctAnswers = 0;
        for (Questions question : questionsList) {
            int questionId = question.getQuestionsId();
            String selectedAnswer = selectedAnswersMap.get(questionId);
            if (selectedAnswer != null && selectedAnswer.equals(question.getCorrectAnswer())) {
                correctAnswers++;
            }
        }
        return correctAnswers;
    }

    private void insertScoreIntoDatabase(int score) {
        QuizDatabase quizDatabase = QuizDatabase.getInstance(this);
        Theme theme = quizDatabase.getThemeDao().getThemeById(themeId);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int userId = sharedPreferences.getInt("userId", 0);
        Score scoreEntity = new Score(score, userId, themeId, System.currentTimeMillis());
        quizDatabase.getScoreDao().insertScore(scoreEntity);

        // Navegar para o fragmento QuizCompleteFragment
        QuizCompleteFragment fragment = QuizCompleteFragment.newInstance(score, themeId, theme.getThemeName());
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }



    private void replaceWithQuestion(int questionId) {
        QuizFragment fragment = QuizFragment.newInstance(questionsList, questionId);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}
