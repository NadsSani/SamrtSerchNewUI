package com.starwings.app.samrtserchnewui;

import android.app.Application;
import android.graphics.Typeface;

import com.starwings.app.samrtserchnewui.data.Cards;

import java.util.ArrayList;

/**
 * Created by user on 12-02-2018.
 */

public class SmartSerch extends Application {

    private Typeface latoblack;
    private Typeface latbitalic;
    private Typeface latobold;
    private Typeface latoboitalic;
    private Typeface latohair;
    private Typeface latohairitalic;
    private Typeface latoitalic;
    private Typeface latolight;
    private Typeface latolitalic;
    private Typeface latoregular;

    private ArrayList<Cards> current;
    public ArrayList<Cards> getCurrent() {
        return current;
    }

    public void setCurrent(ArrayList<Cards> current) {
        this.current = current;
    }


    public Typeface getLatoblack() {

        latoblack=Typeface.createFromAsset(getAssets(), "fonts/latoblack.ttf");
        return latoblack;
    }

    public Typeface getLatbitalic() {
        latbitalic=Typeface.createFromAsset(getAssets(), "fonts/latoblackitalic.ttf");
        return latbitalic;
    }

    public Typeface getLatobold() {
        latobold=Typeface.createFromAsset(getAssets(), "fonts/latobold.ttf");
        return latobold;
    }

    public Typeface getLatoboitalic() {
        latoboitalic=Typeface.createFromAsset(getAssets(), "fonts/latobolditalic.ttf");
        return latoboitalic;
    }

    public Typeface getLatohair() {
        latohair=Typeface.createFromAsset(getAssets(), "fonts/latohairline.ttf");
        return latohair;
    }

    public Typeface getLatohairitalic() {
        latohairitalic=Typeface.createFromAsset(getAssets(), "fonts/latohairlineitalic.ttf");
        return latohairitalic;
    }

    public Typeface getLatoitalic() {
        latoitalic=Typeface.createFromAsset(getAssets(), "fonts/latoitalic.ttf");
        return latoitalic;
    }

    public Typeface getLatolight() {
        latolight=Typeface.createFromAsset(getAssets(), "fonts/latolight.ttf");
        return latolight;
    }

    public Typeface getLatolitalic() {
        latolitalic=Typeface.createFromAsset(getAssets(), "fonts/latolightitalic.ttf");
        return latolitalic;
    }

    public Typeface getLatoregular() {
        latoregular=Typeface.createFromAsset(getAssets(), "fonts/latoregular.ttf");
        return latoregular;
    }

}
