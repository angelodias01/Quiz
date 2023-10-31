package quiz.app.project.dias.dias.model.achievements;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import quiz.app.project.dias.dias.model.achievements.Achievements;

@Dao
public interface AchievementsDao {
    @Insert
    void insertAchievement(Achievements achievement);

    @Update
    void updateAchievement(Achievements achievement);

    @Delete
    void deleteAchievement(Achievements achievement);

    @Query("SELECT * FROM Achievements")
    List<Achievements> getAllAchievements();

    @Query("SELECT * FROM Achievements WHERE achievementId = :achievementId")
    Achievements getAchievementById(int achievementId);
}
