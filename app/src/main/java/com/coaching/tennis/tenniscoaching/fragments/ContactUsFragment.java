package com.coaching.tennis.tenniscoaching.fragments;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.coaching.tennis.tenniscoaching.DetailArticle;
import com.coaching.tennis.tenniscoaching.R;

import static com.coaching.tennis.tenniscoaching.Constants.FACEBOOK_PAGE;
import static com.coaching.tennis.tenniscoaching.Constants.WEB_SITE;
import static com.coaching.tennis.tenniscoaching.Constants.phone;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactUsFragment extends Fragment {


   View v;
   ImageButton btnCall, btnMsg;
   ImageView imgFacebook;
   ImageView imgWeb;
   ImageView imgGmail;
    final int REQUEST_PERMISSION = 1000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_contact_us, container, false);


        initFields();

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PERMISSION);

                    return;
                } else {

                    Intent intent = new Intent();
                    Uri uri = Uri.parse("tel:" + phone.trim());
                    intent.setAction(Intent.ACTION_CALL);
                    intent.setData(uri);
                    startActivity(intent);
                }


            }
        });

        btnMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phone));
                intent.putExtra("sms_body", "");
                startActivity(intent);
            }
        });

        imgFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detail = new Intent(getActivity().getBaseContext(),DetailArticle.class);
                detail.putExtra("webURL",FACEBOOK_PAGE);
                startActivity(detail);
            }
        });
        imgWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detail = new Intent(getActivity().getBaseContext(),DetailArticle.class);
                detail.putExtra("webURL",WEB_SITE);
                startActivity(detail);
            }
        });

        return v;
    }

    private void initFields(){
        btnCall = v.findViewById(R.id.btn_call);
        btnMsg = v.findViewById(R.id.btn_message);
        imgFacebook = v.findViewById(R.id.img_facebook);
        imgWeb = v.findViewById(R.id.img_web);
        imgGmail = v.findViewById(R.id.img_gplus);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                            Intent intent = new Intent();
                            Uri uri = Uri.parse("tel:" + phone.trim());
                            intent.setAction(Intent.ACTION_CALL);
                            intent.setData(uri);
                            startActivity(intent);

                }
        }
    }

}
