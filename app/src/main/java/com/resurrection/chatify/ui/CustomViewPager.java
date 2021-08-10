package com.resurrection.chatify.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class CustomViewPager extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragmentList;
    private ArrayList<String> tabNames;

    public CustomViewPager(@NonNull FragmentManager fm) {
        super(fm);
        fragmentList = new ArrayList<>();
        tabNames = new ArrayList<>();

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabNames.get(position);
    }

    public void addFragment(Fragment fragment, String name){
        fragmentList.add(fragment);
        tabNames.add(name);
    }

}
