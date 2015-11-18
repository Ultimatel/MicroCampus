package org.smile.microcampus.Activitys;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.smile.microcampus.Fragments.ActivityFragment;
import org.smile.microcampus.Fragments.LearningFragment;
import org.smile.microcampus.Fragments.MeFragment;
import org.smile.microcampus.Fragments.NeedFragment;
import org.smile.microcampus.Fragments.ShareFragment;
import org.smile.microcampus.R;

import cn.bmob.v3.Bmob;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private LearningFragment learningFragment;
    private NeedFragment needFragment;
    private ActivityFragment activityFragment;
    private ShareFragment shareFragment;
    private MeFragment myFragment;
    private FragmentManager fragmentManager;    //用于对Fragment进行管理
    private Toolbar mToolbar;

    /**底部五个按钮*/
    private LinearLayout layoutBtnLearn;
    private LinearLayout layoutBtnNeed;
    private LinearLayout layoutBtnActivity;
    private LinearLayout layoutBtnShare;
    private LinearLayout layoutBtnMe;

    /**底部五个按钮图片*/
    private ImageView layoutImageLearn;
    private ImageView layoutImageNeed;
    private ImageView layoutImageActivity;
    private ImageView layoutImageShare;
    private ImageView layoutImageMe;

    /**底部五个按钮文字*/
    private TextView layoutTextLearn;
    private TextView layoutTextNeed;
    private TextView layoutTextActivity;
    private TextView layoutTextShare;
    private TextView layoutTextMe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        initView();

    }

    private void initView(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);   //ToolBar布局
        mToolbar.setTitleTextColor(Color.WHITE);  //设置ToolBar字体颜色为白色
        setToolBarTitle(3);
        //mToolbar.setTitle("微校");
        setSupportActionBar(mToolbar);  //将ToolBar设置为ActionBAr

        layoutBtnLearn = (LinearLayout) findViewById(R.id.layout_btn_learn);
        layoutBtnNeed = (LinearLayout) findViewById(R.id.layout_btn_need);
        layoutBtnActivity = (LinearLayout) findViewById(R.id.layout_btn_activity);
        layoutBtnShare = (LinearLayout) findViewById(R.id.layout_btn_share);
        layoutBtnMe = (LinearLayout) findViewById(R.id.layout_btn_me);

        layoutBtnLearn.setOnClickListener(this);
        layoutBtnNeed.setOnClickListener(this);
        layoutBtnActivity.setOnClickListener(this);
        layoutBtnShare.setOnClickListener(this);
        layoutBtnMe.setOnClickListener(this);

        layoutImageLearn = (ImageView) findViewById(R.id.layout_image_learn);
        layoutImageNeed = (ImageView) findViewById(R.id.layout_image_need);
        layoutImageActivity = (ImageView) findViewById(R.id.layout_image_activity);
        layoutImageShare = (ImageView) findViewById(R.id.layout_image_share);
        layoutImageMe = (ImageView) findViewById(R.id.layout_image_me);

        layoutTextLearn = (TextView) findViewById(R.id.layout_text_learn);
        layoutTextNeed = (TextView) findViewById(R.id.layout_text_need);
        layoutTextActivity= (TextView) findViewById(R.id.layout_text_activity);
        layoutTextShare = (TextView) findViewById(R.id.layout_text_share);
        layoutTextMe = (TextView) findViewById(R.id.layout_text_me);


        boolean isFromlogin; //是否是从登陆或注册界面跳转过来
        isFromlogin =  getIntent().getBooleanExtra("isFromlogin", false);
        if(isFromlogin){   //从登陆界面返回的话,显示我的Fragment
            resetLayoutButton();
            setLayoutButton(5);
            setTabSelection(5);
            setToolBarTitle(5);
        }else{   //否则显示活动主页面
            resetLayoutButton();
            setLayoutButton(3);
            setTabSelection(3);
            setToolBarTitle(3);
        }
    }

    /**设置当前Fragment界面标题*/
    private void setToolBarTitle(int index){
        if(index == 1){
            mToolbar.setTitle("学习");
        }else if(index == 2){
            mToolbar.setTitle("我需要");
        }else if(index == 3){
            mToolbar.setTitle("活动");
        }else if(index == 4){
            mToolbar.setTitle("分享");
        }else if(index == 5){
            mToolbar.setTitle("我");
        }
    }

    /**初始化底部按钮*/
    public void resetLayoutButton(){
        layoutImageLearn.setImageResource(R.drawable.bottom_bar_learn_normal);
        layoutImageNeed.setImageResource(R.drawable.bottom_bar_need_normal);
        layoutImageActivity.setImageResource(R.drawable.bottom_bar_activity_normal);
        layoutImageShare.setImageResource(R.drawable.bottom_bar_share_normal);
        layoutImageMe.setImageResource(R.drawable.bottom_bar_me_normal);

        layoutTextLearn.setTextColor(Color.GRAY);
        layoutTextNeed.setTextColor(Color.GRAY);
        layoutTextActivity.setTextColor(Color.GRAY);
        layoutTextShare.setTextColor(Color.GRAY);
        layoutTextMe.setTextColor(Color.GRAY);
    }

    /**设置底部按钮*/
    public void setLayoutButton(int index){
        switch (index){
            case 1:
                layoutImageLearn.setImageResource(R.drawable.bottom_bar_learn_press);
                layoutTextLearn.setTextColor(getResources().getColor(R.color.mainColor));
                break;
            case 2:
                layoutImageNeed.setImageResource(R.drawable.bottom_bar_need_press);
                layoutTextNeed.setTextColor(getResources().getColor(R.color.mainColor));
                break;
            case 3:
                layoutImageActivity.setImageResource(R.drawable.bottom_bar_activity_press);
                layoutTextActivity.setTextColor(getResources().getColor(R.color.mainColor));
                break;
            case 4:
                layoutImageShare.setImageResource(R.drawable.bottom_bar_share_press);
                layoutTextShare.setTextColor(getResources().getColor(R.color.mainColor));
                break;
            case 5:
                layoutImageMe.setImageResource(R.drawable.bottom_bar_me_press);
                layoutTextMe.setTextColor(getResources().getColor(R.color.mainColor));
                break;
            default:
                layoutImageActivity.setImageResource(R.drawable.bottom_bar_activity_press);
                layoutTextActivity.setTextColor(getResources().getColor(R.color.mainColor));
                break;
        }

    }

    @Override
    public void onClick(View v) {
        resetLayoutButton();
        switch (v.getId()) {
            case R.id.layout_btn_learn:
                setLayoutButton(1);
                setTabSelection(1);
                setToolBarTitle(1);
                break;
            case R.id.layout_btn_need:
                setLayoutButton(2);
                setTabSelection(2);
                setToolBarTitle(2);
                break;
            case R.id.layout_btn_activity:
                setLayoutButton(3);
                setTabSelection(3);
                setToolBarTitle(3);
                break;
            case R.id.layout_btn_share:
                setLayoutButton(4);
                setTabSelection(4);
                setToolBarTitle(4);
                break;
            case R.id.layout_btn_me:
                setLayoutButton(5);
                setTabSelection(5);
                setToolBarTitle(5);
                break;
            default:
                setLayoutButton(3);
                setTabSelection(3);
                setToolBarTitle(3);
                break;
        }

    }

    /**根据传入的index参数来设置选中的tab页*/
    private void setTabSelection(int index){
        FragmentTransaction transaction = fragmentManager.beginTransaction();  // 开启一个Fragment事务
        hideFragments(transaction);  // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        switch (index) {
            case 1:    //
                if (learningFragment == null) {  //
                    learningFragment = new LearningFragment();
                    transaction.add(R.id.fragment_content, learningFragment);
                } else{
                    transaction.show(learningFragment);  //
                }
                break;

            case 2:   //
                if (needFragment == null) {   //
                    needFragment = new NeedFragment();
                    transaction.add(R.id.fragment_content, needFragment);
                } else{
                    transaction.show(needFragment);  //
                }
                break;

            case 3:  //
                if (activityFragment == null) {  //
                    activityFragment = new ActivityFragment();
                    transaction.add(R.id.fragment_content, activityFragment);
                } else{
                    transaction.show(activityFragment);  //
                }
                break;

            case 4:   //
                if (shareFragment == null) {  //
                    shareFragment = new ShareFragment();
                    transaction.add(R.id.fragment_content, shareFragment);
                } else{
                    transaction.show(shareFragment);   //
                }
                break;
            case 5:   //资讯主题界面 mArrayFragment
                if (myFragment == null) {  // 如果mCommentTop10Fragment为空，则创建一个并添加到界面上
                    myFragment = new MeFragment();
                    transaction.add(R.id.fragment_content, myFragment);
                } else{
                    transaction.show(myFragment);  //如果mCommentTop10Fragment不为空，则直接将它显示出来
                }

                break;

            default:
                break;
        }
        transaction.commit();  //提交事务
    }

    /**将所有的Fragment都置为隐藏状态*/
    private void hideFragments(FragmentTransaction transaction){
        if (learningFragment != null){
            transaction.hide(learningFragment);
        }
        if (needFragment != null){
            transaction.hide(needFragment);
        }
        if (activityFragment != null){
            transaction.hide(activityFragment);
        }
        if (shareFragment != null){
            transaction.hide(shareFragment);
        }
        if (myFragment != null){
            transaction.hide(myFragment);
        }
    }

    /** 解决Fragment 重叠问题*/
    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (learningFragment == null && fragment instanceof LearningFragment) {
            learningFragment = (LearningFragment) fragment;
        }else if (needFragment == null && fragment instanceof NeedFragment) {
            needFragment = (NeedFragment)fragment;
        }else if (activityFragment == null && fragment instanceof ActivityFragment) {
            activityFragment = (ActivityFragment)fragment;
        }else if (shareFragment == null && fragment instanceof ShareFragment) {
            shareFragment = (ShareFragment)fragment;
        }else if (myFragment == null && fragment instanceof MeFragment) {
            myFragment = (MeFragment)fragment;
        }
    }



}
