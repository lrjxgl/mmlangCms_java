package com.deitui.morelang.index.model;
import java.util.Map;

import com.model.Model;
public class FavModel extends Model {
	public FavModel() {
		 this.preTable=this.table_pre+"fav";
	}
	
	public int isFav(int userid,String tablename,int objectid) {
		Map  row=this.where("userid="+userid+" AND tablename='"+tablename+"' AND objectid="+objectid).selectRow();
		return row.size();
	}
}
