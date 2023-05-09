package quiz.app.project.dias.dias.QuizDatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Users")
public class Users{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    @NonNull
    private int userId;
    @NonNull
    @ColumnInfo(name = "Username")
    private String username;
    @NonNull
    @ColumnInfo(name = "Email")
    private String email;
    @NonNull
    @ColumnInfo(name = "Password")
    private String password;
    @NonNull
    @ColumnInfo(name = "isAdmin")
    private boolean isAdmin;

    public Users(String username, String email,String password, boolean isAdmin) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Boolean isAdmin() {
        return isAdmin;
    }

    public void setUserId(int UserId) {
        this.userId = UserId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
