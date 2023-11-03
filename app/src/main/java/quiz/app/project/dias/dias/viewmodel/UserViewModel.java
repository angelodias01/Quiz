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

    public UserViewModel(@NonNull Application application) {
        super(application);
        // Initialize repository
        this.repository = new UserRepo(application.getApplicationContext());
    }

    public LiveData<List<User>> getAllUsers() {
        return repository.getAllUsers();
    }

    public LiveData<User> getUserById(int userId) {
        return this.repository.getUserById(userId);
    }

    public LiveData<User> getUserByEmail(String email) {
        return this.repository.getUserByEmail(email);
    }

    public LiveData<User> getUserByPassword(String password) {
        return this.repository.getUserByPassword(password);
    }

    public void insertAll(User... user) {
        this.repository.insertAll(user);
    }

    public void updateAll(User user) {
        this.repository.updateAll(user);
    }

    public void deleteAll(User user) {
        this.repository.deleteAll(user);
    }

    public void deleteUserById(int userId) {
        this.repository.deleteUserById(userId);
    }

    public void updateCurrencyAmount(User user) {
        this.repository.updateCurrencyAmount(user);
    }
}
