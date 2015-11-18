package org.smile.microcampus.Fragments;

import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import org.json.JSONObject;
import org.smile.microcampus.Activitys.LoginActivity;
import org.smile.microcampus.Activitys.UserInfoActivity;
import org.smile.microcampus.Model.MyUser;
import org.smile.microcampus.R;
import org.smile.microcampus.Utils.CircleImageView;
import org.smile.microcampus.Utils.MyImageLoader;



/**
 * 个人模块
 */

public class MeFragment extends Fragment implements View.OnClickListener{

    View view;
    LinearLayout login;
    TextView nickname;  // 昵称
    CircleImageView userIcon;  //头像
    MyUser userInfo;  //当前用户

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_me, container, false);

        userIcon = (CircleImageView) view.findViewById(R.id.user_icon);
        nickname = (TextView) view.findViewById(R.id.nickname);
        login = (LinearLayout) view.findViewById(R.id.login);
        login.setOnClickListener(this);
        view.findViewById(R.id.login_out).setOnClickListener(this);  //退出登陆

        userInfo = MyUser.getCurrentUser(getActivity(), MyUser.class);  //获取当前用户
        if(userInfo != null){   //当前存在用户
//            nickname.setText((String) userInfo.getObjectByKey(getActivity(), "nickname"));
            nickname.setText(userInfo.getNickname());
            JSONObject iconJSON = (JSONObject) userInfo.getObjectByKey(getActivity(), "icon");
            String imageUrl = null;
            if(iconJSON != null){  //当存在图象时
                imageUrl = getUrl(iconJSON);
                Log.d("iconJSON:",iconJSON.toString());
                Log.d("imageUrl:", imageUrl);

                ImageLoader.getInstance().displayImage(imageUrl, userIcon, MyImageLoader.options);

            }else{ //不存在图象时显示默认头像
                userIcon.setImageResource(R.drawable.user_icon);
            }
        }else {   //不存在用户
            nickname.setText("点击登陆");
            userIcon.setImageResource(R.drawable.user_icon);
        }

        return view;
    }

    private String getUrl(JSONObject jsonData) {
        String fileName = null, fileUrl = null, url = null;
//        String accessKey = "33a30344a3f7935b514fdbf5e865fa2c";
        try {
            fileName = jsonData.getString("filename");  //icon.jpg
            fileUrl = jsonData.getString("url");  //M02\/99\/C5\/oYYBAFZITGyAP07xAACrU3v0Z-M566.jpg
        } catch (Exception e) {
            e.printStackTrace();
        }
        url = "http://file.bmob.cn/"+ fileUrl.replace("\\","");
//        url = BmobProFile.getInstance(getActivity()).signURL(fileName,fileUrl,accessKey,0,null);
        return url;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                if(userInfo != null){
                    Intent intent = new Intent(getActivity(), UserInfoActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.login_out:
                MyUser.logOut(getActivity());  //清除缓存用户对象
                userInfo = MyUser.getCurrentUser(getActivity(), MyUser.class);
                nickname.setText("点击登陆");
                userIcon.setImageResource(R.drawable.user_icon);
            default:
                break;
        }
    }


}