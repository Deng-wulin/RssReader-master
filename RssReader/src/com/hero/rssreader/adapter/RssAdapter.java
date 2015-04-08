package com.hero.rssreader.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.hero.rssreader.R;
import com.hero.rssreader.entity.RssEntity;

/** Rss 结果集的解析器
 * @author wulin
 *
 */
public class RssAdapter extends BaseAdapter {
	
	private LayoutInflater mInflater;// 得到一个LayoutInfalter对象用来导入布局
	private ArrayList<RssEntity> rssEntity;
	AQuery aq;
	public RssAdapter(Context context,ArrayList<RssEntity> rssEntity) {
		mInflater = LayoutInflater.from(context);
		aq = new AQuery(context);
		this.rssEntity = rssEntity;
	}

	@Override
	public int getCount() {
		return rssEntity!=null? rssEntity.size():0;
	}

	@Override
	public RssEntity getItem(int position) {
		return rssEntity!=null?rssEntity.get(position):null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
	
	public String getItemLink(int position){
		return rssEntity!=null?rssEntity.get(position).link:"";
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.rss_feed_item, null);
			holder = new ViewHolder();
			holder.title = (TextView) convertView.findViewById(R.id.title);
			holder.image = (ImageView) convertView.findViewById(R.id.image);
			holder.description = (TextView) convertView
					.findViewById(R.id.description);
			holder.pubDate = (TextView) convertView.findViewById(R.id.pubDate);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		aq.id(holder.title).text(getItem(position).title);
		aq.id(holder.description).text(getItem(position).description);
		holder.pubDate.setText(getItem(position).pubDate);
		//aq.id(holder.image).gone();
		/*if(TextUtils.isEmpty(getItem(position).link)){
			aq.id(holder.image).gone();
		}else{
			aq.id(holder.image).visible().image(getItem(position).link, true, true);
		}*/
		
		return convertView;
	}
	
	class ViewHolder{
		TextView title;
		TextView description;
		ImageView image;
		TextView pubDate;
	}
}