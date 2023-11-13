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

    public LiveData<UserCurrency> getUserCurrencyByUserId(int userId) {
        return this.userCurrencyDao.getUserCurrencyByUserId(userId);
    }
    public List<UserCurrency> getUserCurrencysByUserId(int userId) {
        return this.userCurrencyDao.getUserCurrencysByUserId(userId);
    }

    public LiveData<List<UserCurrency>> getAllCurrencies() {
        return this.userCurrencyDao.getAllCurrencies();
    }

    public LiveData<UserCurrency> getCurrencyById(int currencyId) {
        return this.userCurrencyDao.getCurrencyById(currencyId);
    }

    public LiveData<List<UserCurrency>> getCurrenciesByUserId(int userId) {
        return this.userCurrencyDao.getCurrenciesByUserId(userId);
    }

    public void updateValue(int userId) {
        this.userCurrencyDao.updateValue(userId);
    }

    public LiveData<Integer> getCollectedCoins(int userId) {
        return this.userCurrencyDao.getCollectedCoins(userId);
    }
    public void updateCurrency(UserCurrency userCurrency) {
        executor.execute(() -> {
            userCurrencyDao.updateCurrency(userCurrency);
        });
    }
    public void updateValueInBackground(int value, int userId) {
        // Perform the update in the background using an executor
        executor.execute(() -> {
            userCurrencyDao.updateValues(value, userId);
        });
    }
    public void insertCurrency(UserCurrency userCurrency) {
        executor.execute(() -> {
            userCurrencyDao.insertCurrency(userCurrency);
        });
    }
}