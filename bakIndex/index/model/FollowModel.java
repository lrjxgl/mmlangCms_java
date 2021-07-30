package com.deitui.morelang.index.model;
import java.util.Map;

import com.model.Model;
public class FollowModel extends Model {
	public FollowModel() {
		 this.preTable=this.table_pre+"follow";
	}
	public int isFollow(int userid,int t_userid) {
		Map row=this.where("userid="+userid+" AND t_userid="+t_userid).selectRow();
		if(row.size()==0) {
			return 0;
		}else {
			return 1;
		}
	}
}
