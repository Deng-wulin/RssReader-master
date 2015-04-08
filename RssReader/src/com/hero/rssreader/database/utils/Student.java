package com.hero.rssreader.database.utils;

import java.util.List;

import com.hero.rssreader.database.annotation.Column;
import com.hero.rssreader.database.annotation.Entity;
import com.hero.rssreader.database.annotation.NoColumn;

@Entity(table="stu")
public class Student {
	
	@Column(auto=true,pk=true,name="_stuid")
	private int stuid;
	private String name;
	private String age;
	private int sex;
	private boolean dangyuang;
	@NoColumn
	private List<String> str;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public boolean isDangyuang() {
		return dangyuang;
	}
	public void setDangyuang(boolean dangyuang) {
		this.dangyuang = dangyuang;
	}
	
	
	
}
