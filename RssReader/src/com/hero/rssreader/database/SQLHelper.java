package com.hero.rssreader.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHelper extends SQLiteOpenHelper {
	public static final String DB_NAME = "rss.db";// 数据库名称
	public static final int VERSION = 1;
	
	public static final String TABLE_CHANNEL = "channel";//数据表 

	public static final String ID = "_id";//名称
	public static final String NAME = "name";//名称
	public static final String LOGO = "logo";//订阅的logo
	public static final String FEED_URI = "feet_url";//订阅地址
	public static final String DESCRIPTION = "description";//订阅的描述
	
	private Context context;
	public SQLHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
		this.context = context;
	}

	public Context getContext(){
		return context;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO 创建数据库后，对数据库的操作
		String sql = "create table if not exists "+TABLE_CHANNEL +"("
				+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
				NAME + " TEXT , " +
				DESCRIPTION + " TEXT , " +
				FEED_URI + " TEXT , " +
				LOGO + " TEXT )";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 更改数据库版本的操作
		db.execSQL("drop table if exists "+TABLE_CHANNEL);
		onCreate(db);
	}

}
