/**
 * QuizDatabase.java
 * Represents the Room database for the quiz app, providing access to various DAOs (Data Access Objects)
 * for interacting with different entities like User, UserCurrency, Theme, Score, Questions, Achievements,
 * AchievementUser, and Shop.
 */


package quiz.app.project.dias.dias.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import quiz.app.project.dias.dias.model.achievements.Achievements;
import quiz.app.project.dias.dias.model.achievements.AchievementsDao;
import quiz.app.project.dias.dias.model.achievementsuser.AchievementUser;
import quiz.app.project.dias.dias.model.achievementsuser.AchievementUserDao;
import quiz.app.project.dias.dias.model.questions.Questions;
import quiz.app.project.dias.dias.model.questions.QuestionsDao;
import quiz.app.project.dias.dias.model.score.Score;
import quiz.app.project.dias.dias.model.score.ScoreDao;
import quiz.app.project.dias.dias.model.shop.Shop;
import quiz.app.project.dias.dias.model.shop.ShopDao;
import quiz.app.project.dias.dias.model.theme.Themes;
import quiz.app.project.dias.dias.model.theme.ThemeDao;
import quiz.app.project.dias.dias.model.user.User;
import quiz.app.project.dias.dias.model.user.UserDao;
import quiz.app.project.dias.dias.model.usercurrency.UserCurrency;
import quiz.app.project.dias.dias.model.usercurrency.UserCurrencyDao;

@Database(entities = {User.class, UserCurrency.class, Themes.class, Score.class, Questions.class, Achievements.class, AchievementUser.class, Shop.class}, version = 1, exportSchema = false)
public abstract class QuizDatabase extends RoomDatabase {
    private static QuizDatabase INSTANCE;

    public abstract UserDao getUserDao();

    public abstract ThemeDao getThemeDao();

    public abstract ScoreDao getScoreDao();

    public abstract QuestionsDao getQuestionsDao();

    public abstract AchievementsDao getAchievementsDao();

    public abstract AchievementUserDao getAchievementUserDao();

    public abstract UserCurrencyDao getUserCurrencyDao();

    public abstract ShopDao getShopDao();

    /**
     * Returns the singleton instance of the QuizDatabase.
     * If the instance is not yet created, it will be created using Room's databaseBuilder.
     * @param context The application context.
     * @return The singleton instance of QuizDatabase.
     */
    public static QuizDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            QuizDatabase.class, "QuizDatabase").allowMainThreadQueries()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('What is the most played CS:GO map?', 8, 'Dust 2', 'Mirage', 'Inferno', 'Nuke')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('What is the most played CS:GO map?', 8, 'Dust 2', 'Mirage', 'Inferno', 'Nuke')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('What is the most played CS:GO map?', 8, 'Dust 2', 'Mirage', 'Inferno', 'Nuke')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('What is the most played CS:GO map?', 8, 'Dust 2', 'Mirage', 'Inferno', 'Nuke')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('What is the most played CS:GO map?', 8, 'Dust 2', 'Mirage', 'Inferno', 'Nuke')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('What is the most played CS:GO map?', 8, 'Dust 2', 'Mirage', 'Inferno', 'Nuke')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('What is the most played CS:GO map?', 8, 'Dust 2', 'Mirage', 'Inferno', 'Nuke')");
                            //Exec to insert data in Shop
                            db.execSQL("Insert into Shop values (1,'T-shirt Size S', 56)");
                            db.execSQL("Insert into Shop values (2,'T-shirt Size M',80)");
                            db.execSQL("Insert into Shop values (3,'T-shirt Size L',96)");

                        }
                    }).build();
        }
        return INSTANCE;
    }
}

