package com.coaching.tennis.tenniscoaching;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.coaching.tennis.tenniscoaching.Common.Common;
import com.coaching.tennis.tenniscoaching.Model.Article;
import com.coaching.tennis.tenniscoaching.Model.News;
import com.coaching.tennis.tenniscoaching.Model.User;
import com.coaching.tennis.tenniscoaching.adapters.ListNewsAdapter;
import com.coaching.tennis.tenniscoaching.application.BaseApplication;
import com.coaching.tennis.tenniscoaching.interfaces.NewsService;
import com.coaching.tennis.tenniscoaching.interfaces.UserDataService;
import com.squareup.picasso.Picasso;

import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.coaching.tennis.tenniscoaching.Constants.WEB_SITE;

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
                _email_edit.setError(null);
                _password_edit.setError(null);
                String login = _email_edit.getText().toString();
                String pwd = _password_edit.getText().toString();

                boolean cancel = false;
                View focusView = null;
                if (TextUtils.isEmpty(login)) {
                    _email_edit.setError(getString(R.string.error_field_required));
                    focusView = _email_edit;
                    cancel = true;
                }
                if (TextUtils.isEmpty(pwd)) {
                    _password_edit.setError(getString(R.string.error_field_required));
                    focusView = _password_edit;
                    cancel = true;
                }

                if (cancel) {
                    // There was an error; don't attempt login and focus the first
                    // form field with an error.
                    focusView.requestFocus();

                } else {
                    login(login,pwd);
                }


            }
        });

        TextView email_sign_up_button = findViewById(R.id.email_sign_up_button);
        email_sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
               // startActivity(intent);
                Intent detail = new Intent(LoginActivity.this,RegisterActivity.class);
               // detail.putExtra("webURL",WEB_SITE);
                startActivity(detail);
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
                            User user = response.body();
                            BaseApplication.session.createLoginSession(
                                    user.getId_client(),user.getLogin(),user.getPassword(),user.getNom(),
                                    user.getPrenom(),user.getAdresse(),user.getDate_naissance(),
                                    user.getActivite_sportif(),user.getProfile_img(), user.getNom_pere(),
                                    user.getPrenom_pere(),user.getEmail_pere(),user.getTravail_pere(),user.getTel_pere(),
                                    user.getNom_mere(),user.getPrenom_mere(),user.getEmail_mere(),user.getTravail_mere(),user.getTel_mere());
                            Log.e("Login "," user data : "+response.body().toString());
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);

                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                            dialog.dismiss();
                            _email_edit.setText("");
                            _password_edit.setText("");
                            new AlertDialog.Builder(LoginActivity.this)
                                    .setTitle("")
                                    .setMessage("Login ou Mot de passe incorrect")
                                    .setCancelable(true)
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    }).show();
                        }
                    });

    }

}
