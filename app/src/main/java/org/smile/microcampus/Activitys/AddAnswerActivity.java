package org.smile.microcampus.Activitys;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.smile.microcampus.R;

/**
 * Created by Ben on 2015/10/28.
 */
public class AddAnswerActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private ImageView imageView;
    private TextView textView1,textView2,textView3;
    private String name="微校工作室";
    private String jianJie="用户简介信息";
    private String answer="一个人去旅游用三分钟决定，真土豪！直接就来一场说走就走的旅游····················iahdfadfaoeifa;ldjfbhoaehf";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_part_timejob);
        initView();
    }
    public void initView() {
        //获取Toolbar控件
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle("兼职");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //获取布局的其他控件
        imageView = (ImageView) findViewById(R.id.detail_imageview);
        textView1 = (TextView) findViewById(R.id.detail_name);
        textView2 = (TextView) findViewById(R.id.tv_content);
        textView3 = (TextView) findViewById(R.id.text_jianjie);
        textView1.setText(name);
        textView2.setText(jianJie);
        textView3.setText(answer);
    }
}
