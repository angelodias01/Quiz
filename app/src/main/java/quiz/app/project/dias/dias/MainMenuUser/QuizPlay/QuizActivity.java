package quiz.app.project.dias.dias.MainMenuUser.QuizPlay;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import quiz.app.project.dias.dias.QuizDatabase.AchievementUserDB.AchievementUser;
import quiz.app.project.dias.dias.QuizDatabase.AchievementsDB.Achievements;
import quiz.app.project.dias.dias.QuizDatabase.QuestionsDB.Questions;
import quiz.app.project.dias.dias.QuizDatabase.QuestionsDB.QuestionsDao;
import quiz.app.project.dias.dias.QuizDatabase.QuizDatabase;
import quiz.app.project.dias.dias.QuizDatabase.ScoreDB.Score;
import quiz.app.project.dias.dias.QuizDatabase.ScoreDB.ScoreDao;
import quiz.app.project.dias.dias.QuizDatabase.ThemeDB.Theme;
import quiz.app.project.dias.dias.QuizDatabase.UserCurrencyDB.UserCurrency;
import quiz.app.project.dias.dias.QuizDatabase.UserCurrencyDB.UserCurrencyDao;
import quiz.app.project.dias.dias.QuizDatabase.UserDB.User;
import quiz.app.project.dias.dias.QuizDatabase.UserDB.UserDao;
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

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Do You Want Quit The Quiz?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
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

        // Update the user's currency amount
        int updatedCurrencyAmount = getUserCurrencyAmount(quizDatabase, userId) + 10;

        if (score != -1) {
            switch (score) {
                case 1:
                    updatedCurrencyAmount += 1;
                    break;
                case 2:
                    updatedCurrencyAmount += 2;
                    break;
                case 3:
                    updatedCurrencyAmount += 4;
                    break;
                case 4:
                    updatedCurrencyAmount += 5;
                    break;
                case 5:
                    updatedCurrencyAmount += 7;
                    break;
                case 6:
                    updatedCurrencyAmount += 8;
                    break;
                case 7:
                    updatedCurrencyAmount += 10;
                    break;
            }
        }

        UserCurrencyDao userCurrencyDao = quizDatabase.getUserCurrencyDao();
        UserCurrency userCurrency = userCurrencyDao.getUserCurrencyByUserId(userId);

        if (userCurrency != null) {
            userCurrency.setAmount(updatedCurrencyAmount);
            userCurrencyDao.updateCurrency(userCurrency);
        }

        // Check if the user already has the achievement "The Beginning"
        if (!hasAchievement(quizDatabase, userId, 2)) {
            quizDatabase.getAchievementUserDao().insertAchievementUser(new AchievementUser(userId, 2, System.currentTimeMillis()));
        }

        // Check if the user already has the achievement "Ding Ding Ding!"
        if (hasPerfectScore(quizDatabase, userId, themeId)) {
            if (!hasAchievement(quizDatabase, userId, 3)) {
                quizDatabase.getAchievementUserDao().insertAchievementUser(new AchievementUser(userId, 3, System.currentTimeMillis()));
            }
        }

        // Check if the user already has the achievement "A Great Loser"
        if (hasZeroScore(quizDatabase, userId, themeId)) {
            if (!hasAchievement(quizDatabase, userId, 4)) {
                quizDatabase.getAchievementUserDao().insertAchievementUser(new AchievementUser(userId, 4, System.currentTimeMillis()));
            }
        }

        // Check if the user already has the achievement "Pootis enjoyer"
        //if (score == 7 && !hasAchievement(quizDatabase, userId, 5)) {
          //  quizDatabase.getAchievementUserDao().insertAchievementUser(new AchievementUser(userId, 5, System.currentTimeMillis()));
        //}

        // Check if the user already has the achievement "Tough Mind"
        if (hasWinningStreak(quizDatabase, userId, 3)) {
            if (!hasAchievement(quizDatabase, userId, 6)) {
                quizDatabase.getAchievementUserDao().insertAchievementUser(new AchievementUser(userId, 6, System.currentTimeMillis()));
            }
        }

        // Check if the user already has the achievement "A.I."
        if (hasWinningStreak(quizDatabase, userId, 10)) {
            if (!hasAchievement(quizDatabase, userId, 7)) {
                quizDatabase.getAchievementUserDao().insertAchievementUser(new AchievementUser(userId, 7, System.currentTimeMillis()));
            }
        }

        // Check if the user already has the achievement "Pootis Penser Here!"
        if (selectedAnswersMap.containsValue("Pootis Penser Here!")) {
            if (!hasAchievement(quizDatabase, userId, 8)) {
                quizDatabase.getAchievementUserDao().insertAchievementUser(new AchievementUser(userId, 8, System.currentTimeMillis()));
            }
        }

        // Check if the user already has the achievement "101 Questions"
        if (hasAnswered101Questions(quizDatabase, userId)) {
            if (!hasAchievement(quizDatabase, userId, 9)) {
                quizDatabase.getAchievementUserDao().insertAchievementUser(new AchievementUser(userId, 9, System.currentTimeMillis()));
            }
        }

        // Check if the user already has the achievement "Medic!"
       // if (hasAssistedQuestion(quizDatabase, userId)) {
         //   if (!hasAchievement(quizDatabase, userId, 10)) {
           //     quizDatabase.getAchievementUserDao().insertAchievementUser(new AchievementUser(userId, 10, System.currentTimeMillis()));
         //   }
        //}

        // Check if the user already has the achievement "Weekly Challenger"
        //if (hasWonWeeklyChallenge(quizDatabase, userId)) {
           // if (!hasAchievement(quizDatabase, userId, 11)) {
             //   quizDatabase.getAchievementUserDao().insertAchievementUser(new AchievementUser(userId, 11, System.currentTimeMillis()));
            //}
       // }

        // Check if the user already has the achievement "Well Played"
        int totalScore = quizDatabase.getScoreDao().getTotalScoreByUserId(userId);

        // Check if the user already has the achievement "Well Played"
        if (totalScore >= 50 && !hasAchievement(quizDatabase, userId, 12)) {
            quizDatabase.getAchievementUserDao().insertAchievementUser(new AchievementUser(userId, 12, System.currentTimeMillis()));
        }

        // Check if the user already has the achievement "The Investor"
        if (hasCollected100Coins(quizDatabase, userId)) {
            if (!hasAchievement(quizDatabase, userId, 13)) {
                quizDatabase.getAchievementUserDao().insertAchievementUser(new AchievementUser(userId, 13, System.currentTimeMillis()));
            }
        }

        // Check if the user already has the achievement "Smart Investment"
        if (hasWasted110Coins(quizDatabase, userId)) {
            if (!hasAchievement(quizDatabase, userId, 14)) {
                quizDatabase.getAchievementUserDao().insertAchievementUser(new AchievementUser(userId, 14, System.currentTimeMillis()));
            }
        }

        // Check if the user already has the achievement "High 5 player"
        if (hasPlayed5Quizzes(quizDatabase, userId)) {
            if (!hasAchievement(quizDatabase, userId, 15)) {
                quizDatabase.getAchievementUserDao().insertAchievementUser(new AchievementUser(userId, 15, System.currentTimeMillis()));
            }
        }

        // Check if the user already has the achievement "Gimme 10!"
        if (hasPlayed10Quizzes(quizDatabase, userId)) {
            if (!hasAchievement(quizDatabase, userId, 16)) {
                quizDatabase.getAchievementUserDao().insertAchievementUser(new AchievementUser(userId, 16, System.currentTimeMillis()));
            }
        }

        // Check if the user already has the achievement "Down and give me 20!"
        if (hasPlayed20Quizzes(quizDatabase, userId)) {
            if (!hasAchievement(quizDatabase, userId, 17)) {
                quizDatabase.getAchievementUserDao().insertAchievementUser(new AchievementUser(userId, 17, System.currentTimeMillis()));
            }
        }

        // Check if the user already has the achievement "101 reasons to..."
        if (hasPlayed101Quizzes(quizDatabase, userId)) {
            if (!hasAchievement(quizDatabase, userId, 18)) {
                quizDatabase.getAchievementUserDao().insertAchievementUser(new AchievementUser(userId, 18, System.currentTimeMillis()));
            }
        }

        // Check if the user already has the achievement "Heavy wants this!"
        if (hasClearedAllQuestions(quizDatabase, userId, themeId)) {
            if (!hasAchievement(quizDatabase, userId, 19)) {
                quizDatabase.getAchievementUserDao().insertAchievementUser(new AchievementUser(userId, 19, System.currentTimeMillis()));
            }
        }

        // Check if the user already has the achievement "I am planting the bomb"
        if (hasClearedAllQuestions(quizDatabase, userId, themeId)) {
            if (!hasAchievement(quizDatabase, userId, 20)) {
                quizDatabase.getAchievementUserDao().insertAchievementUser(new AchievementUser(userId, 20, System.currentTimeMillis()));
            }
        }

        // Check if the user already has the achievement "Deploying Freddy Fazbear"
        if (hasClearedAllQuestions(quizDatabase, userId, themeId)) {
            if (!hasAchievement(quizDatabase, userId, 21)) {
                quizDatabase.getAchievementUserDao().insertAchievementUser(new AchievementUser(userId, 21, System.currentTimeMillis()));
            }
        }

        // Check if the user already has the achievement "Next stop: Redshift"
        if (hasClearedAllQuestions(quizDatabase, userId, themeId)) {
            if (!hasAchievement(quizDatabase, userId, 22)) {
                quizDatabase.getAchievementUserDao().insertAchievementUser(new AchievementUser(userId, 22, System.currentTimeMillis()));
            }
        }

        // Check if the user already has the achievement "Generalist"
        if (hasClearedAllQuestions(quizDatabase, userId, themeId)) {
            if (!hasAchievement(quizDatabase, userId, 23)) {
                quizDatabase.getAchievementUserDao().insertAchievementUser(new AchievementUser(userId, 23, System.currentTimeMillis()));
            }
        }

        // Check if the user already has the achievement "Sentence: Completed"
        if (hasClearedAllQuestions(quizDatabase, userId, themeId)) {
            if (!hasAchievement(quizDatabase, userId, 24)) {
                quizDatabase.getAchievementUserDao().insertAchievementUser(new AchievementUser(userId, 24, System.currentTimeMillis()));
            }
        }

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

    // Method to check if the user already has a specific achievement
    private boolean hasAchievement(QuizDatabase quizDatabase, int userId, int achievementId) {
        List<AchievementUser> achievements = quizDatabase.getAchievementUserDao().getAchievementUsersByUserId(userId);
        for (AchievementUser achievement : achievements) {
            if (achievement.getAchievementId() == achievementId) {
                return true;
            }
        }
        return false;
    }

    // Method to check if the user has a perfect score in a specific theme
    private boolean hasPerfectScore(QuizDatabase quizDatabase, int userId, int themeId) {
        return quizDatabase.getScoreDao().hasPerfectScore(userId, themeId);
    }

    // Method to check if the user has a zero score in a specific theme
    private boolean hasZeroScore(QuizDatabase quizDatabase, int userId, int themeId) {
        return quizDatabase.getScoreDao().hasZeroScore(userId, themeId);
    }

    // Method to check if the user has a winning streak in quizzes
    private boolean hasWinningStreak(QuizDatabase quizDatabase, int userId, int streakCount) {
        return quizDatabase.getScoreDao().getMaxWinningStreak(userId) >= streakCount;
    }

    // Method to check if the user has answered 101 questions
    private boolean hasAnswered101Questions(QuizDatabase quizDatabase, int userId) {
        return quizDatabase.getScoreDao().getAnsweredQuestionCount(userId) >= 101;
    }

    // Method to check if the user has assisted in a question
    //private boolean hasAssistedQuestion(QuizDatabase quizDatabase, int userId) {
      //  return quizDatabase.getScoreDao().hasAssistedQuestion(userId);
    //}

    // Method to check if the user has won the weekly challenge
    //private boolean hasWonWeeklyChallenge(QuizDatabase quizDatabase, int userId) {
        // Implementation to check if the user has won the weekly challenge
        // Replace with your logic
       // return false;
    //}

    // Method to check if the user has collected 100 coins
    private boolean hasCollected100Coins(QuizDatabase quizDatabase, int userId) {
        // Implementation to check if the user has collected 100 coins
        // Replace with your logic
        return false;
    }

    // Method to check if the user has wasted 110 coins
    private boolean hasWasted110Coins(QuizDatabase quizDatabase, int userId) {
        // Implementation to check if the user has wasted 110 coins
        // Replace with your logic
        return false;
    }

    private List<Score> getQuizHistory(QuizDatabase quizDatabase, int userId) {
        return quizDatabase.getScoreDao().getQuizHistoryByUserId(userId);
    }

    private boolean hasPlayed5Quizzes(QuizDatabase quizDatabase, int userId) {
        List<Score> quizHistory = getQuizHistory(quizDatabase, userId);
        return quizHistory.size() >= 5;
    }

    private boolean hasPlayed10Quizzes(QuizDatabase quizDatabase, int userId) {
        List<Score> quizHistory = getQuizHistory(quizDatabase, userId);
        return quizHistory.size() >= 10;
    }
    private boolean hasPlayed20Quizzes(QuizDatabase quizDatabase, int userId) {
        List<Score> quizHistory = getQuizHistory(quizDatabase, userId);
        return quizHistory.size() >= 20;
    }

    private boolean hasPlayed101Quizzes(QuizDatabase quizDatabase, int userId) {
        List<Score> quizHistory = getQuizHistory(quizDatabase, userId);
        return quizHistory.size() >= 101;
    }

    // Method to check if the user has cleared all questions in a theme
    private boolean hasClearedAllQuestions(QuizDatabase quizDatabase, int userId, int themeId) {
        // Implementation to check if the user has cleared all questions in a theme
        // Replace with your logic
        return false;
    }

    private int getUserCurrencyAmount(QuizDatabase quizDatabase, int userId) {
        return quizDatabase.getUserCurrencyDao().getCollectedCoins(userId);
    }
}
