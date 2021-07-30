package com.deitui.morelang.forum.model;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.model.Help;
import com.model.Model;

public class ForumFavModel extends Model {
    public ForumFavModel(){
        this.preTable=this.table_pre+"fav";
    }
    
    public List Dselect() {
    	List list=this.select();
    	ArrayList ids=new ArrayList();
    	int len=list.size();
    	if(len==0) {
    		return list;
    	}
    	for(int i=0;i<len;i++) {
    		JSONObject json=(JSONObject) JSONObject.toJSON(list.get(i));
    		ids.add(json.getIntValue("objectid"));
    	}
    	ForumModel f=new ForumModel();
    	List fss=f.getListByIds(ids, "");
    	for(int i=0;i<len;i++) {
    		JSONObject json=(JSONObject) JSONObject.toJSON(list.get(i));
    		list.set(i, Help.getObjectByKey(fss, "id", json.getString("objectid")));
    	}
    	return  list;
    }
}
