package org.smile.microcampus.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mob.tools.utils.UIHandler;

import org.smile.microcampus.Activitys.AuthPage;
import org.smile.microcampus.Activitys.PersanalPage;
import org.smile.microcampus.R;
import org.smile.microcampus.Utils.CircleImageView;
import org.smile.microcampus.Utils.NormalLoadPictrue;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;


import cn.sharesdk.framework.CustomPlatform;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.ShareCore;
import cn.sharesdk.tencent.qq.QQ;

/**
 * 个人模块
 */

public class MeFragment extends Fragment implements View.OnClickListener{

    View view;
    LinearLayout login;
    TextView userName;
    CircleImageView userImage;
    Platform qq;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_me, container, false);


        userImage = (CircleImageView) view.findViewById(R.id.user_image);
        userName = (TextView) view.findViewById(R.id.user_name);
        login = (LinearLayout) view.findViewById(R.id.login);
        login.setOnClickListener(this);

        ShareSDK.initSDK(getActivity());
        qq = ShareSDK.getPlatform(getActivity(), QQ.NAME);
        if(qq.isAuthValid()) {
            String userId = qq.getDb().getUserId();
            if (userId != null) {
                userName.setText(qq.getDb().getUserName());
                new NormalLoadPictrue().getPicture(qq.getDb().getUserIcon(), userImage);
            }else{
                userName.setText("立即登陆");
            }
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                if(qq.isAuthValid()) {
                    String userId = qq.getDb().getUserId();
                    if (userId != null) {
                        Intent intent = new Intent(getActivity(),PersanalPage.class);
                        startActivity(intent);
                    }
                } else{
                    Intent intent = new Intent(getActivity(), AuthPage.class);
                    startActivity(intent);
                }


                break;
            default:
                break;
        }
    }


}