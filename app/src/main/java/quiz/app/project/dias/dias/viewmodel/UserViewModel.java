/**
 * ViewModel class for managing User data.
 */
package quiz.app.project.dias.dias.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
import quiz.app.project.dias.dias.model.user.User;
import quiz.app.project.dias.dias.model.user.UserRepo;

public class UserViewModel extends AndroidViewModel {
    private UserRepo repository;

    /**
     * Constructor for the UserViewModel.
     *
     * @param application The application instance.
     */
    public UserViewModel(@NonNull Application application) {
        super(application);
        // Initialize repository
        this.repository = new UserRepo(application.getApplicationContext());
    }

    /**
     * Get LiveData containing User information for a specific username.
     *
     * @param username Username of the user to retrieve.
     * @return LiveData<User> representing User information for the username.
     */
    public LiveData<User> getUserByUsername(String username) {
        return repository.getUserByUsername(username);
    }

    /**
     * Get LiveData containing a list of all User objects.
     *
     * @return LiveData<List<User>> representing a list of all User objects.
     */
    public LiveData<List<User>> getAllUsers() {
        return repository.getAllUsers();
    }

    /**
     * Get LiveData containing User information for a specific user ID.
     *
     * @param userId ID of the user to retrieve.
     * @return LiveData<User> representing User information for the user.
     */
    public LiveData<User> getUserById(int userId) {
        return this.repository.getUserById(userId);
    }

    /**
     * Get LiveData containing User information for a specific email.
     *
     * @param email Email of the user to retrieve.
     * @return LiveData<User> representing User information for the email.
     */
    public LiveData<User> getUserByEmail(String email) {
        return this.repository.getUserByEmail(email);
    }

    /**
     * Get LiveData containing User information for a specific email and password combination.
     *
     * @param email    Email of the user.
     * @param password Password of the user.
     * @return LiveData<User> representing User information for the email and password combination.
     */
    public LiveData<User> getUserByEmailAndPassword(String email, String password) {
        return this.repository.getUserByEmailAndPassword(email, password);
    }

    /**
     * Get LiveData containing User information for a specific password.
     *
     * @param password Password of the user to retrieve.
     * @return LiveData<User> representing User information for the password.
     */
    public LiveData<User> getUserByPassword(String password) {
        return this.repository.getUserByPassword(password);
    }

    /**
     * Insert one or more User objects into the database.
     *
     * @param users User objects to be inserted.
     */
    public void insertAll(User... users) {
        this.repository.insertAll(users);
    }

    /**
     * Update a User object in the database.
     *
     * @param user User object to be updated.
     */
    public void updateAll(User user) {
        this.repository.updateAll(user);
    }

    /**
     * Delete a User object from the database.
     *
     * @param user User object to be deleted.
     */
    public void deleteAll(User user) {
        this.repository.deleteAll(user);
    }

    /**
     * Delete a User object from the database based on the user ID.
     *
     * @param userId ID of the user to be deleted.
     */
    public void deleteUserById(int userId) {
        this.repository.deleteUserById(userId);
    }

    /**
     * Update the currency amount of a specific user.
     *
     * @param user User object with updated currency amount.
     */
    public void updateCurrencyAmount(User user) {
        this.repository.updateCurrencyAmount(user);
    }
}
