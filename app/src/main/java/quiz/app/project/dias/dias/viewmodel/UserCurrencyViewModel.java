/**
 * ViewModel class for managing UserCurrency data.
 */
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

    /**
     * Constructor for the UserCurrencyViewModel.
     *
     * @param application The application instance.
     */
    public UserCurrencyViewModel(@NonNull Application application) {
        super(application);
        // Initialize repository
        this.repository = new UserCurrencyRepo(application.getApplicationContext());
    }

    /**
     * Get LiveData containing UserCurrency information for a specific user.
     *
     * @param userId ID of the user to retrieve UserCurrency information.
     * @return LiveData<UserCurrency> representing UserCurrency information for the user.
     */
    public LiveData<UserCurrency> getUserCurrencyByUserId(int userId) {
        return this.repository.getUserCurrencyByUserId(userId);
    }

    /**
     * Get a list of UserCurrency objects for a specific user.
     *
     * @param userId ID of the user to retrieve UserCurrency objects.
     * @return List<UserCurrency> representing UserCurrency objects for the user.
     */
    public List<UserCurrency> getUserCurrencysByUserId(int userId) {
        return this.repository.getUserCurrencysByUserId(userId);
    }

    /**
     * Get LiveData containing a list of all UserCurrency objects.
     *
     * @return LiveData<List < UserCurrency>> representing a list of all UserCurrency objects.
     */
    public LiveData<List<UserCurrency>> getAllCurrencies() {
        return this.repository.getAllCurrencies();
    }

    /**
     * Get LiveData containing UserCurrency information for a specific currency ID.
     *
     * @param currencyId ID of the currency to retrieve.
     * @return LiveData<UserCurrency> representing UserCurrency information for the currency.
     */
    public LiveData<UserCurrency> getCurrencyById(int currencyId) {
        return this.repository.getCurrencyById(currencyId);
    }

    /**
     * Get LiveData containing a list of UserCurrency objects for a specific user.
     *
     * @param userId ID of the user to retrieve UserCurrency objects.
     * @return LiveData<List < UserCurrency>> representing UserCurrency objects for the user.
     */
    public LiveData<List<UserCurrency>> getCurrenciesByUserId(int userId) {
        return this.repository.getCurrenciesByUserId(userId);
    }

    /**
     * Insert a new UserCurrency object.
     *
     * @param userCurrency UserCurrency object to be inserted.
     */
    public void insertCurrency(UserCurrency userCurrency) {
        this.repository.insertCurrency(userCurrency);
    }

    /**
     * Update the value of a specific user's UserCurrency object.
     *
     * @param userId ID of the user to update.
     */
    public void updateValue(int userId) {
        this.repository.updateValue(userId);
    }

    /**
     * Update the value of a specific user's UserCurrency object in the background.
     *
     * @param value  New value to set.
     * @param userId ID of the user to update.
     */
    public void updateValueInBackground(int value, int userId) {
        this.repository.updateValueInBackground(value, userId);
    }

    /**
     * Get LiveData containing the total collected coins for a specific user.
     *
     * @param userId ID of the user to retrieve collected coins.
     * @return LiveData<Integer> representing the total collected coins for the user.
     */
    public LiveData<Integer> getCollectedCoins(int userId) {
        return this.repository.getCollectedCoins(userId);
    }

    /**
     * Update a UserCurrency object.
     *
     * @param userCurrency UserCurrency object to be updated.
     */
    public void updateCurrency(UserCurrency userCurrency) {
        this.repository.updateCurrency(userCurrency);
    }
}
