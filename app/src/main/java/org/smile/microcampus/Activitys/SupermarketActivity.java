package org.smile.microcampus.Activitys;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.smile.microcampus.Adapters.CommonAdapter;
import org.smile.microcampus.Adapters.ViewHolder;
import org.smile.microcampus.R;
import org.smile.microcampus.Utils.ImageCycleView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SupermarketActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private ImageCycleView mAdView;
    private ArrayList<String> mImageUrl = null;
    private String imageUrl1 = "http://img.lakalaec.com/ad/57ab6dc2-43f2-4087-81e2-b5ab5681642d.jpg";
    private String imageUrl2 = "http://img.lakalaec.com/ad/cb56a1a6-6c33-41e4-9c3c-363f4ec6b728.jpg";
    private String imageUrl3 = "http://img.lakalaec.com/ad/e4229e25-3906-4049-9fe8-e2b52a98f6d1.jpg";
    public int stype = 1;  //游标是圆形还是长条，要是设置为0是长条，要是1就是圆形 默认是圆形

    private ListView listSupermarket;
    private List<Map> mDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supermarket);
        initView();
    }

    private void initView(){

        mToolbar = (Toolbar) findViewById(R.id.toolbar);   //ToolBar布局
        mToolbar.setTitleTextColor(Color.WHITE);  //设置ToolBar字体颜色为白色
        mToolbar.setTitle("超市");
        setSupportActionBar(mToolbar);  //将ToolBar设置为ActionBAr
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //在ToolBar左边，即当前标题前添加图标
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        mImageUrl = new ArrayList<String>();
        mImageUrl.add(imageUrl1);
        mImageUrl.add(imageUrl2);
        mImageUrl.add(imageUrl3);
        mAdView = (ImageCycleView) findViewById(R.id.image_cycle_view);
        mAdView.setImageResources(mImageUrl, mAdCycleViewListener, stype);

        mDatas = new ArrayList<Map>();
        for(int i = 0; i < 20; i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("supermarket_image",R.drawable.supermarket_image);
            map.put("supermarket_title", "及时送668");
            map.put("month_sale", " 月售100单");
            map.put("send_price", "起送价￥10");
            map.put("send_time", "30分钟送达");
            mDatas.add(map);
        }
        listSupermarket = (ListView) findViewById(R.id.list_view_supermarket);
        listSupermarket.setAdapter(new CommonAdapter<Map>(this, mDatas, R.layout.list_item_supermarket) {
            @Override
            public void convert(ViewHolder holder, Map map) {
                holder.setImageResource(R.id.supermarket_image, Integer.parseInt(map.get("supermarket_image").toString()));
                holder.setText(R.id.supermarket_title, map.get("supermarket_title").toString());
                holder.setText(R.id.month_sale, map.get("month_sale").toString());
                holder.setText(R.id.send_price, map.get("send_price").toString());
                holder.setText(R.id.send_time, map.get("send_time").toString());
            }
        });
    }

    private ImageCycleView.ImageCycleViewListener mAdCycleViewListener = new ImageCycleView.ImageCycleViewListener() {
        @Override
        public void onImageClick(int position, View imageView) {
            //单击图片处理事件
            Toast.makeText(SupermarketActivity.this, "图片" + position , Toast.LENGTH_SHORT).show();
        }
    };

}
