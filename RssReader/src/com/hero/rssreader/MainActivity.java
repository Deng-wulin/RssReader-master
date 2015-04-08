package com.hero.rssreader;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.XmlDom;
import com.hero.rssreader.adapter.MyFragPagerAdapter;
import com.hero.rssreader.adapter.RssAdapter;
import com.hero.rssreader.comm.BaseActivity;
import com.hero.rssreader.entity.RssEntity;
import com.hero.rssreader.views.PagerSlidingTabStrip;

public class MainActivity extends BaseActivity implements OnItemClickListener {
	
	private RssAdapter rssAdapter;
	private ArrayList<RssEntity> rssEntity;
	private String link;
	
	@Override
	protected int getContainer() {
		return R.layout.rss_feeds_list;
	}
	
	@Override
	protected void onActivityStart() {
		Intent intent = new Intent(this,RssViewPagerActivity.class);
		startActivity(intent);
		finish();
		
	/*	if(getIntent().hasExtra("link")){
			link = getIntent().getExtras().getString("link");	
		}
		
		else{
			SqlProxy sql = SqlProxy.select(ChannelEntity.class, "name=?", "csdn");
			ChannelEntity dbChanne = MyApplication.db.queryFrist(sql);
			link = dbChanne.feedUrl;
		}
		//onRssCenter(null);
		
		if(TextUtils.isEmpty(link)){
			onRssCenter(null);
		}else{
			aq.ajax(link, XmlDom.class, this, "callBack");
			aq.id(R.id.listview).getListView().setOnItemClickListener(this);
		}*/
		
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	 @Override  
	public boolean onOptionsItemSelected(MenuItem item) {  
		 switch (item.getItemId()) {
		case R.id.action_settings:
			onRssCenter(null);
			break;

		default:
			break;
		}
		 return super.onOptionsItemSelected(item); 
	}
	
	public void onRssCenter(View v){
		Intent intent = new Intent(this,RssCenterActivity.class);
		//intent.putExtra("link",rssAdapter.getItemLink(position));
		startActivity(intent);
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
		rssAdapter = new RssAdapter(this,rssEntity);
		aq.id(R.id.listview).adapter(rssAdapter);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(this,NewsDetailsActivity.class);
		intent.putExtra("link",rssAdapter.getItemLink(position));
		startActivity(intent);
	}

	@Override
	protected String getAcitityTitle() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	protected boolean getDisplayBack() {
		// TODO Auto-generated method stub
		return true;
	}
	
	/*public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK)) {     
	    	finish();
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}*/

	

	
	
	
}
