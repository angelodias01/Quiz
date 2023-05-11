package quiz.app.project.dias.dias.QuizDatabase;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import quiz.app.project.dias.dias.QuizDatabase.ScoreDB.Score;
import quiz.app.project.dias.dias.QuizDatabase.ScoreDB.ScoreDao;
import quiz.app.project.dias.dias.QuizDatabase.ThemeDB.Theme;
import quiz.app.project.dias.dias.QuizDatabase.ThemeDB.ThemeDao;
import quiz.app.project.dias.dias.QuizDatabase.UserDB.User;
import quiz.app.project.dias.dias.QuizDatabase.UserDB.UserDao;

@Database(entities = {User.class}, version = 1)
public abstract class QuizDatabase extends RoomDatabase {
    private static QuizDatabase INSTANCE;
    public abstract UserDao getUserDao();

    public static QuizDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            QuizDatabase.class, "QuizDatabase").allowMainThreadQueries()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            db.execSQL("INSERT INTO User VALUES ('admin', 'admin@gmail.com', 'admin')");
                        }
                    })
                    .build();
        }
        return INSTANCE;
    }
}