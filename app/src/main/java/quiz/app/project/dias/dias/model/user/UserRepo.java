/**
 * UserRepo.java
 * Repository class for handling interactions between the ViewModel and the UserDao.
 * Acts as a single entry point to the underlying data source (database) for user-related operations.
 */

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
    /**
     * Constructor for UserRepo.
     *
     * @param context The application context used to obtain the UserDao instance.
     */
    public UserRepo(Context context) {
        this.userDao = QuizDatabase.getInstance(context).getUserDao();
    }

    /**
     * Retrieves a user from the database based on the provided username.
     *
     * @param username User's chosen username.
     * @return LiveData object containing the user with the provided username.
     */
    public LiveData<User> getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    /**
     * Retrieves a user from the database based on the provided email and password.
     *
     * @param email    User's email address.
     * @param password User's password.
     * @return LiveData object containing the user with the provided email and password.
     */
    public LiveData<User> getUserByEmailAndPassword(String email, String password) {
        return userDao.getUserByEmailAndPassword(email, password);
    }

    /**
     * Retrieves a list of all users from the database.
     *
     * @return LiveData object containing a list of all users.
     */
    public LiveData<List<User>> getAllUsers() {
        return userDao.getAllUsers();
    }

    /**
     * Retrieves a user from the database based on the provided user ID.
     *
     * @param userId User's unique identifier.
     * @return LiveData object containing the user with the provided user ID.
     */
    public LiveData<User> getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    /**
     * Retrieves a user from the database based on the provided email address.
     *
     * @param email User's email address.
     * @return LiveData object containing the user with the provided email address.
     */
    public LiveData<User> getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    /**
     * Retrieves a user from the database based on the provided password.
     *
     * @param password User's password.
     * @return LiveData object containing the user with the provided password.
     */
    public LiveData<User> getUserByPassword(String password) {
        return userDao.getUserByPassword(password);
    }

    /**
     * Inserts one or more users into the User table.
     *
     * @param user User(s) to be inserted.
     */
    public void insertAll(User... user) {
        executor.execute(() -> userDao.insertAll(user));
    }

    /**
     * Updates a user in the User table.
     *
     * @param user User to be updated.
     */
    public void updateAll(User user) {
        executor.execute(() -> userDao.updateAll(user));
    }

    /**
     * Deletes a user from the User table.
     *
     * @param user User to be deleted.
     */
    public void deleteAll(User user) {
        executor.execute(() -> userDao.deleteAll(user));
    }

    /**
     * Deletes a user from the User table based on the provided user ID.
     *
     * @param userId User's unique identifier.
     */
    public void deleteUserById(int userId) {
        executor.execute(() -> userDao.deleteUserById(userId));
    }

    /**
     * Updates the currency amount for a user in the User table.
     *
     * @param user User with updated currency amount.
     */
    public void updateCurrencyAmount(User user) {
        executor.execute(() -> userDao.updateCurrencyAmount(user));
    }
}