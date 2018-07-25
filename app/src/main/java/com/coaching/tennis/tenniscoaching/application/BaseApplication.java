package com.coaching.tennis.tenniscoaching.application;

import android.app.Application;

import com.coaching.tennis.tenniscoaching.session.SessionManager;

/**
 * Created by macbook on 28/06/2018.
 */

public class BaseApplication extends Application{
    public static SessionManager session;

    @Override
    public void onCreate() {
        super.onCreate();

        session = new SessionManager(getApplicationContext());

        //  SystemClock.sleep(3000);
    }
}
