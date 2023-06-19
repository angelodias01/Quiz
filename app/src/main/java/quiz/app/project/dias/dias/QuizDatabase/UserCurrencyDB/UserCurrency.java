package quiz.app.project.dias.dias.QuizDatabase.UserCurrencyDB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import quiz.app.project.dias.dias.QuizDatabase.UserDB.User;

@Entity(tableName = "UserCurrency", foreignKeys = @ForeignKey(entity= User.class, parentColumns="userId", childColumns="userId"))
public class UserCurrency {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "currencyId")
    @NonNull
    private int currencyId;

    @NonNull
    @ColumnInfo(name = "userId")
    private int userId;

    @NonNull
    @ColumnInfo(name = "amount")
    private double amount;

    public UserCurrency(int userId, double amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
