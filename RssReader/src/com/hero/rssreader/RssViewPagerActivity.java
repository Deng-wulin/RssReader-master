package com.hero.rssreader;

import java.util.ArrayList;
import java.util.List;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.hero.rssreader.adapter.MyFragPagerAdapter;
import com.hero.rssreader.comm.BaseActivity;
import com.hero.rssreader.comm.MyApplication;
import com.hero.rssreader.entity.ChannelEntity;
import com.hero.rssreader.views.PagerSlidingTabStrip;

/** Rss的订阅中心
 * @author XFJ-01
 *
 */
public class RssViewPagerActivity extends BaseActivity {

	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	private List<Fragment> fragList = new ArrayList<Fragment>();
	private ArrayList<String> titles = new ArrayList<String>();;
	private MyFragPagerAdapter adapter;
	private final String TAG = "RssViewPagerActivity";
	private static boolean isLeave=false;
	
	@Override
	protected int getContainer() {
		return R.layout.activity_main;
	}


	@Override
	protected String getAcitityTitle() {
		return "";
	}

	@Override
	protected boolean getDisplayBack() {
		return false;
	}

	
	@Override
	protected void onActivityStart() {
		tabs = (PagerSlidingTabStrip) aq.id(R.id.tabs).getView();
		pager = (ViewPager) aq.id(R.id.pager).getView();
		
		loadData();
	}
	
	
	private void loadData(){
		fragList.clear();
		titles.clear();
		List<ChannelEntity> channeArrs = getAllRssList();
		if(channeArrs!=null&&channeArrs.size()==0){
			Intent intent = new Intent(this,RssCenterActivity.class);
			startActivityForResult(intent,REQUEST_UPDATE);
			return;
		}
		for (ChannelEntity item : channeArrs ) {
			RssListFragment rssListFragment = new RssListFragment();
			Bundle bundle = new Bundle();  
		    bundle.putString("title",item.getName()); 
		    bundle.putString("link",item.getFeedUrl()); 
		    rssListFragment.setArguments(bundle);  
		    fragList.add(rssListFragment);
			titles.add(item.getName());
		}
	
		
		if(adapter==null){
			android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
			adapter = new MyFragPagerAdapter(fm, fragList,titles);
			pager.setOffscreenPageLimit(fragList.size());
		}
		adapter.setData(fragList, titles);
		pager.setAdapter(adapter);
		tabs.setViewPager(pager);
		adapter.notifyDataSetChanged();
	}

	public List<ChannelEntity> getAllRssList(){
		ChannelEntity channe = new ChannelEntity();
		List<ChannelEntity> channeArrs = MyApplication.db.queryList(ChannelEntity.class, " isAdd=?", new String[]{"1"});
		//List<ChannelEntity> channeArrs = MyApplication.db.queryAll(ChannelEntity.class);
		return channeArrs;
	}
	

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.news_pager, menu);
        return super.onCreateOptionsMenu(menu);
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_rss_center:
			Intent intent = new Intent(this,RssCenterActivity.class);
			startActivityForResult(intent,REQUEST_UPDATE);
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(requestCode==REQUEST_UPDATE&&resultCode==RESULT_OK){
			loadData();
		}
	}
	
	@Override
	public void onBackPressed() {
		if(!isLeave){
			isLeave = true;
			//AlertDialog alert =  AlertDialog.Builder();
			Toast.makeText(this, "确定要离开", 0).show();
		}
		super.onBackPressed();
	}
}
