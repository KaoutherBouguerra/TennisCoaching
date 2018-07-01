package com.coaching.tennis.tenniscoaching;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.coaching.tennis.tenniscoaching.Common.Common;
import com.coaching.tennis.tenniscoaching.Model.Article;
import com.coaching.tennis.tenniscoaching.Model.News;
import com.coaching.tennis.tenniscoaching.Model.User;
import com.coaching.tennis.tenniscoaching.adapters.ListNewsAdapter;
import com.coaching.tennis.tenniscoaching.interfaces.NewsService;
import com.coaching.tennis.tenniscoaching.interfaces.UserDataService;
import com.squareup.picasso.Picasso;

import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    UserDataService mService;
    AlertDialog dialog;
    EditText _email_edit, _password_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initFields();

        mService = Common.login();
        dialog = new SpotsDialog(LoginActivity.this);

        Button _login_btn = findViewById(R.id.login_btn);
        _login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login(_email_edit.getText().toString(), _password_edit.getText().toString());

            }
        });

        Button _register_btn = findViewById(R.id.register_btn);
        _register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    private void initFields(){
        _email_edit = (EditText) findViewById(R.id.email_edit);
        _password_edit = (EditText) findViewById(R.id.password_edit);
    }

    private void login( String username, String password) {

            dialog.show();
            String url = Common.getLoginURL("login.php?",username, password);
            Log.e("Login "," login url "+url);
            mService.getUserData(url)
                    .enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            dialog.dismiss();
                            //Get first article
                            Log.e("Login "," user data : "+response.body().toString());
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);

                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });

    }

}
