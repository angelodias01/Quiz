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
                                    "('Which weapon has the highest price in CS:GO?', 8, 'AWP', 'M4A4', 'AK-47', 'SCAR-20')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('What is the maximum number of players on a CS:GO competitive team?', 8, '5', '4', '6', '7')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('What is the name of the terrorist group in CS:GO?', 8, 'Phoenix Connexion', 'Elite Crew', 'Guerrilla Warfare', 'Separatists')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Which CS:GO operation introduced the battle royale mode?', 8, 'Danger Zone', 'Shattered Web', 'Operation Hydra', 'Operation Wildfire')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Which team won the CS:GO Major Championship in 2019?', 8, 'Astralis', 'Liquid', 'Fnatic', 'NaVi')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('What is the name of the CS:GO knife with a tiger tooth pattern?', 8, 'Karambit', 'Butterfly Knife', 'M9 Bayonet', 'Gut Knife')");

                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('What is the capital of Australia?', 35, 'Camberra', 'Sydney', 'Crykey', 'Solemn')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Fastest speed in the world. By an animal. Who has it?', 35, 'Peregrine Falcon', 'Cheetah', 'Tiger', 'Deer')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Which of these places is the coldest?', 35, 'Sahara desert', 'Lisbon', 'Punta Cana', 'Amazon forest')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Painter of the Mona Lisa?', 35, 'Leonardo da Vinci', 'Pablo Picasso', 'Michelangelo', 'Van Gogh')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('What’s the red planet?', 35, 'Mars', 'Jupiter', 'Esperanto', 'Saturn')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('How many different characters are in a binary code?', 35, '2', '12', '64', '8')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('What’s the country known as the Land of the Rising Sun?', 35, 'Japan', 'China', 'USA', 'UK')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Father of the Modern Physics. Who does this reference?', 35, 'Albert Einstein', 'Nikolas Tesla', 'Galileo Galilei', 'Isaac Newton')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Where was the 2016 Summer Olympics hosted?', 35, 'Rio de Janeiro', 'Tokyo', 'Lisbon', 'Uganda')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Who were the champions of the 2016 Euro cup?',35, 'Portugal', 'France', 'England', 'Spain')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Best soccer player in the world, based on achievements?', 35, 'Ronaldo', 'Messi', 'Haaland', 'Eusébio')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('What countries share the biggest international border?', 35, 'USA and Canada', 'Russia and China', 'Portugal and Angola', 'India and China')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Which year did WW2 end?', 35, '1945', '1950', '1922', '1935')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('What’s the largest internal organ a human being has?', 35, 'Liver', 'Heart', 'Skin', 'Lungs')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Which element has the chemical symbol K?', 35, 'Potassium', 'Iron', 'Gold', 'Uranium')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Tallest waterfall in the world?', 35, 'Angel Falls', 'Niagara Falls', 'Gravity Falls', 'Victoria Falls')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('First person to walk on the moon was the astronaut…', 35, 'Neil Armstrong', 'Yuri Gagarin', 'Alan Shepard', 'Buzz Aldrin')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('What’s the biggest ocean in the world?', 35, 'Pacific', 'Atlantic', 'Indian', 'Southern')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('What country makes the biggest amount of chocolate in the world?', 35, 'Ivory Coast', 'Switzerland', 'Uganda', 'USA')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Who is the goddess of wisdom, based on Greek mythology?', 35, 'Athena', 'Hera', 'Aphrodite', 'Artemis')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Which gas makes up the majority of our planet’s atmosphere?', 35, 'Nitrogen', 'Hydrogen', 'Oxygen', 'Carbon Dioxide')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Who is the author of the book \"The Art of War\"?', 35, 'Sun Tzu', 'Niccolò Machiavelli', 'Sun Yat-sen', 'Confucius')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Which famous scientist developed the theory of relativity?', 35, 'Albert Einstein', 'Isaac Newton', 'Galileo Galilei', 'Nikola Tesla')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Who was the first person to reach the summit of Mount Everest?', 35, 'Sir Edmund Hillary', 'Tenzing Norgay', 'Reinhold Messner', 'Junko Tabei')");

                            // Add questions for the Team Fortress 2 theme
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Qual é a classe que pode construir sentry guns?', 51, 'Engineer', 'Medic', 'Pyro', 'Spy')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Qual é o nome do chefe robô no modo Mann vs. Machine?', 51, 'Gray Mann', 'Redmond Mann', 'Blutarch Mann', 'Miss Pauling')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Qual é o nome do mapa mais jogado de Team Fortress 2?', 51, '2Fort', 'Badwater Basin', 'Dustbowl', 'Upward')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Qual é a arma primária do Sniper?', 51, 'Rifle de Precisão', 'Submetralhadora SMG', 'Arco de Caçador', 'Fuzil de Caça')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Qual é o nome do Spy francês?', 51, 'Le Fantôme', 'LÉtranger', 'LInspecteur', 'La Résistance')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Qual é a classe que pode disparar flechas curativas?', 51, 'Medic', 'Sniper', 'Pyro', 'Engineer')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Qual é o nome do modo de jogo de ataque e defesa?', 51, 'Payload', 'King of the Hill', 'Capture the Flag', 'Mann vs. Machine')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Qual é o nome do chefe robô no modo Mann vs. Machine?', 51, 'Gray Mann', 'Redmond Mann', 'Blutarch Mann', 'Miss Pauling')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Quantos jogadores podem jogar em uma equipe no modo Capture the Flag?', 51, '12', '8', '16', '10')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Qual é o nome do lança-foguetes do Soldier?', 51, 'Rocket Launcher', 'Shotgun', 'Black Box', 'Beggar''s Bazooka')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Qual é a habilidade especial do Heavy?', 51, 'Sasha Minigun', 'Backstab', 'ÜberCharge', 'Cloak and Dagger')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Qual é o nome do lança-chamas do Pyro?', 51, 'Flamethrower', 'Rocket Launcher', 'Grenade Launcher', 'Stickybomb Launcher')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Qual é o nome do modo de jogo onde duas equipes se enfrentam em uma batalha?', 51, 'Team Deathmatch', 'Capture the Flag', 'King of the Hill', 'Payload')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Qual é o nome do lança-granadas do Demoman?', 51, 'Grenade Launcher', 'Rocket Launcher', 'Minigun', 'Stickybomb Launcher')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Quantas classes jogáveis existem no Team Fortress 2?', 51, '9', '6', '12', '8')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Qual é o nome da arma corpo a corpo do Scout?', 51, 'Bat', 'Wrench', 'Sledgehammer', 'Knife')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Qual é o nome da arma corpo a corpo do Sniper?', 51, 'Kukri', 'Wrench', 'Frying Pan', 'Bottle')");



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

