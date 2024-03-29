/**
 * QuizActivity.java
 * Represents the main activity for conducting quizzes, handling question navigation,
 * calculating scores, and managing achievements.
 */

package quiz.app.project.dias.dias.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import quiz.app.project.dias.dias.model.achievementsuser.AchievementUser;
import quiz.app.project.dias.dias.model.questions.Questions;
import quiz.app.project.dias.dias.model.QuizDatabase;
import quiz.app.project.dias.dias.model.score.Score;
import quiz.app.project.dias.dias.model.score.ScoreDao;
import quiz.app.project.dias.dias.model.usercurrency.UserCurrency;
import quiz.app.project.dias.dias.R;
import quiz.app.project.dias.dias.viewmodel.AchievementUserViewModel;
import quiz.app.project.dias.dias.viewmodel.AchievementViewModel;
import quiz.app.project.dias.dias.viewmodel.QuestionsViewModel;
import quiz.app.project.dias.dias.viewmodel.ScoreViewModel;
import quiz.app.project.dias.dias.viewmodel.ThemeViewModel;
import quiz.app.project.dias.dias.viewmodel.UserCurrencyViewModel;

public class QuizActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private ScoreDao scoreDao;
    private int themeId;
    private List<Questions> questionsList;
    private Map<Integer, String> selectedAnswersMap;
    private QuestionsViewModel questionsViewModel;
    private ThemeViewModel themeViewModel;
    private ScoreViewModel scoreViewModel;
    private UserCurrencyViewModel userCurrencyViewModel;
    private AchievementUserViewModel achievementUserViewModel;
    private AchievementViewModel achievementViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        themeViewModel = new ViewModelProvider(this).get(ThemeViewModel.class);
        questionsViewModel = new ViewModelProvider(this).get(QuestionsViewModel.class);
        scoreViewModel = new ViewModelProvider(this).get(ScoreViewModel.class);
        userCurrencyViewModel = new ViewModelProvider(this).get(UserCurrencyViewModel.class);
        achievementViewModel = new ViewModelProvider(this).get(AchievementViewModel.class);
        achievementUserViewModel = new ViewModelProvider(this).get(AchievementUserViewModel.class);
        fragmentManager = getSupportFragmentManager();

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

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.quizQuit)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton(R.string.no, null)
                .show();
    }

    private void loadQuestions() {
        QuizDatabase db = QuizDatabase.getInstance(this);
        questionsViewModel.getQuestionsByThemeId(themeId).observe(this, new Observer<List<Questions>>() {
            @Override
            public void onChanged(List<Questions> questions) {
                questionsList = questions;
                if (!questionsList.isEmpty()) {
                    Collections.shuffle(questionsList); // Shuffle the questions list
                    startQuiz();
                } else {
                    // Handle error, no questions for the selected theme
                    finish();
                }
            }
        });
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

    public void insertScoreIntoDatabase(int score) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int userId = sharedPreferences.getInt("userId", 0);
        themeViewModel.getThemeById(themeId).observe(this, theme -> {
            Score scoreEntity = new Score(score, userId, themeId, System.currentTimeMillis());
            scoreViewModel.insertScore(scoreEntity);

            // Check if the user already has the achievement "The Beginning"
            if (!hasAchievement(userId, 2)) {
                achievementUserViewModel.createAchievements(new AchievementUser(userId, 2, System.currentTimeMillis()));
            }

            // Check if the user already has the achievement "Ding Ding Ding!"
            if (score == questionsList.size() && !hasAchievement(userId, 3)) {
                achievementUserViewModel.createAchievements(new AchievementUser(userId, 3, System.currentTimeMillis()));
            }

            if (!hasAchievement(userId, 4) && score == 0) {
                achievementUserViewModel.createAchievements(new AchievementUser(userId, 4, System.currentTimeMillis()));
            }

            // Check if the user already has the achievement "Tough Mind"
            if (hasWinningStreak(userId, 3)) {
                if (!hasAchievement(userId, 6)) {
                    achievementUserViewModel.createAchievements(new AchievementUser(userId, 6, System.currentTimeMillis()));
                }
            }

            // Check if the user already has the achievement "A.I."
            if (hasWinningStreak(userId, 10)) {
                if (!hasAchievement(userId, 7)) {
                    achievementUserViewModel.createAchievements(new AchievementUser(userId, 7, System.currentTimeMillis()));
                }
            }

            // Check if the user already has the achievement "Pootis Penser Here!"
            if (selectedAnswersMap.containsValue("Pootis Penser Here!")) {
                if (!hasAchievement(userId, 8)) {
                    achievementUserViewModel.createAchievements(new AchievementUser(userId, 8, System.currentTimeMillis()));
                }
            }

            // Check if the user already has the achievement "101 Questions"
            if (hasAnswered101Questions(userId)) {
                if (!hasAchievement(userId, 9)) {
                    achievementUserViewModel.createAchievements(new AchievementUser(userId, 9, System.currentTimeMillis()));
                }
            }

            scoreViewModel.getTotalScoreByUserId(userId).observe(this, total -> {
                if (total != null && total >= 50 && !hasAchievement(userId, 12)) {
                    achievementUserViewModel.createAchievements(new AchievementUser(userId, 12, System.currentTimeMillis()));
                }
                scoreViewModel.getTotalScoreByUserId(userId).removeObservers(this);
            });

            // Check if the user already has the achievement "High 5 player"
            if (hasPlayed5Quizzes(userId)) {
                if (!hasAchievement(userId, 15)) {
                    achievementUserViewModel.createAchievements(new AchievementUser(userId, 15, System.currentTimeMillis()));
                }
            }

            // Check if the user already has the achievement "Gimme 10!"
            if (hasPlayed10Quizzes(userId)) {
                if (!hasAchievement(userId, 16)) {
                    achievementUserViewModel.createAchievements(new AchievementUser(userId, 16, System.currentTimeMillis()));
                }
            }

            // Check if the user already has the achievement "Down and give me 20!"
            if (hasPlayed20Quizzes(userId)) {
                if (!hasAchievement(userId, 17)) {
                    achievementUserViewModel.createAchievements(new AchievementUser(userId, 17, System.currentTimeMillis()));
                }
            }

            // Check if the user already has the achievement "101 reasons to..."
            if (hasPlayed101Quizzes(userId)) {
                if (!hasAchievement(userId, 18)) {
                    achievementUserViewModel.createAchievements(new AchievementUser(userId, 18, System.currentTimeMillis()));
                }
            }

            // Navegar para o fragmento QuizCompleteFragment
            QuizCompleteFragment fragment = QuizCompleteFragment.newInstance(score, themeId, theme.getThemeName());
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        });

        List<UserCurrency> userCurrencyList = userCurrencyViewModel.getUserCurrencysByUserId(userId);
        if (userCurrencyList != null && userCurrencyList.size() > 0) {
            UserCurrency userCurrency = userCurrencyList.get(0);

            if (score > 0 && score <= 7) {
                int updatedCurrencyAmount = userCurrency.getAmount() + score * 2;

                // Update the currency amount in the fetched UserCurrency object
                userCurrency.setAmount(updatedCurrencyAmount);

                // Ensure that the final currency amount is updated in the view model
                userCurrencyViewModel.updateCurrency(userCurrency);
            }
        }
    }

    private void replaceWithQuestion(int questionId) {
        QuizFragment fragment = QuizFragment.newInstance(questionsList, questionId);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    // Method to check if the user already has a specific achievement
    private boolean hasAchievement(int userId, int achievementId) {
        LiveData<List<AchievementUser>> achievementsLiveData = achievementUserViewModel.getUserAchievementByUserId(userId);
        if (achievementsLiveData != null) {
            List<AchievementUser> achievements = achievementsLiveData.getValue();
            if (achievements != null) {
                for (AchievementUser achievement : achievements) {
                    if (achievement.getAchievementId() == achievementId) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Method to check if the user has a perfect score in a specific theme
    private boolean hasPerfectScore(int userId, int themeId) {
        LiveData<Boolean> perfectScoreLiveData = scoreViewModel.hasPerfectScore(userId, themeId);
        return perfectScoreLiveData.getValue() != null && perfectScoreLiveData.getValue();
    }

    // Method to check if the user has a zero score in a specific theme
    private boolean hasZeroScore(int userId, int themeId) {
        LiveData<Boolean> zeroScoreLiveData = scoreViewModel.hasZeroScore(userId, themeId);
        return zeroScoreLiveData.getValue() != null && zeroScoreLiveData.getValue();
    }

    // Method to check if the user has a winning streak in quizzes
    private boolean hasWinningStreak(int userId, int streakCount) {
        LiveData<Integer> maxWinningStreakLiveData = scoreViewModel.getMaxWinningStreak(userId);
        return maxWinningStreakLiveData.getValue() != null && maxWinningStreakLiveData.getValue() >= streakCount;
    }

    // Method to check if the user has answered 101 questions
    private boolean hasAnswered101Questions(int userId) {
        LiveData<Integer> answeredQuestionCountLiveData = scoreViewModel.getAnsweredQuestionCount(userId);
        return answeredQuestionCountLiveData.getValue() != null && answeredQuestionCountLiveData.getValue() >= 101;
    }

    private List<Score> getQuizHistory(int userId) {
        return scoreViewModel.getQuizHistoryByUserId(userId).getValue();
    }

    private boolean hasPlayed5Quizzes(int userId) {
        List<Score> quizHistory = getQuizHistory(userId);
        return quizHistory != null && quizHistory.size() >= 5;
    }

    private boolean hasPlayed10Quizzes(int userId) {
        List<Score> quizHistory = getQuizHistory(userId);
        return quizHistory != null && quizHistory.size() >= 10;
    }

    private boolean hasPlayed20Quizzes(int userId) {
        List<Score> quizHistory = getQuizHistory(userId);
        return quizHistory != null && quizHistory.size() >= 20;
    }

    private boolean hasPlayed101Quizzes(int userId) {
        List<Score> quizHistory = getQuizHistory(userId);
        return quizHistory != null && quizHistory.size() >= 101;
    }


    private LiveData<Integer> getUserCurrencyAmount(int userId) {
        return userCurrencyViewModel.getCollectedCoins(userId);
    }

}
