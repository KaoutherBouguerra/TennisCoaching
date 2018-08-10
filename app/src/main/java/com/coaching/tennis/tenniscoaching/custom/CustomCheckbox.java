package com.coaching.tennis.tenniscoaching.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by eniso on 12/10/2017.
 */


public class CustomCheckbox extends android.support.v7.widget.AppCompatCheckBox {

    public CustomCheckbox(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomCheckbox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomCheckbox(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/CenturyGothicRegular.ttf");
        setTypeface(tf ,0);

    }
}
