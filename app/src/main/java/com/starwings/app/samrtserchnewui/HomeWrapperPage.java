package com.starwings.app.samrtserchnewui;

 import android.os.Bundle;
 import android.support.v4.app.FragmentManager;
 import android.support.v7.app.ActionBar;
 import android.support.v7.app.AppCompatActivity;
 import android.view.Window;


public class HomeWrapperPage extends AppCompatActivity {

    private CoverFragment coverFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.home_wrapper_layout);
        setTitle("SMARTSERCH");
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeButtonEnabled(true);

        if (savedInstanceState == null) {
            // withholding the previously created fragment from being created again
            // On orientation change, it will prevent fragment recreation
            // its necessary to reserving the fragment stack inside each tab
            initScreen();

        } else {
            // restoring the previously created fragment
            // and getting the reference
            coverFragment = (CoverFragment) getSupportFragmentManager().getFragments().get(0);
        }
    }

    private void initScreen() {
        // Creating the ViewPager container fragment once
        coverFragment = new CoverFragment();

        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, coverFragment)
                .commit();
    }

    /**
     * Only Activity has this special callback method
     * Fragment doesn't have any onBackPressed callback
     *
     * Logic:
     * Each time when the back button is pressed, this Activity will propagate the call to the
     * container Fragment and that Fragment will propagate the call to its each tab Fragment
     * those Fragments will propagate this method call to their child Fragments and
     * eventually all the propagated calls will get back to this initial method
     *
     * If the container Fragment or any of its Tab Fragments and/or Tab child Fragments couldn't
     * handle the onBackPressed propagated call then this Activity will handle the callback itself
     */
    @Override
    public void onBackPressed() {

        if (!coverFragment.onBackPressed()) {
            // container Fragment or its associates couldn't handle the back pressed task
            // delegating the task to super class
            super.onBackPressed();

        } else {
            // carousel handled the back pressed task
            // do not call super
        }
    }
}

