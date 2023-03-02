package org.techtown.challenge21;

import static android.content.Context.MODE_PRIVATE;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class inputFragment extends Fragment {
    EditText title;
    EditText author;
    EditText story;
    SQLiteDatabase db;

    String dbName = "book.db";
    String tableName = "bookList";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_input, container, false);
        title = rootView.findViewById(R.id.editText);
        author = rootView.findViewById(R.id.editText2);
        story = rootView.findViewById(R.id.editText3);

        createDatabase(dbName);
        createTable(tableName);

        Button button = rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertRecord();
            }
        });

        return rootView;
    }

    private void insertRecord() {
        if(db == null){
            Toast.makeText(this.getContext(), "db 없음", Toast.LENGTH_LONG).show();
            return;
        }

        String titleT = title.getText().toString();
        String authorT = author.getText().toString();
        String storyT = story.getText().toString();

        db.execSQL("insert into " + tableName
                +"(title, author, story)"
                + " values "
                + "(" + "'" + titleT + "'" + ", " + "'" + authorT + "'" + ", " + "'" + storyT + "'" + ")");

        Toast.makeText(this.getContext(), "책 정보 저장 완료 :)", Toast.LENGTH_LONG).show();

    }

    private void createDatabase(String dbName) {
        db = getActivity().openOrCreateDatabase(dbName, Context.MODE_PRIVATE, null);
    }

    private void createTable(String tableName) {
        if (db == null) {
            db = getActivity().openOrCreateDatabase(dbName, Context.MODE_PRIVATE, null);
        }

        db.execSQL("create table if not exists " + tableName + " ("
                + " _id integer PRIMARY KEY autoincrement, "
                + " title text, "
                + " author text, "
                + " story text)");
    }
}