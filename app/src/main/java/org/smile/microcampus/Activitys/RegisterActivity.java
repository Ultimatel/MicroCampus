package org.smile.microcampus.Activitys;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.smile.microcampus.Model.MyUser;
import org.smile.microcampus.R;
import org.smile.microcampus.Utils.RegexValidateUtil;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private Toolbar mToolbar;
    private EditText username;  //用户名
    private EditText password;  //密码
    private EditText passwordConfirm;  //确认密码
    private Button register;   //注册按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        intView();
    }
    public void intView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);   //ToolBar布局
        mToolbar.setTitleTextColor(Color.WHITE);  //设置ToolBar字体颜色为白色
        mToolbar.setTitle("注册");
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
        passwordConfirm = (EditText) findViewById(R.id.password_confirm);
        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
//                Toast.makeText(this, username.getText().toString(),Toast.LENGTH_SHORT).show();
                register(username.getText().toString(), password.getText().toString(),
                                        passwordConfirm.getText().toString());
                break;
            default:
                break;
        }
    }


    public void register(String username, String password, String passwordConfirm){
        if(password.equals(passwordConfirm)){     //判断两次输入的密码
            MyUser myUser = new MyUser();

            boolean valid = false;
            if(RegexValidateUtil.checkStudentId(username)){     //学号
                myUser.setUsername(username);
                myUser.setPassword(password);
                myUser.setStudentId(username);
                myUser.setNickname("匿名用户");
                myUser.signUp(this, saveListener);
                valid = true;
//                Toast.makeText(RegisterActivity.this, "学号", Toast.LENGTH_SHORT).show();
            }

            if(RegexValidateUtil.checkMobileNumber(username)){   //手机号码
                myUser.setUsername(username);
                myUser.setPassword(password);
                myUser.setMobilePhoneNumber(username);
                myUser.setNickname("匿名用户");
                myUser.signUp(this, saveListener);
                valid = true;
//                Toast.makeText(RegisterActivity.this, "手机号码", Toast.LENGTH_SHORT).show();

            }

            if(RegexValidateUtil.checkEmail(username)){   //邮箱
                myUser.setUsername(username);
                myUser.setPassword(password);
                myUser.setEmail(username);
                myUser.setNickname("匿名用户");
                myUser.signUp(this, saveListener);
                valid = true;
//                Toast.makeText(RegisterActivity.this, "邮箱", Toast.LENGTH_SHORT).show();
            }

            if(!valid){
                Toast.makeText(RegisterActivity.this, "请输入有效的用户名", Toast.LENGTH_SHORT).show();
            }


        }else{
            Toast.makeText(RegisterActivity.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
        }


    }

    private SaveListener saveListener = new SaveListener(){

        @Override
        public void onSuccess() {
            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
//                toast("注册成功:" + myUser.getUsername() + "-"
//                        + myUser.getObjectId() + "-" + myUser.getCreatedAt()
//                        + "-" + myUser.getSessionToken()+",是否验证："+myUser.getEmailVerified());
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            intent.putExtra("isFromlogin", true);
            startActivity(intent);
        }

        @Override
        public void onFailure(int i, String msg) {
            Toast.makeText(RegisterActivity.this, "用户名已存在", Toast.LENGTH_SHORT).show();
        }
    };


}
