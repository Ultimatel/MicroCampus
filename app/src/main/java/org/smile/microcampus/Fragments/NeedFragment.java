package org.smile.microcampus.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import org.smile.microcampus.Activitys.AccommodationActivity;
import org.smile.microcampus.Activitys.PackageBusActivity;
import org.smile.microcampus.Activitys.SupermarketActivity;
import org.smile.microcampus.Activitys.TravelActivity;
import org.smile.microcampus.Adapters.MyGridAdapter;
import org.smile.microcampus.R;
import org.smile.microcampus.Utils.ImageCycleView;
import org.smile.microcampus.Utils.MyGridView;

import java.util.ArrayList;

/**
 * 我需要模块
 */
public class NeedFragment extends Fragment {

    View view;
    private MyGridView gridview;

    private ImageCycleView mAdView;
    private ArrayList<String> mImageUrl = null;
    private String imageUrl1 = "http://img.lakalaec.com/ad/57ab6dc2-43f2-4087-81e2-b5ab5681642d.jpg";
    private String imageUrl2 = "http://img.lakalaec.com/ad/cb56a1a6-6c33-41e4-9c3c-363f4ec6b728.jpg";
    private String imageUrl3 = "http://img.lakalaec.com/ad/e4229e25-3906-4049-9fe8-e2b52a98f6d1.jpg";
    public int stype = 1;  //游标是圆形还是长条，要是设置为0是长条，要是1就是圆形 默认是圆形

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_need, container, false);

        initView();

        return view;
    }

    private void initView() {
        gridview = (MyGridView) view.findViewById(R.id.gridview);
        gridview.setAdapter(new MyGridAdapter(getActivity()));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 0:   //超市
                    Intent intent = new Intent(getActivity(), SupermarketActivity.class);
                    startActivity(intent);
                    break;
                case 1:  //包车
                    Intent intent1 = new Intent(getActivity(), PackageBusActivity.class);
                    startActivity(intent1);
                    break;
                case 2:  //住宿
                    Intent intent2 = new Intent(getActivity(), AccommodationActivity.class);
                    startActivity(intent2);
                    break;
                case 3:  //旅游
                    Intent intent3 = new Intent(getActivity(), TravelActivity.class);
                    startActivity(intent3);
                    break;
                default:
                    break;
            }
            }
        });

        mImageUrl = new ArrayList<String>();
        mImageUrl.add(imageUrl1);
        mImageUrl.add(imageUrl2);
        mImageUrl.add(imageUrl3);
        mAdView = (ImageCycleView) view.findViewById(R.id.ad_view);
        mAdView.setImageResources(mImageUrl, mAdCycleViewListener, stype);
    }

    private ImageCycleView.ImageCycleViewListener mAdCycleViewListener = new ImageCycleView.ImageCycleViewListener() {
        @Override
        public void onImageClick(int position, View imageView) {
            //单击图片处理事件
            Toast.makeText(getActivity(), "图片" + position , Toast.LENGTH_SHORT).show();
        }
    };



}
