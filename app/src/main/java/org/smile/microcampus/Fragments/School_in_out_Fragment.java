package org.smile.microcampus.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.smile.microcampus.Activitys.NewsActivity;
import org.smile.microcampus.Activitys.Post_ActivityMessages;
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
public class School_in_out_Fragment extends Fragment  implements SwipeRefreshLayout.OnRefreshListener{

    int []images=new int[]{
            R.drawable.p2,R.drawable.p3,
            R.drawable.p4,R.drawable.p5,
            R.drawable.p6,R.drawable.p7,
            R.drawable.p9,R.drawable.p10,
            R.drawable.p11,R.drawable.p13
    };
    String []title={"你若军训","德玛西亚","大学指南","女神男神","翰墨池","你若军训","德玛西亚","大学指南","女神男神","翰墨池"};
    String []content={"便是晴天","你个傻逼","肇庆学院的必备生存软件，哈哈","不知道该说些什么好了","一个约会的好地方，额，目前是这样认为","便是晴天","你个傻逼","肇庆学院的必备生存软件，哈哈","不知道该说些什么好了","一个约会的好地方，额，目前是这样认为"};

    View view;
    private static View loadMoreView;
    private ListView slistView;
    private List<Map> sDatas;
    private TextView loadMoreText;
    private SwipeRefreshLayout  swipeLayout;
    private GridView gridView;
    public School_in_out_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.school_in_out_listview,container,false);
        initView();
        return view ;
    }

public void initView(){

    sDatas=new ArrayList<Map>();
    for(int i=0;i<images.length;i++){
        Map<String,Object>map=new HashMap<String,Object>();
        map.put("image",images[i]);
        map.put("title",title[i]);
        map.put("content",content[i]);
        sDatas.add(map);
    }
    slistView= (ListView) view.findViewById(R.id.school_listview);
    gridView= (GridView) view.findViewById(R.id.school_gridview);
    slistView.setAdapter(new CommonAdapter<Map>(getActivity(),sDatas,R.layout.school_in_out_actvity_fragment) {
        @Override
        public void convert(ViewHolder holder, Map map) {
           holder.setImageResource(R.id.school_image,Integer.parseInt(map.get("image").toString()));
           holder.setText(R.id.school_tv1, map.get("title").toString());
           holder.setText(R.id.school_tv2,map.get("content").toString());
        }
    });
    loadMoreView=getActivity().getLayoutInflater().inflate(R.layout.load_more,null);
    loadMoreText= (TextView) loadMoreView.findViewById(R.id.load_more);
    slistView.addFooterView(loadMoreView);
    swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.refersh_listview);
    swipeLayout.setOnRefreshListener(this);
    //设置刷新时动画的颜色，可以设置4个
    swipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light);
    slistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (position == sDatas.size()) {
                loadMoreText.setText("加载中···");
                //后期加点击加载更多信息内容
                Toast.makeText(getActivity(), "加载更多", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(getActivity(), NewsActivity.class);
                startActivity(intent);
            }
        }
    });
}
    @Override
    public void onRefresh() {
       //暂时不做刷新
        swipeLayout.setRefreshing(false);   //加载完数据后，隐藏刷新进度条
    }

}
