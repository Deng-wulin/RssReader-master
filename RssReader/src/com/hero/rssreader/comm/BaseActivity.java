package com.hero.rssreader.comm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.MenuItem;

import com.androidquery.AQuery;
import com.hero.rssreader.R;

public abstract class BaseActivity extends FragmentActivity{
	protected AQuery aq;
	protected String type;
	public final int REQUEST_UPDATE = 0;
	public final int REQUEST_RESULT = 1;
	
	/** 设置布局
	 * @return
	 */
	protected abstract int getContainer();
	
	/** 设置标题
	 * @return
	 */
	protected abstract String getAcitityTitle();
	
	/** 设置返回按钮
	 * @return
	 */
	protected abstract boolean getDisplayBack();
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		getActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(getContainer());
		aq = new AQuery(this);
		onActivityStart();
		getActionBar().setDisplayShowTitleEnabled(false);
		String title = getAcitityTitle();
		if(!TextUtils.isEmpty(title)){
			getActionBar().setTitle(title);
			getActionBar().setDisplayShowTitleEnabled(true);
		}
		getActionBar().setDisplayHomeAsUpEnabled(getDisplayBack());
		
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {  
        switch (item.getItemId()) {  
        case android.R.id.home:  
            onBackPressed();  
            break;  
        }  
        return true;  
    }  
	
	/**
	 * 替代onCreate
	 */
	protected abstract void onActivityStart();
	
	@Override
	public void onDestroy(){
		aq.dismiss();
		super.onDestroy();
	}
	
	protected void showActivity(Class<?> clazz){
		Intent intent = new Intent();
		intent.setClass(this, clazz);
	}
	
	protected void showActivity(Class<?> clazz,Bundle bundle){
		Intent intent = new Intent();
		intent.setClass(this, clazz);
		intent.putExtra("data", bundle);
		startActivity(intent);
	}
	
}
