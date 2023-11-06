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
    @Insert
    void insertCurrency(UserCurrency userCurrency);

    @Update
    void updateCurrency(UserCurrency userCurrency);

    @Delete
    void deleteCurrency(UserCurrency userCurrency);

    @Query("SELECT * FROM UserCurrency WHERE userId = :userId")
    LiveData<UserCurrency> getUserCurrencyByUserId(int userId);

    @Query("SELECT * FROM UserCurrency")
    LiveData<List<UserCurrency>> getAllCurrencies();

    @Query("SELECT * FROM UserCurrency WHERE currencyId = :currencyId")
    LiveData<UserCurrency> getCurrencyById(int currencyId);

    @Query("SELECT * FROM UserCurrency WHERE userId = :userId")
    LiveData<List<UserCurrency>> getCurrenciesByUserId(int userId);

    @Query("update UserCurrency set amount = 0 where userId = :userId")
    void updateValue(int userId);

    @Query("SELECT SUM(amount) FROM UserCurrency WHERE userId = :userId")
    LiveData<Integer> getCollectedCoins(int userId);
}
