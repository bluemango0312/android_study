package org.techtown.viewpager_pt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class MainFragment extends Fragment {
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    ViewPager pager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        pager = (ViewPager) view.findViewById(R.id.viewpager);
        pager.setOffscreenPageLimit(3);

        MypagerAdapter adapter = new MypagerAdapter(getChildFragmentManager());
        fragment1 = new Fragment1();
        adapter.addItem(fragment1);
        fragment2 = new Fragment2();
        adapter.addItem(fragment2);
        fragment3 = new Fragment3();
        adapter.addItem(fragment3);

        pager.setAdapter(adapter);

        return view;
    }

    class MypagerAdapter extends FragmentPagerAdapter{
        ArrayList<Fragment> items = new ArrayList<Fragment>();

        public MypagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(Fragment f){
            items.add(f);
        }
    }
}
