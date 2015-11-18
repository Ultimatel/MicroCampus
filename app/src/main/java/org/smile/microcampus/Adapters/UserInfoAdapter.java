package org.smile.microcampus.Adapters;

import android.content.Context;
import android.media.Image;
import android.view.View;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.smile.microcampus.R;
import org.smile.microcampus.Utils.CircleImageView;
import org.smile.microcampus.Utils.ImageCycleView;
import org.smile.microcampus.Utils.MyImageLoader;

import java.util.List;
import java.util.Map;
/**
 * Created by guang on 2015/11/17.
 */
public class UserInfoAdapter extends MultiItemCommonAdapter<Map>{
    public UserInfoAdapter(Context context, List<Map> datas){
        super(context, datas, new MultiItemTypeSupport<Map>(){

            @Override
            public int getLayoutId(int position, Map msg){
                if (position == 0){
                    return R.layout.list_item_text_circle_image;
                }
                return R.layout.list_item_text_text;
            }

            @Override
            public int getViewTypeCount(){
                return 2;
            }
            @Override
            public int getItemViewType(int position, Map msg){
                if (position == 0){
                    return 0;
                }
                return 1;
            }
        });
    }

    @Override
    public void convert(ViewHolder holder, Map map){
        switch (holder.getLayoutId()){
            case  R.layout.list_item_text_circle_image:
                holder.setText(R.id.title, map.get("title").toString());

                CircleImageView image = holder.getView(R.id.content);
                String url = map.get("content").toString();
                if(url.length() != 0)
                    ImageLoader.getInstance().displayImage(url, image, MyImageLoader.options);
                else
                    holder.setImageResource(R.id.content, R.drawable.user_icon);

                break;
            case R.layout.list_item_text_text:
                holder.setText(R.id.title, map.get("title").toString());
                String content = map.get("content").toString();
                if( content.length() != 0)
                    holder.setText(R.id.content, content);
                else
                    holder.setText(R.id.content, "未填写");

                break;
        }
    }
}


