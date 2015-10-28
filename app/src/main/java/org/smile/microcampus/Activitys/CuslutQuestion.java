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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.smile.microcampus.Adapters.CommonAdapter;
import org.smile.microcampus.Adapters.ViewHolder;
import org.smile.microcampus.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ben on 2015/10/28.
 */
public class CuslutQuestion extends AppCompatActivity implements View.OnClickListener{

    private Toolbar mToolbar;
    private ListView cListView;
    private TextView textView;
    private List<Map>mDatas;
    private Button button;
    private String question= "喜欢一个人的旅行，喜欢一个人、一个包，踏上远去的旅程。走在乡间的小路，靠在古镇的拱桥边，看着岸边女子衣袂飘飘，惹我阵阵遐想，思绪飘渺。";
    private String title[]={"中国最早说B BOX的是谁","一个人去哪儿旅游呢？","动词后面跟着的是什么呢？","中国最早说B BOX的是谁","中国最早说B BOX的是谁","一个人去哪儿旅游呢？","动词后面跟着的是什么呢？","中国最早说B BOX的是谁"};
    private String content[]={"秦始皇 荆柯刺秦时 秦王说：zhua ci ke bu ci ke（抓刺客捕刺客","是孤独久了，是寂寞惯了，还是太过伤感了，我不知道，突然想一个人去旅行，去完成向往已久的单人旅行，在旅行中寻找那个迷失的自己，找回那个积极乐观的我。喜欢一个人的旅行，喜欢一个人、一个包，踏上远去的旅程。走在乡间的小路，靠在古镇的拱桥边，看着岸边女子衣袂飘飘，惹我阵阵遐想，思绪飘渺。想象她们的生活，是那么的安逸祥和，那样的宁静，那样的令人向往，与此刻的我形成了鲜明的对比，让我尽是羡慕。看着桥下的涓涓流水，那也许就是净化我心灵的解药，也或许是我突然一个人旅行的落脚地。一个人的旅行的那样的美好，那样的随意，不用考虑太多的东西，旅行的目的地随自己的心情而设定，说去哪就去哪，想干嘛就干嘛，无拘无束，甚是让人喜悦，更重要的是单人旅行可以安抚那个被世事挫伤的心灵。一个人可以独自享受安静的快乐，去追寻曾经遗失的美好，努力拼凑那些记忆的碎片，生怕会漏掉一片记忆，留下令人追悔的遗憾。那些记忆，有一起开心的笑，一起感动的哭，这些都是属于我们的记忆。", "动词后面跟着的是（dong ci da ci····）", "秦始皇 荆柯刺秦时 秦王说：          zhua ci ke bu ci ke（抓刺客捕刺客",
            "秦始皇 荆柯刺秦时 秦王说：zhua ci ke bu ci ke（抓刺客捕刺客","  喜欢一个人的旅行，喜欢一个人、一个包，踏上远去的旅程。走在乡间的小路，靠在古镇的拱桥边，看着岸边女子衣袂飘飘，惹我阵阵遐想，思绪飘渺。"
            , "动词后面跟着的是（dong ci da ci····）","秦始皇 荆柯刺秦时 秦王说： zhua ci ke bu ci ke（抓刺客捕刺客"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consult_question);
        initView();
    }
    public void initView(){
        //获取ToolBar控件
        mToolbar= (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("咨询");
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);//设置ActionBar为ToolBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//设置左上角图标为返回键,并添加事件监听，
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() { // 点击返回到咨询界面
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mDatas=new ArrayList<Map>();
        for(int i=0;i<7;i++){
            Map<String,Object>map=new HashMap<String,Object>();
            map.put("title",title[i]);
            map.put("content",content[i]);
            mDatas.add(map);
        }
        textView= (TextView) findViewById(R.id.question_tv4);
        textView.setText(question);
        button= (Button) findViewById(R.id.question_btn);
        button.setOnClickListener(this);
        cListView= (ListView) findViewById(R.id.question_listview);
        cListView.setAdapter(new CommonAdapter<Map>(getApplicationContext(),mDatas,R.layout.study_consult) {
            @Override
            public void convert(ViewHolder holder, Map map) {
                holder.setText(R.id.study_consult_tv1,map.get("title").toString());
                holder.setText(R.id.study_consult_tv2,map.get("content").toString());
            }
        });
    }
    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.question_btn:
               Intent intent=new Intent(getApplicationContext(),AddAnswerActivity.class);
               startActivity(intent);
       }
    }
    /**
     *暂时复制发送页面的菜单代码
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=new MenuInflater(this);
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.post_menu://
                Toast.makeText(getApplicationContext(), "返回数据给活动页", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }


}
