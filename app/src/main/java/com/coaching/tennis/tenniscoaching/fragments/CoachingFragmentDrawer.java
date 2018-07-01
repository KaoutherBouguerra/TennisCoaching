package com.coaching.tennis.tenniscoaching.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coaching.tennis.tenniscoaching.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CoachingFragmentDrawer extends Fragment {


    public CoachingFragmentDrawer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coaching_fragment_drawer, container, false);
    }

}
