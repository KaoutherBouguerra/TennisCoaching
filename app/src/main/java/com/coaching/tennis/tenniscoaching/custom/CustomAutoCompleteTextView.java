package com.coaching.tennis.tenniscoaching.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import java.util.HashMap;

/**
 * Created by macbook on 26/10/2017.
 */

public class CustomAutoCompleteTextView extends android.support.v7.widget.AppCompatAutoCompleteTextView {

    public CustomAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public CustomAutoCompleteTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }



    public CustomAutoCompleteTextView(Context context) {
        super(context);
        init();
    }
    /** Returns the place description corresponding to the selected item */
    @Override
    protected CharSequence convertSelectionToString(Object selectedItem) {
        /** Each item in the autocompetetextview suggestion list is a hashmap object */
        HashMap<String, String> hm = (HashMap<String, String>) selectedItem;
        return hm.get("description");
    }


    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/CenturyGothicRegular.ttf");
        setTypeface(tf ,0);

    }
}
