package org.smile.microcampus.Fragments;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private ViewPager aViewPager;
    private int currentIndex = 0;//当前标签页
    public ActivityFragment() {

    }

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
        layout1.setOnClickListener(this);
        layout2.setOnClickListener(this);
        layout3.setOnClickListener(this);
        textView1= (TextView) view.findViewById(R.id.text1);
        textView2= (TextView) view.findViewById(R.id.text2);
        textView3= (TextView) view.findViewById(R.id.text3);
        //给layout1,2,3绑定事件

        layout1.setBackgroundColor(getResources().getColor(R.color.white));
        aViewPager = (ViewPager) view.findViewById(R.id.actvity_vp);
        aViewPager.setAdapter(new MyViewpagerAdapter(getChildFragmentManager()));
        aViewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_a1:aViewPager.setCurrentItem(0);break;
            case R.id.layout_a2:aViewPager.setCurrentItem(1);break;
            case R.id.layout_a3:aViewPager.setCurrentItem(2);break;

        }

    }
    public void initColor(){
        textView1.setTextColor(getResources().getColor(R.color.light_gray));
        textView2.setTextColor(getResources().getColor(R.color.light_gray));
        textView3.setTextColor(getResources().getColor(R.color.light_gray));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        initColor();
        switch (position){
            case 0:
                textView1.setTextColor(getResources().getColor(R.color.orange));
                break;
            case 1:
                textView2.setTextColor(getResources().getColor(R.color.orange));
                break;
            case 2:
                textView3.setTextColor(getResources().getColor(R.color.orange));
                break;
        }
        currentIndex=position;
    }

    @Override
    public void onPageScrollStateChanged(int position) {

    }

    public class MyViewpagerAdapter extends FragmentPagerAdapter {
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
