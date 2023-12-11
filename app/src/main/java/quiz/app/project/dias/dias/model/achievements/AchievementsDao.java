/*
 * AchievementsDao.java
 * This interface defines the data access object (DAO) for the Achievements entity.
 * It contains methods for performing CRUD operations on the "Achievements" table.
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

/*
 * Dao annotation indicates that this interface is a data access object.
 */
@Dao
public interface AchievementsDao {

    /*
     * Insert method for adding an achievement to the "Achievements" table.
     * If there is a conflict, it ignores the new data.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAchievement(Achievements achievement);

    /*
     * Update method for modifying an existing achievement in the "Achievements" table.
     */
    @Update
    void updateAchievement(Achievements achievement);

    /*
     * Delete method for removing an achievement from the "Achievements" table.
     */
    @Delete
    void deleteAchievement(Achievements achievement);

    /*
     * Query method to retrieve all achievements from the "Achievements" table as LiveData.
     */
    @Query("SELECT * FROM Achievements")
    LiveData<List<Achievements>> getAllAchievements();

    /*
     * Query method to retrieve a specific achievement by its ID as LiveData.
     * Parameters:
     *   - achievementId: The ID of the achievement to be retrieved.
     */
    @Query("SELECT * FROM Achievements WHERE achievementId = :achievementId")
    LiveData<List<Achievements>> getAchievementById(int achievementId);
}
