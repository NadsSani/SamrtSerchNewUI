package com.starwings.app.samrtserchnewui.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.starwings.app.samrtserchnewui.Adapter.CardAdapter;
import com.starwings.app.samrtserchnewui.Links.ApiLinks;
import com.starwings.app.samrtserchnewui.R;
import com.starwings.app.samrtserchnewui.SmartSerch;
import com.starwings.app.samrtserchnewui.data.Cards;
import com.starwings.app.samrtserchnewui.data.Category;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by user on 13-02-2018.
 */

public class CardViewFragment extends RootFragment {
    ArrayList<Cards> cardsList;
    ProgressBar catLoading;
    private RecyclerView recyclerView;
    CardAdapter cardAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.cardwiselistingfragment, container, false);
        Bundle args = getArguments();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        catLoading = (ProgressBar) view.findViewById(R.id.catLoading);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager((FragmentActivity)getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        fetchCards();
    }

    private void fetchCards() {
        cardsList=new ArrayList<>();
        catLoading.setVisibility(View.VISIBLE);
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

            cardsClient.post(getContext(), ApiLinks.baseLink + ApiLinks.cardsLink, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                    try {
                        prepareCardListing(responseBody);
                    } catch (Exception e) {
                        Snackbar.make(recyclerView,"An Error Occurred. Please Try Later",Snackbar.LENGTH_SHORT).show();
                        catLoading.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    processFailure(error);
                }
            });
        }

    }



    private void prepareCardListing(byte[] responseBody) throws Exception {

        SmartSerch appobj=((SmartSerch)((FragmentActivity)getActivity()).getApplication());
        String response=new String(responseBody);
        JSONArray cardArray=new JSONArray(response);
        if(cardArray.length()>0)
        {
            for(int i=0;i<cardArray.length();i++)
            {
                JSONObject card=cardArray.getJSONObject(i);
                Cards temp=new Cards();
                temp.setId(card.getString("id"));
                temp.setCardname(card.getString("caname"));
                temp.setPaidstatus(card.getString("pstatus"));
                temp.setFrontImage(card.getString("fimage"));
                //Log.e("FrontImage",card.getString("fimage"));
                temp.setBackImage(card.getString("bimage"));
                temp.setDateOfEntry(card.getString("dofentry"));
                temp.setDistrict(card.getString("distid"));
                temp.setPlace(card.getString("place"));
                temp.setWeb(card.getString("web"));
                temp.setWhatsapp(card.getString("whatsapp"));
                JSONArray mails=card.getJSONArray("mails");
                JSONArray phones=card.getJSONArray("mobiles");
                JSONArray keyarray=card.getJSONArray("keywords");

                ArrayList<Category> tempkeywords=new ArrayList<Category>();
                for(int j=0;j<keyarray.length();j++)
                {
                    Category tmp=new Category();
                    tmp.setCategoryNumber(keyarray.getJSONObject(j).getInt("id"));
                    tmp.setCategoryName(keyarray.getJSONObject(j).getString("cname"));
                    tmp.setCategoryImage(keyarray.getJSONObject(j).getString("cimage"));
                    tmp.setCardCount(keyarray.getJSONObject(j).getInt("countCard"));
                    tmp.setCardColor(keyarray.getJSONObject(j).getString("colorvalue"));
                    tmp.setHasSub(keyarray.getJSONObject(j).getInt("hasSub"));
                    tmp.setParentCategory(keyarray.getJSONObject(j).getInt("parentCategory"));
                    tempkeywords.add(tmp);
                }
                temp.setKeywords(tempkeywords);
                ArrayList tempmail=new ArrayList();
                for(int j=0;j<mails.length();j++)
                {
                    tempmail.add(mails.getJSONObject(j).getString("mailid"));
                }
                temp.setMail(tempmail);
                ArrayList tempphone=new ArrayList();
                for(int j=0;j<phones.length();j++)
                {
                    tempphone.add(phones.getJSONObject(j).getString("mobile"));
                }
                temp.setPhone(tempphone);
                temp.setDistrictname(card.getString("district"));

                cardsList.add(temp);

            }
            appobj.setCurrent(cardsList);
            cardAdapter=new CardAdapter(this,cardsList,getChildFragmentManager());
            recyclerView.setAdapter(cardAdapter);


        }
        else
        {

            recyclerView.setVisibility(View.GONE);
        }


        catLoading.setVisibility(View.GONE);
    }

    private void processFailure(Throwable error) {
        Snackbar.make(recyclerView,"An Error Occurred. Please Try Later"+error.getClass().getName(),Snackbar.LENGTH_SHORT).show();
        catLoading.setVisibility(View.GONE);
    }

}
