package org.smile.microcampus.Activitys;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.smile.microcampus.R;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.umeng.socialize.bean.SHARE_MEDIA;

import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners.UMAuthListener;
import com.umeng.socialize.exception.SocializeException;

import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.sso.UMSsoHandler;
import com.umeng.socialize.utils.Log;
import android.text.TextUtils;
import java.util.Map;
import java.util.Set;

import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.bean.StatusCode;
import com.umeng.socialize.controller.listener.SocializeListeners.SocializeClientListener;
import com.umeng.socialize.controller.listener.SocializeListeners.UMDataListener;
import com.umeng.socialize.weixin.controller.UMWXHandler;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar mToolbar;
    private EditText username;
    private EditText password;
    // 整个平台的Controller, 负责管理整个SDK的配置、操作等处理
    private UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.login");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        intView();
        addToSocial();
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

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        findViewById(R.id.login).setOnClickListener(this);
        findViewById(R.id.login_qq).setOnClickListener(this);
        findViewById(R.id.login_weixin).setOnClickListener(this);
        findViewById(R.id.login_weibo).setOnClickListener(this);

//        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//        intent.putExtra("isFromlogin", true);
//        startActivity(intent);
    }

    private void addToSocial(){
        //参数1为当前Activity， 参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(this, "100424468",
                "c7394704798a158208a74ab60104f0ba");
        qqSsoHandler.addToSocialSDK();

        //设置新浪SSO handler
        mController.getConfig().setSsoHandler(new SinaSsoHandler());

        // 添加微信平台
        UMWXHandler wxHandler = new UMWXHandler(this, "wx967daebe835fbeac",
                "5fa9e68ca3970e87a1f83e563c8dcbce");
//        UMWXHandler wxHandler = new UMWXHandler(this, "wxd0d84bbf23b4a0e4",
//                "a354f72aa1b4c2b8eaad137ac81434cd");
        wxHandler.addToSocialSDK();
        // 设置每次微信登录都需确认
//        wxHandler.setRefreshTokenAvailable(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                Toast.makeText(this, "登陆" , Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_qq:
                login(SHARE_MEDIA.QQ);
//              Toast.makeText(this, "QQ" , Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_weixin:
                login(SHARE_MEDIA.WEIXIN);
//              Toast.makeText(this, "微信" , Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_weibo:
                login(SHARE_MEDIA.SINA);
//              Toast.makeText(this, "微博" , Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    /**
     * 授权。如果授权成功，则获取用户信息</br>
     */
    private void login(final SHARE_MEDIA platform) {
        mController.doOauthVerify(this, platform, new UMAuthListener() {

            @Override
            public void onStart(SHARE_MEDIA platform) {
                Toast.makeText(LoginActivity.this, "start", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(SocializeException e, SHARE_MEDIA platform) {
                Toast.makeText(LoginActivity.this, "error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete(Bundle value, SHARE_MEDIA platform) {
                Toast.makeText(LoginActivity.this, "onComplete", Toast.LENGTH_SHORT).show();
                String uid = value.getString("uid");
                if (value != null && !TextUtils.isEmpty(uid)) {
                    Toast.makeText(LoginActivity.this, "授权成功：" + uid, Toast.LENGTH_SHORT).show();
                    getUserInfo(platform);
                } else {
                    Toast.makeText(LoginActivity.this, "授权失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
                Toast.makeText(LoginActivity.this, "cancel", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 获取授权平台的用户信息</br>
     */
/*  QQ用户返回的信息
    vip=0
    level=0
    province=广东
    yellow_vip_level=0
    is_yellow_vip=0
    gender=男
    screen_name=怺ぐ恒
    msg=
    profile_image_url=http://q.qlogo.cn/qqapp/100424468/B6FABC8A0F4E922323BD4BCC66E260B8/100
    city=潮州*/
    private void getUserInfo(SHARE_MEDIA platform) {
        mController.getPlatformInfo(this, platform, new UMDataListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onComplete(int status, Map<String, Object> info) {
                if (status == 200 && info != null) {
                    StringBuilder sb = new StringBuilder();
                    Set<String> keys = info.keySet();
                    for (String key : keys) {
                        sb.append(key + "=" + info.get(key).toString() + "\r\n");
                    }
                    Log.d("user:", sb.toString());
                    Toast.makeText(LoginActivity.this, sb, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "获取用户信息失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 注销本次登录</br>
     */
    private void logout(final SHARE_MEDIA platform) {
        mController.deleteOauth(this, platform, new SocializeClientListener() {

            @Override
            public void onStart() {

            }

            @Override
            public void onComplete(int status, SocializeEntity entity) {
                String showText = "解除" + platform.toString() + "平台授权成功";
                if (status != StatusCode.ST_CODE_SUCCESSED) {
                    showText = "解除" + platform.toString() + "平台授权失败[" + status + "]";
                }
                Toast.makeText(LoginActivity.this, showText, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**使用新浪SSO授权必须添加如下代码 */
        UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(
                requestCode);
        if (ssoHandler != null) {
            ssoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.register) {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

