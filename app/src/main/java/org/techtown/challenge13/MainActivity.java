package org.techtown.challenge13;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText name;
    EditText number;
    EditText birth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.clientList);
        button = findViewById(R.id.button);
        name = findViewById(R.id.nameText);
        birth = findViewById(R.id.birthText);
        number = findViewById(R.id.numberText);


        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        PersonAdapter adapter = new PersonAdapter();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String clientTitle = name.getText().toString() + " " + birth.getText().toString();
                String num = number.getText().toString();
                adapter.addItem(new Person(clientTitle, num));
            }
        });

        recyclerView.setAdapter(adapter);
    }
}