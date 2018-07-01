package com.coaching.tennis.tenniscoaching;

import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coaching.tennis.tenniscoaching.fragments.AboutUsFragment;
import com.coaching.tennis.tenniscoaching.fragments.ContactUsFragment;
import com.coaching.tennis.tenniscoaching.fragments.NewsFragment;
import com.coaching.tennis.tenniscoaching.fragments.ProgressVideoFragment;
import com.coaching.tennis.tenniscoaching.fragments.StudentInfoFragment;
import com.coaching.tennis.tenniscoaching.fragments.UpdateUserInfoFragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawer;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById( R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer,   R.string.navigation_drawer_open,  R.string.navigation_drawer_close){

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(R.string.item_Home);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(R.string.item_Home);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        drawer.setDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();



        setTitle( R.string.item_Home);

        navigationView = (NavigationView) findViewById(  R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        setHeaderInfo(navigationView);


        showFragment(new NewsFragment());


    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }


    private void setHeaderInfo(NavigationView navigationView){
        View header=navigationView.getHeaderView(0);

        TextView name = (TextView)header.findViewById(R.id.txtName);

        LinearLayout linear_name = (LinearLayout)header.findViewById(R.id.linear_name);
        CircleImageView imview = (CircleImageView) header.findViewById(R.id.imDriver);

      //  name.setText(BaseApplication.session.getUserDetails().get(KEY_NAME));
        //String img_url = Constants.image_baseUrl+ BaseApplication.session.getUserDetails().get(Key_UserIMAGE);

     /*   Picasso.with(MainActivity.this).load( img_url)
                .placeholder(R.drawable.user_profile_image_background)
                .into(imview);
        */



        linear_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateUserInfoFragment schedule = new UpdateUserInfoFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_frame,schedule,"First Fragment");
                fragmentTransaction.commit();
                drawer.closeDrawer(GravityCompat.START);
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id ==  R.id.item_Home) {
            setTitle( R.string.item_Home);
            NewsFragment schedule = new NewsFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace( R.id.content_frame,schedule,"home Fragment");
            fragmentTransaction.commit();
        }
        else if (id ==  R.id.item_Myorders) {
            setTitle( R.string.item_Progress);
            ProgressVideoFragment schedule = new ProgressVideoFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace( R.id.content_frame,schedule,"home Fragment");
            fragmentTransaction.commit();
        }
        else if (id ==  R.id.item_info) {
            setTitle( R.string.item_MyInfo);
            StudentInfoFragment schedule = new StudentInfoFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace( R.id.content_frame,schedule,"home Fragment");
            fragmentTransaction.commit();
        }

        else if (id ==  R.id.item_about) {
            setTitle( R.string.about);
            AboutUsFragment schedule = new AboutUsFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace( R.id.content_frame,schedule,"home Fragment");
            fragmentTransaction.commit();


        }   else if (id == R.id.item_contac) {
            setTitle( R.string.item_contact);
            ContactUsFragment schedule = new ContactUsFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace( R.id.content_frame,schedule,"home Fragment");
            fragmentTransaction.commit();

        }else if (id == R.id.item_logout) {

//            BaseApplication.session.logoutUser();
            this.finish();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            moveTaskToBack(true);

            super.onBackPressed();
        }
    }
}
