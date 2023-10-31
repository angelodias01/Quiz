package quiz.app.project.dias.dias.model.usercurrency;

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
    UserCurrency getUserCurrencyByUserId(int userId);

    @Query("SELECT * FROM UserCurrency")
    List<UserCurrency> getAllCurrencies();

    @Query("SELECT * FROM UserCurrency WHERE currencyId = :currencyId")
    UserCurrency getCurrencyById(int currencyId);

    @Query("SELECT * FROM UserCurrency WHERE userId = :userId")
    List<UserCurrency> getCurrenciesByUserId(int userId);
    @Query("update UserCurrency set amount = 0 where userId = :userId")
    void updateValue(int userId);

    @Query("SELECT SUM(amount) FROM UserCurrency WHERE userId = :userId")
    int getCollectedCoins(int userId);

    //@Query("SELECT SUM(coinsWasted) FROM Score WHERE userId = :userId")
    //int getWastedCoins(int userId);

}
