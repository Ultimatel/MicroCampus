package org.smile.microcampus.Activitys;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.smile.microcampus.Adapters.CommonAdapter;
import org.smile.microcampus.Adapters.ViewHolder;
import org.smile.microcampus.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    private ListView listService;
    private List<Map> mDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        initView();
    }

    public void initView(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);   //ToolBar布局
        mToolbar.setTitleTextColor(Color.WHITE);  //设置ToolBar字体颜色为白色
        mToolbar.setTitle("服务");
        setSupportActionBar(mToolbar);  //将ToolBar设置为ActionBAr
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //在ToolBar左边，即当前标题前添加图标
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        mDatas = new ArrayList<Map>();
        for(int i = 0; i < 20; i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("service_image",R.drawable.p3);
            map.put("service_title", "大白工作室");
            map.put("service_content", " 本工作室主要经营电脑除尘业务");
            mDatas.add(map);
        }
        listService = (ListView) findViewById(R.id.list_view_service);
        listService.setAdapter(new CommonAdapter<Map>(this, mDatas, R.layout.list_item_service) {
            @Override
            public void convert(ViewHolder holder, Map map) {
                holder.setImageResource(R.id.service_image, Integer.parseInt(map.get("service_image").toString()));
                holder.setText(R.id.service_title, map.get("service_title").toString());
                holder.setText(R.id.service_content, map.get("service_content").toString());
            }
        });
        listService.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ServiceActivity.this, "点击 " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
