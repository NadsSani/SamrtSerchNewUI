package com.starwings.app.samrtserchnewui.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.starwings.app.samrtserchnewui.Links.ApiLinks;
import com.starwings.app.samrtserchnewui.R;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by user on 13-02-2018.
 */

public class CategoryFragment extends RootFragment {

    ProgressBar catLoading;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.category_fragment, container, false);
        Bundle args = getArguments();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        catLoading = (ProgressBar) view.findViewById(R.id.catLoading);
    }

    private void fetchCategories() {
        catLoading.setVisibility(View.VISIBLE);
        ArrayList categories=new ArrayList<>();
        SharedPreferences appPreferences=((FragmentActivity)getActivity()).getSharedPreferences("APP_PREFERENCES", Context.MODE_PRIVATE);
        String apikey=appPreferences.getString("apikey","NA");
        if(!apikey.equals("NA"))
        {
            RequestParams params=new RequestParams();
            params.put("Authorization",apikey);

            AsyncHttpClient cardsClient=new AsyncHttpClient();
            cardsClient.setResponseTimeout(50000);
            cardsClient.setConnectTimeout(50000);
            cardsClient.setTimeout(50000);

            cardsClient.post(((FragmentActivity)getActivity()), ApiLinks.baseLink + ApiLinks.fetchCategories, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                    try {

                       // prepareCategories(responseBody);

                    } catch (Exception e) {
                       // Snackbar.make(recyclerView,"An Error Occurred. Please Try Later"+e.getClass().getName(),Snackbar.LENGTH_SHORT).show();
                        catLoading.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                  //  processFailure(error);
                }
            });
        }

    }

}
