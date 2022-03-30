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
import com.deitui.morelang.index.model.UserVipModel;
import com.model.Help;

@RestController
@CrossOrigin("*")
public class UserVipController {
	
	public UserVipController() {
		
	}
	
	@RequestMapping("/admin/user_vip/index")
	public String Index(
		@RequestParam(value="per_page",defaultValue="0") int per_page
	) {
		UserVipModel am=new UserVipModel();
		String where=" status in(0,1,2) ";
		int start=per_page;
		int limit=24;
		List list=am.where(where).limit(start,limit).Dselect(); 
		int rscount=Integer.parseInt(am.where(where).fields("count(*)").selectOne());
		per_page=per_page+limit;
		per_page=per_page>rscount?0:per_page;
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("list", list);
		redata.put("rscount", rscount);
        redata.put("per_page", per_page);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/admin/user_vip/show")
	public String Show(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="userid",defaultValue="0") int userid
	) {
		UserVipModel am=new UserVipModel();
		Map data=am.where("userid="+userid).selectRow(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("data", data);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/admin/user_vip/add")
	public String Add(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="userid",defaultValue="0") int userid
	) {
		UserVipModel am=new UserVipModel();
		Map data=new HashMap();
		if(userid!=0) {
			data=am.where("userid="+userid).selectRow();
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
	
	@RequestMapping("/admin/user_vip/save")
	public String Save(
		@RequestParam(value="token",defaultValue="") String token,
		@RequestParam(value="userid",defaultValue="0") int userid,
@RequestParam(value="starttime",defaultValue="0") int starttime,
@RequestParam(value="firsttime",defaultValue="0") int firsttime,
@RequestParam(value="endtime",defaultValue="0") int endtime,
@RequestParam(value="stype",defaultValue="0") int stype,
@RequestParam(value="status",defaultValue="0") int status
	) {
		Map indata= new HashMap();
		indata.put("userid", userid);
indata.put("starttime", starttime);
indata.put("firsttime", firsttime);
indata.put("endtime", endtime);
indata.put("stype", stype);
indata.put("status", status);

		UserVipModel am=new UserVipModel();
		if(userid==0) {
			am.insert(indata);
		}else {
			am.update(indata, "userid="+userid);
		}
		return Help.success(0, "保存成功");
	}
	
	@RequestMapping("/admin/user_vip/status")
	public String Status(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="userid",defaultValue="0") int userid
	) {
		UserVipModel am=new UserVipModel();
		Map row=am.where("userid="+userid).selectRow(); 
		JSONObject json=(JSONObject) new JSONObject().toJSON(row);
		int status=0;
		if(json.getIntValue("status")==1) {
			status=2;
		}else {
			status=1;
		}
		Map indata=new HashMap();
		indata.put("status", status);
		am.update(indata,"userid="+userid);
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("status", status);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/admin/user_vip/recommend")
	public String recommend(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="userid",defaultValue="0") int userid
	) {
		UserVipModel am=new UserVipModel();
		Map row=am.where("userid="+userid).selectRow(); 
		JSONObject json=(JSONObject) new JSONObject().toJSON(row);
		int status=0;
		if(json.getIntValue("is_recommend")==1) {
			status=0;
		}else {
			status=1;
		}
		Map indata=new HashMap();
		indata.put("is_recommend", status);
		am.update(indata,"userid="+userid);
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("is_recommend", status);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/admin/user_vip/delete")
	public String delete(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="userid",defaultValue="0") int userid	
	) {
		UserVipModel am=new UserVipModel();
		Map indata= new HashMap();
		indata.put("status", 11);
		am.update(indata, "userid="+userid);
		return Help.success(0, "删除成功");
	}
	
	
	
}

