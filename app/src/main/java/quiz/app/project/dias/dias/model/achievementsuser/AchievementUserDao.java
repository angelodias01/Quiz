package quiz.app.project.dias.dias.model.achievementsuser;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface AchievementUserDao {

    @Query("SELECT * FROM AchievementsUser")
    LiveData<List<AchievementUser>> getAllAchievementUsers();

    @Query("SELECT * FROM AchievementsUser WHERE userId = :userId")
    LiveData<List<AchievementUser>> getAchievementUsersByUserId(int userId);

    @Query("SELECT * FROM AchievementsUser WHERE achievementId = :achievementId")
    LiveData<List<AchievementUser>> getAchievementUsersByAchievementId(int achievementId);

    @Query("SELECT * FROM AchievementsUser WHERE userId = :userId")
    LiveData<List<AchievementUser>> getUserAchievementByUserId(int userId);

    @Query("delete from AchievementsUser where userId = :userId")
    void deleteAchievementsByUserId(int userId);

    @Query("SELECT * FROM AchievementsUser WHERE userId = :userId and achievementId = :achievementId")
    boolean getAchievementUsersByUserIdAndAchId(int userId, int achievementId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAchievementUser(AchievementUser achievementUser);

    @Delete
    void deleteAchievementUser(AchievementUser achievementUser);
}
