
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
import com.deitui.morelang.index.model.UserAuthModel;
import com.model.Help;

@RestController
@CrossOrigin("*")
public class UserAuthController {
	
	@RequestMapping("/admin/user_auth/index")
	public String Index() {
		UserAuthModel am=new UserAuthModel();
		String where=" 1 ";
		
		List list=am.where(where).Dselect(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("list", list);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/admin/user_auth/show")
	public String Show(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="userid",defaultValue="0") int userid
	) {
		UserAuthModel am=new UserAuthModel();
		Map data=am.where("userid="+userid).selectRow(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("data", data);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/admin/user_auth/add")
	public String Add(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="userid",defaultValue="0") int userid
	) {
		UserAuthModel am=new UserAuthModel();
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
	
	@RequestMapping("/admin/user_auth/save")
	public String Save(
		@RequestParam(value="token",defaultValue="") String token,
		@RequestParam(value="userid",defaultValue="0") int userid,
@RequestParam(value="truename",defaultValue="") String truename,
@RequestParam(value="user_card",defaultValue="") String user_card,
@RequestParam(value="true_user_head",defaultValue="") String true_user_head,
@RequestParam(value="income",defaultValue="0") int income,
@RequestParam(value="lasttime",defaultValue="0") int lasttime,
@RequestParam(value="telephone",defaultValue="") String telephone,
@RequestParam(value="status",defaultValue="0") int status,
@RequestParam(value="content",defaultValue="") String content,
@RequestParam(value="info",defaultValue="") String info,
@RequestParam(value="sky_info",defaultValue="") String sky_info
	) {
		Map indata= new HashMap();
		indata.put("truename", truename);
indata.put("user_card", user_card);
indata.put("true_user_head", true_user_head);
indata.put("income", income);
indata.put("lasttime", lasttime);
indata.put("telephone", telephone);
indata.put("status", status);
indata.put("content", content);
indata.put("info", info);
indata.put("sky_info", sky_info);

		UserAuthModel am=new UserAuthModel();
		if(userid==0) {
			am.insert(indata);
		}else {
			am.update(indata, "userid="+userid);
		}
		return Help.success(0, "保存成功");
	}
	
	@RequestMapping("/admin/user_auth/status")
	public String Status(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="userid",defaultValue="0") int userid
	) {
		UserAuthModel am=new UserAuthModel();
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
	
	@RequestMapping("/admin/user_auth/recommend")
	public String recommend(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="userid",defaultValue="0") int userid
	) {
		UserAuthModel am=new UserAuthModel();
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
	
	@RequestMapping("/admin/user_auth/delete")
	public String delete(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="userid",defaultValue="0") int userid	
	) {
		UserAuthModel am=new UserAuthModel();
		Map indata= new HashMap();
		indata.put("status", 11);
		am.update(indata, "userid="+userid);
		return Help.success(0, "删除成功");
	}
	
	
	
}

