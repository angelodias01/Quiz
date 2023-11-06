package quiz.app.project.dias.dias.model.user;

import android.content.Context;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import quiz.app.project.dias.dias.model.QuizDatabase;


public class UserRepo {
    private UserDao userDao;
    private Executor executor = Executors.newSingleThreadExecutor();

    public UserRepo(Context context) {
        this.userDao = QuizDatabase.getInstance(context).getUserDao();
    }

    public LiveData<User> getUserByEmailAndPassword(String email, String password) {
        return userDao.getUserByEmailAndPassword(email, password);
    }
    public LiveData<List<User>> getAllUsers() {
        return userDao.getAllUsers();
    }

    public LiveData<User> getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    public LiveData<User> getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    public LiveData<User> getUserByPassword(String password) {
        return userDao.getUserByPassword(password);
    }

    public void insertAll(User... user) {
        executor.execute(() -> userDao.insertAll(user));
    }

    public void updateAll(User user) {
        executor.execute(() -> userDao.updateAll(user));
    }

    public void deleteAll(User user) {
        executor.execute(() -> userDao.deleteAll(user));
    }

    public void deleteUserById(int userId) {
        executor.execute(() -> userDao.deleteUserById(userId));
    }

    public void updateCurrencyAmount(User user) {
        executor.execute(() -> userDao.updateCurrencyAmount(user));
    }
}
