package com.coaching.tennis.tenniscoaching.fragments;


import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.coaching.tennis.tenniscoaching.Common.Common;
import com.coaching.tennis.tenniscoaching.LoginActivity;
import com.coaching.tennis.tenniscoaching.MainActivity;
import com.coaching.tennis.tenniscoaching.Model.User;
import com.coaching.tennis.tenniscoaching.R;
import com.coaching.tennis.tenniscoaching.application.BaseApplication;
import com.coaching.tennis.tenniscoaching.interfaces.SendFeedBackService;
import com.coaching.tennis.tenniscoaching.interfaces.UserDataService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import dmax.dialog.SpotsDialog;
import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import eu.inmite.android.lib.validations.form.callback.SimpleErrorPopupCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactUsFragment extends Fragment {


    View v;
    @NotEmpty(messageId = R.string.nonEmpty, order = 1)
    protected EditText fullname;
    @NotEmpty(messageId = R.string.nonEmpty, order = 2)
    protected EditText email;
    @NotEmpty(messageId = R.string.nonEmpty, order = 3)
    protected EditText mobile;
    @NotEmpty(messageId = R.string.nonEmpty, order = 4)
    protected EditText feedback;
    Button btnsend;
    ImageView fcb,twitter,insta  ;
    String FullName;
    String Email,   TWITTER,FACEBOOK,Snapchat,Instagram ;
    String Mobile;
    String FeedBak;

    SendFeedBackService mService;
    AlertDialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_about_us, container, false);

        mService = Common.sendFeedBackService();
        dialog = new SpotsDialog(getActivity());
        inializeField();


        /*
        * */
        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validate(view);
            }
        });


        fcb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(FACEBOOK));
                startActivity(i);
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(TWITTER));
                startActivity(i);
            }
        });

        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(Instagram));
                    startActivity(i);
                }catch (Exception e){
                    Log.e("TabFragment1","Exception insta "+e.getMessage());
                }

            }
        });

        return v;
    }

    private void inializeField() {
        fullname =(EditText)v.findViewById(R.id.et_FullName);
        email=(EditText)v.findViewById(R.id.et_Email);
        mobile =(EditText)v.findViewById(R.id.et_PhoneNumber);
        feedback =(EditText)v.findViewById(R.id.et_FeedBack);
        btnsend =(Button)v.findViewById(R.id.btn_SendComplain);
        fcb =(ImageView)v.findViewById(R.id.fcb);
        twitter =(ImageView)v.findViewById(R.id.twitter);
        insta =(ImageView)v.findViewById(R.id.insta);
    }

    public void validate(View view) {
        FullName= String.valueOf(fullname.getText());
        Email = String.valueOf(email.getText());
        Mobile= String.valueOf(mobile.getText());
        FeedBak= String.valueOf(feedback.getText());
        long start = SystemClock.elapsedRealtime();
        final boolean isValid = FormValidator.validate(this, new SimpleErrorPopupCallback(getContext(), true));
        long time = SystemClock.elapsedRealtime() - start;
        Log.e(getClass().getName(), "validation finished in [ms] " + time);
        try {
            String queryFeedBak = URLEncoder.encode(FeedBak, "utf-8");
            String queryFullName = URLEncoder.encode(FullName, "utf-8");
            if (isValid) {
                sendFeedBack(queryFullName, Email, Mobile, queryFeedBak) ;
                // Toast.makeText(getContext(),"Success", Toast.LENGTH_LONG).show();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }



    private void sendFeedBack( String username, String email, String mobile, String feedBak) {

        dialog.show();
        String url = Common.sendFeedBackURL("contact.php?",username, email, mobile, feedBak);
        Log.e("Contact Fragment "," sendFeedBack url "+url);
        mService.sendFeedBack(url)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        dialog.dismiss();
                        //Get first article
                        String msg = response.body();

                        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
                        Log.e("sendFeedBack "," msg data : "+msg);


                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.e("sendFeedBack "," erreur d'envoi: "+call.toString()+" errue est = "+t.getMessage());
                        t.getCause();
                        dialog.dismiss();

                    }
                });

    }


}
