package com.deitui.morelang.forum.model;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.deitui.morelang.index.model.UserModel;
import com.model.AppConfig;
import com.model.Help;
import com.model.Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ForumModel extends  Model {
    public ForumModel(){
        this.table="mod_forum";
        this.preTable=this.table_pre+this.table;
    }
    public List Dselect(){

        List list=this.select();
        int len=list.size();
        if(len==0) {
        	return list;
        }
        ArrayList uids=new ArrayList(); 
        
        for(int i=0;i<len;i++){
        	Object obj=list.get(i);
            JSONObject json= (JSONObject) JSONObject.toJSON(obj);
            uids.add(json.getIntValue("userid"));
        }
        UserModel userModel=new UserModel();
        List us=userModel.getListByIds(uids,"");
        ForumGroupModel forumGroupModel=new ForumGroupModel();
        List groupList=forumGroupModel.where("status in(0,1,2) ").Dselect();
        ForumCategoryModel forumCategoryModel=new ForumCategoryModel();
        List catList=forumCategoryModel.where("status in(0,1,2) ").select();
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        for(int i=0;i<len;i++){
            Object obj=list.get(i);
            JSONObject json= (JSONObject) JSONObject.toJSON(obj);
            
            json.put("imgurl", Help.image_site(json.get("imgurl")+""));
            String imgsdata=json.get("imgsdata")+"";
            String imgList[]=imgsdata.split(",");
            for(int ii=0;ii<imgList.length;ii++){
                imgList[ii]=Help.image_site(imgList[ii]);
            }
            json.put("timeago", sdf.format(json.getSqlDate("createtime")));
            json.put("imgList",imgList);
           
            json.put("user",Help.getObjectByKey(us, "userid", json.get("userid")+"" ) );
            json.put("group", Help.getObjectByKey(groupList, "gid",json.get("gid")+""));
            json.put("cat", Help.getObjectByKey(catList, "catid", json.get("catid")+"" ));
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
