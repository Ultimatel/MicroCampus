package org.smile.microcampus.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.smile.microcampus.R;
import org.smile.microcampus.Utils.DateText;
import org.smile.microcampus.Utils.TimeFormat;

import java.util.List;


/**
 * Created by Ben on 2015/10/16.
 */
public class PartTimeJob_Adapter extends BaseAdapter {
    private Context pContext;
    private List<DateText> list;
    private int resourceId;

    public PartTimeJob_Adapter(Context pContext, int resourceId, List<DateText> list){
        this.pContext=pContext;
        this.list=list;
        this.resourceId=resourceId;
    }

    @Override
    public int getCount() {
        if(list==null){
            return 0;
        }
        return  list.size();
    }

    @Override
    public Object getItem(int position) {
        if(list==null){
            return null;
        }
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        //判断当前的view是否为空，是的话就重新创建一个新的视图
        if(convertView==null){
            //初始化视图组件
            holder=new Holder();
            LayoutInflater inflater=LayoutInflater.from(pContext);
            convertView=inflater.inflate(resourceId,null);
            holder. line=convertView.findViewById(R.id.v_line);
            holder.title= (RelativeLayout) convertView.findViewById(R.id.rl_title);
            holder.date= (TextView) convertView.findViewById(R.id.txt_date_time);
            holder.content= (TextView) convertView.findViewById(R.id.txt_date_content);
            convertView.setTag(holder);//设置标签
        }
        else {
            holder= (Holder) convertView.getTag();
        }
        //时间轴竖线的layout
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.line.getLayoutParams();
        //第一条数据，肯定显示时间标题
        if (position == 0) {
            holder.title.setVisibility(View.VISIBLE);
            holder.date.setText(TimeFormat.format("yyyy.MM.dd",
                    list.get(position).getDate()));
            params.addRule(RelativeLayout.ALIGN_TOP, R.id.rl_title);
            params.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.txt_date_content);
        } else { // 不是第一条数据
            // 本条数据和上一条数据的时间戳相同，时间标题不显示
            if (list.get(position).getDate()
                    .equals(list.get(position - 1).getDate())) {
                holder.title.setVisibility(View.GONE);
                params.addRule(RelativeLayout.ALIGN_TOP, R.id.txt_date_content);
                params.addRule(RelativeLayout.ALIGN_BOTTOM,
                        R.id.txt_date_content);
            } else {
                //本条数据和上一条的数据的时间戳不同的时候，显示数据
                holder.title.setVisibility(View.VISIBLE);
                holder.date.setText(TimeFormat.format("yyyy.MM.dd",
                        list.get(position).getDate()));
                params.addRule(RelativeLayout.ALIGN_TOP, R.id.rl_title);
                params.addRule(RelativeLayout.ALIGN_BOTTOM,
                        R.id.txt_date_content);
            }
        }
        holder.line.setLayoutParams(params);
        holder.content.setText(list.get(position).getText());
        return convertView;

}

    public static class Holder{
        private RelativeLayout title;
        private View line;
        private TextView date;
        private TextView content;
    }
}
