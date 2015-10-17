package org.smile.microcampus.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


import org.smile.microcampus.Activitys.PartTime_Detail;
import org.smile.microcampus.Adapters.PartTimeJob_Adapter;
import org.smile.microcampus.R;
import org.smile.microcampus.Utils.DateComparator;
import org.smile.microcampus.Utils.DateText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PartTime_Job_Fragment extends Fragment {
    public static View view;
    // 时间轴列表
    private ListView lvList;
    // 数据list
    private List<DateText> list=new ArrayList<>();
    // 列表适配器
    private PartTimeJob_Adapter partTimeJob_adapter;
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
        lvList.setAdapter(partTimeJob_adapter);//给ListView绑定适配器
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent(getActivity(), PartTime_Detail.class);
                startActivity(intent);
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
}