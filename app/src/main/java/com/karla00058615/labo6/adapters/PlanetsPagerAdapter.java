package com.karla00058615.labo6.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karla on 13/5/2018.
 */

public class PlanetsPagerAdapter extends FragmentPagerAdapter {

    List<String> titleList;
    List<Fragment> fragmentList;
    Context Ctx;

    public PlanetsPagerAdapter(FragmentManager fm, Context Ctx) {
        super(fm);
        this.Ctx = Ctx;
        this.titleList = new ArrayList<>();
        this.fragmentList = new ArrayList<>();
    }

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
        return titleList.get(position);
    }

    public void addItem(String title, Fragment fragment){
        titleList.add(title);
        fragmentList.add(fragment);
    }
}
