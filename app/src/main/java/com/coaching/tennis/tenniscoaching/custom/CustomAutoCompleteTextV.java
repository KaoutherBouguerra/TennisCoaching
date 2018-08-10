package com.coaching.tennis.tenniscoaching.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by macbook on 26/10/2017.
 */

public class CustomAutoCompleteTextV extends android.support.v7.widget.AppCompatAutoCompleteTextView {

    public CustomAutoCompleteTextV(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public CustomAutoCompleteTextV(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }



    public CustomAutoCompleteTextV(Context context) {
        super(context);
        init();
    }
    /** Returns the place description corresponding to the selected item */

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && isPopupShowing()) {
            InputMethodManager inputManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

            if(inputManager.hideSoftInputFromWindow(findFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS)){
                return true;
            }
        }

        return super.onKeyPreIme(keyCode, event);
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/CenturyGothicRegular.ttf");
        setTypeface(tf ,0);

    }
}
