package com.coaching.tennis.tenniscoaching.interfaces;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by kaoutherbouguerra on 10/4/2017.
 */

public interface RegisterService {
   // @GET("v2/sources?language=en&apiKey="+ Common.API_KEY)


    @GET
    Call<String> register(@Url String url);
}
