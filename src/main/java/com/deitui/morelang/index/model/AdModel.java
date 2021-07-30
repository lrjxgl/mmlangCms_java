package com.deitui.morelang.index.model;

import com.alibaba.fastjson.JSONObject;
import com.model.AppConfig;
import com.model.Help;
import com.model.Model;

import java.util.ArrayList;
import java.util.List;

public class AdModel extends Model {
    public AdModel(){
        this.preTable=this.table_pre+"ad";
    }
    public List listByNo(String key,Integer limit){
        List list=new ArrayList();
        AdTagsModel tags= new AdTagsModel();
        String t=tags.fields("tag_id").where("tagno='"+key+"' ").selectOne();
        if(t==""){
            return list;
        }
        int tag_id=Integer.parseInt(t);
        list=this.where("status=2 AND tag_id_2nd="+tag_id).limit(0,limit).select();
        int len=list.size();
        if(len==0){
            return list;
        }
        for(int i=0;i<len;i++){
            Object obj=list.get(i);
            JSONObject json= (JSONObject) JSONObject.toJSON(obj);
            json.put("imgurl", AppConfig.IMAGES_SITE +json.get("imgurl"));
            if(json.get("imgurl2")!=""){
                json.put("imgurl2", AppConfig.IMAGES_SITE +json.get("imgurl2"));
            }

            list.set(i,json);
        }
        return list;
    }
    
    public List Dselect() {
		List list=this.select();
		int len=list.size();
        if(len==0) {
        	return list;
        }
        for(int i=0;i<len;i++){
            Object obj=list.get(i);
            JSONObject json= (JSONObject) JSONObject.toJSON(obj);
            
            json.put("imgurl", Help.image_site(json.get("imgurl")+""));
            json.put("imgurl2", Help.image_site(json.get("imgurl2")+""));
            list.set(i,json);
        }
		return list;
	}
    
}
