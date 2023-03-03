package org.techtown.challenge14;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    ArrayList<Product> items = new ArrayList<Product>();


    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView price;
        TextView description;
        ImageView img;

        public ViewHolder(View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.nameText);
            price = itemView.findViewById(R.id.priceText);
            description = itemView.findViewById(R.id.descText);
            img = itemView.findViewById(R.id.imageView);

        }

        public void setItem(Product item){
            name.setText(item.getName());
            price.setText(item.getPrice());
            description.setText(item.getDescription());
            img.setImageResource(item.getImg());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.product_item, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product item = items.get(position);
        holder.setItem(item);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void addItem(Product item){
        items.add(item);
    }

    public void setItems(ArrayList<Product> items){
        this.items=items;
    }

    public Product getItem(int position){
        return items.get(position);
    }

    public void setItem(int position, Product item){
        items.set(position, item);
    }
}
