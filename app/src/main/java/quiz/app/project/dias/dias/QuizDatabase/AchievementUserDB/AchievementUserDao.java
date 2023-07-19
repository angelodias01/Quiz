package quiz.app.project.dias.dias.QuizDatabase.AchievementUserDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import quiz.app.project.dias.dias.QuizDatabase.AchievementsDB.Achievements;

@Dao
public interface AchievementUserDao {
    @Insert
    void insertAchievementUser(AchievementUser achievementUser);

    @Update
    void updateAchievementUser(AchievementUser achievementUser);

    @Delete
    void deleteAchievementUser(AchievementUser achievementUser);

    @Query("SELECT * FROM AchievementsUser")
    List<AchievementUser> getAllAchievementUsers();

    @Query("SELECT * FROM AchievementsUser WHERE userId = :userId")
    List<AchievementUser> getAchievementUsersByUserId(int userId);

    @Query("SELECT * FROM AchievementsUser WHERE achievementId = :achievementId")
    List<AchievementUser> getAchievementUsersByAchievementId(int achievementId);
    @Query("SELECT * FROM AchievementsUser WHERE userId = :userId")
    AchievementUser getUserAchievementByUserId(int userId);

    @Query("delete from AchievementsUser where userId = :userId")
    void deleteAchievementsByUserId(int userId);
    @Query("SELECT * FROM AchievementsUser WHERE userId = :userId and achievementId = :achievementId")
    boolean getAchievementUsersByUserIdAndAchId(int userId, int achievementId);
}
