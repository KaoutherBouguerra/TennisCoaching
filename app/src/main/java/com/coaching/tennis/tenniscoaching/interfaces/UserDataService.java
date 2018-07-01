package com.coaching.tennis.tenniscoaching.interfaces;


import com.coaching.tennis.tenniscoaching.Common.Common;
import com.coaching.tennis.tenniscoaching.Model.News;
import com.coaching.tennis.tenniscoaching.Model.User;
import com.coaching.tennis.tenniscoaching.Model.WebSite;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by kaoutherbouguerra on 10/4/2017.
 */

public interface UserDataService {
   // @GET("v2/sources?language=en&apiKey="+ Common.API_KEY)


    @GET
    Call<User> getUserData(@Url String url);
}
