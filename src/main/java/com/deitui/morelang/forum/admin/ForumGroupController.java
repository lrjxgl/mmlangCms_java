
package com.deitui.morelang.forum.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.deitui.morelang.forum.model.ForumGroupModel;
import com.model.Help;

@RestController
@CrossOrigin("*")
public class ForumGroupController {
	
	@RequestMapping("/admin/forum_group/index")
	public String Index() {
		ForumGroupModel am=new ForumGroupModel();
		String where=" 1 ";
		
		List list=am.where(where).Dselect(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("list", list);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/admin/forum_group/show")
	public String Show(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="gid",defaultValue="0") int gid
	) {
		ForumGroupModel am=new ForumGroupModel();
		Map data=am.where("gid="+gid).selectRow(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("data", data);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/admin/forum_group/add")
	public String Add(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="gid",defaultValue="0") int gid
	) {
		ForumGroupModel am=new ForumGroupModel();
		Map data=new HashMap();
		if(gid!=0) {
			data=am.where("gid="+gid).selectRow();
			data.put("trueimgurl", Help.image_site(data.get("imgurl")+""));
		}
		
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("data", data);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/admin/forum_group/save")
	public String Save(
		@RequestParam(value="token",defaultValue="") String token,
		@RequestParam(value="gid",defaultValue="0") int gid,
@RequestParam(value="title",defaultValue="") String title,
@RequestParam(value="imgurl",defaultValue="") String imgurl,
@RequestParam(value="description",defaultValue="") String description,
@RequestParam(value="orderindex",defaultValue="0") int orderindex,
@RequestParam(value="status",defaultValue="0") int status,
@RequestParam(value="topic_num",defaultValue="0") int topic_num
	) {
		Map indata= new HashMap();
		indata.put("title", title);
indata.put("imgurl", imgurl);
indata.put("description", description);
indata.put("orderindex", orderindex);
indata.put("status", status);
indata.put("topic_num", topic_num);

		ForumGroupModel am=new ForumGroupModel();
		if(gid==0) {
			am.insert(indata);
		}else {
			am.update(indata, "gid="+gid);
		}
		return Help.success(0, "保存成功");
	}
	
	@RequestMapping("/admin/forum_group/status")
	public String Status(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="gid",defaultValue="0") int gid
	) {
		ForumGroupModel am=new ForumGroupModel();
		Map row=am.where("gid="+gid).selectRow(); 
		JSONObject json=(JSONObject) new JSONObject().toJSON(row);
		int status=0;
		if(json.getIntValue("status")==1) {
			status=2;
		}else {
			status=1;
		}
		Map indata=new HashMap();
		indata.put("status", status);
		am.update(indata,"gid="+gid);
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("status", status);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/admin/forum_group/recommend")
	public String recommend(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="gid",defaultValue="0") int gid
	) {
		ForumGroupModel am=new ForumGroupModel();
		Map row=am.where("gid="+gid).selectRow(); 
		JSONObject json=(JSONObject) new JSONObject().toJSON(row);
		int status=0;
		if(json.getIntValue("is_recommend")==1) {
			status=0;
		}else {
			status=1;
		}
		Map indata=new HashMap();
		indata.put("is_recommend", status);
		am.update(indata,"gid="+gid);
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("is_recommend", status);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/admin/forum_group/delete")
	public String delete(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="gid",defaultValue="0") int gid	
	) {
		ForumGroupModel am=new ForumGroupModel();
		Map indata= new HashMap();
		indata.put("status", 11);
		am.update(indata, "gid="+gid);
		return Help.success(0, "删除成功");
	}
	
	
	
}

