package quiz.app.project.dias.dias.QuizDatabase;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import quiz.app.project.dias.dias.QuizDatabase.UserDB.User;
import quiz.app.project.dias.dias.QuizDatabase.UserDB.UserDao;

@Database(entities = {User.class}, version = 1)
public abstract class QuizDatabase extends RoomDatabase {
    private static QuizDatabase INSTANCE;
    public abstract UserDao getUserDao();
    String sqlCommand = "INSERT INTO User VALUES (1, 'admin', 'admin@gmail.com', 'admin', 1)";

    public static synchronized QuizDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            QuizDatabase.class, "QuizDatabase").allowMainThreadQueries()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            //code not working!!!
                            db.execSQL(INSTANCE.sqlCommand);
                            //Martim sgp

                            db.execSQL("INSERT INTO Theme VALUES ('Space'),('Technology'),('Animals'), ('Music'),('Team Fortress 2')");

                           // db.execSQL("INSERT INTO themeName VALUES ");
                        }
                    })
                    .build();
        }
        return INSTANCE;
    }
}