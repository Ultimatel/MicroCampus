package org.smile.microcampus.Fragments;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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
public class Volunteer_Fragment extends Fragment  implements SwipeRefreshLayout.OnRefreshListener{
    View view;
    private String title[]={"敬老爱老志愿者服务活动","爱护蓝天、倡导节约减排志愿者活动","成长助学春节送温暖活动",
    "关爱山川，保护自然志愿者活动","关爱特殊儿童青年志愿服务活动","爱护蓝天、倡导节约减排志愿者活动","爱护蓝天、倡导节约减排志愿者活动","爱护蓝天、倡导节约减排志愿者活动",};
    private String content[]={"为庆祝一年一度的重阳佳节，大力弘扬中华民族尊老敬老的传统美德，进一步营造爱老助老的良好社会氛围，组织敬老爱老志愿者服务活动的重阳节志愿者活动",
            "我们同在一蓝天下呼吸，成长，望着蓝蓝的天空，心情自然美好，节约减排，爱护蓝天，从你我开始",
            "祖国的花朵需要我们去爱护，关爱山区贫困儿童，让他们都能学有所成，走出大山，成为祖国栋梁",
            "开展保护母亲河志愿服务活动。以“保护母亲河，让天更蓝、水更清、环境更优美”作为我们的宣传主题。",
            "像小草一样不畏风雨、不惧艰险，始终保持阳光健康、乐观向上的心态，" +
            "顽强地从生活中汲取养分，快乐生活、茁壮成长。在学习中勤学善思、独立自强",
            "开展保护母亲河志愿服务活动。以“保护母亲河，让天更蓝、水更清、环境更优美”作为我们的宣传主题。",
            "开展保护母亲河志愿服务活动。以“保护母亲河，让天更蓝、水更清、环境更优美”作为我们的宣传主题。",
            "开展保护母亲河志愿服务活动。以“保护母亲河，让天更蓝、水更清、环境更优美”作为我们的宣传主题。"};
    private String score[]={"+  0.2","+  0.3","+  0.2","+  0.2","+  0.4","+  0.2","+  0.2","+  0.2"};
    private List<Map> vDatas;
    private ListView vListView;
    private SwipeRefreshLayout swipeLayout;
    public Volunteer_Fragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.voluntee_listview,container,false);
        initView();
        return view;
    }
     public void initView(){
         vDatas=new ArrayList<Map>();
             for(int i=0;i<score.length;i++){
                 Map<String,Object>map=new HashMap<String ,Object>();
                 map.put("title",title[i]);
                 map.put("content",content[i]);
                 map.put("score",score[i]);
                 vDatas.add(map);
             }
         vListView= (ListView) view.findViewById(R.id.vlistview);
         swipeLayout= (SwipeRefreshLayout) view.findViewById(R.id.voluntee_refresh);
         swipeLayout.setOnRefreshListener(this);
         vListView.setAdapter(new CommonAdapter<Map>(getActivity(), vDatas, R.layout.volunee_detail) {
             @Override
             public void convert(ViewHolder holder, Map map) {
                 holder.setText(R.id.voluntee_score, map.get("score").toString());
                 holder.setText(R.id.voluntee_title, map.get("title").toString());
                 holder.setText(R.id.voluntee_content, map.get("content").toString());
             }
         });
         //设置刷新时动画的颜色，可以设置4个
         swipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                 android.R.color.holo_green_light,
                 android.R.color.holo_orange_light,
                 android.R.color.holo_red_light);
     }

    @Override
    public void onRefresh() {
        //暂时不做刷新
        swipeLayout.setRefreshing(false);
    }
}
