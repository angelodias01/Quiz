package quiz.app.project.dias.dias.model.shop;

import java.util.List;
public class ShopResponse {
    public List<Shop> data;

    public ShopResponse(List<Shop> data) {
        this.data = data;
    }

    public List<Shop> getData() {
        return data;
    }
}
