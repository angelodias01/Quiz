/*
 * AchievementUserDao.java
 * This interface defines the data access object (DAO) for the AchievementUser entity.
 * It contains methods for performing CRUD operations on the "AchievementsUser" table.
 */

package quiz.app.project.dias.dias.model.achievementsuser;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/*
 * Dao annotation indicates that this interface is a data access object.
 */
@Dao
public interface AchievementUserDao {

    /*
     * Query method to retrieve all entries from the "AchievementsUser" table as LiveData.
     */
    @Query("SELECT * FROM AchievementsUser")
    LiveData<List<AchievementUser>> getAllAchievementUsers();

    /*
     * Query method to retrieve entries from the "AchievementsUser" table by user ID as LiveData.
     * Parameters:
     *   - userId: The ID of the user.
     */
    @Query("SELECT * FROM AchievementsUser WHERE userId = :userId")
    LiveData<List<AchievementUser>> getAchievementUsersByUserId(int userId);

    /*
     * Query method to retrieve entries from the "AchievementsUser" table by achievement ID as LiveData.
     * Parameters:
     *   - achievementId: The ID of the achievement.
     */
    @Query("SELECT * FROM AchievementsUser WHERE achievementId = :achievementId")
    LiveData<List<AchievementUser>> getAchievementUsersByAchievementId(int achievementId);

    /*
     * Query method to retrieve user achievements from the "AchievementsUser" table by user ID as LiveData.
     * Parameters:
     *   - userId: The ID of the user.
     */
    @Query("SELECT * FROM AchievementsUser WHERE userId = :userId")
    LiveData<List<AchievementUser>> getUserAchievementByUserId(int userId);

    /*
     * Query method to delete user achievements from the "AchievementsUser" table by user ID.
     * Parameters:
     *   - userId: The ID of the user.
     */
    @Query("delete from AchievementsUser where userId = :userId")
    void deleteAchievementsByUserId(int userId);

    /*
     * Query method to check if a user has earned a specific achievement.
     * Parameters:
     *   - userId: The ID of the user.
     *   - achievementId: The ID of the achievement.
     */
    @Query("SELECT * FROM AchievementsUser WHERE userId = :userId and achievementId = :achievementId")
    boolean getAchievementUsersByUserIdAndAchId(int userId, int achievementId);

    /*
     * Insert method for adding an entry to the "AchievementsUser" table.
     * If there is a conflict, it ignores the new data.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAchievementUser(AchievementUser achievementUser);

    /*
     * Delete method for removing an entry from the "AchievementsUser" table.
     */
    @Delete
    void deleteAchievementUser(AchievementUser achievementUser);
}
