package com.coaching.tennis.tenniscoaching.Common;


import com.coaching.tennis.tenniscoaching.Remote.IconBetterIdeaClient;
import com.coaching.tennis.tenniscoaching.Remote.RetrofitClient;
import com.coaching.tennis.tenniscoaching.interfaces.FeedDataService;
import com.coaching.tennis.tenniscoaching.interfaces.IconBetterIdeaService;
import com.coaching.tennis.tenniscoaching.interfaces.ImagesVideosDataService;
import com.coaching.tennis.tenniscoaching.interfaces.NewsService;
import com.coaching.tennis.tenniscoaching.interfaces.RegisterService;
import com.coaching.tennis.tenniscoaching.interfaces.SendFeedBackService;
import com.coaching.tennis.tenniscoaching.interfaces.UserDataService;

/**
 * Created by kaoutherbouguerra on 10/4/2017.
 */

public class Common {
    private static final String BASE_URL="http://admin.salhicoach.com/api/";

    public  static final String API_KEY="a7072d9c2ad9495a8dd5cb58a7fd30df";

    public static NewsService getNewsService()
    {
        return RetrofitClient.getClient(BASE_URL).create(NewsService.class);
    }
    public static UserDataService login()
    {
        return RetrofitClient.getClient(BASE_URL).create(UserDataService.class);
    }
    public static RegisterService register()
    {
        return RetrofitClient.getClient(BASE_URL).create(RegisterService.class);
    }
    public static SendFeedBackService sendFeedBackService()
    {
        return RetrofitClient.getClient(BASE_URL).create(SendFeedBackService.class);
    }
    public static FeedDataService getActualite()
    {
        return RetrofitClient.getClient(BASE_URL).create(FeedDataService.class);
    }
    public static ImagesVideosDataService getImages()
    {
        return RetrofitClient.getClient(BASE_URL).create(ImagesVideosDataService.class);
    }

    public static IconBetterIdeaService getIconService()
    {
        return IconBetterIdeaClient.getClient().create(IconBetterIdeaService.class);
    }

    //https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey=a7072d9c2ad9495a8dd5cb58a7fd30df
    public static String getAPIUrl(String source, String sortBy, String apiKEY)
    {
        StringBuilder apiUrl = new StringBuilder("https://newsapi.org/v2/top-headlines?sources=");
        return apiUrl.append(source)
                .append("&apiKey=")
                .append(apiKEY)
                .toString();
    }
    public static String getLoginURL(String source, String username, String password)
    {
        StringBuilder apiUrl = new StringBuilder(BASE_URL);
        return apiUrl.append(source)
                .append("username=")
                .append(username)
                .append("&password=")
                .append(password)
                .toString();
    }
    public static String sendFeedBackURL(String source, String username, String email, String mobile, String feedBack)
    {
        StringBuilder apiUrl = new StringBuilder(BASE_URL);
        return apiUrl.append(source)
                .append("username=")
                .append(username)
                .append("&email=")
                .append(email)
                .append("&phone")
                .append(mobile)
                .append("&body")
                .append(feedBack)
                .toString();
    }
    public static String sendregisterURL(String source, String username, String email, String mobile, String pwd)
    {
        StringBuilder apiUrl = new StringBuilder(BASE_URL);
        return apiUrl.append(source)
                .append("username=")
                .append(username)
                .append("&email=")
                .append(email)
                .append("&phone")
                .append(mobile)
                .append("&pwd")
                .append(pwd)
                .toString();
    }
    public static String getImagesVideosURL(String source, String id)
    {
        StringBuilder apiUrl = new StringBuilder(BASE_URL);
        return apiUrl.append(source)
                .append("id=")
                .append(id)
                .toString();
    }
    public static String getActualiteURL(String source)
    {
        StringBuilder apiUrl = new StringBuilder(BASE_URL);
        return apiUrl.append(source)

                .toString();
    }


}
