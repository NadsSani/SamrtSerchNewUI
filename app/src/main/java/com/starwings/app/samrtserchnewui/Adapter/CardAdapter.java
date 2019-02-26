package com.starwings.app.samrtserchnewui.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.starwings.app.samrtserchnewui.Fragments.CardDetailFragment;
import com.starwings.app.samrtserchnewui.HomeWrapperPage;
import com.starwings.app.samrtserchnewui.Links.ApiLinks;
import com.starwings.app.samrtserchnewui.R;
import com.starwings.app.samrtserchnewui.SmartSerch;
import com.starwings.app.samrtserchnewui.data.Cards;
import com.starwings.app.samrtserchnewui.data.Category;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by user on 04-11-2017.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> implements Filterable {
    ArrayList<Cards> cardsList;
    private Fragment mContext;
    private ValueFilter valueFilter;
    private ArrayList<Cards> mStringFilterList;
    FragmentManager childFragmentManager;
    public  CardAdapter(Fragment mContext, ArrayList<Cards> Cards,FragmentManager fragmentManager) {
        this.mContext = mContext;
        this.cardsList = Cards;
        this.mStringFilterList=Cards;
        childFragmentManager=fragmentManager;
    }
    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_content, parent, false);


        return new CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, final int position) {
        Cards cards = cardsList.get(position);
        holder.title.setText(cards.getCardname());
        Glide.with(mContext)
                .load(ApiLinks.basegalLink+cardsList.get(position).getFrontImage())
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into( holder.thumbnail);

        ArrayList<Category> keywords=cardsList.get(position).getKeywords();

        holder.buttonlayout.removeAllViews();
        for(int i=0;i<keywords.size();i++)
        {
            LinearLayout.LayoutParams txtparams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            txtparams.setMargins(5,5,5,5);

           final TextView btn_=new TextView(mContext.getContext());

            btn_.setText(keywords.get(i).getCategoryName());
            btn_.setId(keywords.get(i).getCategoryNumber());
            btn_.setPadding(5,5,5,5);
            btn_.setTextColor(Color.parseColor("#FFFFFF"));
            btn_.setLayoutParams(txtparams);
            HomeWrapperPage pobj=((HomeWrapperPage)mContext.getActivity());
            SmartSerch appobj=(SmartSerch)pobj.getApplication();
            btn_.setTypeface(appobj.getLatoregular());
            btn_.setBackgroundResource(R.drawable.button_back);
            holder.buttonlayout.addView(btn_);
            btn_.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  //  showSimilarCards(btn_.getText(),btn_.getId());
                }
            });
        }

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDetailPage(position,cardsList);
            }
        });


    }

    private void showDetailPage(int position,ArrayList cardsList) {
        CardDetailFragment cardDetailFragment = new CardDetailFragment();
        FragmentTransaction transaction = childFragmentManager.beginTransaction();

        // Store the Fragment in stack
        transaction.addToBackStack(null);
        transaction.replace(R.id.mainContainer, cardDetailFragment).commit();
    }


