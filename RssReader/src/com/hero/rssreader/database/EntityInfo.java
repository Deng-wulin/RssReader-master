package com.hero.rssreader.database;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


import android.text.TextUtils;

import com.hero.rssreader.database.annotation.Column;
import com.hero.rssreader.database.annotation.Entity;
import com.hero.rssreader.database.annotation.NoColumn;


public class EntityInfo {
	/**表名*/
	public String table;
	/**主键*/
	public String pk;
	/**主键是否自动增长*/
	public boolean pkAuto;
	/**字段*/
	public Map<String, String> columns = new HashMap<String, String>();

	public boolean checked = false;

	private static final Map<String,EntityInfo> entitys=new HashMap<String,EntityInfo>();
	
	private EntityInfo(Class<?> entityClazz){
		Entity entity=entityClazz.getAnnotation(Entity.class);
		if(entity!=null){
			table=TextUtils.isEmpty(entity.table())?entityClazz.getSimpleName():entity.table();
		}else{
			table=entityClazz.getSimpleName();
		}
		Field[] fields=entityClazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field=fields[i];
			Column column=field.getAnnotation(Column.class);
			NoColumn nocolumn=field.getAnnotation(NoColumn.class);
			if(column!=null||nocolumn==null){
				String columname;
				if(column!=null){
					columname=TextUtils.isEmpty(column.name())?field.getName():column.name(); 
					if(column.pk()){
						this.pk=field.getName();
						Class<?> primaryClazz=field.getType();
						if( primaryClazz == int.class || primaryClazz==Integer.class || primaryClazz == long.class || primaryClazz == Long.class){
							pkAuto=column.auto();
						}else{
							pkAuto=false;
						}
					}
				}else{
					columname=field.getName();
				}
				columns.put(field.getName(),columname);
			}
		}
	}
	
	public static EntityInfo build(Class<?> entityClazz){
		EntityInfo info=entitys.get(entityClazz);
		if(info==null){
			info=new EntityInfo(entityClazz);
			entitys.put(info.table, info);
		}
		return info;
	}
	
	
	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public boolean isPkAuto() {
		return pkAuto;
	}

	public void setPkAuto(boolean pkAuto) {
		this.pkAuto = pkAuto;
	}

	public Map<String, String> getColumns() {
		return columns;
	}

	public void setColumns(Map<String, String> columns) {
		this.columns = columns;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	
}
