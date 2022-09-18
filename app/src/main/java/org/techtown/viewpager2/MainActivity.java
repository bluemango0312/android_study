package org.techtown.viewpager2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int NUM_PAGES=3;  //페이지 수
    private ViewPager2 pager;
    private FragmentStateAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager=findViewById(R.id.pager);
        pagerAdapter=new ScreeSlidePagerAdapter(this);
        pager.setAdapter(pagerAdapter);

    }


    //뒤로가기 눌렀을때 처리
//뷰페이저2 동작하는 것과 상관 없으므로 (onBackPressed 메소드)생략 가능
    @Override
    public void onBackPressed() {
        if(pager.getCurrentItem()==0){
            Toast.makeText(this, "뒤로가기가 눌렸습니다.", Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        }else{
            pager.setCurrentItem(pager.getCurrentItem()-1);
        }
    }

    //페이저와 프래그먼트 이어주기
    private class ScreeSlidePagerAdapter extends FragmentStateAdapter{
        public ScreeSlidePagerAdapter(FragmentActivity fa){
            super (fa);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {//포지션마다 있을 fragment설정
            if(position==0) return new Ex01();
            else if(position==1) return new Ex02();
            else return new Ex03();
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;  //페이지 수 지정.
        }
    }



}
