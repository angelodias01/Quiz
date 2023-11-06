package quiz.app.project.dias.dias.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

import quiz.app.project.dias.dias.model.usercurrency.UserCurrency;
import quiz.app.project.dias.dias.model.usercurrency.UserCurrencyRepo;

public class UserCurrencyViewModel extends AndroidViewModel {
    private UserCurrencyRepo repository;

    public UserCurrencyViewModel(@NonNull Application application) {
        super(application);
        // Initialize repository
        this.repository = new UserCurrencyRepo(application.getApplicationContext());
    }

    public LiveData<UserCurrency> getUserCurrencyByUserId(int userId) {
        return this.repository.getUserCurrencyByUserId(userId);
    }

    public LiveData<List<UserCurrency>> getAllCurrencies() {
        return this.repository.getAllCurrencies();
    }

    public LiveData<UserCurrency> getCurrencyById(int currencyId) {
        return this.repository.getCurrencyById(currencyId);
    }

    public LiveData<List<UserCurrency>> getCurrenciesByUserId(int userId) {
        return this.repository.getCurrenciesByUserId(userId);
    }
    public void insertCurrency(UserCurrency userCurrency) {
        this.repository.insertCurrency(userCurrency);
    }

    public void updateValue(int userId) {
        this.repository.updateValue(userId);
    }

    public LiveData<Integer> getCollectedCoins(int userId) {
        return this.repository.getCollectedCoins(userId);
    }
}
