package com.deitui.morelang.index.model;
import com.alibaba.fastjson.JSONObject;
import com.model.AppConfig;
import com.model.Help;
import com.model.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class UserModel extends Model {
	 public UserModel(){
		 this.preTable=this.table_pre+"user";
	}
	public Map get(int userid,String fields) {
		 
		if(fields=="") {
			fields="userid,user_head,nickname,description";
		}
	 
		Map<String,Object> user=this.fields(fields).where("userid="+userid).selectRow();
		user.put("user_head",Help.image_site(user.get("user_head")+""));
		return user;
	}
	
	public List Dselect() {
		List list=this.select();
		int len=list.size();
		for(int i=0;i<len;i++) {
			JSONObject json=(JSONObject) JSONObject.toJSON(list.get(i));
			json.put("user_head",Help.image_site(json.get("user_head")+""));
			list.set(i,json);
		}
		return list;
	}
	public List getListByIds(ArrayList ids,String fields) {
		if(fields=="") {
			fields="userid,user_head,nickname,description";
		}
		String idStr=Help._implode(ids); 
		List list=this.fields(fields).where(" userid in("+idStr+") ").Dselect();
		return list;
	}
}
