package com.starwings.app.samrtserchnewui.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.starwings.app.samrtserchnewui.Adapter.CardPageAdapter;
import com.starwings.app.samrtserchnewui.Adapter.PageAdapter;
import com.starwings.app.samrtserchnewui.Links.ApiLinks;
import com.starwings.app.samrtserchnewui.R;

import org.json.JSONArray;

import cz.msebera.android.httpclient.Header;

/**
 * Created by user on 13-02-2018.
 */

public class CardListingFragment extends RootFragment {
    CardPageAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.cardlistfragment, container, false);
        Bundle args = getArguments();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


}
