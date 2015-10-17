package org.smile.microcampus.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.smile.microcampus.R;

/**
 * @Description:gridview的Adapter
 * @author http://blog.csdn.net/finddreams
 */
public class MyGridAdapter extends BaseAdapter {

	private Context mContext;

	public String[] img_text = { "超市", "包车", "住宿", "旅游", "校联", "拍拍",
			"聚会", "电影", "服务", };
	public int[] imgs = { R.drawable.app_transfer, R.drawable.app_fund,R.drawable.app_creditcard,
			R.drawable.app_plane, R.drawable.app_movie, R.drawable.app_game,
			R.drawable.app_facepay, R.drawable.app_close,R.drawable.app_phonecharge  };

	public MyGridAdapter(Context mContext) {
		super();
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return img_text.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		ViewHolder viewHolder;
		if (convertView == null) {
			view = LayoutInflater.from(mContext).inflate(R.layout.grid_item, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.iv = (ImageView) view.findViewById(R.id.iv_item);
			viewHolder.tv = (TextView) view.findViewById(R.id.tv_item);
			view.setTag(viewHolder);
		} else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag(); //重新获取viewHolder
		}
		viewHolder.iv.setBackgroundResource(imgs[position]);
		viewHolder.tv.setText(img_text[position]);
		return view;
	}

	class ViewHolder {
		ImageView iv;
		TextView tv;
		}
}
