package com.starwings.app.samrtserchnewui.Adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.starwings.app.samrtserchnewui.Fragments.CardListingFragment;
import com.starwings.app.samrtserchnewui.Fragments.CardViewFragment;
import com.starwings.app.samrtserchnewui.Fragments.HomeFragment;


/**
 * Created by user on 09-02-2018.
 */

public class CardPageAdapter extends FragmentPagerAdapter {
    public CardPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment=null;
        Bundle args = null;
        switch (i)
        {
            case 0:

                fragment= new CardViewFragment();
                args = new Bundle();
                // Our object is just an integer :-P
                args.putString(HomeFragment.ARG_OBJECT, "CARDS");
                fragment.setArguments(args);
                break;
            case 1:

                fragment= new Fragment();
                args = new Bundle();
                // Our object is just an integer :-P
                args.putString(HomeFragment.ARG_OBJECT, "CATEGORIES");
                fragment.setArguments(args);
                break;



        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title="NA";
        switch (position)
        {
            case 0:
                title= "CARDS";
                break;
            case 1:
                title= "CATEGORIES";
                break;
        }
        return title;
    }
}
