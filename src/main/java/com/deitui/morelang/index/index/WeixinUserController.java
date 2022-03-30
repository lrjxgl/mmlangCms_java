
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
import com.deitui.morelang.index.model.WeixinUserModel;
import com.model.Help;

@RestController
@CrossOrigin("*")
public class WeixinUserController {
	
	@RequestMapping("/weixin_user/index")
	public String Index() {
		WeixinUserModel am=new WeixinUserModel();
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
	
	@RequestMapping("/weixin_user/show")
	public String Show(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		WeixinUserModel am=new WeixinUserModel();
		Map data=am.where("id="+id).selectRow(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("data", data);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/weixin_user/add")
	public String Add(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		WeixinUserModel am=new WeixinUserModel();
		Map data=new HashMap();
		if(id!=0) {
			data=am.where("id="+id).selectRow();
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
	
	@RequestMapping("/weixin_user/save")
	public String Save(
		@RequestParam(value="token",defaultValue="") String token,
		@RequestParam(value="id",defaultValue="0") int id,
@RequestParam(value="openid",defaultValue="") String openid,
@RequestParam(value="add_time",defaultValue="0") int add_time,
@RequestParam(value="last_time",defaultValue="0") int last_time,
@RequestParam(value="del_time",defaultValue="0") int del_time,
@RequestParam(value="status",defaultValue="0") int status,
@RequestParam(value="nickname",defaultValue="") String nickname,
@RequestParam(value="sex",defaultValue="0") int sex,
@RequestParam(value="city",defaultValue="") String city,
@RequestParam(value="country",defaultValue="") String country,
@RequestParam(value="province",defaultValue="") String province,
@RequestParam(value="user_head",defaultValue="") String user_head,
@RequestParam(value="update_time",defaultValue="0") int update_time,
@RequestParam(value="reply_num",defaultValue="0") int reply_num,
@RequestParam(value="wid",defaultValue="0") int wid,
@RequestParam(value="shopid",defaultValue="0") int shopid
	) {
		Map indata= new HashMap();
		indata.put("openid", openid);
indata.put("add_time", add_time);
indata.put("last_time", last_time);
indata.put("del_time", del_time);
indata.put("status", status);
indata.put("nickname", nickname);
indata.put("sex", sex);
indata.put("city", city);
indata.put("country", country);
indata.put("province", province);
indata.put("user_head", user_head);
indata.put("update_time", update_time);
indata.put("reply_num", reply_num);
indata.put("wid", wid);
indata.put("shopid", shopid);

		WeixinUserModel am=new WeixinUserModel();
		if(id==0) {
			am.insert(indata);
		}else {
			am.update(indata, "id="+id);
		}
		return Help.success(0, "保存成功");
	}
	
	@RequestMapping("/weixin_user/status")
	public String Status(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		WeixinUserModel am=new WeixinUserModel();
		Map row=am.where("id="+id).selectRow(); 
		JSONObject json=(JSONObject) new JSONObject().toJSON(row);
		int status=0;
		if(json.getIntValue("status")==1) {
			status=2;
		}else {
			status=1;
		}
		Map indata=new HashMap();
		indata.put("status", status);
		am.update(indata,"id="+id);
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("status", status);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/weixin_user/recommend")
	public String recommend(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		WeixinUserModel am=new WeixinUserModel();
		Map row=am.where("id="+id).selectRow(); 
		JSONObject json=(JSONObject) new JSONObject().toJSON(row);
		int status=0;
		if(json.getIntValue("is_recommend")==1) {
			status=0;
		}else {
			status=1;
		}
		Map indata=new HashMap();
		indata.put("is_recommend", status);
		am.update(indata,"id="+id);
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("is_recommend", status);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/weixin_user/delete")
	public String delete(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id	
	) {
		WeixinUserModel am=new WeixinUserModel();
		Map indata= new HashMap();
		indata.put("status", 11);
		am.update(indata, "id="+id);
		return Help.success(0, "删除成功");
	}
	
	
	
}

