package quiz.app.project.dias.dias.QuizDatabase.ShopDB;

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

    public Shop(int shopId, String itemName,int price) {
        this.shopId = shopId;
        this.itemName = itemName;
        this.price = price;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}