//    private void showDetailPage(int position, ArrayList<Cards> cardsList) {
//
//        SmartSerch appobj=(SmartSerch)((HomeWrapperPage)mContext.getActivity()).getApplication();
//        appobj.setCurrent(cardsList);
//
//        CardDetailFragment card_detail_fragment=new CardDetailFragment();
//        FragmentManager fragManager=mContext.getChildFragmentManager();
//        fragManager.re
//        fragManager.beginTransaction();
//        Intent cdpage=new Intent(mContext,CardDetailFragment.class);
//        cdpage.putExtra("selected",""+position);
//        mContext.startActivity(cdpage);
//    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        return cardsList.size();
    }

    @Override
    public Filter getFilter() {
        if(valueFilter==null) {

            valueFilter=new ValueFilter();
        }

        return valueFilter;
    }

    private class ValueFilter extends Filter {

        //Invoked in a worker thread to filter the data according to the constraint.
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results=new FilterResults();
            if(constraint!=null && constraint.length()>0)
            {

                String contstraintString = constraint.toString();
//                if (contstraintString.contains(" "))
//                {
//                    ArrayList<Cards> filterList = new ArrayList<Cards>();
//                    String constraintarray[]= contstraintString.split(" ");
//                    for(int key=0;key<constraintarray.length;key++)
//                    {
//                       for (int i = 0; i < mStringFilterList.size(); i++)
//                       {
//                           if (!((mStringFilterList.get(i).getDistrictname().toUpperCase()).contains(constraintarray[key].toString().toUpperCase()) ||
//                                 (mStringFilterList.get(i).getCardname().toUpperCase()).contains(constraintarray[key].toString().toUpperCase()) ||
//                                   filterByKeywords(mStringFilterList.get(i), constraintarray[key]) || mStringFilterList.get(i).getPlace().toUpperCase().contains(constraintarray[key].toString().toUpperCase())))
//                           {
//
//                           }
//                           else
//                           {
//
//                               filterList.add(mStringFilterList.get(i));
//                               break;
//                           }
//                       }
//
//                   }
//                    Set<Cards> hs = new HashSet<>();
//                    hs.addAll(filterList);
//                    filterList.clear();
//                    filterList.addAll(hs);
//                    results.count = filterList.size();
//                    results.values = filterList;
//                }
//                else
//                {
                    Log.e("Constraint","Contains No Space");
                ArrayList<Cards> filterList = new ArrayList<Cards>();
                for (int i = 0; i < mStringFilterList.size(); i++) {
                    if ((mStringFilterList.get(i).getDistrictname().toUpperCase())
                            .contains(constraint.toString().toUpperCase()) || (mStringFilterList.get(i).getCardname().toUpperCase())
                            .contains(constraint.toString().toUpperCase()) || filterByKeywords(mStringFilterList.get(i), constraint) || mStringFilterList.get(i).getPlace().toUpperCase().contains(constraint.toString().toUpperCase())) {
                        Cards temp = new Cards();
                        temp.setId(mStringFilterList.get(i).getId());
                        temp.setCardname(mStringFilterList.get(i).getCardname());
                        temp.setPaidstatus(mStringFilterList.get(i).getPaidstatus());
                        temp.setFrontImage(mStringFilterList.get(i).getFrontImage());
                        temp.setBackImage(mStringFilterList.get(i).getBackImage());
                        temp.setDateOfEntry(mStringFilterList.get(i).getDateOfEntry());
                        temp.setDistrict(mStringFilterList.get(i).getDistrict());
                        temp.setPhone(mStringFilterList.get(i).getPhone());
                        temp.setPlace(mStringFilterList.get(i).getPlace());
                        temp.setWeb(mStringFilterList.get(i).getWeb());
                        temp.setMail(mStringFilterList.get(i).getMail());
                        temp.setKeywords(mStringFilterList.get(i).getKeywords());
                        temp.setWhatsapp(mStringFilterList.get(i).getWhatsapp());
                        temp.setDistrictname(mStringFilterList.get(i).getDistrictname());
                        filterList.add(temp);
                    }

                }


                    Set<Cards> hs = new HashSet<>();
                    hs.addAll(filterList);
                    filterList.clear();
                    filterList.addAll(hs);
                results.count = filterList.size();
                results.values = filterList;
           // }
            }else{
                results.count=mStringFilterList.size();
                results.values=mStringFilterList;
            }
            return results;
        }


        //Invoked in the UI thread to publish the filtering results in the user interface.
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            cardsList=(ArrayList<Cards>) results.values;
            notifyDataSetChanged();
        }

        private boolean filterByKeywords(Cards card,CharSequence keyword)
        {
            boolean matched=false;
            ArrayList<Category> keywords=card.getKeywords();
            for(int j=0;j<keywords.size();j++)
            {
                if(keywords.get(j).getCategoryName().trim().toLowerCase().equals(keyword.toString().trim().toLowerCase()))
                {
                    matched=true;
                    break;
                }
            }
            return matched;
        }
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;
        public LinearLayout buttonlayout;


        public CardViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.txtCaption);
            thumbnail = (ImageView) view.findViewById(R.id.imgIcon);
            buttonlayout = (LinearLayout) view.findViewById(R.id.keywords_layout);

        }
    }
}
