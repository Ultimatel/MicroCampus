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


import org.smile.microcampus.Activitys.PartTime_Detail;
import org.smile.microcampus.Adapters.PartTimeJob_Adapter;
import org.smile.microcampus.Entitys.ActivityMessages;
import org.smile.microcampus.R;
import org.smile.microcampus.Utils.DateComparator;
import org.smile.microcampus.Utils.DateText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PartTime_Job_Fragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    public static View view;
    private TextView loadMoreText;
    private static View loadMoreView;
    // 时间轴列表
    private ListView lvList;
    // 数据list
    private List<DateText> list=new ArrayList<>();
    // 列表适配器
    private PartTimeJob_Adapter partTimeJob_adapter;
    private SwipeRefreshLayout  swipeLayout;
    public PartTime_Job_Fragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.time_line_listview, container, false);
        initView();
        return view;
    }

    public void initView() {
        addData();
        lvList = (ListView) view.findViewById(R.id.time_listview);
        partTimeJob_adapter = new PartTimeJob_Adapter(getActivity(), R.layout.time_line_job,list);
        DateComparator comparator = new DateComparator();
        // 将数据按照时间排序
        Collections.sort(list, comparator);
        loadMoreView=getActivity().getLayoutInflater().inflate(R.layout.load_more,null);
        loadMoreText= (TextView) loadMoreView.findViewById(R.id.load_more);
        lvList.addFooterView(loadMoreView);
        lvList.setAdapter(partTimeJob_adapter);//给ListView绑定适配器
        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.line_refresh);
        swipeLayout.setOnRefreshListener(this);
        //设置刷新时动画的颜色，可以设置4个
        swipeLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==list.size()){
                    loadMoreText.setText("加载中···");
                    //后期加点击加载更多信息内容
                    Toast.makeText(getActivity(), "加载更多", Toast.LENGTH_SHORT).show();}
               else {
                    Intent intent=new Intent(getActivity(), PartTime_Detail.class);
                       startActivity(intent);
                    }
            }
        });
    }

    //提供假数据
    private List<DateText>  addData() {
        String date[]=new String[]{"20140710","20140709","20140709","20140726"
                ,"20140710","20140711","20140713","20140712" };
        String content[]=new String[]{  "撒萨达", "撒大ADS" ,"撒萨达", "撒大ADS" , "撒达到对萨达撒地"
                , "撒大阿瑟的萨达地", "撒撒大大地", "撒大鼎折覆餗地"};
        if(list.size()==0){
            for(int i=0;i<date.length;i++){
                DateText dateText=new DateText();
                dateText.setDate(date[i]);
                dateText.setText(content[i]);
                list.add(dateText);
            }
        }
      return list;
    }

    @Override
    public void onRefresh() {
        //暂时没有刷新数据，等连接数据库再补充
        swipeLayout.setRefreshing(false);   //加载完数据后，隐藏刷新进度条
    }
}