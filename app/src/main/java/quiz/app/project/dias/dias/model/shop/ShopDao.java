/**
 * ShopDao.java
 * This interface defines data access operations (DAO) for interacting with the Shop entity.
 * It includes methods for querying, inserting, updating, and deleting shop items.
 */

package quiz.app.project.dias.dias.model.shop;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface ShopDao {
    /**
     * Retrieves a list of all shop items from the data source as LiveData.
     *
     * @return LiveData containing a list of all shop items.
     */
    @Query("SELECT * FROM Shop")
    LiveData<List<Shop>> getItems();

    /**
     * Inserts a new shop item into the data source.
     *
     * @param item The shop item to insert.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertItem(Shop item);

    /**
     * Deletes a specific shop item from the data source.
     *
     * @param item The shop item to delete.
     */
    @Delete
    void deleteItem(Shop item);

    /**
     * Updates an existing shop item in the data source.
     *
     * @param item The shop item to update.
     */
    @Update
    void updateItem(Shop item);
}