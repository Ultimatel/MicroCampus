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
 * Created by Ben on 2015/10/17.
 */
public class PartTime_Detail extends AppCompatActivity {
    private Toolbar mToolbar;
    private ImageView imageView;
    private TextView textView1,textView2,textView3;
    private String name="微校工作室";
    private String content="本工作室主要经营APP开发，网络运营等方面";
    private String jianJie="现招聘以下人员：\n" +
            "　　操作工：50名\n" +
            "　　一、基本要求\n" +
            "　　1. 年龄18周岁以上，性别不限。\n" +
            "　　2. 初中以上学历。\n" +
            "　　3. 身体健康，无不良嗜好。\n" +
            "　　二、待遇\n" +
            "　　1. 试用期：两个月个月，转正后工资4000-5000元。\n" +
            "　　3. 公司按照国家规定给员工办理社会保险。\n" +
            "　　4. 包吃包住，住集体宿舍。\n" +
            "　　5. 公司每年组织一次集体旅游。\n" +
            "　　6.对在工作中表现优异的员工年终公司给予一定的奖励。\n" +
            "　　传真：020-2584754\n" +
            "　　手机：某经理 13202783511\n" +
            "　　网 址：www.XXXXX.com\n" +
            "　　公司地址：广州市XX区xx路xx号\n" +
            "　　普工招聘启事怎么写，HR你知道了吗?\n" +
            "　　若您还有疑问，请点击纳才社区进行讨论与交流。\n" +
            "　　若您还有困惑，还可以进入职场知道进行提问与交流。";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_part_timejob);
        initView();
    }
    public void initView(){
        //获取Toolbar控件
        mToolbar= (Toolbar) findViewById(R.id.toolbar);
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
        imageView= (ImageView) findViewById(R.id.detail_imageview);
        textView1= (TextView) findViewById(R.id.detail_name);
        textView2= (TextView) findViewById(R.id.tv_content);
        textView3= (TextView) findViewById(R.id.text_jianjie);
        textView2.setText(content);
        textView3.setText(jianJie);
    }

   /* public void shuju(){
        tv_content.setText("本工作室主要经营APP开发，网络运营等方面");
        content_jianjie.setText("现招聘以下人员：\n" +
                "　　操作工：50名\n" +
                "　　一、基本要求\n" +
                "　　1. 年龄18周岁以上，性别不限。\n" +
                "　　2. 初中以上学历。\n" +
                "　　3. 身体健康，无不良嗜好。\n" +
                "　　二、待遇\n" +
                "　　1. 试用期：两个月个月，转正后工资4000-5000元。\n" +
                "　　3. 公司按照国家规定给员工办理社会保险。\n" +
                "　　4. 包吃包住，住集体宿舍。\n" +
                "　　5. 公司每年组织一次集体旅游。\n" +
                "　　6.对在工作中表现优异的员工年终公司给予一定的奖励。\n" +
                "　　传真：020-2584754\n" +
                "　　手机：某经理 13202783511\n" +
                "　　网 址：www.XXXXX.com\n" +
                "　　公司地址：广州市XX区xx路xx号\n" +
                "　　普工招聘启事怎么写，HR你知道了吗?\n" +
                "　　若您还有疑问，请点击纳才社区进行讨论与交流。\n" +
                "　　若您还有困惑，还可以进入职场知道进行提问与交流。");
    }*/

}
