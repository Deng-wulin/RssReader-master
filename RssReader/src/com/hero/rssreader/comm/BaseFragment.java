package com.hero.rssreader.comm;

import java.util.ArrayList;

import com.androidquery.AQuery;
import com.hero.rssreader.R;
import com.hero.rssreader.adapter.RssAdapter;
import com.hero.rssreader.entity.RssEntity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BaseFragment extends Fragment {
	
	protected View root;
	protected AQuery aq;
	public String feed_url;
	protected RssAdapter rssAdapter;
	protected ArrayList<RssEntity> rssEntity;
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		 aq = new AQuery(root);
	}
}
