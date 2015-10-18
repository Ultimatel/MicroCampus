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
import android.widget.TextView;
import android.widget.Toast;

import org.smile.microcampus.Activitys.NewsActivity;
import org.smile.microcampus.Adapters.School_ActivityAdapter;
import org.smile.microcampus.Entitys.ActivityMessages;
import org.smile.microcampus.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class School_in_out_Fragment extends Fragment  implements SwipeRefreshLayout.OnRefreshListener{

    View view;
    private static View loadMoreView;
    private ListView slistView;
    private School_ActivityAdapter school_in_out_adapter;
    private List<ActivityMessages> aList=new ArrayList<ActivityMessages>();
    private ActivityMessages actvityMessages;
    private TextView loadMoreText;
    private SwipeRefreshLayout  swipeLayout;
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
    acess();
    slistView= (ListView) view.findViewById(R.id.school_listview);
    loadMoreView=getActivity().getLayoutInflater().inflate(R.layout.load_more,null);
    loadMoreText= (TextView) loadMoreView.findViewById(R.id.load_more);
    school_in_out_adapter=new School_ActivityAdapter(getActivity(),R.layout.school_in_out_actvity_fragment,aList);
    slistView.addFooterView(loadMoreView);
    slistView.setAdapter(school_in_out_adapter);
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
            if(position==aList.size()){
                loadMoreText.setText("加载中···");
                //后期加点击加载更多信息内容
                Toast.makeText(getActivity(),"加载更多",Toast.LENGTH_SHORT).show();}
            else {
                Intent intent=new Intent(getActivity(), NewsActivity.class);
                startActivity(intent);
            }

        }
    });

}
    public List<ActivityMessages> acess(){

        String []title={"你若军训","德玛西亚","大学指南","女神男神","翰墨池","你若军训","德玛西亚","大学指南","女神男神","翰墨池"};
        String []content={"便是晴天","你个傻逼","肇庆学院的必备生存软件，哈哈","不知道该说些什么好了","一个约会的好地方，额，目前是这样认为","便是晴天","你个傻逼","肇庆学院的必备生存软件，哈哈","不知道该说些什么好了","一个约会的好地方，额，目前是这样认为"};
          if(aList.size()==0){
              for(int i=0;i<title.length;i++){
                  actvityMessages=new ActivityMessages();
                  actvityMessages.setTitle(title[i]);
                  actvityMessages.setContent(content[i]);
                  aList.add(actvityMessages);
          }

        }
        return aList;
    }


    @Override
    public void onRefresh() {
       //暂时不做刷新
        swipeLayout.setRefreshing(false);   //加载完数据后，隐藏刷新进度条
    }
}
