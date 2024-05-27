package com.example.fruteria;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.FruitViewHolder> {

    private List<Fruit> fruits;
    private OnQuantityChangedListener onQuantityChangedListener;

    public interface OnQuantityChangedListener {
        void onQuantityChanged();
    }

    public FruitAdapter(List<Fruit> fruits, OnQuantityChangedListener onQuantityChangedListener) {
        this.fruits = fruits;
        this.onQuantityChangedListener = onQuantityChangedListener;
    }

    @NonNull
    @Override
    public FruitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fruit_list_item, parent, false);
        return new FruitViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FruitViewHolder holder, int position) {
        Fruit fruit = fruits.get(position);
        holder.ivFruit.setImageResource(fruit.getImageResId());
        holder.tvFruitName.setText(fruit.getName());
        holder.tvQuantity.setText(String.valueOf(fruit.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }

    class FruitViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFruit;
        TextView tvFruitName;
        TextView tvQuantity;

        public FruitViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFruit = itemView.findViewById(R.id.ivFruit);
            tvFruitName = itemView.findViewById(R.id.tvFruitName);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position!= RecyclerView.NO_POSITION) {
                        Fruit fruit = fruits.get(position);
                        fruit.setQuantity(fruit.getQuantity() + 1);
                        notifyItemChanged(position);
                        onQuantityChangedListener.onQuantityChanged();
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = getAdapterPosition();
                    if (position!= RecyclerView.NO_POSITION) {
                        Fruit fruit = fruits.get(position);
                        if (fruit.getQuantity() > 0) {
                            fruit.setQuantity(fruit.getQuantity() - 1);
                            notifyItemChanged(position);
                            onQuantityChangedListener.onQuantityChanged();
                        }
                    }
                    return true;
                }
            });
        }
    }
}