//
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.support.design.widget.Snackbar;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.view.ViewPager;
//import android.support.design.widget.TabLayout;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.HorizontalScrollView;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//import com.loopj.android.http.AsyncHttpClient;
//import com.loopj.android.http.AsyncHttpResponseHandler;
//import com.loopj.android.http.RequestParams;
//import com.starwings.app.samrtserchnewui.Adapter.PageAdapter;
//import com.starwings.app.samrtserchnewui.Links.ApiLinks;
//import com.starwings.app.samrtserchnewui.data.Cards;
//import com.starwings.app.samrtserchnewui.data.Category;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//
//import cz.msebera.android.httpclient.Header;
//
///**
// * Created by user on 09-02-2018.
// */
//
//public class HomeWrapperPage extends FragmentActivity {
//    // When requested, this adapter returns a HomeFragment,
//    // representing an object in the collection.
//    PageAdapter mDemoCollectionPagerAdapter;
//    ViewPager mViewPager;
//
//    /* Recent Cards */
//
//    private LinearLayout mGallery;
//    private int[] mImgIds;
//    private LayoutInflater mInflater;
//    private HorizontalScrollView horizontalScrollView;
//    ArrayList<Cards> cardsList;
//
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.home_wrapper_layout);
//
//        // ViewPager and its adapters use support library
//        // fragments, so use getSupportFragmentManager.
//        mDemoCollectionPagerAdapter =
//                new PageAdapter(
//                        getSupportFragmentManager());
//        mViewPager = (ViewPager) findViewById(R.id.pager);
//        mViewPager.setAdapter(mDemoCollectionPagerAdapter);
//
//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(mViewPager);
//
//        /*Recent Cards */
//
//        mInflater = LayoutInflater.from(this);
//
//        initView();
//    }
//
//    private void fetchCards() {
//        cardsList=new ArrayList<>();
//       // catLoading.setVisibility(View.VISIBLE);
//        SharedPreferences appPreferences=getSharedPreferences("APP_PREFERENCES",MODE_PRIVATE);
//        String apikey=appPreferences.getString("apikey","NA");
//
//        if(!apikey.equals("NA"))
//        {
//            RequestParams params=new RequestParams();
//            params.put("Authorization",apikey);
//
//            AsyncHttpClient cardsClient=new AsyncHttpClient();
//            cardsClient.setResponseTimeout(50000);
//            cardsClient.setConnectTimeout(50000);
//            cardsClient.setTimeout(50000);
//
//            cardsClient.post(this, ApiLinks.baseLink + ApiLinks.recentCards, params, new AsyncHttpResponseHandler() {
//
//
//
//                @Override
//                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                    try {
//                        prepareCardListing(responseBody);
//                    } catch (Exception e) {
//                        Snackbar.make(mViewPager,"An Error Occurred. Please Try Later",Snackbar.LENGTH_SHORT).show();
//                        //  catLoading.setVisibility(View.GONE);
//                    }
//                }
//
//                @Override
//                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                    //processFailure(error);
//                }
//            });
//        }
//
//    }
//
//    private void prepareCardListing(byte[] responseBody) throws Exception {
//
//        SmartSerch appobj=((SmartSerch)getApplication());
//        String response=new String(responseBody);
//        JSONArray cardArray=new JSONArray(response);
//        if(cardArray.length()>0)
//        {
//            for(int i=0;i<cardArray.length();i++)
//            {
//                JSONObject card=cardArray.getJSONObject(i);
//                Cards temp=new Cards();
//                temp.setId(card.getString("id"));
//                temp.setCardname(card.getString("caname"));
//                temp.setPaidstatus(card.getString("pstatus"));
//                temp.setFrontImage(card.getString("fimage"));
//                //Log.e("FrontImage",card.getString("fimage"));
//                temp.setBackImage(card.getString("bimage"));
//                temp.setDateOfEntry(card.getString("dofentry"));
//                temp.setDistrict(card.getString("distid"));
//                temp.setPlace(card.getString("place"));
//                temp.setWeb(card.getString("web"));
//                temp.setWhatsapp(card.getString("whatsapp"));
//                JSONArray mails=card.getJSONArray("mails");
//                JSONArray phones=card.getJSONArray("mobiles");
//                JSONArray keyarray=card.getJSONArray("keywords");
//
//                ArrayList<Category> tempkeywords=new ArrayList<Category>();
//                for(int j=0;j<keyarray.length();j++)
//                {
//                    Category tmp=new Category();
//                    tmp.setCategoryNumber(keyarray.getJSONObject(j).getInt("id"));
//                    tmp.setCategoryName(keyarray.getJSONObject(j).getString("cname"));
//                    tmp.setCategoryImage(keyarray.getJSONObject(j).getString("cimage"));
//                    tmp.setCardCount(keyarray.getJSONObject(j).getInt("countCard"));
//                    tmp.setCardColor(keyarray.getJSONObject(j).getString("colorvalue"));
//                    tmp.setHasSub(keyarray.getJSONObject(j).getInt("hasSub"));
//                    tmp.setParentCategory(keyarray.getJSONObject(j).getInt("parentCategory"));
//                    tempkeywords.add(tmp);
//                }
//                temp.setKeywords(tempkeywords);
//                ArrayList tempmail=new ArrayList();
//                for(int j=0;j<mails.length();j++)
//                {
//                    tempmail.add(mails.getJSONObject(j).getString("mailid"));
//                }
//                temp.setMail(tempmail);
//                ArrayList tempphone=new ArrayList();
//                for(int j=0;j<phones.length();j++)
//                {
//                    tempphone.add(phones.getJSONObject(j).getString("mobile"));
//                }
//                temp.setPhone(tempphone);
//                temp.setDistrictname(card.getString("district"));
//
//                cardsList.add(temp);
//
//            }
//            appobj.setCurrent(cardsList);
//
//            mGallery = (LinearLayout) findViewById(R.id.id_gallery);
//
//            for (int i = 0; i < cardsList.size(); i++)
//            {
//
//                View view = mInflater.inflate(R.layout.gallery_image_item,
//                        mGallery, false);
//                ImageView img = (ImageView) view
//                        .findViewById(R.id.id_index_gallery_item_image);
//                Glide.with(this)
//                        .load(ApiLinks.basegalLink+cardsList.get(i).getFrontImage())
//                        .placeholder(R.drawable.placeholder)
//                        .diskCacheStrategy(DiskCacheStrategy.NONE)
//                        .skipMemoryCache(true)
//                        .into( img);
//
//                TextView txt = (TextView) view
//                        .findViewById(R.id.id_index_gallery_item_text);
//                txt.setText(cardsList.get(i).getCardname());
//                mGallery.addView(view);
//            }
//        }
//
//    }
//
//    private void initView()
//    {
//        fetchCards();
//
//
//    }
//}
//
//// Since this is an object collection, use a FragmentStatePagerAdapter,
//// and NOT a FragmentPagerAdapter.
//
//
//// Instances of this class are fragments representing a single
//// object in our collection.
