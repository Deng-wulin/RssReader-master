package com.hero.rssreader.comm;

import com.hero.rssreader.database.DhDB;

import android.app.Application;


public class MyApplication extends Application {

	public static DhDB db;
	@Override
	public void onCreate() {
		super.onCreate();
		
		//数据库初始化
		db = new DhDB();
		db.init(this, "channe.db", 1);
	}
	
	@Override
	public void onTerminate() {
		super.onTerminate();
	}
}
