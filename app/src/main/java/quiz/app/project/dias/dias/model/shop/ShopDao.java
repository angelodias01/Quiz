package quiz.app.project.dias.dias.model.shop;

import androidx.room.Dao;
import androidx.room.Query;
import java.util.List;

@Dao
public interface ShopDao {
    @Query("SELECT * FROM Shop")
    List<Shop> getItems();
}