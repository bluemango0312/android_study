package org.techtown.challenge21;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class bookAdapter extends RecyclerView.Adapter<bookAdapter.ViewHolder> {
    ArrayList<Book> items = new ArrayList<Book>();


    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleText;
        TextView authorText;

        public ViewHolder(View itemView){
            super(itemView);

            titleText = itemView.findViewById(R.id.titleText);
            authorText = itemView.findViewById(R.id.authorText);
        }

        public void setItem(Book item){
            titleText.setText(item.getTitle());
            authorText.setText(item.getAuthor());
        }
    }

    @NonNull
    @Override
    public bookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.booklist, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull bookAdapter.ViewHolder holder, int position) {
        Book item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Book item){
        items.add(item);
    }

    public void setItems(ArrayList<Book> items){
        this.items=items;
    }

    public Book getItem(int position){
        return items.get(position);
    }

    public void setItem(int position, Book item){
        items.set(position, item);
    }
}
