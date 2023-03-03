package org.techtown.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        //리싸이클러뷰에 레이아웃 매니저 설정하기
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        PersonAdapter adapter = new PersonAdapter();

        adapter.addItem(new Person("서정후", "010-1234-5678"));
        adapter.addItem(new Person("신도민", "010-1111-2222"));
        adapter.addItem(new Person("박성화", "010-8888-2222"));

        recyclerView.setAdapter(adapter); //리싸이클러뷰에 어댑터 설정하기
    }
}