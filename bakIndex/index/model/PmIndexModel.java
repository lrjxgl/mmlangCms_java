package com.deitui.morelang.index.model;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.model.Help;
import com.model.Model;
public class PmIndexModel extends Model {
	public PmIndexModel() {
		this.table="pm_index";
		this.preTable=this.table_pre+this.table;
	}
	
	public List Dselect() {
		List list=this.select();
		int len=list.size();
		if(len==0) return list;
		ArrayList uids=new ArrayList();
		for(int i=0;i<len;i++) {
			Object obj=list.get(i);
            JSONObject json= (JSONObject) JSONObject.toJSON(obj);
            uids.add(json.getIntValue("userid"));
		}
		UserModel userModel=new UserModel();
	    List us=userModel.getListByIds(uids,"");
	    for(int i=0;i<len;i++) {
	    	Object obj=list.get(i);
            JSONObject json= (JSONObject) JSONObject.toJSON(obj);
            json.put("user",Help.getObjectByKey(us, "userid", json.get("userid")+"" ) );
            list.set(i,json);
	    }
		return list;
	}
}
