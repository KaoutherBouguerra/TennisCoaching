package com.coaching.tennis.tenniscoaching;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.coaching.tennis.tenniscoaching.application.BaseApplication;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                } finally {


                  //  if (!BaseApplication.session.isLoggedIn()){
                        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                  //  }else BaseApplication.session.checkLogin();

                }
            }
        };
        timerThread.start();

    }
}
