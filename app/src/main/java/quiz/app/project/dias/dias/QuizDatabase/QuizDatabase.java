package quiz.app.project.dias.dias.QuizDatabase;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import quiz.app.project.dias.dias.QuizDatabase.AchievementUserDB.AchievementUser;
import quiz.app.project.dias.dias.QuizDatabase.AchievementUserDB.AchievementUserDao;
import quiz.app.project.dias.dias.QuizDatabase.AchievementsDB.Achievements;
import quiz.app.project.dias.dias.QuizDatabase.AchievementsDB.AchievementsDao;
import quiz.app.project.dias.dias.QuizDatabase.QuestionsDB.Questions;
import quiz.app.project.dias.dias.QuizDatabase.QuestionsDB.QuestionsDao;
import quiz.app.project.dias.dias.QuizDatabase.ShopDB.Shop;
import quiz.app.project.dias.dias.QuizDatabase.ShopDB.ShopDao;
import quiz.app.project.dias.dias.QuizDatabase.UserCurrencyDB.UserCurrency;
import quiz.app.project.dias.dias.QuizDatabase.UserCurrencyDB.UserCurrencyDao;
import quiz.app.project.dias.dias.QuizDatabase.UserDB.User;
import quiz.app.project.dias.dias.QuizDatabase.UserDB.UserDao;
import quiz.app.project.dias.dias.QuizDatabase.ScoreDB.Score;
import quiz.app.project.dias.dias.QuizDatabase.ScoreDB.ScoreDao;
import quiz.app.project.dias.dias.QuizDatabase.ThemeDB.Theme;
import quiz.app.project.dias.dias.QuizDatabase.ThemeDB.ThemeDao;


@Database(entities = {User.class, UserCurrency.class, Theme.class, Score.class, Questions.class, Achievements.class, AchievementUser.class, Shop.class}, version = 1, exportSchema = false)
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
    public static QuizDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            QuizDatabase.class, "QuizDatabase").allowMainThreadQueries()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            //Exec to insert data in themes
                            db.execSQL("INSERT INTO Theme VALUES (1,'General Knowledge','GK')");
                            db.execSQL("INSERT INTO Theme VALUES (2,'Team Fortress 2','TF2')");
                            db.execSQL("INSERT INTO Theme VALUES (3,'Programming','Code')");
                            db.execSQL("INSERT INTO Theme VALUES (4,'CS:GO','CS:GO')");
                            db.execSQL("INSERT INTO Theme VALUES (5, \"Five Night's at Freddy's\", \"FNaF\")");
                            db.execSQL("INSERT INTO Theme VALUES (6,'CS:GO','Cts')");
                            //Exec to insert data in Achievements
                            db.execSQL("Insert into Achievements values (1,'First Login', 'This is the first achievement')");
                            //Exec to insert data in Shop
                            db.execSQL("Insert into Shop values (1,'T-shirt Size S', 56)");
                            db.execSQL("Insert into Shop values (2,'T-shirt Size M',80)");
                            db.execSQL("Insert into Shop values (3,'T-shirt Size L',96)");
                        }
                    })
                    .build();
        }
        return INSTANCE;
    }

}