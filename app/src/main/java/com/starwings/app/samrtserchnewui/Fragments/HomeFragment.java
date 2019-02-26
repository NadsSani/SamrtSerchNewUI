package com.starwings.app.samrtserchnewui.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.starwings.app.samrtserchnewui.Links.ApiLinks;
import com.starwings.app.samrtserchnewui.R;

import org.json.JSONArray;

import cz.msebera.android.httpclient.Header;

/**
 * Created by user on 09-02-2018.
 */

public class HomeFragment extends RootFragment {
    public static final String ARG_OBJECT = "object";
    ViewFlipper flipper;
    private int mFlipping;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.homefragment, container, false);
        Bundle args = getArguments();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        flipper = (ViewFlipper) view.findViewById(R.id.home_top_layout);
        loadGallery();
    }

    private void loadGallery() {
        SharedPreferences appPreferences = getActivity().getSharedPreferences("APP_PREFERENCES", Context.MODE_PRIVATE);
        String apikey = appPreferences.getString("apikey", "NA");

        if (!apikey.equals("NA")) {
            AsyncHttpClient galleryClient = new AsyncHttpClient();
            galleryClient.setResponseTimeout(50000);
            galleryClient.setConnectTimeout(50000);
            galleryClient.setTimeout(50000);
            RequestParams params = new RequestParams();
            params.put("Authorization", apikey);
            Log.e("API", ApiLinks.baseLink + ApiLinks.homePageGallery);
            galleryClient.post(getContext(), ApiLinks.baseLink + ApiLinks.homePageGallery, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    try {
                        Log.e("API", "Response" + new String(responseBody));
                        loadGalleryImages(responseBody);
                    } catch (Exception e) {

                        //Snackbar.make(mFab, "Failed to Load Ads.", Snackbar.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    showFailure(error);
                }


            });
        }
    }
    private void loadGalleryImages(byte[] responseBody) throws Exception {
        String galResponse = new String(responseBody);
        JSONArray galArray = new JSONArray(galResponse);
        for (int i = 0; i < galArray.length(); i++) {
            int adType = galArray.getJSONObject(i).getInt("adType");
            switch (adType) {
                case 0:
                    ImageView imgPhoto = new ImageView(getContext());
                    imgPhoto.setAdjustViewBounds(true);
                    imgPhoto.setScaleType(ImageView.ScaleType.FIT_XY);
                    Glide.with(this)
                            .load(ApiLinks.basegalLink + galArray.getJSONObject(i).getString("path"))
                            .placeholder(R.drawable.placeholder)
                            .into(imgPhoto);
                    flipper.addView(imgPhoto);
                    flipper.setFlipInterval(10000);
                    break;


            }

        }
        if (mFlipping == 0) {
            /** Start Flipping */
            flipper.startFlipping();
            mFlipping = 1;

        } else {
            /** Stop Flipping */
            flipper.stopFlipping();
            mFlipping = 0;

        }
    }
    private void showFailure(Throwable error) {


    }
}
