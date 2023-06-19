package quiz.app.project.dias.dias.QuizDatabase.UserCurrencyDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Currency;
import java.util.List;

@Dao
public interface UserCurrencyDao {
    @Insert
    void insertCurrency(UserCurrency userCurrency);

    @Update
    void updateCurrency(UserCurrency userCurrency);

    @Delete
    void deleteCurrency(UserCurrency userCurrency);

    @Query("SELECT * FROM UserCurrency")
    List<Currency> getAllCurrencies();

    @Query("SELECT * FROM UserCurrency WHERE currencyId = :currencyId")
    Currency getCurrencyById(int currencyId);

    @Query("SELECT * FROM UserCurrency WHERE userId = :userId")
    List<Currency> getCurrenciesByUserId(int userId);
}
