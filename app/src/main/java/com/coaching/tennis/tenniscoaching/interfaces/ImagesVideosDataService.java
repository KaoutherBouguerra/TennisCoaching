package com.coaching.tennis.tenniscoaching.interfaces;


import com.coaching.tennis.tenniscoaching.Model.Actualite;
import com.coaching.tennis.tenniscoaching.Model.ImageVideo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by kaoutherbouguerra on 10/4/2017.
 */

public interface ImagesVideosDataService {
   // @GET("v2/sources?language=en&apiKey="+ Common.API_KEY)


    @GET
    Call<ArrayList<ImageVideo>> getFeedData(@Url String url);
}
