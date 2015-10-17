package org.smile.microcampus.Activitys;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.smile.microcampus.R;
import org.smile.microcampus.Utils.BaseActivity;

import static android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS;

/**
 * Created by Ben on 2015/10/14.
 */
public class BaoMingActivity extends BaseActivity implements View.OnClickListener{
    private TextView textView1,textView2,textView3;
    private EditText editText1,editText2,editText3,edtiForm;
    private Button btnCommit;
    private static ActionBar actionBar;
    public void  initView(){
        setContentView(R.layout.application_form);
        textView1= (TextView) findViewById(R.id.textview_name);
        textView2= (TextView) findViewById(R.id.textview_name1);
        textView3= (TextView) findViewById(R.id.textview_name2);
        editText1= (EditText) findViewById(R.id.edtext_name);
        editText2= (EditText) findViewById(R.id.edtext_name1);
        editText3= (EditText) findViewById(R.id.edtext_name2);
        editText3.setInputType((TYPE_TEXT_VARIATION_EMAIL_ADDRESS ));//设置可以填入邮箱地址
        edtiForm= (EditText) findViewById(R.id.content_form);
        btnCommit= (Button) findViewById(R.id.btn_commit);
        btnCommit.setOnClickListener(this);

    }
    @Override
    protected void initActionBar() {
        super.initActionBar();
        actionBar=this.getSupportActionBar();//获取actionBar对象
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.btn_commit:
                simple(v);break;
        }
    }
    //弹出提示对话框，让用户确认是否要提交报名表
    public void simple(View source){
        AlertDialog.Builder builder=new AlertDialog.Builder(this)
        .setIcon(R.drawable.ic_theme)
        .setTitle("提交报名表")
        .setMessage("你好，是否确认提交报名表？");
         setPositiveButton(builder);
         setNegativeButton(builder)
        .create()
        .show();
    }
    private AlertDialog.Builder setPositiveButton(AlertDialog.Builder builder){
        return  builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String s1,s2,s3;
                s1=editText1.getText().toString();
                s2=editText2.getText().toString();
                s3=editText3.getText().toString();
                if(s1.equals("")||s2.equals("")||s3.equals("")){


                    Toast.makeText(BaoMingActivity.this,"资料不完整，提交不成功，重新填写或者退出报名",Toast.LENGTH_SHORT).show();
                }
              else {
                    //要把数据存入到数据库，后期再补充
                    Toast.makeText(BaoMingActivity.this,"提交成功！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private AlertDialog.Builder setNegativeButton(final AlertDialog.Builder builder){
        return builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               //取消提交，不做任何事情
            }
        });
    }
}
