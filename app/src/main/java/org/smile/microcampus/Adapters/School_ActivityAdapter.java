package org.smile.microcampus.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.smile.microcampus.Entitys.ActivityMessages;
import org.smile.microcampus.R;

import java.util.List;

/**
 * Created by Ben on 2015/10/17.
 */
public class School_ActivityAdapter extends BaseAdapter{
    Context sContext;
    private int resourceId;
    private List<ActivityMessages> listItem;

    public  School_ActivityAdapter(Context sContext, int resourceId,
                                 List< ActivityMessages>listItem) {
        super();
        this.sContext=sContext;
        this.resourceId=resourceId;
        this.listItem=listItem;
    }



    @Override
    public int getCount() {
        return listItem.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View v;
        final ViewHolder viewHolder;
        if(convertView==null){

            LayoutInflater inflater=LayoutInflater.from(sContext);
            v=inflater.inflate(resourceId,null);
            viewHolder=new ViewHolder();
            viewHolder.imageView= (ImageView) v.findViewById(R.id.school_image);
            viewHolder.title= (TextView) v.findViewById(R.id.school_tv1);
            viewHolder.content= (TextView) v.findViewById(R.id.school_tv2);
            v.setTag(viewHolder);
        }
        else {
            v = convertView;
            viewHolder= (ViewHolder) v.getTag();
        }
        String title=listItem.get(position).getTitle();
        String content=listItem.get(position).getContent();

        viewHolder.title.setText(title);
        viewHolder.content.setText(content);
        viewHolder.imageView.setImageResource(listItem.get(position).getImageView());
        return v;

    }
    public  class ViewHolder{
        public TextView content;
        public TextView title;
        public ImageView imageView;
    }
}
