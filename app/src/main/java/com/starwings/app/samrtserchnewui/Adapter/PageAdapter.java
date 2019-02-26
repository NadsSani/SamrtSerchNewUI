package com.starwings.app.samrtserchnewui.Adapter;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.starwings.app.samrtserchnewui.Fragments.CardListingFragment;
import com.starwings.app.samrtserchnewui.Fragments.CardViewFragment;
import com.starwings.app.samrtserchnewui.Fragments.HomeFragment;


/**
 * Created by user on 09-02-2018.
 */

public class PageAdapter extends FragmentPagerAdapter {


    SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

    public PageAdapter(final Resources resources, FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment=null;
        Bundle args = null;
        switch (i)
        {
            case 0:

                fragment= new HomeFragment();
                args = new Bundle();
                // Our object is just an integer :-P
                args.putString(HomeFragment.ARG_OBJECT, "HOME");
                fragment.setArguments(args);
                break;
            case 1:

                fragment= new CardViewFragment();
                args = new Bundle();
                // Our object is just an integer :-P
                args.putString(HomeFragment.ARG_OBJECT, "DIRECTORY");
                fragment.setArguments(args);
                break;



        }
        return fragment;
    }
    /**
     * On each Fragment instantiation we are saving the reference of that Fragment in a Map
     * It will help us to retrieve the Fragment by position
     *
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }
    /**
     * Remove the saved reference from our Map on the Fragment destroy
     *
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
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
                title= "HOME";
                break;
            case 1:
                title= "DIRECTORY";
                break;
        }
        return title;
    }

    /**
     * Get the Fragment by position
     *
     * @param position tab position of the fragment
     * @return
     */
    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }

}
