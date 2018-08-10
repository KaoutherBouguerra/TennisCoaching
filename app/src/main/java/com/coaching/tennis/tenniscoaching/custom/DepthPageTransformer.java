package com.coaching.tennis.tenniscoaching.custom;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by macbook on 29/11/2017.
 */

public class DepthPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.75f;

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0);

        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when moving to the left page


        } else if (position <= 1) { // (0,1]
            // Fade the page out.


        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0);
        }
    }
}
