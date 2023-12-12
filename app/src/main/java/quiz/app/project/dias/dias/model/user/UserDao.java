/**
 * UserDao.java
 * Data Access Object (DAO) interface for the User entity. Defines methods to interact with the User table.
 */

package quiz.app.project.dias.dias.model.user;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface UserDao {
    /**
     * Retrieves a user from the database based on the provided email and password.
     *
     * @param email    User's email address.
     * @param password User's password.
     * @return LiveData object containing the user with the provided email and password.
     */
    @Query("SELECT * FROM User WHERE email = :email AND password = :password")
    LiveData<User> getUserByEmailAndPassword(String email, String password);

    /**
     * Retrieves all users from the database.
     *
     * @return LiveData object containing a list of all users.
     */
    @Query("SELECT * FROM User")
    LiveData<List<User>> getAllUsers();

    /**
     * Retrieves a user from the database based on the provided user ID.
     *
     * @param userId User's unique identifier.
     * @return LiveData object containing the user with the provided user ID.
     */
    @Query("SELECT * FROM User WHERE userId = :userId")
    LiveData<User> getUserById(int userId);

    /**
     * Retrieves a user from the database based on the provided username.
     *
     * @param username User's chosen username.
     * @return LiveData object containing the user with the provided username.
     */
    @Query("SELECT * FROM User WHERE username = :username")
    LiveData<User> getUserByUsername(String username);

    /**
     * Retrieves a user from the database based on the provided email address.
     *
     * @param email User's email address.
     * @return LiveData object containing the user with the provided email address.
     */
    @Query("SELECT * FROM User WHERE email = :email")
    LiveData<User> getUserByEmail(String email);

    /**
     * Retrieves a user from the database based on the provided password.
     *
     * @param password User's password.
     * @return LiveData object containing the user with the provided password.
     */
    @Query("SELECT * FROM User WHERE password = :password")
    LiveData<User> getUserByPassword(String password);

    /**
     * Inserts one or more users into the User table.
     *
     * @param user User(s) to be inserted.
     */
    @Insert
    void insertAll(User... user);

    /**
     * Updates a user in the User table.
     *
     * @param user User to be updated.
     */
    @Update
    void updateAll(User user);

    /**
     * Deletes a user from the User table.
     *
     * @param user User to be deleted.
     */
    @Delete
    void deleteAll(User user);

    /**
     * Deletes a user from the User table based on the provided user ID.
     *
     * @param userId User's unique identifier.
     */
    @Query("delete from User where userId = :userId")
    void deleteUserById(int userId);

    /**
     * Updates the currency amount for a user in the User table.
     *
     * @param user User with updated currency amount.
     */
    @Update
    void updateCurrencyAmount(User user);
}