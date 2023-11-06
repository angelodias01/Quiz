package quiz.app.project.dias.dias.model.shop;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface ShopDao {
    @Query("SELECT * FROM Shop")
    LiveData<List<Shop>> getItems();

    @Insert
    void insertItem(Shop item);

    @Delete
    void deleteItem(Shop item);

    @Update
    void updateItem(Shop item);
}