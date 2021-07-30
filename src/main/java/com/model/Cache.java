package com.model;

import com.deitui.morelang.index.model.DbcacheModel;

public class Cache {
	public DbcacheModel dbc;
	public Cache() {
		this.dbc=new DbcacheModel();
	}
	public Boolean set(String k,String v,int expire) {
	 
		this.dbc.set(k,v,expire);
		return true;
	}
	
	public String get(String k) {
		return this.dbc.get(k);
		
	}
	
}
