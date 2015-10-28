package org.smile.microcampus.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.smile.microcampus.Activitys.CuslutQuestion;
import org.smile.microcampus.Adapters.CommonAdapter;
import org.smile.microcampus.Adapters.ViewHolder;
import org.smile.microcampus.R;
import org.smile.microcampus.Utils.CircleImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  还没有做上拉加载更多
 * A simple {@link Fragment} subclass.
 */
public class ConsultFragment extends Fragment  implements  SwipeRefreshLayout.OnRefreshListener {

    private View view;
    private ListView listView;
    private List<Map>mDatas;
    private SwipeRefreshLayout swipeLayout;
    private String title[]={"如数奉还还不是电话费？","这个世界真的除了那一个人之外，只能将就吗？","撒旦辐射多个地方出现变异体","王尼玛最近太尼玛了！","如数奉还还不是电话费？","这个世界真的除了那一个人之外，只能将就吗？"};
    private String content[]={"近日移动、联通、电信三家巨头都向外界说明，今后的流量全部都不清零。如数的返回到下个月用户的流量里，但流量不是本来就属于我们的花费吗？如数奉还还不是电话费"
            ,"看了尼玛的黄教主和杨mimi的何以笙箫默，亮瞎了钛合金uzi的眼，PPT形式的电影，表现出了一场爱恨离愁的爱情剧，你会将就吗？",
            "啊诶到甲方科技为根据阿金额上搭噶尔轧亚然后阿萨德刚被人","创建暴走漫画app并成立暴走漫画公司，总部在深圳，在全国许多地方都有暴漫的分公司，他的贴吧id为“亲一下王尼玛”，也兼任暴漫吧吧主，口头禅：\"荆轲刺秦王，两条毛腿肩上扛\"，\"呵呵\"，\"我和我的小伙伴都惊呆了\"，\"shenmegui\"，“小孩子不要看暴漫”,\"why are you so diao \"，“你 在逗我”等，在暴走大事件第一季第10期时与小伙伴“王蜜桃”共同出演。",
            "近日移动、联通、电信三家巨头都向外界说明，今后的流量全部都不清零。如数的返回到下个月用户的流量里，但流量不是本来就属于我们的花费吗？如数奉还还不是电话费",
            "看了尼玛的黄教主和杨mimi的何以笙箫默，亮瞎了钛合金uzi的眼，PPT形式的电影，表现出了一场爱恨离愁的爱情剧，你会将就吗？"};
    public ConsultFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.constult_listview,container,false);
        initView();
        return view;
    }
    /**
     * 初始化视图控件和数据
     */
   public void initView(){
      mDatas=new ArrayList<Map>();
       for(int i=0;i<title.length;i++){
           Map<String,Object>map=new HashMap<String,Object>();
           map.put("title",title[i]);
           map.put("content",content[i]);
           mDatas.add(map);
       }
       listView= (ListView) view.findViewById(R.id.clistview);
       swipeLayout= (SwipeRefreshLayout) view.findViewById(R.id.constult_refresh);
       swipeLayout.setOnRefreshListener(this);
       listView.setAdapter(new CommonAdapter<Map>(getActivity(), mDatas, R.layout.study_consult) {
           @Override
           public void convert(ViewHolder holder, Map map) {
               holder.setText(R.id.study_consult_tv1, map.get("title").toString());
               holder.setText(R.id.study_consult_tv2, map.get("content").toString());
           }
       });
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent=new Intent(getActivity(), CuslutQuestion.class);
               startActivity(intent);
           }
       });
       //设置刷新时动画的颜色，可以设置4个
       swipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
               android.R.color.holo_green_light,
               android.R.color.holo_orange_light,
               android.R.color.holo_red_light);   }

    @Override
    public void onRefresh() {
        //暂时不做刷新处理
        swipeLayout.setRefreshing(false);
    }

}
