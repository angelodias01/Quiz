/**
 * ShopAdapter.java
 * Adapter for the RecyclerView in the ShopFragment, responsible for displaying shop items.
 */

package quiz.app.project.dias.dias.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import quiz.app.project.dias.dias.model.shop.Shop;
import quiz.app.project.dias.dias.R;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> {

    // List to store shop items
    private List<Shop> itemList;

    // Listener for item click events
    private OnItemClickListener itemClickListener;

    // Constructor to initialize the adapter with a list of shop items
    public ShopAdapter(List<Shop> itemList) {
        this.itemList = itemList;
        this.itemClickListener = itemClickListener;
    }

    // Creates and returns a new ShopViewHolder
    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_item, parent, false);
        return new ShopViewHolder(itemView);
    }

    // Binds the data to the ViewHolder at the specified position
    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
        Shop shop = itemList.get(position);
        holder.bind(shop);
    }

    // Returns the total number of items in the data set
    @Override
    public int getItemCount() {
        return itemList.size();
    }

    // ViewHolder class to hold references to UI components
    public class ShopViewHolder extends RecyclerView.ViewHolder {

        private TextView lblPrice;
        private TextView lblName;

        // Constructor to initialize the ViewHolder
        public ShopViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize UI components
            lblPrice = itemView.findViewById(R.id.lblPrice);
            lblName = itemView.findViewById(R.id.lblName);

            // Set up click listener for the item view
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            // Notify the listener about the item click
                            itemClickListener.onItemClick(position);
                        }
                    }
                }
            });
        }

        // Binds data to the ViewHolder
        public void bind(Shop shop) {
            lblName.setText(shop.getItemName());
            lblPrice.setText(String.valueOf(shop.getPrice())); // Convert int to String
        }
    }

    // Interface for item click events
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}