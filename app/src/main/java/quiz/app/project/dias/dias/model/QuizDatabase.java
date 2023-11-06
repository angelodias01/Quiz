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
import quiz.app.project.dias.dias.model.theme.Theme;
import quiz.app.project.dias.dias.model.theme.ThemeDao;
import quiz.app.project.dias.dias.model.user.User;
import quiz.app.project.dias.dias.model.user.UserDao;
import quiz.app.project.dias.dias.model.usercurrency.UserCurrency;
import quiz.app.project.dias.dias.model.usercurrency.UserCurrencyDao;

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
                            db.execSQL("Insert into Achievements values (6,'Tough Mind', 'Get a winning-streak on 3 quizzes with no mistakes')");
                            db.execSQL("Insert into Achievements values (7,'A.I.', 'Get a winning-streak on 10 quizzes with no mistakes')");
                            db.execSQL("Insert into Achievements values (8,'Pootis Penser Here!', 'You clicked somewhere heavy wanted!')");
                            db.execSQL("Insert into Achievements values (9,'101 Questions', 'You went trought 101 questions. Simple')");
                            db.execSQL("Insert into Achievements values (10,'Medic!', 'You got assisted in a question')");
                            db.execSQL("Insert into Achievements values (11,'Weekly Challenger', 'You won the weekly challenge')");
                            db.execSQL("Insert into Achievements values (12,'Well Played', 'You got 50 correct questions')");
                            db.execSQL("Insert into Achievements values (13,'The Investor', 'You collected 100 coins')");
                            db.execSQL("Insert into Achievements values (14,'Smart Investment', 'You wasted 110 coins')");
                            db.execSQL("Insert into Achievements values (15,'High 5 player', 'You played 5 quizzes')");
                            db.execSQL("Insert into Achievements values (16,'Gimme 10!', 'You played 10 quizzes')");
                            db.execSQL("Insert into Achievements values (17,'Down and give me 20!', 'You played 20 quizzes')");
                            db.execSQL("Insert into Achievements values (18,'101 reasons to...', 'You played 101 quizzes')");
                            db.execSQL("Insert into Achievements values (19,'Heavy wants this!', 'You cleared all TF2 themed questions')");
                            db.execSQL("Insert into Achievements values (20,'I am planting the bomb', 'You cleared all CS:GO themed questions')");
                            db.execSQL("Insert into Achievements values (21,'Deploying Freddy Fazbear', 'You cleared all FNaF themed questions')");
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
                                    "('What is the capital of Australia?', 1, 'Camberra', 'Sydney', 'Crykey', 'Solemn')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Fastest speed in the world. By an animal. Who has it?', 1, 'Peregrine Falcon', 'Cheetah', 'Tiger', 'Deer')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Which of these places is the coldest?', 1, 'Sahara desert', 'Lisbon', 'Punta Cana', 'Amazon forest')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Painter of the Mona Lisa?', 1, 'Leonardo da Vinci', 'Pablo Picasso', 'Michelangelo', 'Van Gogh')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('What’s the red planet?', 1, 'Mars', 'Jupiter', 'Esperanto', 'Saturn')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('How many different characters are in a binary code?', 1, '2', '12', '64', '8')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('What’s the country known as the Land of the Rising Sun?', 1, 'Japan', 'China', 'USA', 'UK')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Father of the Modern Physics. Who does this reference?', 1, 'Albert Einstein', 'Nikolas Tesla', 'Galileo Galilei', 'Isaac Newton')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Where was the 2016 Summer Olympics hosted?', 1, 'Rio de Janeiro', 'Tokyo', 'Lisbon', 'Uganda')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Who were the champions of the 2016 Euro cup?', 1, 'Portugal', 'France', 'England', 'Spain')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Best soccer player in the world, based on achievements?', 1, 'Ronaldo', 'Messi', 'Haaland', 'Eusébio')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('What countries share the biggest international border?', 1, 'USA and Canada', 'Russia and China', 'Portugal and Angola', 'India and China')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Which year did WW2 end?', 1, '1945', '1950', '1922', '1935')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('What’s the largest internal organ a human being has?', 1, 'Liver', 'Heart', 'Skin', 'Lungs')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Which element has the chemical symbol K?', 1, 'Potassium', 'Iron', 'Gold', 'Uranium')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Tallest waterfall in the world?', 1, 'Angel Falls', 'Niagara Falls', 'Gravity Falls', 'Victoria Falls')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('First person to walk on the moon was the astronaut…', 1, 'Neil Armstrong', 'Yuri Gagarin', 'Alan Shepard', 'Buzz Aldrin')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('What’s the biggest ocean in the world?', 1, 'Pacific', 'Atlantic', 'Indian', 'Southern')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('What country makes the biggest amount of chocolate in the world?', 1, 'Ivory Coast', 'Switzerland', 'Uganda', 'USA')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Who is the goddess of wisdom, based on Greek mythology?', 1, 'Athena', 'Hera', 'Aphrodite', 'Artemis')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Which gas makes up the majority of our planet’s atmosphere?', 1, 'Nitrogen', 'Hydrogen', 'Oxygen', 'Carbon Dioxide')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Who is the author of the book \"The Art of War\"?', 1, 'Sun Tzu', 'Niccolò Machiavelli', 'Sun Yat-sen', 'Confucius')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Which famous scientist developed the theory of relativity?', 1, 'Albert Einstein', 'Isaac Newton', 'Galileo Galilei', 'Nikola Tesla')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Who was the first person to reach the summit of Mount Everest?', 1, 'Sir Edmund Hillary', 'Tenzing Norgay', 'Reinhold Messner', 'Junko Tabei')");

                            // Add questions for the Team Fortress 2 theme
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Qual é a classe que pode construir sentry guns?', 2, 'Engineer', 'Medic', 'Pyro', 'Spy')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Qual é o nome do chefe robô no modo Mann vs. Machine?', 2, 'Gray Mann', 'Redmond Mann', 'Blutarch Mann', 'Miss Pauling')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Qual é o nome do mapa mais jogado de Team Fortress 2?', 2, '2Fort', 'Badwater Basin', 'Dustbowl', 'Upward')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Qual é a arma primária do Sniper?', 2, 'Rifle de Precisão', 'Submetralhadora SMG', 'Arco de Caçador', 'Fuzil de Caça')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Qual é o nome do Spy francês?', 2, 'Le Fantôme', 'LÉtranger', 'LInspecteur', 'La Résistance')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Qual é a classe que pode disparar flechas curativas?', 2, 'Medic', 'Sniper', 'Pyro', 'Engineer')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Qual é o nome do modo de jogo de ataque e defesa?', 2, 'Payload', 'King of the Hill', 'Capture the Flag', 'Mann vs. Machine')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Qual é o nome do chefe robô no modo Mann vs. Machine?', 2, 'Gray Mann', 'Redmond Mann', 'Blutarch Mann', 'Miss Pauling')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Quantos jogadores podem jogar em uma equipe no modo Capture the Flag?', 2, '12', '8', '16', '10')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Qual é o nome do lança-foguetes do Soldier?', 2, 'Rocket Launcher', 'Shotgun', 'Black Box', 'Beggar''s Bazooka')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Qual é a habilidade especial do Heavy?', 2, 'Sasha Minigun', 'Backstab', 'ÜberCharge', 'Cloak and Dagger')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Qual é o nome do lança-chamas do Pyro?', 2, 'Flamethrower', 'Rocket Launcher', 'Grenade Launcher', 'Stickybomb Launcher')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Qual é o nome do modo de jogo onde duas equipes se enfrentam em uma batalha?', 2, 'Team Deathmatch', 'Capture the Flag', 'King of the Hill', 'Payload')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Qual é o nome do lança-granadas do Demoman?', 2, 'Grenade Launcher', 'Rocket Launcher', 'Minigun', 'Stickybomb Launcher')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Quantas classes jogáveis existem no Team Fortress 2?', 2, '9', '6', '12', '8')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Qual é o nome da arma corpo a corpo do Scout?', 2, 'Bat', 'Wrench', 'Sledgehammer', 'Knife')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Qual é o nome do modo de jogo onde uma equipe precisa capturar e segurar um ponto de controle?', 2, 'Control Point', 'Payload', 'King of the Hill', 'Capture the Flag')");
                            db.execSQL("INSERT INTO Questions (questionsText, themeId, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3) VALUES " +
                                    "('Qual é o nome da arma corpo a corpo do Sniper?', 2, 'Kukri', 'Wrench', 'Frying Pan', 'Bottle')");

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

