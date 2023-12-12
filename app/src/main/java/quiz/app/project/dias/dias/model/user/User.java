/**
 * User.java
 * Represents a user entity in the application.
 */

package quiz.app.project.dias.dias.model.user;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "User")
public class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    @NonNull
    private int userId;
    @NonNull
    @ColumnInfo(name = "username")
    private String username;
    @NonNull
    @ColumnInfo(name = "email")
    private String email;
    @NonNull
    @ColumnInfo(name = "password")
    private String password;

    /**
     * Constructor for the User class.
     *
     * @param username User's chosen username.
     * @param email    User's email address.
     * @param password User's password.
     */
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /**
     * Gets the unique identifier of the user.
     *
     * @return User's ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Gets the user's chosen username.
     *
     * @return User's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the user's email address.
     *
     * @return User's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the user's password.
     *
     * @return User's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the unique identifier of the user.
     *
     * @param userId User's ID.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Sets the user's chosen username.
     *
     * @param username User's username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the user's email address.
     *
     * @param email User's email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the user's password.
     *
     * @param password User's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}