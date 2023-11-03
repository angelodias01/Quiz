package quiz.app.project.dias.dias.model.shop;

import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ShopRepo {
    private ShopDao shopDao;
    private Executor executor = Executors.newSingleThreadExecutor();

    public ShopRepo(ShopDao shopDao) {
        this.shopDao = shopDao;
    }

    public LiveData<List<Shop>> getItems() {
        return shopDao.getItems();
    }

    public void insertItem(Shop item) {
        executor.execute(() -> shopDao.insertItem(item));
    }

    public void deleteItem(Shop item) {
        executor.execute(() -> shopDao.deleteItem(item));
    }

    public void updateItem(Shop item) {
        executor.execute(() -> shopDao.updateItem(item));
    }

}
