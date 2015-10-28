package org.smile.microcampus.Fragments;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.smile.microcampus.R;
import org.smile.microcampus.Utils.FragmentsFactory;



/**
 * 活动模块.
 *
 */
public class ActivityFragment extends Fragment implements  View.OnClickListener,ViewPager.OnPageChangeListener{

    View view;
    private LinearLayout layout1,layout2,layout3;
    private TextView textView1,textView2,textView3;
    private ImageView imageView1,imageView2,imageView3;
    private ViewPager aViewPager;
    private int currentIndex = 0;//当前标签页
    public ActivityFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_parttime_volunteer,container,false);
        initView();
        return view;
    }

    public void initView() {
        layout1 = (LinearLayout) view.findViewById(R.id.layout_a1);
        layout2 = (LinearLayout) view.findViewById(R.id.layout_a2);
        layout3 = (LinearLayout) view.findViewById(R.id.layout_a3);
       //给layout1,2,3绑定事件器
        layout1.setOnClickListener(this);
        layout2.setOnClickListener(this);
        layout3.setOnClickListener(this);

        imageView1= (ImageView) view.findViewById(R.id.iv_1);
        imageView2= (ImageView) view.findViewById(R.id.iv_2);
        imageView3= (ImageView) view.findViewById(R.id.iv_3);

        textView1= (TextView) view.findViewById(R.id.text1);
        textView2= (TextView) view.findViewById(R.id.text2);
        textView3= (TextView) view.findViewById(R.id.text3);

        aViewPager = (ViewPager) view.findViewById(R.id.actvity_vp);
        aViewPager.setAdapter(new MyViewpagerAdapter(getChildFragmentManager()));
        aViewPager.setOnPageChangeListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_a1:aViewPager.setCurrentItem(0);break;//给导航标签设置标签页
            case R.id.layout_a2:aViewPager.setCurrentItem(1);break;
            case R.id.layout_a3:aViewPager.setCurrentItem(2);break;
        }
    }

    /**
     *初始化导航页的视图
     */
    public void initColorPicture(){
        imageView1.setImageResource(R.drawable.school_activity2);
        imageView2.setImageResource(R.drawable.part_timejob2);
        imageView3.setImageResource(R.drawable.voluntee2);

        textView1.setTextColor(getResources().getColor(R.color.light_gray));
        textView2.setTextColor(getResources().getColor(R.color.light_gray));
        textView3.setTextColor(getResources().getColor(R.color.light_gray));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
    /**
     *当页面选择发生改变的时候，重新设置图片和字体的颜色
     */

    @Override
    public void onPageSelected(int position) {
        initColorPicture();
        switch (position){
            case 0:
                imageView1.setImageResource(R.drawable.school_activity1);
                textView1.setTextColor(getResources().getColor(R.color.green));
                break;
            case 1:
                imageView2.setImageResource(R.drawable.part_timejob1);
                textView2.setTextColor(getResources().getColor(R.color.green));
                break;
            case 2:
                imageView3.setImageResource(R.drawable.voluntee1);
                textView3.setTextColor(getResources().getColor(R.color.green));
                break;
        }
        currentIndex=position;
    }

    @Override
    public void onPageScrollStateChanged(int position) {}

    public class MyViewpagerAdapter extends FragmentStatePagerAdapter {
        public MyViewpagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentsFactory.creatFragments(position);
        }

        @Override
        public int getCount() {
            return 3;
        }

    }

}
