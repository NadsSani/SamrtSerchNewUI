package com.starwings.app.samrtserchnewui;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.starwings.app.samrtserchnewui.Adapter.PageAdapter;
import com.starwings.app.samrtserchnewui.Listener.OnBackPressListener;
import com.starwings.app.samrtserchnewui.PagerIndicator.TabPageIndicator;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class CoverFragment extends Fragment {

    /**
     * TabPagerIndicator
     *
     * Please refer to ViewPagerIndicator library
     */
    protected TabPageIndicator indicator;

    protected ViewPager pager;

    private PageAdapter adapter;


    public CoverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.cover_fragment_layout, container, false);

        indicator = (TabPageIndicator) rootView.findViewById(R.id.tpi_header);
        pager = (ViewPager) rootView.findViewById(R.id.pager);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Note that we are passing childFragmentManager, not FragmentManager
        adapter = new PageAdapter(getResources(), getChildFragmentManager()) {
        };

        pager.setAdapter(adapter);
        indicator.setViewPager(pager);
    }

    /**
     * Retrieve the currently visible Tab Fragment and propagate the onBackPressed callback
     *
     * @return true = if this fragment and/or one of its associates Fragment can handle the backPress
     */
    public boolean onBackPressed() {
        // currently visible tab Fragment
        OnBackPressListener currentFragment = (OnBackPressListener) adapter.getRegisteredFragment(pager.getCurrentItem());

        if (currentFragment != null) {
            // lets see if the currentFragment or any of its childFragment can handle onBackPressed
            return currentFragment.onBackPressed();
        }

        // this Fragment couldn't handle the onBackPressed call
        return false;
    }

}
