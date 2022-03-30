
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
import com.deitui.morelang.index.model.UserGroupModel;
import com.model.Help;

@RestController
@CrossOrigin("*")
public class UserGroupController {
	
	@RequestMapping("/admin/user_group/index")
	public String Index() {
		UserGroupModel am=new UserGroupModel();
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
	
	@RequestMapping("/admin/user_group/show")
	public String Show(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="groupid",defaultValue="0") int groupid
	) {
		UserGroupModel am=new UserGroupModel();
		Map data=am.where("groupid="+groupid).selectRow(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("data", data);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/admin/user_group/add")
	public String Add(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="groupid",defaultValue="0") int groupid
	) {
		UserGroupModel am=new UserGroupModel();
		Map data=new HashMap();
		if(groupid!=0) {
			data=am.where("groupid="+groupid).selectRow();
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
	
	@RequestMapping("/admin/user_group/save")
	public String Save(
		@RequestParam(value="token",defaultValue="") String token,
		@RequestParam(value="groupid",defaultValue="0") int groupid,
@RequestParam(value="groupname",defaultValue="") String groupname,
@RequestParam(value="access",defaultValue="") String access
	) {
		Map indata= new HashMap();
		indata.put("groupname", groupname);
indata.put("access", access);

		UserGroupModel am=new UserGroupModel();
		if(groupid==0) {
			am.insert(indata);
		}else {
			am.update(indata, "groupid="+groupid);
		}
		return Help.success(0, "保存成功");
	}
	
	@RequestMapping("/admin/user_group/status")
	public String Status(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="groupid",defaultValue="0") int groupid
	) {
		UserGroupModel am=new UserGroupModel();
		Map row=am.where("groupid="+groupid).selectRow(); 
		JSONObject json=(JSONObject) new JSONObject().toJSON(row);
		int status=0;
		if(json.getIntValue("status")==1) {
			status=2;
		}else {
			status=1;
		}
		Map indata=new HashMap();
		indata.put("status", status);
		am.update(indata,"groupid="+groupid);
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("status", status);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/admin/user_group/recommend")
	public String recommend(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="groupid",defaultValue="0") int groupid
	) {
		UserGroupModel am=new UserGroupModel();
		Map row=am.where("groupid="+groupid).selectRow(); 
		JSONObject json=(JSONObject) new JSONObject().toJSON(row);
		int status=0;
		if(json.getIntValue("is_recommend")==1) {
			status=0;
		}else {
			status=1;
		}
		Map indata=new HashMap();
		indata.put("is_recommend", status);
		am.update(indata,"groupid="+groupid);
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("is_recommend", status);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/admin/user_group/delete")
	public String delete(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="groupid",defaultValue="0") int groupid	
	) {
		UserGroupModel am=new UserGroupModel();
		Map indata= new HashMap();
		indata.put("status", 11);
		am.update(indata, "groupid="+groupid);
		return Help.success(0, "删除成功");
	}
	
	
	
}

