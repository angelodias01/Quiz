/**
 * UserCurrencyRepo.java
 * Repository class for interacting with the UserCurrency table in the Room database.
 */
package quiz.app.project.dias.dias.model.usercurrency;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.room.Query;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import quiz.app.project.dias.dias.model.QuizDatabase;
public class UserCurrencyRepo {

    private UserCurrencyDao userCurrencyDao;
    private Executor executor = Executors.newSingleThreadExecutor();

    public UserCurrencyRepo(Context context) {
        this.userCurrencyDao = QuizDatabase.getInstance(context).getUserCurrencyDao();
    }

    // Retrieves UserCurrency based on the associated userId (LiveData version).
    public LiveData<UserCurrency> getUserCurrencyByUserId(int userId) {
        return this.userCurrencyDao.getUserCurrencyByUserId(userId);
    }

    // Retrieves a list of UserCurrency entries based on the associated userId.
    public List<UserCurrency> getUserCurrencysByUserId(int userId) {
        return this.userCurrencyDao.getUserCurrencysByUserId(userId);
    }

    // Retrieves all UserCurrency entries in the UserCurrency table (LiveData version).
    public LiveData<List<UserCurrency>> getAllCurrencies() {
        return this.userCurrencyDao.getAllCurrencies();
    }

    // Retrieves UserCurrency based on its unique currencyId (LiveData version).
    public LiveData<UserCurrency> getCurrencyById(int currencyId) {
        return this.userCurrencyDao.getCurrencyById(currencyId);
    }

    // Retrieves a list of UserCurrency entries based on the associated userId.
    public LiveData<List<UserCurrency>> getCurrenciesByUserId(int userId) {
        return this.userCurrencyDao.getCurrenciesByUserId(userId);
    }

    // Updates the amount of currency to 0 for a specific userId.
    public void updateValue(int userId) {
        this.userCurrencyDao.updateValue(userId);
    }

    // Retrieves the sum of currency amounts collected by a specific userId (LiveData version).
    public LiveData<Integer> getCollectedCoins(int userId) {
        return this.userCurrencyDao.getCollectedCoins(userId);
    }

    // Updates an existing UserCurrency entry in the background using a separate thread.
    public void updateCurrency(UserCurrency userCurrency) {
        executor.execute(() -> {
            userCurrencyDao.updateCurrency(userCurrency);
        });
    }

    // Updates the amount of currency to a specified value for a specific userId in the background.
    public void updateValueInBackground(int value, int userId) {
        executor.execute(() -> {
            userCurrencyDao.updateValues(value, userId);
        });
    }

    // Inserts a new UserCurrency entry in the background using a separate thread.
    public void insertCurrency(UserCurrency userCurrency) {
        executor.execute(() -> {
            userCurrencyDao.insertCurrency(userCurrency);
        });
    }
}