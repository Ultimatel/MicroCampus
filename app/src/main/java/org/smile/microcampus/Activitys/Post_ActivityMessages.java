package org.smile.microcampus.Activitys;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import android.widget.Toast;

import org.smile.microcampus.Adapters.CommonAdapter;
import org.smile.microcampus.Adapters.ViewHolder;
import org.smile.microcampus.R;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Ben on 2015/10/25.
 */
public class Post_ActivityMessages extends AppCompatActivity {
    private int images= R.drawable.p2;
    private List<Map> pDatas;
    private Toolbar mToolbar;
    private GridView sGridview;
    private List<String>plist=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.school_fragment_post_form);
        initView();
    }
    public void initView(){
        mToolbar= (Toolbar) findViewById(R.id.toolbar);//toolbar布局
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

        pDatas=new ArrayList<>();
        for(int i=0;i<1;i++){
           Map<String,Object>map=new HashMap<String,Object>();
           map.put("image",images);
           pDatas.add(map);
       }
        sGridview= (GridView) findViewById(R.id.grid_post);
        sGridview.setAdapter(new CommonAdapter<Map>(this, pDatas, R.layout.grid_view_image) {
            @Override
            public void convert(ViewHolder holder, Map map) {
                holder.setImageResource(R.id.gird_image, Integer.parseInt(map.get("image").toString()));

            }
        });
        sGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent=new Intent(getApplicationContext(),Activity_ShowPictureFiles.class);
                        startActivity(intent);
                        finish();//关闭当前的活动
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=new MenuInflater(this);
        inflater.inflate(R.menu.menu_search,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.activity_postmessages://
                Toast.makeText(getApplicationContext(),"提交数据到数据库",Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }
}
