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
                            db.execSQL("INSERT INTO Theme VALUES (6,'Complete The Sentence','Cts')");
                            //Exec to insert data in Achievements
                            db.execSQL("Insert into Achievements values (1,'First Login', 'You created an account')");
                            db.execSQL("Insert into Achievements values (2,'The Beginning', 'Complete any quiz')");
                            db.execSQL("Insert into Achievements values (3,'Ding Ding Ding!', 'Complete a quiz with every question correctly answered')");
                            db.execSQL("Insert into Achievements values (4,'A Great Loser', 'Complete a quiz with no correct answers')");
                            db.execSQL("Insert into Achievements values (5,'Pootis enjoyer', 'You got 7/7 in a TF2 themed quiz')");
                            //Estes dos sem erros deve ser difícil fazer, podemos cagar só tu é que sabes
                            db.execSQL("Insert into Achievements values (6,'Tough Mind', 'Get a winning-streak on 3 quizzes with no mistakes')");
                            db.execSQL("Insert into Achievements values (7,'A.I.', 'Get a winning-streak on 10 quizzes with no mistakes')");
                            //"Pootis Penser Here!" pode ser tipo uma cena escondida, seja botão letra ou o caneco e deve ser fácil de fazer
                            db.execSQL("Insert into Achievements values (8,'Pootis Penser Here!', 'You clicked somewhere heavy wanted!')");
                            //"101 Questions" não sei se damos track ás perguntas que ele já fez, qlq caga-se pra este
                            db.execSQL("Insert into Achievements values (9,'101 Questions', 'You went trought 101 questions. Simple')");
                            //"Medic!" e "Weekly Challenger" era com a cena das ajudas e com os desafios semanais que falamos no início, nao sei se vamos ter isso
                            db.execSQL("Insert into Achievements values (10,'Medic!', 'You got assisted in a question')");
                            db.execSQL("Insert into Achievements values (11,'Weekly Challenger', 'You won the weekly challenge')");
                            db.execSQL("Insert into Achievements values (12,'Well Played', 'You got 50 correct questions')");
                            //estes das moedas não sei se vale a pena ter
                            db.execSQL("Insert into Achievements values (13,'The Investor', 'You collected 100 coins')");
                            db.execSQL("Insert into Achievements values (14,'Smart Investment', 'You wasted 110 coins')");
                            //Quizzes jogados
                            db.execSQL("Insert into Achievements values (15,'High 5 player', 'You played 5 quizzes')");
                            db.execSQL("Insert into Achievements values (16,'Gimme 10!', 'You played 10 quizzes')");
                            db.execSQL("Insert into Achievements values (17,'Down and give me 20!', 'You played 20 quizzes')");
                            db.execSQL("Insert into Achievements values (18,'101 reasons to...', 'You played 101 quizzes')");
                            //temas "acabados", que já passou pelas perguntas todas (basta ser tipo o gajo fez 12 de um tema e ganha isto
                            db.execSQL("Insert into Achievements values (19,'Heavy wants this!', 'You cleared all TF2 themed questions')");
                            db.execSQL("Insert into Achievements values (20,'I am planting the bomb', 'You cleared all CS:GO themed questions')");
                            db.execSQL("Insert into Achievements values (21,'Deploying Freddy Fazbear', 'You cleared all FNaF themed questions')");
                            //literalmente o nome da empresa do professor (pra dar gracha pra ires pra lá estagiar porque DINHEIRO
                            db.execSQL("Insert into Achievements values (22,'Next stop: Redshift', 'You cleared all Programming themed questions')");
                            db.execSQL("Insert into Achievements values (23,'Generalist', 'You cleared all General Knowledge themed questions')");
                            db.execSQL("Insert into Achievements values (24,'Sentence: Completed', 'You cleared all Complete the Sentence themed questions')");
                            //Exec to insert data in Shop
                            db.execSQL("Insert into Shop values (1,'T-shirt Size S', 56)");
                            db.execSQL("Insert into Shop values (2,'T-shirt Size M',80)");
                            db.execSQL("Insert into Shop values (3,'T-shirt Size L',96)");

                            // Add questions for the CS:GO theme
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('What is the most played CS:GO map?', 4, 'Dust 2', 'Mirage', 'Inferno', 'Nuke')");

                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Which weapon has the highest price in CS:GO?', 4, 'AWP', 'M4A4', 'AK-47', 'SCAR-20')");

                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('What is the maximum number of players on a CS:GO competitive team?', 4, '5', '4', '6', '7')");

                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('What is the name of the terrorist group in CS:GO?', 4, 'Phoenix Connexion', 'Elite Crew', 'Guerrilla Warfare', 'Separatists')");

                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Which CS:GO operation introduced the battle royale mode?', 4, 'Danger Zone', 'Shattered Web', 'Operation Hydra', 'Operation Wildfire')");

                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Which team won the CS:GO Major Championship in 2019?', 4, 'Astralis', 'Liquid', 'Fnatic', 'NaVi')");

                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('What is the name of the CS:GO knife with a tiger tooth pattern?', 4, 'Karambit', 'Butterfly Knife', 'M9 Bayonet', 'Gut Knife')");
                            // Add questions for the General Knowledge theme
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('What is the capital of France?', 1, 'Paris', 'Rome', 'Madrid', 'London')");

                            // Add questions for the Team Fortress 2 theme
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Which class in TF2 can build sentry guns?', 2, 'Engineer', 'Medic', 'Pyro', 'Spy')");

                            // Add questions for the Programming theme
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('What is the most popular programming language?', 3, 'Java', 'Python', 'C++', 'JavaScript')");

                            // Add questions for the Five Night's at Freddy's theme
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Who is the main animatronic character in FNaF?', 5, 'Freddy Fazbear', 'Chica', 'Bonnie', 'Foxx')");

                            // Add questions for the Complete The Sentence theme
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('The quick brown __ jumps over the lazy dog.', 6, 'Fox', 'Cat', 'Dog', 'Bird')");
                        }
                    }).build();
        }
        return INSTANCE;
    }
}

