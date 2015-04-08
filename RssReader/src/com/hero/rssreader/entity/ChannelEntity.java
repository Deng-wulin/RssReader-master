package com.hero.rssreader.entity;

import java.io.Serializable;

import com.hero.rssreader.database.annotation.Column;
import com.hero.rssreader.database.annotation.Entity;


import android.provider.BaseColumns;

/** rssdignyue
 * @author wulin
 *
 */
@Entity(table="channe")
public class ChannelEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	@Column(auto=true,pk=true)
	public int _id;
	public String name;//名称
	public String logo;//订阅的logo
	public String feedUrl;//订阅地址
	public String description;//订阅描述
	public int isAdd;//是否已经订阅
	
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getFeedUrl() {
		return feedUrl;
	}
	public void setFeedUrl(String feedUrl) {
		this.feedUrl = feedUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getIsAdd() {
		return isAdd;
	}
	public void setIsAdd(int isAdd) {
		this.isAdd = isAdd;
	}
	
	
	
}
