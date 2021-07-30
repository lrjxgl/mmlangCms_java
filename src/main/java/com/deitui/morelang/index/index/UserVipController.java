
package com.deitui.morelang.index.index;

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
	
	@RequestMapping("/user_vip/index")
	public String Index() {
		UserVipModel am=new UserVipModel();
		String where=" 1 ";
		
		List list=am.where(where).Dselect(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("list", list);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/user_vip/show")
	public String Show(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="userid",defaultValue="0") int userid
	) {
		UserVipModel am=new UserVipModel();
		Map data=am.where("userid="+userid).selectRow(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("data", data);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/user_vip/add")
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
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("data", data);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/user_vip/save")
	public String Save(
		@RequestParam(value="token",defaultValue="") String token,
		@RequestParam(value="userid",defaultValue="0") int userid,
@RequestParam(value="starttime",defaultValue="0") int starttime,
@RequestParam(value="firsttime",defaultValue="0") int firsttime,
@RequestParam(value="endtime",defaultValue="0") int endtime,
@RequestParam(value="stype",defaultValue="0") int stype
	) {
		Map indata= new HashMap();
		indata.put("starttime", starttime);
indata.put("firsttime", firsttime);
indata.put("endtime", endtime);
indata.put("stype", stype);

		UserVipModel am=new UserVipModel();
		if(userid==0) {
			am.insert(indata);
		}else {
			am.update(indata, "userid="+userid);
		}
		return Help.success(0, "保存成功");
	}
	
	@RequestMapping("/user_vip/status")
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
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("status", status);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/user_vip/recommend")
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
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("is_recommend", status);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/user_vip/delete")
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

