package quiz.app.project.dias.dias.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
import quiz.app.project.dias.dias.model.shop.Shop;
import quiz.app.project.dias.dias.model.shop.ShopRepo;

/**
 * ViewModel class for managing Shop data.
 */
public class ShopViewModel extends AndroidViewModel {
    private ShopRepo repository;

    /**
     * Constructor for the ShopViewModel.
     *
     * @param application The application instance.
     */
    public ShopViewModel(@NonNull Application application) {
        super(application);
        // Initialize repository
        this.repository = new ShopRepo(application.getApplicationContext());
    }

    /**
     * Get LiveData containing a list of items from the shop.
     *
     * @return LiveData<List<Shop>> representing items available in the shop.
     */
    public LiveData<List<Shop>> getItems() {
        return repository.getItems();
    }
}
