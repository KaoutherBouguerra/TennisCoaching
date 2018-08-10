package com.coaching.tennis.tenniscoaching.fragments;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coaching.tennis.tenniscoaching.MainActivity;
import com.coaching.tennis.tenniscoaching.R;
import com.coaching.tennis.tenniscoaching.adapters.FeedAdapter;
import com.coaching.tennis.tenniscoaching.adapters.FeedItemAnimator;
import com.coaching.tennis.tenniscoaching.view.FeedContextMenu;
import com.coaching.tennis.tenniscoaching.view.FeedContextMenuManager;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment implements FeedAdapter.OnFeedItemClickListener{


   View v;
    public static final String ACTION_SHOW_LOADING_ITEM = "action_show_loading_item";

    private static final int ANIM_DURATION_TOOLBAR = 300;
    private static final int ANIM_DURATION_FAB = 400;

    @BindView(R.id.rvFeed)
    RecyclerView rvFeed;

    FloatingActionButton fabCreate;
    @BindView(R.id.content)
    CoordinatorLayout clContent;

    private FeedAdapter feedAdapter;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_feed, container, false);
        initFields();
        setupFeed();

     if (savedInstanceState == null) {
     // MainActivity.pendingIntroAnimation = true;
     } else {
      //feedAdapter.updateItems(false);
     }

        return v;
    }

    private void initFields(){
     rvFeed = v.findViewById(R.id.rvFeed);
    }
    private void setupFeed() {
     LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()) {
   @Override
   protected int getExtraLayoutSpace(RecyclerView.State state) {
    return 300;
   }
  };
  rvFeed.setLayoutManager(linearLayoutManager);

  //feedAdapter = new FeedAdapter(getActivity());
  feedAdapter.setOnFeedItemClickListener(this);
  rvFeed.setAdapter(feedAdapter);
  rvFeed.setOnScrollListener(new RecyclerView.OnScrollListener() {
   @Override
   public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
    FeedContextMenuManager.getInstance().onScrolled(recyclerView, dx, dy);
   }
  });
  rvFeed.setItemAnimator(new FeedItemAnimator());
 }
 @Override
 public void onCommentsClick(View v, int position) {

 }

 @Override
 public void onMoreClick(View v, int position) {

 }

 @Override
 public void onProfileClick(View v) {

 }
}
