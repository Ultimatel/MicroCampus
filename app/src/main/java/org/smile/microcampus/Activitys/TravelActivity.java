package org.smile.microcampus.Activitys;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

import org.smile.microcampus.Adapters.CommonAdapter;
import org.smile.microcampus.Adapters.ViewHolder;
import org.smile.microcampus.R;
import org.smile.microcampus.Utils.ImageCycleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TravelActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ImageCycleView mAdView;
    private ArrayList<String> mImageUrl = null;
    private String imageUrl1 = "http://img.lakalaec.com/ad/57ab6dc2-43f2-4087-81e2-b5ab5681642d.jpg";
    private String imageUrl2 = "http://img.lakalaec.com/ad/cb56a1a6-6c33-41e4-9c3c-363f4ec6b728.jpg";
    private String imageUrl3 = "http://img.lakalaec.com/ad/e4229e25-3906-4049-9fe8-e2b52a98f6d1.jpg";
    public int stype = 1;  //游标是圆形还是长条，要是设置为0是长条，要是1就是圆形 默认是圆形

    private GridView gridPlace;
    private ArrayList<String> listPlace;
    private GridView gridList;
    private List<Map> mDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        initView();
    }

    private void initView(){

        mToolbar = (Toolbar) findViewById(R.id.toolbar);   //ToolBar布局
        mToolbar.setTitleTextColor(Color.WHITE);  //设置ToolBar字体颜色为白色
        mToolbar.setTitle("旅游");
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


        listPlace = new ArrayList<String>(Arrays.asList("阳江", "阳朔", "九龙湖", "北海", "海南",
                "凤凰古城","张家界","九寨沟"));
        gridPlace = (GridView) findViewById(R.id.grid_view_city);
        gridPlace.setAdapter((ListAdapter) (new CommonAdapter<String>(this, listPlace, R.layout.grid_view_item) {
            @Override
            public void convert(ViewHolder holder, String s) {
                holder.setText(R.id.grid_view_item_text, s);
            }
        }));



        mDatas = new ArrayList<Map>();
        for(int i = 0; i < 10; i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image",R.drawable.guilin);
            map.put("imageTitle", "桂林+阳朔三天四夜游,今日特价,逾时不再有");
            map.put("originalPrice", "￥1000");
            map.put("currentPrice", "￥600");
            mDatas.add(map);
        }
        gridList = (GridView) findViewById(R.id.grid_view_travel);
        gridList.setAdapter(new CommonAdapter<Map>(this, mDatas, R.layout.grid_item_travel) {
            @Override
            public void convert(ViewHolder holder, Map map) {
                holder.setImageResource(R.id.image, Integer.parseInt(map.get("image").toString()));
                holder.setText(R.id.image_title, map.get("imageTitle").toString());
                holder.setText(R.id.original_price, map.get("originalPrice").toString());
                holder.setText(R.id.current_price, map.get("currentPrice").toString());
            }

        });
    }

    private ImageCycleView.ImageCycleViewListener mAdCycleViewListener = new ImageCycleView.ImageCycleViewListener() {
        @Override
        public void onImageClick(int position, View imageView) {
            //单击图片处理事件
            Toast.makeText(TravelActivity.this, "图片" + position, Toast.LENGTH_SHORT).show();
        }
    };


}
