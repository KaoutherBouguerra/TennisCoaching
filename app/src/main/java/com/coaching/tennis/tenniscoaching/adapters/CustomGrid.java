package com.coaching.tennis.tenniscoaching.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.coaching.tennis.tenniscoaching.Model.Actualite;
import com.coaching.tennis.tenniscoaching.Model.ImageVideo;
import com.coaching.tennis.tenniscoaching.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


import me.relex.circleindicator.CircleIndicator;

import static com.coaching.tennis.tenniscoaching.Constants.baseUrlImages;
import static com.coaching.tennis.tenniscoaching.Constants.baseUrlImagesAdmin;

/**
 * Created by macbook on 29/12/2017.
 */

public class CustomGrid extends BaseAdapter {
    private Context mContext;
    String languageToLoad;
    ArrayList<ImageVideo> imageVideoArrayList = new ArrayList<>();;

    private MediaController ctlr;

    private static ViewPager mPager;
    CircleIndicator indicator;
    SlideshowAdapter slideshowAdapter;
    Dialog dialogImages;

    public CustomGrid(Context c, ArrayList<ImageVideo> categories) {
        mContext = c;
        this.imageVideoArrayList = categories;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return imageVideoArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final ImageVideo imageVideo = imageVideoArrayList.get(position);
        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.grid_single, null);

            ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
            VideoView video = (VideoView)grid.findViewById(R.id.video);




          //  imageView.setImageResource(ca
            // t.getImage());

            Log.e("CustomGrid Adapter "," URL image = "+imageVideo.getUrl_image());

            String ext = getFileExtension(imageVideo.getUrl_image());




            if (ext.equalsIgnoreCase("jpg")
                    || ext.equalsIgnoreCase("png")
                    || ext.equalsIgnoreCase("jpeg")) {

                imageView.setVisibility(View.VISIBLE);
                Log.e("CustomGrid Adapter "," ext image = "+ext);
                Log.e("CustomGrid Adapter "," img url **  = "+baseUrlImagesAdmin+imageVideo.getUrl_image());
                Picasso.with(mContext)
                        .load(baseUrlImagesAdmin+imageVideo.getUrl_image())
                        .fit()
                        .into(imageView);

                video.setVisibility(View.GONE);


            } else {
                imageView.setVisibility(View.GONE);
                video.setVisibility(View.VISIBLE);

                String path = baseUrlImagesAdmin+imageVideo.getUrl_image();
                Uri uri = Uri.parse(path);
                video.setVideoURI(uri);
              //  video.start();
                ctlr = new MediaController(mContext);
                ctlr.setMediaPlayer(video);
                video.setMediaController(ctlr);
                video.requestFocus();
            }


            assert convertView != null;
            grid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    showDialog_image();

                }
            });
        } else {
            grid = (View) convertView;
        }




        return grid;
    }
    public void updateItems(boolean animated, ArrayList<ImageVideo> actualiteArrayList) {
        imageVideoArrayList.clear();

        imageVideoArrayList = actualiteArrayList;
      /*  feedItems.addAll(Arrays.asList(
                new FeedItem(33, false),
                new FeedItem(1, false),
                new FeedItem(223, false),
                new FeedItem(2, false),
                new FeedItem(6, false),
                new FeedItem(8, false),
                new FeedItem(99, false)
        ));
        */
       // if (animated) {
       //     notifyItemRangeInserted(0, feedItems.size());
        //} else {
            notifyDataSetChanged();
       // }
    }
    private static String getFileExtension(String fileName) {

        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }


    private void init(Dialog dialog) {

        mPager = (ViewPager) dialog.findViewById(R.id.pager);
        slideshowAdapter = new SlideshowAdapter(mContext,imageVideoArrayList);
        mPager.setAdapter(slideshowAdapter);
        indicator = (CircleIndicator) dialog.findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

    }

    public void showDialog_image() {

        dialogImages = new Dialog(mContext);
        dialogImages.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogImages.setCancelable(false);
        dialogImages.setContentView(R.layout.custom_dialog_big_image);
     //   dialogImages.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        init(dialogImages);

        final ImageView dialogButtoncancel = (ImageView) dialogImages.findViewById(R.id.img_cancel);
        dialogButtoncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogImages.dismiss();
            }
        });

        dialogImages.show();

    }
}
