package org.smile.microcampus.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import org.smile.microcampus.R;
import org.smile.microcampus.Utils.FragmentsFactory;

import java.util.List;
import java.util.Map;

/**
 * 学习模块
 */
public class LearningFragment extends Fragment implements  View.OnClickListener,ViewPager.OnPageChangeListener {

    private View view;
    private ViewPager lViewPager;
    private ImageView imageView1,imageView2,imageView3;
   // private int currentIndex = 0;//当前标签页
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.study_layout,container,false);
        initView();
        initImage();
        return view;
    }

    public void initView(){

        //找到所有控件
        imageView1= (ImageView) view.findViewById(R.id.university_guide);
        imageView2= (ImageView) view.findViewById(R.id.study_consult);
        imageView3= (ImageView) view.findViewById(R.id.library);
        lViewPager= (ViewPager) view.findViewById(R.id.study_viewpager);

        //给iamgeview设置点击事件
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);

        //给viewpager设置适配器和监听器
        lViewPager.setAdapter(new StudyFragmentAdapter(getChildFragmentManager()));
        lViewPager.setOnPageChangeListener(this) ;

    }

    //初始化ImageView的视图资源
    public void initImage(){
        imageView1.setImageResource(R.drawable.p5);
        imageView2.setImageResource(R.drawable.p3);
        imageView3.setImageResource(R.drawable.p7);
    }
    /**
     * 给三个ImgaeView绑定各自对应的fragment页面
     */
    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.university_guide:lViewPager.setCurrentItem(0);
              break;
          case R.id.study_consult:lViewPager.setCurrentItem(1);break;
          case R.id.library:lViewPager.setCurrentItem(2);break;
      }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageScrollStateChanged(int state) {}
    @Override
    public void onPageSelected(int position) {
        initImage();
        switch (position){
            case 0:imageView1.setImageResource(R.drawable.p11);break;
            case 1:imageView2.setImageResource(R.drawable.p9);break;
            case 2:imageView3.setImageResource(R.drawable.p13);break;
        }
    }

    public class StudyFragmentAdapter extends FragmentStatePagerAdapter{
        public StudyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
         return FragmentsFactory.creatFragments(position+3);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
