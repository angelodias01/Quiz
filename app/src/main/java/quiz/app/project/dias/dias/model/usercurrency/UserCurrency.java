/**
 * UserCurrency.java
 * Entity class representing the UserCurrency table in the Room database.
 */

package quiz.app.project.dias.dias.model.usercurrency;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import quiz.app.project.dias.dias.model.user.User;

@Entity(tableName = "UserCurrency",
        foreignKeys = @ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "userId"),
        indices = {@Index("userId")})
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
    private int amount;

    /**
     * Empty constructor for Room database. Should be annotated with @Ignore.
     */
    @Ignore
    public UserCurrency() {
        // Empty constructor for Room database
    }

    /**
     * Constructor for creating a UserCurrency object with userId and amount.
     *
     * @param userId Foreign key referencing the User table's userId column.
     * @param amount Amount of currency associated with the user.
     */
    public UserCurrency(int userId, int amount) {
        this.userId = userId;
        this.amount = amount;
    }

    /**
     * Getter for retrieving the currencyId.
     *
     * @return Unique identifier for each UserCurrency entry.
     */
    public int getCurrencyId() {
        return currencyId;
    }

    /**
     * Setter for setting the currencyId.
     *
     * @param currencyId Unique identifier for each UserCurrency entry.
     */
    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    /**
     * Getter for retrieving the userId.
     *
     * @return Foreign key referencing the User table's userId column.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Setter for setting the userId.
     *
     * @param userId Foreign key referencing the User table's userId column.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Getter for retrieving the amount of currency.
     *
     * @return Amount of currency associated with the user.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Setter for setting the amount of currency.
     *
     * @param amount Amount of currency associated with the user.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }
}