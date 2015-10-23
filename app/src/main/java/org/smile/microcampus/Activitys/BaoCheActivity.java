package org.smile.microcampus.Activitys;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

import org.smile.microcampus.Adapters.CommonAdapter;
import org.smile.microcampus.Adapters.ViewHolder;
import org.smile.microcampus.R;
import org.smile.microcampus.Utils.ImageCycleView;

import java.util.ArrayList;
import java.util.Arrays;

public class BaoCheActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ImageCycleView mAdView;
    private ArrayList<String> mImageUrl = null;
    private String imageUrl1 = "http://img.lakalaec.com/ad/57ab6dc2-43f2-4087-81e2-b5ab5681642d.jpg";
    private String imageUrl2 = "http://img.lakalaec.com/ad/cb56a1a6-6c33-41e4-9c3c-363f4ec6b728.jpg";
    private String imageUrl3 = "http://img.lakalaec.com/ad/e4229e25-3906-4049-9fe8-e2b52a98f6d1.jpg";
    public int stype = 1;  //游标是圆形还是长条，要是设置为0是长条，要是1就是圆形 默认是圆形

    private GridView gridCity;
    private ArrayList<String> listCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bao_che);
        initView();
    }

    private void initView(){

        mToolbar = (Toolbar) findViewById(R.id.toolbar);   //ToolBar布局
        mToolbar.setTitleTextColor(Color.WHITE);  //设置ToolBar字体颜色为白色
        mToolbar.setTitle("包车");
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

        listCity = new ArrayList<String>(Arrays.asList("广州","深圳","佛山","珠海","中山","东莞","惠州","江门",
                                            "清远","河源","茂名","湛江","阳江","汕头","汕尾","潮州","梅州"));
        gridCity = (GridView) findViewById(R.id.grid_view_city);
        gridCity.setAdapter((ListAdapter) (new CommonAdapter<String>(this,listCity, R.layout.grid_view_item ) {
                    @Override
                    public void convert(ViewHolder holder, String s) {
                        holder.setText(R.id.grid_view_item_text, s);
                    }
                }));
    }

    private ImageCycleView.ImageCycleViewListener mAdCycleViewListener = new ImageCycleView.ImageCycleViewListener() {
        @Override
        public void onImageClick(int position, View imageView) {
            //单击图片处理事件
            Toast.makeText(BaoCheActivity.this, "图片" + position, Toast.LENGTH_SHORT).show();
        }
    };

}
