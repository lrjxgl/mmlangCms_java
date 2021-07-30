
package com.deitui.morelang.index.model;

import java.util.List;
import java.util.ArrayList;
import com.alibaba.fastjson.JSONObject;
import com.model.Help;
import com.model.Model;

public class WeixinModel extends Model {
	public WeixinModel() {
		this.preTable=this.table_pre+"weixin";
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
            String imgsdata=json.get("imgsdata")+"";
            String imgList[]=imgsdata.split(",");
            for(int ii=0;ii<imgList.length;ii++){
                imgList[ii]=Help.image_site(imgList[ii]);
            }
            
            json.put("imgList",imgList);
           
            list.set(i,json);
        }
		return list;
	}
	 public List getListByIds(ArrayList ids,String fields) {
    	if(fields=="") {
			fields="*";
		}
		String idStr=Help._implode(ids); 
		List list=this.fields(fields).where(" id in("+idStr+") ").Dselect();
		return list;
    }
}


