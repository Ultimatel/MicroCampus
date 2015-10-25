package org.smile.microcampus.Activitys;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;
import android.widget.ListAdapter;

import org.smile.microcampus.Adapters.CommonAdapter;
import org.smile.microcampus.Adapters.ViewHolder;
import org.smile.microcampus.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Ben on 2015/10/25.
 */
public class Post_ActivityMessages extends AppCompatActivity {
    private int images[]={
            R.drawable.p2,R.drawable.p3,
            R.drawable.p4,R.drawable.p5
    };
    private ArrayList<Map> pDatas;
    private Toolbar mToolbar;
    private GridView sGridview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.school_fragment_post_form);
        initView();
    }
    public void initView(){
        mToolbar= (Toolbar) findViewById(R.id.toolbar);//toolbar布局
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle("图片");
        mToolbar.setBackgroundColor(getResources().getColor(R.color.green));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        pDatas=new ArrayList<>();
       for(int i=0;i<images.length;i++){
           Map<String,Object>map=new HashMap<String,Object>();
           map.put("image",images[i]);
           pDatas.add(map);
       }
        sGridview= (GridView) findViewById(R.id.grid_post);
        sGridview.setAdapter(new CommonAdapter<Map>(this,pDatas,R.layout.school_fragment_post_form) {
            @Override
            public void convert(ViewHolder holder, Map map) {

            }
        });
    }
}
