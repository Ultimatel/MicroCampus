package org.smile.microcampus.Fragments;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.TextView;
import org.smile.microcampus.Adapters.CommonAdapter;
import org.smile.microcampus.Adapters.ViewHolder;
import org.smile.microcampus.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudyGuide_Fragment extends Fragment implements  SwipeRefreshLayout.OnRefreshListener{

    View view;
    private static int count=0;
    private TextView textView,textViewButton;
    private String title[]={"中国最早说B BOX的是谁","一个人去哪儿旅游呢？","动词后面跟着的是什么呢？","中国最早说B BOX的是谁","中国最早说B BOX的是谁","一个人去哪儿旅游呢？","动词后面跟着的是什么呢？","中国最早说B BOX的是谁"};
    private String content[]={"秦始皇 荆柯刺秦时 秦王说：zhua ci ke bu ci ke（抓刺客捕刺客","是孤独久了，是寂寞惯了，还是太过伤感了，我不知道，突然想一个人去旅行，去完成向往已久的单人旅行，在旅行中寻找那个迷失的自己，找回那个积极乐观的我。喜欢一个人的旅行，喜欢一个人、一个包，踏上远去的旅程。走在乡间的小路，靠在古镇的拱桥边，看着岸边女子衣袂飘飘，惹我阵阵遐想，思绪飘渺。想象她们的生活，是那么的安逸祥和，那样的宁静，那样的令人向往，与此刻的我形成了鲜明的对比，让我尽是羡慕。看着桥下的涓涓流水，那也许就是净化我心灵的解药，也或许是我突然一个人旅行的落脚地。一个人的旅行的那样的美好，那样的随意，不用考虑太多的东西，旅行的目的地随自己的心情而设定，说去哪就去哪，想干嘛就干嘛，无拘无束，甚是让人喜悦，更重要的是单人旅行可以安抚那个被世事挫伤的心灵。一个人可以独自享受安静的快乐，去追寻曾经遗失的美好，努力拼凑那些记忆的碎片，生怕会漏掉一片记忆，留下令人追悔的遗憾。那些记忆，有一起开心的笑，一起感动的哭，这些都是属于我们的记忆。", "动词后面跟着的是（dong ci da ci····）", "秦始皇 荆柯刺秦时 秦王说：          zhua ci ke bu ci ke（抓刺客捕刺客",
            "秦始皇 荆柯刺秦时 秦王说：zhua ci ke bu ci ke（抓刺客捕刺客","  喜欢一个人的旅行，喜欢一个人、一个包，踏上远去的旅程。走在乡间的小路，靠在古镇的拱桥边，看着岸边女子衣袂飘飘，惹我阵阵遐想，思绪飘渺。"
            , "动词后面跟着的是（dong ci da ci····）","秦始皇 荆柯刺秦时 秦王说： zhua ci ke bu ci ke（抓刺客捕刺客"};
    private List<Map> mdatas;
    private ListView listView;
    private SwipeRefreshLayout swipeLayout;
    public StudyGuide_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.voluntee_listview,container,false);
        initView();
        return view;
    }

    public void initView(){

        mdatas=new ArrayList<Map>();
        for(int i=0;i<title.length;i++){
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("title",title[i]);
            map.put("content",content[i]);
            mdatas.add(map);
        }

        listView= (ListView) view.findViewById(R.id.vlistview);
        swipeLayout= (SwipeRefreshLayout) view.findViewById(R.id.voluntee_refresh);
        swipeLayout.setOnRefreshListener(this);
        listView.setAdapter(new CommonAdapter<Map>(getActivity(), mdatas, R.layout.university_guide) {
            @Override
            public void convert(final ViewHolder holder, Map map) {
                holder.setText(R.id.guide_tv1, map.get("title").toString());
                holder.setText(R.id.guide_tv2, map.get("content").toString());
              //  holder.getView(R.id.guide_line).setBackgroundResource(R.color.green);
                textView=holder.getView(R.id.guide_tv2);
                textViewButton=holder.getView(R.id.guide_btn);

                holder.setOnClickListener(R.id.guide_btn, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView=holder.getView(R.id.guide_tv2);
                        textViewButton=holder.getView(R.id.guide_btn);
                        switch (count){
                            case 0:
                                textViewButton.setText("收起");
                                textView.setMaxLines(100);
                                count++;break;
                            case 1:
                                textViewButton.setText("全文");
                                textView.setMaxLines(6);
                                count--;break;

                        }

                    }
                });
             textlength();
            }
        });
        //设置刷新时动画的颜色，可以设置4个
        swipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }


      public void textlength(){
          int i=textView.getText().length();
          if(i<130){
              textViewButton.setVisibility(View.INVISIBLE);
          }

          else{
              textView.setMaxLines(6);
              textView.setEllipsize(TextUtils.TruncateAt.END);
              textViewButton.setVisibility(View.VISIBLE);
        }

      }


    @Override
    public void onRefresh() {
        //暂时不做刷新处理
        swipeLayout.setRefreshing(false);
    }
}
