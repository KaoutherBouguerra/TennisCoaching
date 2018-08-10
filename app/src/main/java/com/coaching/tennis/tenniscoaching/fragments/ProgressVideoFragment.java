package com.coaching.tennis.tenniscoaching.fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.coaching.tennis.tenniscoaching.Common.Common;
import com.coaching.tennis.tenniscoaching.MainActivity;
import com.coaching.tennis.tenniscoaching.Model.Actualite;
import com.coaching.tennis.tenniscoaching.Model.ImageVideo;
import com.coaching.tennis.tenniscoaching.R;
import com.coaching.tennis.tenniscoaching.adapters.CustomGrid;
import com.coaching.tennis.tenniscoaching.adapters.FeedAdapter;
import com.coaching.tennis.tenniscoaching.adapters.SlideshowAdapter;
import com.coaching.tennis.tenniscoaching.application.BaseApplication;
import com.coaching.tennis.tenniscoaching.interfaces.FeedDataService;
import com.coaching.tennis.tenniscoaching.interfaces.ImagesVideosDataService;


import org.lucasr.twowayview.widget.TwoWayView;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.coaching.tennis.tenniscoaching.session.SessionManager.Key_id_client;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProgressVideoFragment extends Fragment {

    GridView mGridView;
    View v;
    ImagesVideosDataService mService;
    AlertDialog dialog;

    private CustomGrid adapter;

    private ArrayList<ImageVideo> imageVideoArrayList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_progress_video, container, false);
        mService = Common.getImages();
        dialog = new SpotsDialog(getActivity());
        adapter = new CustomGrid(getActivity(),imageVideoArrayList );
 /*       mGridView = (StaggeredGridView) v.findViewById(R.id.grid_view);
        mGridView.setAdapter(adapter);
*/
        mGridView = (GridView) v.findViewById(R.id.grid);
       // mGridView.setHasFixedSize(true);
      //  mRecyclerView.setLongClickable(true);
        mGridView.setAdapter(adapter);


        getImagesVideos();



        return v;
    }

    private void getImagesVideos() {

        dialog.show();
        String url = Common.getImagesVideosURL("getImages.php?", BaseApplication.session.getUserDetails().get(Key_id_client));
        Log.e("get Images "," get Images url "+url);
        mService.getFeedData(url)
                .enqueue(new Callback<ArrayList<ImageVideo>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ImageVideo>> call, Response<ArrayList<ImageVideo>> response) {
                        dialog.dismiss();
                        //Get first article
                        Log.e("get Images "," get Images response "+response.body());
                        imageVideoArrayList = response.body();

                        Log.e("get Images "," get Images url "+imageVideoArrayList.get(0).getUrl_image());

                      //  adapter.notifyDataSetChanged();
                        adapter.updateItems(true,imageVideoArrayList);



                        //  feedAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ImageVideo>> call, Throwable t) {
                        Log.e("get Images "," get Images onFailure ");

                        dialog.dismiss();
                    }
                });

    }


}
