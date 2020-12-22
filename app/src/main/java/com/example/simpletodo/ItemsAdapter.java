package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {
    // Container to provide access to views that represent each row of the list
    List<String> items;
    OnLongClickListener longClickListener;

    public interface OnLongClickListener{
       void onItemLongClicked(int position);
    }

    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Use layout inflater to inflate value
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        // wrap it inside a viewholder and return it
        return new ViewHolder(todoView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = items.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends  RecyclerView.ViewHolder {

        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(Objects.requireNonNull(itemView));
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
