package org.techtown.challenge14;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        ProductAdapter adapter = new ProductAdapter();

        adapter.addItem(new Product("숏패딩", "160,000원", "추운 겨울엔 트렌디한 숏패딩!", R.drawable.shoes));
        adapter.addItem(new Product("선글라스", "139,000원", "에이티즈 성화가 착용한 상품-!", R.drawable.sunglasses));
        adapter.addItem(new Product("청바지", "100,000원", "뉴진스를 좋아한다면? 이 청바지를 pick**!", R.drawable.jeans));
        adapter.addItem(new Product("치마", "50,000원", "미리 준비하는 spring :)", R.drawable.skirt));
        adapter.addItem(new Product("운동화", "500,000원", "인기많은 그 운동화!!", R.drawable.shoes));

        recyclerView.setAdapter(adapter);
    }
}