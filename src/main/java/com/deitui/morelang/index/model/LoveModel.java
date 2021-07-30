package com.deitui.morelang.index.model;

import java.util.Map;

import com.model.Model;

public class LoveModel extends Model {
	public LoveModel() {
		 this.preTable=this.table_pre+"love";
	}
	
	public int isLove(int userid,String tablename,int objectid) {
		Map  row=this.where("userid="+userid+" AND tablename='"+tablename+"' AND objectid="+objectid).selectRow();
		return row.size();
	}
}