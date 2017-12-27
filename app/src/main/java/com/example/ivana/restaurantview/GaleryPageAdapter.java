package com.example.ivana.restaurantview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Ivana on 12/26/2017.
 */

public class GaleryPageAdapter extends FragmentPagerAdapter {
    private static ArrayList<Menu> imageDataArrayList = new ArrayList<>();


    public GaleryPageAdapter(FragmentManager fm, ArrayList<Menu> _imageDataArrayList) {
        super(fm);
        imageDataArrayList = _imageDataArrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentMain.newInstance(imageDataArrayList.get(position).getLink());
    }

    @Override
    public int getCount() {
        return imageDataArrayList.size();
    }



}
