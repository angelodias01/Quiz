/**
 * Shop.java
 * This class represents an entity for items in the shop.
 * Each shop item has a unique identifier (shopId), a name (itemName), and a price.
 */

package quiz.app.project.dias.dias.model.shop;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Shop")
public class Shop {
    @PrimaryKey(autoGenerate = true)
    private int shopId;
    @ColumnInfo(name = "itemName")
    private String itemName;
    @ColumnInfo(name = "price")
    private int price;

    /**
     * Constructor for the Shop class.
     *
     * @param shopId   The unique identifier for the shop item.
     * @param itemName The name of the shop item.
     * @param price    The price of the shop item.
     */
    public Shop(int shopId, String itemName, int price) {
        this.shopId = shopId;
        this.itemName = itemName;
        this.price = price;
    }

    /**
     * Retrieves the unique identifier of the shop item.
     *
     * @return The unique identifier (shopId) of the shop item.
     */
    public int getShopId() {
        return shopId;
    }

    /**
     * Sets the unique identifier of the shop item.
     *
     * @param shopId The unique identifier to set.
     */
    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    /**
     * Retrieves the price of the shop item.
     *
     * @return The price of the shop item.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the price of the shop item.
     *
     * @param price The price to set.
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Retrieves the name of the shop item.
     *
     * @return The name of the shop item.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Sets the name of the shop item.
     *
     * @param itemName The name to set.
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}