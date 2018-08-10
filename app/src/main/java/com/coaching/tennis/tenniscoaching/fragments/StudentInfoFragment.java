package com.coaching.tennis.tenniscoaching.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import com.coaching.tennis.tenniscoaching.Constants;
import com.coaching.tennis.tenniscoaching.R;
import com.coaching.tennis.tenniscoaching.application.BaseApplication;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.coaching.tennis.tenniscoaching.session.SessionManager.*;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentInfoFragment extends Fragment {


    View v;
    TextView txtName, txtAdresse, txtNameP, txtNameM, txtPhone;
    VideoView videoAvant, videoApres;
    CircleImageView imgProfile;
    private static String TAG = StudentInfoFragment.class.getSimpleName();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_student_info, container, false);
        initFields();
        String name = BaseApplication.session.getUserDetails().get(Key_prenom)+" "+BaseApplication.session.getUserDetails().get(Key_nom);
        String nameP = BaseApplication.session.getUserDetails().get(Key_prenom_pere)+" "+BaseApplication.session.getUserDetails().get(Key_nom_pere);
        String nameM = BaseApplication.session.getUserDetails().get(Key_prenom_mere)+" "+BaseApplication.session.getUserDetails().get(Key_nom_mere);
        txtName.setText(name);
        txtAdresse.setText(BaseApplication.session.getUserDetails().get(Key_adresse));
        txtNameP.setText(nameP);
        txtNameM.setText(nameM);
        txtPhone.setText(BaseApplication.session.getUserDetails().get(Key_tel_pere));
        Log.e(TAG," profile image url = "+Constants.baseUrlImages+BaseApplication.session.getUserDetails().get(Key_UserImage));

        Picasso.with(getActivity())
                .load(BaseApplication.session.getUserDetails().get(Key_UserImage))
                .into(imgProfile);

        return v;
    }

    private void initFields(){
        txtName = v.findViewById(R.id.txt_name);
        txtAdresse = v.findViewById(R.id.txt_adresse);
        txtNameP = v.findViewById(R.id.txt_nom_pere);
        txtNameM = v.findViewById(R.id.txt_nom_mere);
       // videoApres = v.findViewById(R.id.video_apres);
        //videoAvant = v.findViewById(R.id.video_avant);
        imgProfile = v.findViewById(R.id.img_profile_image);
        txtPhone = v.findViewById(R.id.txt_phone);


    }

}
