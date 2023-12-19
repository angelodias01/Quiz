/**
 * ShopResponse.java
 * This class represents a response containing a list of shop items.
 * It is used in conjunction with Retrofit to handle responses from the remote API.
 */

package quiz.app.project.dias.dias.model.shop;

import java.util.List;
public class ShopResponse {

    public List<Shop> shopItems;

    /**
     * Constructor for the ShopResponse class.
     *
     * @param shopItems The list of shop items in the response.
     */
    public ShopResponse(List<Shop> shopItems) {
        this.shopItems = shopItems;
    }

    /**
     * Retrieves the list of shop items from the response.
     *
     * @return The list of shop items.
     */
    public List<Shop> getShopItems() {
        return shopItems;
    }
}
