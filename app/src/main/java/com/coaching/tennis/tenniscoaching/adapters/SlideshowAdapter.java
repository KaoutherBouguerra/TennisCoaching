package com.coaching.tennis.tenniscoaching.adapters;

/**
 * Created by macbook on 14/12/2017.
 */
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;


import com.coaching.tennis.tenniscoaching.Model.ImageVideo;
import com.coaching.tennis.tenniscoaching.R;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.coaching.tennis.tenniscoaching.Constants.baseUrlImagesAdmin;
import static com.coaching.tennis.tenniscoaching.session.SessionManager.Key_UserImage;

public class SlideshowAdapter extends PagerAdapter {
    private ArrayList<ImageVideo> images;
    private LayoutInflater inflater;
    private Context context;
    private Intent intent_delete;
    private MediaController ctlr;

    public SlideshowAdapter(Context context, ArrayList<ImageVideo> images) {
        this.context = context;
        this.images=images;
        inflater = LayoutInflater.from(context);
       // intent_delete = new Intent(MapActivity.BROADCAST_ACTION_DELETE);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
      //  return super.getItemPosition(object);
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int position) {
        View myImageLayout = inflater.inflate(R.layout.slide, view, false);
        ImageView myImage = (ImageView) myImageLayout.findViewById(R.id.image);
        VideoView video = (VideoView) myImageLayout.findViewById(R.id.video);
     //   ImageView img_delete = (ImageView) myImageLayout.findViewById(R.id.btn_delete);
       // myImage.setRotation(90);
     //   myImage.setImageBitmap(images.get(position));



        String ext = getFileExtension(baseUrlImagesAdmin+images.get(position).getUrl_image());




        if (ext.equalsIgnoreCase("jpg")
                || ext.equalsIgnoreCase("png")
                || ext.equalsIgnoreCase("jpeg")) {

            myImage.setVisibility(View.VISIBLE);
            Log.e("CustomGrid Adapter "," ext image = "+ext);
            Log.e("CustomGrid Adapter "," img url **  = "+baseUrlImagesAdmin+images.get(position).getUrl_image());
            Picasso.with(context)
                    .load(baseUrlImagesAdmin+images.get(position).getUrl_image())
                    .into(myImage);

            video.setVisibility(View.GONE);


        } else {
            myImage.setVisibility(View.GONE);
            video.setVisibility(View.VISIBLE);

            String path = baseUrlImagesAdmin+images.get(position).getUrl_image();
            Uri uri = Uri.parse(path);
            video.setVideoURI(uri);
            //  video.start();
            ctlr = new MediaController(context);
            ctlr.setMediaPlayer(video);
            video.setMediaController(ctlr);
            video.requestFocus();
        }




        view.addView(myImageLayout, 0);
       /* img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("SlideShow","images.get(position)  "+position);
              //  images.remove(images.get(position));
             //   notifyDataSetChanged();
              //  notifyChangeInPosition(1)
                intent_delete.putExtra("POSITION", position);
                context.sendBroadcast(intent_delete);

            }
        });
        */
        return myImageLayout;
    }
    private static String getFileExtension(String fileName) {

        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
