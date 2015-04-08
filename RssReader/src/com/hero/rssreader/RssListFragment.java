package com.hero.rssreader;

import java.util.ArrayList;
import java.util.List;

import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.XmlDom;
import com.hero.rssreader.adapter.RssAdapter;
import com.hero.rssreader.comm.BaseFragment;
import com.hero.rssreader.entity.RssEntity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;

/** 显示Rss订阅的消息
 * @author XFJ-01
 *
 */
public class RssListFragment extends BaseFragment implements
		OnItemClickListener {

	private static final String TAG = "RssListFragment";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.rss_feeds_list, null);
		return root;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		feed_url = getArguments().getString("link");
		Log.i(TAG , "feed_url"+feed_url);
		aq.ajax(feed_url, XmlDom.class, this, "callBack");
		aq.id(R.id.listview).getListView().setOnItemClickListener(this);

	}


	public void callBack(String url, XmlDom xml, AjaxStatus status) {
		if (xml == null)
			return;
		List<XmlDom> entries = xml.tags("item");
		rssEntity = new ArrayList<RssEntity>();
		String imageUrl = null;
		for (XmlDom entry : entries) {
			RssEntity entity = new RssEntity();
			entity.title = entry.text("title");
			entity.link = entry.text("link");
			entity.description = entry.text("author");
			entity.pubDate = entry.text("pubDate");
			entity.guid = entry.text("guid");
			rssEntity.add(entity);
		}
		rssAdapter = new RssAdapter(getActivity(), rssEntity);
		aq.id(R.id.listview).adapter(rssAdapter);
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
		intent.putExtra("link", rssAdapter.getItemLink(position));
		intent.putExtra("title", rssAdapter.getItem(position).title);
		startActivity(intent);
	}

}
