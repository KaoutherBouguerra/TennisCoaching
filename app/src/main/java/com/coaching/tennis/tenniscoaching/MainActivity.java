package com.coaching.tennis.tenniscoaching;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.coaching.tennis.tenniscoaching.Common.Common;
import com.coaching.tennis.tenniscoaching.Model.Actualite;
import com.coaching.tennis.tenniscoaching.adapters.FeedAdapter;
import com.coaching.tennis.tenniscoaching.adapters.FeedItemAnimator;
import com.coaching.tennis.tenniscoaching.application.BaseApplication;
import com.coaching.tennis.tenniscoaching.custom.CustomTypefaceSpan;
import com.coaching.tennis.tenniscoaching.fragments.ContactUsFragment;
import com.coaching.tennis.tenniscoaching.fragments.AboutUsFragment;
import com.coaching.tennis.tenniscoaching.fragments.ProgressVideoFragment;
import com.coaching.tennis.tenniscoaching.fragments.StudentInfoFragment;
import com.coaching.tennis.tenniscoaching.fragments.UpdateUserInfoFragment;
import com.coaching.tennis.tenniscoaching.interfaces.FeedDataService;
import com.coaching.tennis.tenniscoaching.utils.Utils;
import com.coaching.tennis.tenniscoaching.view.FeedContextMenuManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.coaching.tennis.tenniscoaching.session.SessionManager.Key_UserImage;
import static com.coaching.tennis.tenniscoaching.session.SessionManager.Key_nom;
import static com.coaching.tennis.tenniscoaching.session.SessionManager.Key_prenom;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener , FeedAdapter.OnFeedItemClickListener{

    DrawerLayout drawer;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;


    public static final String ACTION_SHOW_LOADING_ITEM = "action_show_loading_item";

    private static final int ANIM_DURATION_TOOLBAR = 300;
    private static final int ANIM_DURATION_FAB = 400;

    RecyclerView rvFeed;
    CoordinatorLayout clContent;
    FeedDataService mService;
    AlertDialog dialog;
    private FeedAdapter feedAdapter;
    TextView txt_msg;
    private ArrayList<Actualite> actualiteArrayList = new ArrayList<>();
    private boolean pendingIntroAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFields();

        toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);



        mService = Common.getActualite();
        dialog = new SpotsDialog(MainActivity.this);
        setupFeed();

        if (savedInstanceState == null) {
            pendingIntroAnimation = true;
        } else {
            feedAdapter.updateItems(false, actualiteArrayList);
        }


        drawer = (DrawerLayout) findViewById( R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer,   R.string.navigation_drawer_open,  R.string.navigation_drawer_close){
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
            /** Called when a drawer has settled in a completely open state. */

        };


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        drawer.setDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        setTitle( R.string.item_Home);
        applyFontForToolbarTitle(MainActivity.this, toolbar);
        navigationView = (NavigationView) findViewById(  R.id.nav_view);

        Menu m = navigationView.getMenu();
        for (int i=0;i<m.size();i++) {
            MenuItem mi = m.getItem(i);

            //for aapplying a font to subMenu ...
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu!=null && subMenu.size() >0 ) {
                for (int j=0; j <subMenu.size();j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }

            //the method we have create in activity
            applyFontToMenuItem(mi);
        }

        navigationView.setNavigationItemSelectedListener(this);


        setHeaderInfo(navigationView);


        if (BaseApplication.session.isLoggedIn()) {

           // nav_Menu.findItem(R.id.item_logout).setVisible(true);
            m.findItem(R.id.item_info).setVisible(true);
            m.findItem(R.id.item_galery).setVisible(true);

        } else {

           // nav_Menu.findItem(R.id.item_logout).setVisible(false);
            m.findItem(R.id.item_info).setVisible(false);
            m.findItem(R.id.item_galery).setVisible(false);
        }




     //   showFragment(new FeedFragment());


    }
    private void initFields(){
        rvFeed =  findViewById(R.id.rvFeed);
        clContent =  findViewById(R.id.content);
        txt_msg =  findViewById(R.id.txt_msg);
    }
    private void setupFeed() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 300;
            }
        };
        rvFeed.setLayoutManager(linearLayoutManager);

        feedAdapter = new FeedAdapter(this, actualiteArrayList);
        feedAdapter.setOnFeedItemClickListener(this);
        rvFeed.setAdapter(feedAdapter);
        rvFeed.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                FeedContextMenuManager.getInstance().onScrolled(recyclerView, dx, dy);
            }
        });
        rvFeed.setItemAnimator(new FeedItemAnimator());

        startContentAnimation();
    }



    private void getActualite() {

        dialog.show();
        String url = Common.getActualiteURL("getActualite.php");
        Log.e("get Actualite "," get Actualite url "+url);
        mService.getFeedData(url)
                .enqueue(new Callback<ArrayList<Actualite>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Actualite>> call, Response<ArrayList<Actualite>> response) {
                        dialog.dismiss();
                        //Get first article
                        Log.e("get Actualite "," get Actualite response "+response.body());
                        actualiteArrayList = response.body();

                        Log.e("get Actualite "," get Actualite getTitle "+actualiteArrayList.get(0).getTitre());
                        Log.e("get Actualite "," get Actualite getUrlImage "+actualiteArrayList.get(0).getUrl_image());

                        feedAdapter.updateItems(true,actualiteArrayList);
                        txt_msg.setVisibility(View.GONE);
                        //  feedAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Actualite>> call, Throwable t) {
                        Log.e("get Actualite "," get Actualite onFailure "+t.getMessage());

                        dialog.dismiss();
                        txt_msg.setVisibility(View.VISIBLE);


                    }
                });

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (ACTION_SHOW_LOADING_ITEM.equals(intent.getAction())) {
            showFeedLoadingItemDelayed();
        }
    }

    private void showFeedLoadingItemDelayed() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rvFeed.smoothScrollToPosition(0);
                feedAdapter.showLoadingView();
            }
        }, 500);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        if (pendingIntroAnimation) {
            pendingIntroAnimation = false;
            startIntroAnimation();
        }


        //reference to the item of the menu
     /*   MenuItem i = menu.findItem(R.id.item_Home);
        Button item_Home =(Button) i.getActionView();
        Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/CenturyGothicRegular.ttf");

        if(item_Home!=null) {
            // Create Typeface object to use unicode font in assets folder

          //  Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial.ttf");
            // Set unicode font to menu item
            item_Home.setTypeface(font);
            // Set item text and color
            item_Home.setText(getResources().getString(R.string.item_Home));
            item_Home.setTextColor(Color.GRAY);
        }
        */
        return true;
    }

    private void startIntroAnimation() {

        int actionbarSize = Utils.dpToPx(56);
        toolbar.setTranslationY(-actionbarSize);
      //  getIvLogo().setTranslationY(-actionbarSize);
       // getInboxMenuItem().getActionView().setTranslationY(-actionbarSize);

        toolbar.animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(300);
   /*     getIvLogo().animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(400);
        getInboxMenuItem().getActionView().animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        startContentAnimation();
                    }
                })
                .start();
    */


    }

    private void startContentAnimation() {
        getActualite();
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // This is required to make the drawer toggle work
        if(toggle.onOptionsItemSelected(item)) {
            return true;
        }

    /*
     * if you have other menu items in your activity/toolbar
     * handle them here and return true
     */

        return super.onOptionsItemSelected(item);
    }

    private void setHeaderInfo(NavigationView navigationView) {

        View header = navigationView.getHeaderView(0);
        TextView txtName = (TextView)header.findViewById(R.id.txtName);
        TextView login_or_logout_button = (TextView)navigationView.findViewById(R.id.login_or_logout_button);
        ImageView img_profile = (ImageView)header.findViewById(R.id.img_profile);


        LinearLayout linear_name = (LinearLayout)header.findViewById(R.id.linear_name);
        CircleImageView imview = (CircleImageView) header.findViewById(R.id.imDriver);
        if (BaseApplication.session.isLoggedIn()) {

            String name = BaseApplication.session.getUserDetails().get(Key_prenom)+" "+BaseApplication.session.getUserDetails().get(Key_nom);
            txtName.setText(name);

            Picasso.with(MainActivity.this).load(BaseApplication.session.getUserDetails().get(Key_UserImage))
                    .placeholder(R.drawable.user_profile_image_background)
                    .into(imview);

            linear_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, R.string.error_not_exist, Toast.LENGTH_LONG).show();

                 /*   UpdateUserInfoFragment schedule = new UpdateUserInfoFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame,schedule,"First Fragment");
                    fragmentTransaction.commit();
                    */
                    drawer.closeDrawer(GravityCompat.START);


                }
            });

            login_or_logout_button.setText(R.string.sgnout);

        }else {
           // txtName.setText("Bienvenue");
            login_or_logout_button.setText("Login");
            imview.setVisibility(View.GONE);
            img_profile.setVisibility(View.GONE);
            txtName.setVisibility(View.GONE);

        }


        login_or_logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (BaseApplication.session.isLoggedIn()) {
                    BaseApplication.session.logoutUser();
                    finish();
                }else {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });




    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id ==  R.id.item_Home) {
         /*   setTitle( R.string.item_Home);
            FeedFragment schedule = new FeedFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace( R.id.frame,schedule,"home Fragment");
            fragmentTransaction.commit();
            */
            setTitle( R.string.item_Home);
            for (Fragment fragment:getSupportFragmentManager().getFragments()) {
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }

        } else if (id ==  R.id.item_galery) {
            setTitle( R.string.item_Progress);
            ProgressVideoFragment schedule = new ProgressVideoFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace( R.id.frame,schedule,"home Fragment");
            fragmentTransaction.commit();
        } else if (id ==  R.id.item_info) {
            setTitle( R.string.item_MyInfo);
            StudentInfoFragment schedule = new StudentInfoFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace( R.id.frame,schedule,"home Fragment");
            fragmentTransaction.commit();
        } else if (id ==  R.id.item_contac) {
            setTitle( R.string.item_contact);
            ContactUsFragment schedule = new ContactUsFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace( R.id.frame,schedule,"home Fragment");
            fragmentTransaction.commit();


        }   else if (id == R.id.item_about) {
            setTitle( R.string.about);
            AboutUsFragment schedule = new AboutUsFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace( R.id.frame,schedule,"home Fragment");
            fragmentTransaction.commit();

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

    @Override
    public void onCommentsClick(View v, int position) {

        Toast.makeText(MainActivity.this, R.string.error_not_exist, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMoreClick(View v, int position) {
        Toast.makeText(MainActivity.this, R.string.error_not_exist, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onProfileClick(View v) {
        Toast.makeText(MainActivity.this, R.string.error_not_exist, Toast.LENGTH_LONG).show();

    }


    public static void applyFontForToolbarTitle(Context context,  Toolbar toolbar){

        for(int i = 0; i < toolbar.getChildCount(); i++){
            View view = toolbar.getChildAt(i);
            if(view instanceof TextView){
                TextView tv = (TextView) view;
                Typeface titleFont = Typeface.
                        createFromAsset(context.getAssets(), "fonts/CenturyGothicRegular.ttf");
                if(tv.getText().equals(toolbar.getTitle())){
                    tv.setTypeface(titleFont);
                    break;
                }
            }
        }
    }
    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/CenturyGothicRegular.ttf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("" , font), 0 , mNewTitle.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }
}
