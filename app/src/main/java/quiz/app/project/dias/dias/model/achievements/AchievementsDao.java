/**
 * AchievementsDao.java
 * Data Access Object (DAO) interface for achievements, providing methods to interact with the database.
 */

package quiz.app.project.dias.dias.model.achievements;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import quiz.app.project.dias.dias.model.theme.Themes;

@Dao
public interface AchievementsDao {

    /**
     * Inserts an achievement into the database.
     *
     * @param achievement The achievement to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAchievement(Achievements achievement);

    /**
     * Updates an existing achievement in the database.
     *
     * @param achievement The achievement to be updated.
     */
    @Update
    void updateAchievement(Achievements achievement);

    /**
     * Deletes an achievement from the database.
     *
     * @param achievement The achievement to be deleted.
     */
    @Delete
    void deleteAchievement(Achievements achievement);

    /**
     * Retrieves an achievement by its ID from the database as LiveData.
     *
     * @param achievementId The ID of the achievement to be retrieved.
     * @return LiveData containing a list with the specified achievement.
     */
    @Query("SELECT * FROM Achievements WHERE achievementId = :achievementId")
    LiveData<List<Achievements>> getAchievementById(int achievementId);

    @Query("SELECT * FROM Achievements WHERE achievementId = :achievementId")
    LiveData<Achievements> getAchievementByIdLiveData(int achievementId);

    @Query("SELECT * FROM Achievements")
    LiveData<List<Achievements>> getAllAchievementsLiveData();
    @Query("SELECT * FROM achievements")
    LiveData<List<Achievements>> getAllAchievements();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAchievements(List<Achievements> achievements);

    @Query("DELETE FROM achievements")
    void deleteAllAchievements();
}
