
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
import com.deitui.morelang.index.model.UserModel;
import com.model.Help;

@RestController
@CrossOrigin("*")
public class UserController {
	
	@RequestMapping("/admin/user/index")
	public String Index() {
		UserModel am=new UserModel();
		String where=" 1 ";
		
		List list=am.where(where).Dselect(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("list", list);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/admin/user/show")
	public String Show(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="userid",defaultValue="0") int userid
	) {
		UserModel am=new UserModel();
		Map data=am.where("userid="+userid).selectRow(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("data", data);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/admin/user/add")
	public String Add(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="userid",defaultValue="0") int userid
	) {
		UserModel am=new UserModel();
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
	
	@RequestMapping("/admin/user/save")
	public String Save(
		@RequestParam(value="token",defaultValue="") String token,
		@RequestParam(value="userid",defaultValue="0") int userid,
@RequestParam(value="username",defaultValue="") String username,
@RequestParam(value="telephone",defaultValue="") String telephone,
@RequestParam(value="nickname",defaultValue="") String nickname,
@RequestParam(value="money",defaultValue="0") Double money,
@RequestParam(value="gold",defaultValue="0") int gold,
@RequestParam(value="grade",defaultValue="0") int grade,
@RequestParam(value="lasttime",defaultValue="") String lasttime,
@RequestParam(value="user_type",defaultValue="0") int user_type,
@RequestParam(value="status",defaultValue="0") int status,
@RequestParam(value="user_head",defaultValue="") String user_head,
@RequestParam(value="follow_num",defaultValue="0") int follow_num,
@RequestParam(value="followed_num",defaultValue="0") int followed_num,
@RequestParam(value="gender",defaultValue="0") int gender,
@RequestParam(value="invite_userid",defaultValue="0") int invite_userid,
@RequestParam(value="birthday",defaultValue="") String birthday,
@RequestParam(value="description",defaultValue="") String description
	) {
		Map indata= new HashMap();
		indata.put("username", username);
indata.put("telephone", telephone);
indata.put("nickname", nickname);
indata.put("money", money);
indata.put("gold", gold);
indata.put("grade", grade);
indata.put("lasttime", lasttime);
indata.put("user_type", user_type);
indata.put("status", status);
indata.put("user_head", user_head);
indata.put("follow_num", follow_num);
indata.put("followed_num", followed_num);
indata.put("gender", gender);
indata.put("invite_userid", invite_userid);
indata.put("birthday", birthday);
indata.put("description", description);

		UserModel am=new UserModel();
		if(userid==0) {
			am.insert(indata);
		}else {
			am.update(indata, "userid="+userid);
		}
		return Help.success(0, "保存成功");
	}
	
	@RequestMapping("/admin/user/status")
	public String Status(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="userid",defaultValue="0") int userid
	) {
		UserModel am=new UserModel();
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
	
	@RequestMapping("/admin/user/recommend")
	public String recommend(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="userid",defaultValue="0") int userid
	) {
		UserModel am=new UserModel();
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
	
	@RequestMapping("/admin/user/delete")
	public String delete(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="userid",defaultValue="0") int userid	
	) {
		UserModel am=new UserModel();
		Map indata= new HashMap();
		indata.put("status", 11);
		am.update(indata, "userid="+userid);
		return Help.success(0, "删除成功");
	}
	
	
	
}

