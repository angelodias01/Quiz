/**
 * ShopRepo.java
 * This class acts as a repository for handling data operations related to shop items.
 * It manages interactions between the ViewModel and the underlying data source (Room database).
 */

package quiz.app.project.dias.dias.model.shop;

import android.content.Context;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import quiz.app.project.dias.dias.model.QuizDatabase;

public class ShopRepo {
    private ShopDao shopDao;
    private Executor executor = Executors.newSingleThreadExecutor();

    /**
     * Constructor for the ShopRepo class.
     * Initializes the ShopDao using the Room database instance.
     *
     * @param context The application context.
     */
    public ShopRepo(Context context) {
        this.shopDao = QuizDatabase.getInstance(context).getShopDao();
    }

    /**
     * Retrieves a list of all shop items from the data source as LiveData.
     *
     * @return LiveData containing a list of all shop items.
     */
    public LiveData<List<Shop>> getItems() {
        return shopDao.getItems();
    }

    /**
     * Inserts a new shop item into the data source.
     *
     * @param item The shop item to insert.
     */
    public void insertItem(Shop item) {
        executor.execute(() -> shopDao.insertItem(item));
    }

    /**
     * Deletes a specific shop item from the data source.
     *
     * @param item The shop item to delete.
     */
    public void deleteItem(Shop item) {
        executor.execute(() -> shopDao.deleteItem(item));
    }

    /**
     * Updates an existing shop item in the data source.
     *
     * @param item The shop item to update.
     */
    public void updateItem(Shop item) {
        executor.execute(() -> shopDao.updateItem(item));
    }
}
