package com.coaching.tennis.tenniscoaching;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.coaching.tennis.tenniscoaching.Common.Common;
import com.coaching.tennis.tenniscoaching.Model.User;
import com.coaching.tennis.tenniscoaching.application.BaseApplication;
import com.coaching.tennis.tenniscoaching.interfaces.RegisterService;
import com.coaching.tennis.tenniscoaching.interfaces.UserDataService;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    TextView edtName, edtEmail, edtPhone, edtPwd, edtConfirmPwd;
    Button btnConfirm;
    Button btnBack;
    LinearLayout linearLayout;
    AlertDialog dialog;
    RegisterService mService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initFields();
        mService = Common.register();
        dialog = new SpotsDialog(RegisterActivity.this);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private void initFields() {

        edtName = findViewById(R.id.edt_name);
        edtEmail = findViewById(R.id.edt_mail);
        edtPhone = findViewById(R.id.edt_phone);
        edtPwd = findViewById(R.id.edt_pwd);
        edtConfirmPwd = findViewById(R.id.edt_confirm);
        btnConfirm = findViewById(R.id.btn_register);
        btnBack = findViewById(R.id.btn_back);
        linearLayout = findViewById(R.id.linear);


    }

    private void attemptRegister() {

        // Reset errors.
        edtName.setError(null);
        edtEmail.setError(null);
        edtPhone.setError(null);
        edtPwd.setError(null);
        edtConfirmPwd.setError(null);


        // Store values at the time of the login attempt.
        String name = edtName.getText().toString();
        String email = edtEmail.getText().toString();
        String phone = edtPhone.getText().toString();
        String pwd = edtPwd.getText().toString();
        String confirmPwd = edtConfirmPwd.getText().toString();



        boolean cancel = false;
        View focusView = null;
        if (TextUtils.isEmpty(phone)) {
            edtPhone.setError(getString(R.string.error_field_required));
            focusView = edtPhone;
            cancel = true;
        }
        if (TextUtils.isEmpty(name)) {
            edtName.setError(getString(R.string.error_field_required));
            focusView = edtName;
            cancel = true;
        }
        if (TextUtils.isEmpty(email)) {
            edtEmail.setError(getString(R.string.error_field_required));
            focusView = edtEmail;
            cancel = true;
        }
        if (TextUtils.isEmpty(pwd)) {
            edtPwd.setError(getString(R.string.error_field_required));
            focusView = edtPwd;
            cancel = true;
        }
        if (TextUtils.isEmpty(confirmPwd)) {
            edtConfirmPwd.setError(getString(R.string.error_field_required));
            focusView = edtConfirmPwd;
            cancel = true;
        }

        if (!pwd.equals(confirmPwd)){
            edtConfirmPwd.setError(getString(R.string.error_field_not_match));
            focusView = edtConfirmPwd;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();

        } else {
            linearLayout.setVisibility(View.GONE);

            register(name,email,phone, pwd);
        }
    }

    private void register(String name, String email, String phone, String pwd) {
        String url = Common.sendregisterURL("register.php?",name, email, phone, pwd);
        Log.e("register "," register url "+url);
        mService.register(url)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        dialog.dismiss();
                        //Get first article
                        String msg = response.body();


                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        dialog.dismiss();
                    }
                });
    }


}
