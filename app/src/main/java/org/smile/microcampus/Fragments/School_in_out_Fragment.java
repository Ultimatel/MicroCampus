package org.smile.microcampus.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.smile.microcampus.Activitys.NewsActivity;
import org.smile.microcampus.Adapters.School_ActivityAdapter;
import org.smile.microcampus.Entitys.ActivityMessages;
import org.smile.microcampus.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class School_in_out_Fragment extends Fragment {

    View view;
    private ListView slistView;
    private School_ActivityAdapter school_in_out_adapter;
    private List<ActivityMessages> aList=new ArrayList<ActivityMessages>();
    private ActivityMessages actvityMessages;
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
    school_in_out_adapter=new School_ActivityAdapter(getActivity(),R.layout.school_in_out_actvity_fragment,aList);
    slistView.setAdapter(school_in_out_adapter);
    slistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent=new Intent(getActivity(), NewsActivity.class);
            startActivity(intent);

        }
    });

}
    public void acess(){

        String []title={"你若军训","德玛西亚","大学指南","女神男神","翰墨池","你若军训","德玛西亚","大学指南","女神男神","翰墨池"};
        String []content={"便是晴天","你个傻逼","肇庆学院的必备生存软件，哈哈","不知道该说些什么好了","一个约会的好地方，额，目前是这样认为","便是晴天","你个傻逼","肇庆学院的必备生存软件，哈哈","不知道该说些什么好了","一个约会的好地方，额，目前是这样认为"};
            for(int i=0;i<title.length;i++){
            actvityMessages=new ActivityMessages();
            actvityMessages.setTitle(title[i]);
            actvityMessages.setContent(content[i]);
            aList.add(actvityMessages);
        }
    }


}
