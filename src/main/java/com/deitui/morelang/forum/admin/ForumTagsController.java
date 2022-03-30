
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
import com.deitui.morelang.forum.model.ForumTagsModel;
import com.model.Help;

@RestController
@CrossOrigin("*")
public class ForumTagsController {
	
	@RequestMapping("/admin/forum_tags/index")
	public String Index() {
		ForumTagsModel am=new ForumTagsModel();
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
	
	@RequestMapping("/admin/forum_tags/show")
	public String Show(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="tagid",defaultValue="0") int tagid
	) {
		ForumTagsModel am=new ForumTagsModel();
		Map data=am.where("tagid="+tagid).selectRow(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("data", data);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/admin/forum_tags/add")
	public String Add(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="tagid",defaultValue="0") int tagid
	) {
		ForumTagsModel am=new ForumTagsModel();
		Map data=new HashMap();
		if(tagid!=0) {
			data=am.where("tagid="+tagid).selectRow();
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
	
	@RequestMapping("/admin/forum_tags/save")
	public String Save(
		@RequestParam(value="token",defaultValue="") String token,
		@RequestParam(value="tagid",defaultValue="0") int tagid,
@RequestParam(value="title",defaultValue="") String title,
@RequestParam(value="status",defaultValue="0") int status,
@RequestParam(value="total_num",defaultValue="0") int total_num,
@RequestParam(value="gkey",defaultValue="") String gkey,
@RequestParam(value="gnum",defaultValue="0") int gnum
	) {
		Map indata= new HashMap();
		indata.put("title", title);
indata.put("status", status);
indata.put("total_num", total_num);
indata.put("gkey", gkey);
indata.put("gnum", gnum);

		ForumTagsModel am=new ForumTagsModel();
		if(tagid==0) {
			am.insert(indata);
		}else {
			am.update(indata, "tagid="+tagid);
		}
		return Help.success(0, "保存成功");
	}
	
	@RequestMapping("/admin/forum_tags/status")
	public String Status(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="tagid",defaultValue="0") int tagid
	) {
		ForumTagsModel am=new ForumTagsModel();
		Map row=am.where("tagid="+tagid).selectRow(); 
		JSONObject json=(JSONObject) new JSONObject().toJSON(row);
		int status=0;
		if(json.getIntValue("status")==1) {
			status=2;
		}else {
			status=1;
		}
		Map indata=new HashMap();
		indata.put("status", status);
		am.update(indata,"tagid="+tagid);
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("status", status);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/admin/forum_tags/recommend")
	public String recommend(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="tagid",defaultValue="0") int tagid
	) {
		ForumTagsModel am=new ForumTagsModel();
		Map row=am.where("tagid="+tagid).selectRow(); 
		JSONObject json=(JSONObject) new JSONObject().toJSON(row);
		int status=0;
		if(json.getIntValue("is_recommend")==1) {
			status=0;
		}else {
			status=1;
		}
		Map indata=new HashMap();
		indata.put("is_recommend", status);
		am.update(indata,"tagid="+tagid);
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("is_recommend", status);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/admin/forum_tags/delete")
	public String delete(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="tagid",defaultValue="0") int tagid	
	) {
		ForumTagsModel am=new ForumTagsModel();
		Map indata= new HashMap();
		indata.put("status", 11);
		am.update(indata, "tagid="+tagid);
		return Help.success(0, "删除成功");
	}
	
	
	
}

