package org.techtown.challenge21;

import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class output extends Fragment {
    SQLiteDatabase db;
    TextView titleText;
    TextView authorText;
    bookAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_output, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        titleText = rootView.findViewById(R.id.titleText);
        authorText = rootView.findViewById(R.id.authorText);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new bookAdapter();

        executeQuery();

        recyclerView.setAdapter(adapter);

        return rootView;
    }

    private void executeQuery() {
        try{
            if(db == null){
                db = getActivity().openOrCreateDatabase("book.db", Context.MODE_PRIVATE, null);
            }
            Cursor cursor = db.rawQuery("select _id, title, author from bookList", null);
            int recordCount = cursor.getCount();

            for(int i = 0; i<recordCount; i++){
                cursor.moveToNext();
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String author = cursor.getString(2);

                adapter.addItem(new Book("#" + id + " " + title, author));
            }
            cursor.close();
        } catch (NullPointerException e){
            Toast.makeText(getContext(), "에러 발생 : " + e,Toast.LENGTH_LONG).show();
        }

    }
}