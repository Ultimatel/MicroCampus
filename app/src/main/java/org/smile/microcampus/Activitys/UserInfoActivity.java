package org.smile.microcampus.Activitys;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;


import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;

import org.smile.microcampus.Adapters.UserInfoAdapter;
import org.smile.microcampus.Model.MyUser;
import org.smile.microcampus.R;
import org.smile.microcampus.Utils.CircleImageView;
import org.smile.microcampus.Utils.ImageCycleView;
import org.smile.microcampus.Utils.MyImageLoader;
import org.smile.microcampus.Utils.ToolBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserInfoActivity extends AppCompatActivity {

    private ListView listUserInfo;
    private List<Map> mDatas;
    MyUser userInfo;  //当前用户

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        setToolbar();
        initView();
    }
    private void initView(){
        listUserInfo = (ListView) findViewById(R.id.list_user_info);
        userInfo = MyUser.getCurrentUser(UserInfoActivity.this, MyUser.class);  //获取当前用户
        String[] title = {"头像", "昵称","性别", "学号" ,"手机" ,"邮箱" ,"年级" ,"班级" };
        String url = "http://file.bmob.cn/M02/9A/63/oYYBAFZIf2eADcJfAACrU3v0Z-M919.jpg";
        String[] content = { url, userInfo.getNickname(), userInfo.getGender(), userInfo.getStudentId(),
                            userInfo.getMobilePhoneNumber(), userInfo.getEmail(),
                            userInfo.getGrade(), userInfo.getClasses() };
        mDatas = new ArrayList<Map>();
        for(int i = 0; i < 8; i++){
            Map<String, String> map = new HashMap<String, String>();
            map.put("title", title[i]);
            map.put("content", content[i]);
            mDatas.add(map);
        }
        listUserInfo.setAdapter(new UserInfoAdapter(this, mDatas));
    }

    public void setToolbar(){
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);   //ToolBar布局
        mToolbar.setTitleTextColor(Color.WHITE);  //设置ToolBar字体颜色为白色
        mToolbar.setTitle("个人信息");
        setSupportActionBar(mToolbar);  //将ToolBar设置为ActionBAr
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //在ToolBar左边，即当前标题前添加图标
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


}

