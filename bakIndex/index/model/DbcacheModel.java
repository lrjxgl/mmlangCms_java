package com.deitui.morelang.index.model;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.model.Model;
public class DbcacheModel extends Model {
	public DbcacheModel() {
		this.preTable=this.table_pre+"dbcache";
	}
	
	public   String get(String key) {
		Map<String,String> row=this.where("k='"+key+"' ").selectRow();
		 
		if(row.size()==0) {
			return "";
		}else {
			String jsonStr=JSON.toJSONString(row);
			JSONObject jsonObject = JSONObject.parseObject(jsonStr);
			int time=(int) (new Date().getTime()/1000);
			int expire = jsonObject.getIntValue("expire");
			if(expire<time) {
				return ""; 
			}
			return row.get("v");
		}
		
	}
	public String set(String key, String val,int expire) {
		Map<String,String> row=this.where("k='"+key+"' ").selectRow();
		Map<String,Object> data=new HashMap<String,Object>();
		data.put("k", key);
		data.put("v", val);
		int time=(int) (new Date().getTime()/1000);
		data.put("expire",time+expire);
		if(row.size()==0) {
			
			this.insert(data);
		}else {
			this.update(data,"k='"+key+"'");
		}
		return "";
	}
}
