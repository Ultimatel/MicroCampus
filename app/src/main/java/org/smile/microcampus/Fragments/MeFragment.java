package org.smile.microcampus.Fragments;

import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;

import org.smile.microcampus.Activitys.LoginActivity;
import org.smile.microcampus.R;
import org.smile.microcampus.Utils.CircleImageView;


/**
 * 个人模块
 */

public class MeFragment extends Fragment implements View.OnClickListener{

    View view;
    LinearLayout login;
    TextView userName;
    CircleImageView userImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_me, container, false);

        userImage = (CircleImageView) view.findViewById(R.id.user_image);
        userName = (TextView) view.findViewById(R.id.user_name);
        login = (LinearLayout) view.findViewById(R.id.login);
        login.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }


}