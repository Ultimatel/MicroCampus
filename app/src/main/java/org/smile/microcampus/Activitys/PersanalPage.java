package org.smile.microcampus.Activitys;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.smile.microcampus.R;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

public class PersanalPage extends AppCompatActivity implements View.OnClickListener{

    private Toolbar mToolbar;
    private TextView personalInfo;
    private Button loginOut;
    Platform qq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persanal_page);
        ShareSDK.initSDK(this);
        intView();
    }

    public void intView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);   //ToolBar布局
        mToolbar.setTitleTextColor(Color.WHITE);  //设置ToolBar字体颜色为白色
        mToolbar.setTitle("我的账号");
        setSupportActionBar(mToolbar);  //将ToolBar设置为ActionBAr
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //在ToolBar左边，即当前标题前添加图标
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        personalInfo = (TextView) findViewById(R.id.persaonal_info);
        qq = ShareSDK.getPlatform(this, QQ.NAME);
        PlatformDb platDB = qq.getDb();//获取数平台数据DB
        //通过DB获取各种数据
        String info =    "授权Token：" +  platDB.getToken() + "\n" +
                         "ID: " + platDB.getUserId() + "\n" +
                         "用户名： "+       platDB.getUserName() + "\n" +
                         "性别："   +   platDB.getUserGender() + "\n" +
                         "用户头像地址："+      platDB.getUserIcon() + "\n"  ;
        personalInfo.setText(info);

        loginOut = (Button) findViewById(R.id.login_out);
        loginOut.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_out:
                 qq.removeAccount(); //移除授权
                Intent intent = new Intent(PersanalPage.this, MainActivity.class);
                intent.putExtra("isFromlogin", true);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
}
