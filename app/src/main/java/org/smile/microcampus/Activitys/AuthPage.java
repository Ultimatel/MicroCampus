package org.smile.microcampus.Activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.smile.microcampus.R;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.HashMap;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

import com.mob.tools.utils.UIHandler;

/**
 * 授权和取消授权演示页面
 * 由于UI显示的需要授权过的平台显示账户的名称，
 *因此此页面事实上展示的是“获取用户资料”和“取消
 *授权”两个功能。如果想看纯粹的“授权”操作，
 */
public class AuthPage extends AppCompatActivity implements View.OnClickListener,Handler.Callback,PlatformActionListener{

    private Toolbar mToolbar;
    private EditText account;
    private EditText password;
    private Button login;
    private LinearLayout qqLogin;
    private LinearLayout weixinLogin;
    private LinearLayout weiboLogin;
    Platform qq;
    Platform weixin;
    Platform weibo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_page);
        ShareSDK.initSDK(this);
        qq = ShareSDK.getPlatform(this, QQ.NAME);
        weixin = ShareSDK.getPlatform(this, Wechat.NAME);
        weibo = ShareSDK.getPlatform(this, SinaWeibo.NAME);

        intView();
    }

    public void intView(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);   //ToolBar布局
        mToolbar.setTitleTextColor(Color.WHITE);  //设置ToolBar字体颜色为白色
        mToolbar.setTitle("登陆");
        setSupportActionBar(mToolbar);  //将ToolBar设置为ActionBAr
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //在ToolBar左边，即当前标题前添加图标
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        account = (EditText) findViewById(R.id.account);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        qqLogin = (LinearLayout) findViewById(R.id.qq_login);
        weixinLogin = (LinearLayout) findViewById(R.id.weixin_login);
        weiboLogin = (LinearLayout) findViewById(R.id.weibo_login);

        login.setOnClickListener(this);
        qqLogin.setOnClickListener(this);
        weixinLogin.setOnClickListener(this);
        weiboLogin.setOnClickListener(this);
    }

    public void onComplete(Platform plat, int action,
                           HashMap<String, Object> res) {
        Message msg = new Message();
        msg.arg1 = 1;
        msg.arg2 = action;
        msg.obj = plat;
        UIHandler.sendMessage(msg, this);
    }

    public void onError(Platform plat, int action, Throwable t) {
        t.printStackTrace();

        Message msg = new Message();
        msg.arg1 = 2;
        msg.arg2 = action;
        msg.obj = plat;
        UIHandler.sendMessage(msg, this);
    }

    public void onCancel(Platform plat, int action) {
        Message msg = new Message();
        msg.arg1 = 3;
        msg.arg2 = action;
        msg.obj = plat;
        UIHandler.sendMessage(msg, this);
    }

    /**
     * 处理操作结果
     * <p>
     * 如果获取到用户的名称，则显示名称；否则如果已经授权，则显示
     *平台名称
     */
    public boolean handleMessage(Message msg) {
        Platform plat = (Platform) msg.obj;
        //String text = actionToString(msg.arg2);
        switch (msg.arg1) {
            case 1: {
                // 成功
                //text = plat.getName() + " completed at " + text;
                //Toast.makeText(this, text, Toast.LENGTH_SHORT).show();

                String accessToken = qq.getDb().getToken(); // 获取授权token
                String openId = qq.getDb().getUserId(); // 获取用户在此平台的ID
                String nickname = qq.getDb().get("nickname"); // 获取用户昵称
                //Toast.makeText(this, nickname , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AuthPage.this, MainActivity.class);
                intent.putExtra("isFromlogin", true);
                startActivity(intent);
            }
            break;
            case 2: {
                // 失败
                //text = plat.getName() + " caught error at " + text;
                //Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                return false;
            }
            case 3: {
                // 取消
                //text = plat.getName() + " canceled at " + text;
                //Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.login:
                Toast.makeText(this, "登陆" , Toast.LENGTH_SHORT).show();
                break;
            case R.id.qq_login:
                //Toast.makeText(this, "QQ" , Toast.LENGTH_SHORT).show();
                authorize(qq);
                break;
            case R.id.weixin_login:
                //Toast.makeText(this, "微信" , Toast.LENGTH_SHORT).show();
                authorize(weixin);
                break;
            case R.id.weibo_login:
                //Toast.makeText(this, "微博" , Toast.LENGTH_SHORT).show();
                authorize(weibo);
                break;
            default:
                break;
        }
    }

    public void qqLogin(){
        /*if (qq.isValid ()) {
            qq.removeAccount(); //移除授权
        }*/
        qq.SSOSetting(false);  //设置false表示使用SSO授权方式
        qq.setPlatformActionListener(this);
        qq.authorize();
    }
    private void authorize(Platform plat) {
        //判断指定平台是否已经完成授权
        if(plat.isAuthValid()) {
            String userId = plat.getDb().getUserId();
            if (userId != null) {
            }
        }
        plat.SSOSetting(false); // true不使用SSO授权，false使用SSO授权
        plat.setPlatformActionListener(this);
        plat.authorize();
        //plat.showUser(null); //获取用户资料
    }
}

