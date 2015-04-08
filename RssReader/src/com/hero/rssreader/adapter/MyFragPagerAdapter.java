package com.hero.rssreader.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyFragPagerAdapter extends FragmentPagerAdapter {
	private List<String> titles;
	private List<Fragment> fList;

	
	
	public MyFragPagerAdapter(FragmentManager fm, List<Fragment> fList,
			List<String> titles) {
		super(fm);
		this.titles = titles;
		this.fList = fList;
	}
	
	public void setData(List<Fragment> fList,
			List<String> titles){
		this.titles = titles;
		this.fList = fList;
	}

	@Override
	public int getCount() {
		return titles.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		String title = titles.get(position);
		if(title.length()>8){
			title = title.substring(0,8);
		}
		return title;
	}

	@Override
	public Fragment getItem(int position) {
		return fList.get(position);
	}
}