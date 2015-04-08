package com.hero.rssreader;

import java.util.List;

import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.hero.rssreader.adapter.ChanneAdapter;
import com.hero.rssreader.comm.BaseActivity;
import com.hero.rssreader.comm.MyApplication;
import com.hero.rssreader.entity.ChannelEntity;

/**
 * Rss订阅中心
 * 
 * @author wulin
 * 
 */
public class RssCenterActivity extends BaseActivity implements
		OnItemClickListener {

	private ChanneAdapter channeAdapter;
	private String TAG = "RssCenterActivity";
	private boolean isUpdate = false;
	
	@Override
	protected int getContainer() {
		return R.layout.activity_rss_center;
	}

	@Override
	protected void onActivityStart() {
		ChannelEntity channe = new ChannelEntity();
		List<ChannelEntity> channeArrs = MyApplication.db
				.queryAll(ChannelEntity.class);
		channeAdapter = new ChanneAdapter(this, channeArrs);
		//ListView listView = aq.id(R.id.gridview).getListView();
		
		aq.id(R.id.gridview).getListView().setOnItemClickListener(this);
		aq.id(R.id.gridview).getListView().setAdapter(channeAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.rss_center, menu);
		return super.onCreateOptionsMenu(menu);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			break;
		case R.id.action_menu_add:
			startActivityForResult(new Intent(this, AddChanneActivity.class),
					REQUEST_UPDATE);
			break;
		}
		return true;
	}

	@Override
	public void onBackPressed() {
		if(isUpdate){
			setResult(RESULT_OK);
		}
		super.onBackPressed();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		isUpdate = true;
		ChannelEntity click = channeAdapter.getItem(position);
		click.setIsAdd(click.getIsAdd() == 0 ? 1 : 0);
		MyApplication.db.update(click);
		channeAdapter.notifyDataSetChanged();
		Log.i(TAG, click.getName());
//		Toast.makeText(this, "click me ", 0).show();
		/*
		 * Intent intent = new Intent(); if(click.get_id()!=0){
		 * intent.setClass(this, MainActivity.class); intent.putExtra("link",
		 * click.getFeedUrl()); intent.putExtra("name", click.getName());
		 * startActivity(intent); }else{
		 * intent.setClass(this,AddChanneActivity.class);
		 * startActivityForResult(intent, REQUEST_UPDATE); }
		 */

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_UPDATE && resultCode == RESULT_OK) {
			onActivityStart();
		}
	}

	@Override
	protected String getAcitityTitle() {
		return getString(R.string.action_rss_center);
	}

	@Override
	protected boolean getDisplayBack() {
		return true;
	}

}
