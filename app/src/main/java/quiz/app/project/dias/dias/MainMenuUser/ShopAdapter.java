package quiz.app.project.dias.dias.MainMenuUser;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import quiz.app.project.dias.dias.QuizDatabase.ShopDB.Shop;
import quiz.app.project.dias.dias.R;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> {
    private List<Shop> itemList;
    private OnItemClickListener itemClickListener;

    public ShopAdapter(List<Shop> itemList) {
        this.itemList = itemList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_item, parent, false);
        return new ShopViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
        Shop shop = itemList.get(position);
        holder.bind(shop);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ShopViewHolder extends RecyclerView.ViewHolder {
        private TextView lblPrice;
        private TextView lblName;

        public ShopViewHolder(@NonNull View itemView) {
            super(itemView);
            lblPrice = itemView.findViewById(R.id.lblPrice);
            lblName = itemView.findViewById(R.id.lblName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            itemClickListener.onItemClick(position);
                        }
                    }
                }
            });
        }

        public void bind(Shop shop) {
            lblName.setText(shop.getItemName());
            lblPrice.setText(shop.getPrice()+"");
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}