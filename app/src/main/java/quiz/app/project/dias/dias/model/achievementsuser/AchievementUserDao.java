/**
 * AchievementUserDao.java
 * This Data Access Object (DAO) interface defines methods to interact with the "AchievementsUser" table
 * in the Room database for the association between users and achievements.
 */

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

    /**
     * Retrieves all records of AchievementUser from the "AchievementsUser" table.
     *
     * @return LiveData containing a list of all AchievementUser records.
     */
    @Query("SELECT * FROM AchievementsUser")
    LiveData<List<AchievementUser>> getAllAchievementUsers();

    /**
     * Retrieves all AchievementUser records associated with a specific user ID.
     *
     * @param userId The ID of the user to retrieve achievements for.
     * @return LiveData containing a list of AchievementUser records for the specified user.
     */
    @Query("SELECT * FROM AchievementsUser WHERE userId = :userId")
    LiveData<List<AchievementUser>> getAchievementUsersByUserId(int userId);

    /**
     * Retrieves all AchievementUser records associated with a specific achievement ID.
     *
     * @param achievementId The ID of the achievement to retrieve users for.
     * @return LiveData containing a list of AchievementUser records for the specified achievement.
     */
    @Query("SELECT * FROM AchievementsUser WHERE achievementId = :achievementId")
    LiveData<List<AchievementUser>> getAchievementUsersByAchievementId(int achievementId);

    /**
     * Retrieves all AchievementUser records associated with a specific user ID.
     *
     * @param userId The ID of the user to retrieve achievements for.
     * @return LiveData containing a list of AchievementUser records for the specified user.
     */
    @Query("SELECT * FROM AchievementsUser WHERE userId = :userId")
    LiveData<List<AchievementUser>> getUserAchievementByUserId(int userId);

    /**
     * Deletes all AchievementUser records associated with a specific user ID.
     *
     * @param userId The ID of the user for which achievements should be deleted.
     */
    @Query("delete from AchievementsUser where userId = :userId")
    void deleteAchievementsByUserId(int userId);

    /**
     * Checks if a specific AchievementUser record exists for a given user ID and achievement ID.
     *
     * @param userId        The ID of the user.
     * @param achievementId The ID of the achievement.
     * @return True if the record exists, false otherwise.
     */
    @Query("SELECT * FROM AchievementsUser WHERE userId = :userId AND achievementId = :achievementId")
    boolean getAchievementUsersByUserIdAndAchId(int userId, int achievementId);

    /**
     * Inserts a new AchievementUser record into the "AchievementsUser" table.
     *
     * @param achievementUser The AchievementUser record to insert.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAchievementUser(AchievementUser achievementUser);

    /**
     * Deletes a specific AchievementUser record from the "AchievementsUser" table.
     *
     * @param achievementUser The AchievementUser record to delete.
     */
    @Delete
    void deleteAchievementUser(AchievementUser achievementUser);


}