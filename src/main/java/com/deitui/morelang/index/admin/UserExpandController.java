
package com.deitui.morelang.index.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.deitui.morelang.index.model.UserExpandModel;
import com.model.Help;

@RestController
@CrossOrigin("*")
public class UserExpandController {
	
	@RequestMapping("/admin/user_expand/index")
	public String Index() {
		UserExpandModel am=new UserExpandModel();
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
	
	@RequestMapping("/admin/user_expand/show")
	public String Show(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="uid",defaultValue="0") int uid
	) {
		UserExpandModel am=new UserExpandModel();
		Map data=am.where("uid="+uid).selectRow(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("data", data);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/admin/user_expand/add")
	public String Add(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="uid",defaultValue="0") int uid
	) {
		UserExpandModel am=new UserExpandModel();
		Map data=new HashMap();
		if(uid!=0) {
			data=am.where("uid="+uid).selectRow();
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
	
	@RequestMapping("/admin/user_expand/save")
	public String Save(
		@RequestParam(value="token",defaultValue="") String token,
		@RequestParam(value="uid",defaultValue="0") int uid,
@RequestParam(value="content",defaultValue="") String content
	) {
		Map indata= new HashMap();
		indata.put("content", content);

		UserExpandModel am=new UserExpandModel();
		if(uid==0) {
			am.insert(indata);
		}else {
			am.update(indata, "uid="+uid);
		}
		return Help.success(0, "保存成功");
	}
	
	@RequestMapping("/admin/user_expand/status")
	public String Status(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="uid",defaultValue="0") int uid
	) {
		UserExpandModel am=new UserExpandModel();
		Map row=am.where("uid="+uid).selectRow(); 
		JSONObject json=(JSONObject) new JSONObject().toJSON(row);
		int status=0;
		if(json.getIntValue("status")==1) {
			status=2;
		}else {
			status=1;
		}
		Map indata=new HashMap();
		indata.put("status", status);
		am.update(indata,"uid="+uid);
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("status", status);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/admin/user_expand/recommend")
	public String recommend(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="uid",defaultValue="0") int uid
	) {
		UserExpandModel am=new UserExpandModel();
		Map row=am.where("uid="+uid).selectRow(); 
		JSONObject json=(JSONObject) new JSONObject().toJSON(row);
		int status=0;
		if(json.getIntValue("is_recommend")==1) {
			status=0;
		}else {
			status=1;
		}
		Map indata=new HashMap();
		indata.put("is_recommend", status);
		am.update(indata,"uid="+uid);
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("is_recommend", status);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/admin/user_expand/delete")
	public String delete(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="uid",defaultValue="0") int uid	
	) {
		UserExpandModel am=new UserExpandModel();
		Map indata= new HashMap();
		indata.put("status", 11);
		am.update(indata, "uid="+uid);
		return Help.success(0, "删除成功");
	}
	
	
	
}

