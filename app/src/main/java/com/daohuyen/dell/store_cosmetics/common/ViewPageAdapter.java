package com.daohuyen.dell.store_cosmetics.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.daohuyen.dell.store_cosmetics.R;
import com.daohuyen.dell.store_cosmetics.view.fragment.home.HomeFragment;
import com.daohuyen.dell.store_cosmetics.view.fragment.inform.InformationFragment;

public class ViewPageAdapter extends FragmentStatePagerAdapter {
    private Fragment[] fragments;
    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
        fragments= new Fragment[3];
        fragments[0]= new HomeFragment();
        fragments[1]= new HomeFragment();
        fragments[2]= new InformationFragment();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    public static int getMenuIDByPosition(int position) {
        switch (position) {
            case 0: {
                return R.id.nav_type_book;
            }

            case 1: {
                return R.id.nav_get_book;
            }
            case 2:{
                return R.id.nav_search_book;
            }
            default: {
                return R.id.nav_search_book;
            }
        }
    }
}
