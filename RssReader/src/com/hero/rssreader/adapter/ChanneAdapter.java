package com.hero.rssreader.adapter;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.hero.rssreader.R;
import com.hero.rssreader.comm.MyApplication;
import com.hero.rssreader.entity.ChannelEntity;

public class ChanneAdapter extends BaseAdapter {
	
	private LayoutInflater mInflater;// 得到一个LayoutInfalter对象用来导入布局
	private List<ChannelEntity> channeEntity;
	

	public ChanneAdapter(Context context,List<ChannelEntity> channeEntity) {
		mInflater = LayoutInflater.from(context);
		this.channeEntity = channeEntity;
		
	}

	@Override
	public int getCount() {
		return channeEntity!=null? channeEntity.size():0;
	}

	@Override
	public ChannelEntity getItem(int position) {
		return channeEntity!=null?channeEntity.get(position):null;
	}

	@Override
	public long getItemId(int position) {
		return channeEntity!=null?channeEntity.get(position).get_id():-1;
	}
	
	public String getItemLink(int position){
		return channeEntity!=null?channeEntity.get(position).getFeedUrl():"";
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.rss_center_item, null);
			holder = new ViewHolder();
			holder.title = (TextView) convertView.findViewById(R.id.title);
			holder.description = (TextView) convertView
					.findViewById(R.id.description);
			holder.imgBtn = (ImageView) convertView.findViewById(R.id.add);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.title.setText(getItem(position).getName());
		holder.description.setText(getItem(position).description);
		if(getItem(position).getIsAdd()==1){
			holder.imgBtn.setImageResource(R.drawable.icon_added);
		}else{
			holder.imgBtn.setImageResource(R.drawable.icon_add);
		}
		//holder.imgBtn.setTag(""+position);
		/*holder.imgBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//v
				String postionTag = v.getTag().toString();
				if(!TextUtils.isEmpty(postionTag)){
					int pos = Integer.parseInt(postionTag);
					ChannelEntity channe = getItem(pos);
					channe.setIsAdd(channe.getIsAdd()==0?1:0);
					MyApplication.db.update(channe);
				}
				
//				
			}
		});*/
		return convertView;
	}
	
	class ViewHolder{
		TextView title;
		TextView description;
		ImageView image;
		ImageView imgBtn;
	}
}
