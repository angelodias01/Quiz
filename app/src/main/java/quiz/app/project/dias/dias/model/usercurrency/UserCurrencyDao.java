/**
 * UserCurrencyDao.java
 * Data Access Object (DAO) interface for interacting with the UserCurrency table in the Room database.
 */
package quiz.app.project.dias.dias.model.usercurrency;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
@Dao
public interface UserCurrencyDao {

    // Inserts a UserCurrency entry into the UserCurrency table.
    @Insert
    void insertCurrency(UserCurrency userCurrency);

    // Updates an existing UserCurrency entry in the UserCurrency table.
    @Update
    void updateCurrency(UserCurrency userCurrency);

    // Deletes a UserCurrency entry from the UserCurrency table.
    @Delete
    void deleteCurrency(UserCurrency userCurrency);

    // Retrieves a UserCurrency entry based on the associated userId (LiveData version).
    @Query("SELECT * FROM UserCurrency WHERE userId = :userId")
    LiveData<UserCurrency> getUserCurrencyByUserId(int userId);

    // Retrieves a list of UserCurrency entries based on the associated userId.
    @Query("SELECT * FROM UserCurrency WHERE userId = :userId")
    List<UserCurrency> getUserCurrencysByUserId(int userId);

    // Retrieves all UserCurrency entries in the UserCurrency table (LiveData version).
    @Query("SELECT * FROM UserCurrency")
    LiveData<List<UserCurrency>> getAllCurrencies();

    // Retrieves a UserCurrency entry based on its unique currencyId (LiveData version).
    @Query("SELECT * FROM UserCurrency WHERE currencyId = :currencyId")
    LiveData<UserCurrency> getCurrencyById(int currencyId);

    // Retrieves a list of UserCurrency entries based on the associated userId.
    @Query("SELECT * FROM UserCurrency WHERE userId = :userId")
    LiveData<List<UserCurrency>> getCurrenciesByUserId(int userId);

    // Updates the amount of currency to 0 for a specific userId.
    @Query("update UserCurrency set amount = 0 where userId = :userId")
    void updateValue(int userId);

    // Updates the amount of currency to a specified value for a specific userId.
    @Query("update UserCurrency set amount = :value where userId = :userId")
    void updateValues(int value, int userId);

    // Retrieves the sum of currency amounts collected by a specific userId (LiveData version).
    @Query("SELECT SUM(amount) FROM UserCurrency WHERE userId = :userId")
    LiveData<Integer> getCollectedCoins(int userId);
}