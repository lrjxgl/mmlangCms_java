package com.deitui.morelang.forum.model;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.model.AppConfig;
import com.model.Help;
import com.model.Model;

public class ForumGroupModel extends Model {
    public ForumGroupModel(){
        this.preTable=this.table_pre+"mod_forum_group";
    }
    public List Dselect(){

        List list=this.select();
        int len=list.size();
        for(int i=0;i<len;i++){
            Object obj=list.get(i);
            JSONObject json= (JSONObject) JSONObject.toJSON(obj);
            json.put("imgurl", AppConfig.IMAGES_SITE +json.get("imgurl"));
            list.set(i,json);
        }
        return list;
    }
    
    public List getListByIds(ArrayList ids,String fields) {
    	if(fields=="") {
			fields="*";
		}
		String idStr=Help._implode(ids); 
		List list=this.fields(fields).where(" gid in("+idStr+") ").Dselect();
		return list;
    }

}
