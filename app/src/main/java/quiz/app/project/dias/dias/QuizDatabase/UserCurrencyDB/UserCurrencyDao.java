package quiz.app.project.dias.dias.QuizDatabase.UserCurrencyDB;

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

    @Query("SELECT * FROM UserCurrency")
    List<UserCurrency> getAllCurrencies();

    @Query("SELECT * FROM UserCurrency WHERE currencyId = :currencyId")
    UserCurrency getCurrencyById(int currencyId);

    @Query("SELECT * FROM UserCurrency WHERE userId = :userId")
    List<UserCurrency> getCurrenciesByUserId(int userId);
}
