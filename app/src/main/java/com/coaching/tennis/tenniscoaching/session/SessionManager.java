package com.coaching.tennis.tenniscoaching.session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;


import com.coaching.tennis.tenniscoaching.Constants;
import com.coaching.tennis.tenniscoaching.LoginActivity;
import com.coaching.tennis.tenniscoaching.MainActivity;

import java.util.HashMap;

public class
SessionManager {
    // access token
    public static final String KEY_Acesstoken = "access_token";

    // Sharedpref file name
    private static final String PREF_NAME = "TennisCoachPref";
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String Key_id_client="id_client";
    public static final String Key_login="id_login";
    public static final String Key_password="password";
    public static final String Key_nom="nom";
    public static final String Key_prenom="prenom";
    public static final String Key_adresse="adresse";
    public static final String Key_date_naissance="date_naissance";
    public static final String Key_activite_sportif="activite_sportif";
    public static final String Key_nom_pere="nom_pere";
    public static final String Key_prenom_pere="prenom_pere";
    public static final String Key_email_pere="email_pere";
    public static final String Key_travail_pere="travail_pere";
    public static final String Key_tel_pere="tel_pere";
    public static final String Key_nom_mere="nom_mere";
    public static final String Key_prenom_mere="prenom_mere";
    public static final String Key_email_mere="email_mere";
    public static final String Key_travail_mere="travail_mere";
    public static final String Key_tel_mere="tel_mere";
    public static final String Key_UserImage="USERIMAGE";




    // Shared Preferences
    SharedPreferences pref;
    // Editor for Shared preferences
    Editor editor;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Constructor
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public void saveAccessToken(String accessToken){
        editor.putString(KEY_Acesstoken,accessToken);
        editor.commit();


    }
    public String getAccessToken() {
        return pref.getString(KEY_Acesstoken, null);
    }
    /**
     * Create login session
     */

    public void saveLoginState(boolean isLogin){
        editor.putBoolean(IS_LOGIN, isLogin);
        editor.commit();


    }
    public void createLoginSession(String id, String login,String password,String nom, String prenom,String adresse, String date_naissance,String activite_sportif,String url_image,
                                   String nom_pere,String prenom_pere, String email_pere, String travail_pere, String tel_pere,
                                   String nom_mere,String prenom_mere, String email_mere, String travail_mere, String tel_mere) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN,true);

        // storing access token in pref
        editor.putString(Key_id_client, id);
        Log.v("id", ""+id);

        // Storing name in pref
        editor.putString(Key_nom, nom);
        editor.putString(Key_prenom, prenom);

        // Storing phone in pref
        editor.putString(Key_login, login);
        editor.putString(Key_password, password);
        editor.putString(Key_adresse, adresse);
        editor.putString(Key_date_naissance, date_naissance);
        editor.putString(Key_activite_sportif, activite_sportif);

        editor.putString(Key_nom_pere, nom_pere);
        editor.putString(Key_prenom_pere, prenom_pere);
        editor.putString(Key_travail_pere, travail_pere);
        editor.putString(Key_email_pere, email_pere);
        editor.putString(Key_tel_pere, tel_pere);

        editor.putString(Key_nom_mere, nom_mere);
        editor.putString(Key_prenom_mere, prenom_mere);
        editor.putString(Key_travail_mere, travail_mere);
        editor.putString(Key_email_mere, email_mere);
        editor.putString(Key_tel_mere, tel_mere);


        // Storing email in pref
       // editor.putString(Key_UserPhoneHome, phoneHome);
       // Log.v("key phoneHome", phoneHome);

        // Storing email in pref
        editor.putString(Key_UserImage, Constants.baseUrlImages+url_image );
        Log.v("key thumbnail", url_image);


        // commit changes
        editor.commit();
    }
    // to get user id
    public void saveUserId(int userId){
        editor.putInt(Key_id_client,userId);
        editor.commit();


    }




    /**
     * Get stored session data
     */
    public HashMap<String, String > getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();

        user.put(Key_id_client, pref.getString(Key_id_client, null));

        user.put(Key_nom, pref.getString(Key_nom, null));
        user.put(Key_prenom, pref.getString(Key_prenom, null));
        user.put(Key_login, pref.getString(Key_login, null));
        user.put(Key_password, pref.getString(Key_password, null));
        user.put(Key_adresse, pref.getString(Key_adresse, null));
        user.put(Key_nom, pref.getString(Key_nom, null));
        user.put(Key_activite_sportif, pref.getString(Key_activite_sportif, null));
        user.put(Key_date_naissance, pref.getString(Key_date_naissance, null));

        user.put(Key_nom_pere, pref.getString(Key_nom_pere, null));
        user.put(Key_prenom_pere, pref.getString(Key_prenom_pere, null));
        user.put(Key_email_pere, pref.getString(Key_email_pere, null));
        user.put(Key_travail_pere, pref.getString(Key_travail_pere, null));
        user.put(Key_tel_pere, pref.getString(Key_tel_pere, null));

        user.put(Key_nom_mere, pref.getString(Key_nom_mere, null));
        user.put(Key_prenom_mere, pref.getString(Key_prenom_mere, null));
        user.put(Key_email_mere, pref.getString(Key_email_mere, null));
        user.put(Key_travail_mere, pref.getString(Key_travail_mere, null));
        user.put(Key_tel_mere, pref.getString(Key_tel_mere, null));
        user.put(Key_UserImage, pref.getString(Key_UserImage, null));

        // return user
        return user;
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     */
    public void checkLogin() {
        // Check login status
        if (this.isLoggedIn()) {
            // user is  logged in redirect him to main Activity
            Intent i = new Intent(_context, MainActivity.class);
            // Closing all the Activities
          //  i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
          //  System.exit(0);
        }

    }


    public HashMap<String ,Integer> getUserId(){
        HashMap<String, Integer> usrId = new HashMap<String, Integer>();
        // customer or user id
        usrId.put(Key_id_client, pref.getInt(Key_id_client,0));
        return usrId;
    }


    /**
     * Clear session details
     */
    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();


        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);

    }

    /**
     * Quick check for login
     **/
    // Get Login State
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }






}
