package com.starwings.app.samrtserchnewui.Fragments;

import android.support.v4.app.Fragment;

import com.starwings.app.samrtserchnewui.Adapter.BackPressImpl;
import com.starwings.app.samrtserchnewui.Listener.OnBackPressListener;



/**
 * Created by shahabuddin on 6/6/14.
 */
public class RootFragment extends Fragment implements OnBackPressListener {

    @Override
    public boolean onBackPressed() {
        return new BackPressImpl(this).onBackPressed();
    }
}
