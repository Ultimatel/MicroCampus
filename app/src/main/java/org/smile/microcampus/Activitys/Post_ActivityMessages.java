package org.smile.microcampus.Activitys;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import android.widget.ImageView;
import android.widget.Toast;

import org.smile.microcampus.Adapters.CommonAdapter;
import org.smile.microcampus.Adapters.ViewHolder;
import org.smile.microcampus.R;
import org.smile.microcampus.Utils.ImageBean;
import org.smile.microcampus.Utils.NativeImageLoader;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Ben on 2015/10/25.
 */
public class Post_ActivityMessages extends AppCompatActivity {

    private List<Map> pDatas;
    private Toolbar mToolbar;
    private GridView sGridview;
    private EditText et;
    private List<String> plist = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.school_fragment_post_form);
        initView();
    }

    public void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);//toolbar布局
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle("发布活动信息");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        pDatas = new ArrayList<>();
        plist = getIntent().getStringArrayListExtra("checkphoto");
        Datas();
        et= (EditText) findViewById(R.id.school_fragment_post_tv);
        et.setTextColor(getResources().getColor(R.color.black));
        sGridview= (GridView) findViewById(R.id.grid_post);
        sGridview.setAdapter(new CommonAdapter<Map>(this, pDatas, R.layout.grid_view_image) {
            @Override
            public void convert(ViewHolder holder, Map map) {
                if (plist!=null) {
                    holder.setImageBitmap(R.id.gird_image, (Bitmap) map.get("image"));
                }
                /* else {
                    holder.setImageResource(R.id.gird_image, Integer.parseInt(map.get("image1").toString()));
               }*/
            }
        });
        sGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                    Intent intent = new Intent(getApplicationContext(), Activity_ShowPictureFiles.class);
                        startActivity(intent);
                        finish();//关闭当前的活动
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu_search, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.activity_postmessages://
                Toast.makeText(getApplicationContext(), "提交数据到数据库", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    /**
     * 开启子线程来添加
     * 返回的图片，填充到GridView中
     */
    public void Datas() {
        new Thread(new Runnable() {
            @Override
            public void run() {
              //  Map<String, Object> map = new HashMap<>();
                if (plist!=null) {
                    Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.p2);
                    Map<String,Object>map1=new HashMap<String, Object>();
                    map1.put("image",bitmap);
                    pDatas.add(map1);
                    for (int i = 0; i < (plist.size()); i++) {
                        Map<String, Object> map = new HashMap<>();
                        Bitmap bitmap1 = BitmapFactory.decodeFile(plist.get(i));
                        map.put("image", bitmap1);
                        pDatas.add(map);
                    }
                }
               /* else
                {
                    Map<String, Object> map = new HashMap<>();
                    map.put("image1", images);
                    pDatas.add(map);
                }*/
            }
        }).start();

    }




}
