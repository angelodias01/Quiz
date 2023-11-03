package quiz.app.project.dias.dias.viewmodel;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

import quiz.app.project.dias.dias.model.shop.Shop;
import quiz.app.project.dias.dias.model.shop.ShopRepo;

public class ShopViewModel extends AndroidViewModel {
    private ShopRepo repository;

    public ShopViewModel(@NonNull Application application) {
        super(application);
        // Initialize repository
        this.repository = new ShopRepo(application.getApplicationContext());
    }

    public LiveData<List<Shop>> getItems() {
        return repository.getItems();
    }
}
