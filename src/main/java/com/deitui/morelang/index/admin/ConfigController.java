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
import com.deitui.morelang.index.model.ConfigModel;
import com.model.Help;

@RestController
@CrossOrigin("*")
public class ConfigController {
	
	@RequestMapping("/admin/config/index")
	public String Index() {
		ConfigModel am=new ConfigModel();
		String where=" 1 ";
		Map data=am.where(where).selectRow(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("data", data);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	 
	
	@RequestMapping("/admin/config/add")
	public String Add(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		ConfigModel am=new ConfigModel();
		String where=" 1 ";
		Map data=am.where(where).selectRow(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("data", data);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/admin/config/save")
	public String Save(
		@RequestParam(value="token",defaultValue="") String token,
		@RequestParam(value="id",defaultValue="0") int id,
@RequestParam(value="siteid",defaultValue="0") int siteid,
@RequestParam(value="phone_on",defaultValue="0") int phone_on,
@RequestParam(value="phone_reg",defaultValue="0") int phone_reg,
@RequestParam(value="phone_user",defaultValue="") String phone_user,
@RequestParam(value="phone_pwd",defaultValue="") String phone_pwd,
@RequestParam(value="phone_num",defaultValue="") String phone_num,
@RequestParam(value="smtphost",defaultValue="") String smtphost,
@RequestParam(value="smtpport",defaultValue="0") int smtpport,
@RequestParam(value="smtpemail",defaultValue="") String smtpemail,
@RequestParam(value="smtpuser",defaultValue="") String smtpuser,
@RequestParam(value="smtppwd",defaultValue="") String smtppwd,
@RequestParam(value="water_on",defaultValue="0") int water_on,
@RequestParam(value="water_type",defaultValue="0") int water_type,
@RequestParam(value="water_pos",defaultValue="0") int water_pos,
@RequestParam(value="water_str",defaultValue="") String water_str,
@RequestParam(value="water_img",defaultValue="") String water_img,
@RequestParam(value="water_size",defaultValue="0") int water_size,
@RequestParam(value="rewrite_on",defaultValue="0") int rewrite_on,
@RequestParam(value="spread_on",defaultValue="0") int spread_on,
@RequestParam(value="spread_discount",defaultValue="0") int spread_discount,
@RequestParam(value="spread_money",defaultValue="0") int spread_money,
@RequestParam(value="spread_type",defaultValue="0") int spread_type,
@RequestParam(value="grade_on",defaultValue="0") int grade_on,
@RequestParam(value="hotsearch",defaultValue="") String hotsearch,
@RequestParam(value="kf_qq",defaultValue="") String kf_qq,
@RequestParam(value="kf_ww",defaultValue="") String kf_ww,
@RequestParam(value="sms_qianming",defaultValue="") String sms_qianming,
@RequestParam(value="sms_type",defaultValue="") String sms_type,
@RequestParam(value="s_bank_name",defaultValue="") String s_bank_name,
@RequestParam(value="s_bank_user",defaultValue="") String s_bank_user,
@RequestParam(value="s_bank_num",defaultValue="") String s_bank_num
	) {
		Map indata= new HashMap();
		indata.put("siteid", siteid);
indata.put("phone_on", phone_on);
indata.put("phone_reg", phone_reg);
indata.put("phone_user", phone_user);
indata.put("phone_pwd", phone_pwd);
indata.put("phone_num", phone_num);
indata.put("smtphost", smtphost);
indata.put("smtpport", smtpport);
indata.put("smtpemail", smtpemail);
indata.put("smtpuser", smtpuser);
indata.put("smtppwd", smtppwd);
indata.put("water_on", water_on);
indata.put("water_type", water_type);
indata.put("water_pos", water_pos);
indata.put("water_str", water_str);
indata.put("water_img", water_img);
indata.put("water_size", water_size);
indata.put("rewrite_on", rewrite_on);
indata.put("spread_on", spread_on);
indata.put("spread_discount", spread_discount);
indata.put("spread_money", spread_money);
indata.put("spread_type", spread_type);
indata.put("grade_on", grade_on);
indata.put("hotsearch", hotsearch);
indata.put("kf_qq", kf_qq);
indata.put("kf_ww", kf_ww);
indata.put("sms_qianming", sms_qianming);
indata.put("sms_type", sms_type);
indata.put("s_bank_name", s_bank_name);
indata.put("s_bank_user", s_bank_user);
indata.put("s_bank_num", s_bank_num);

		ConfigModel am=new ConfigModel();
		if(id==0) {
			am.insert(indata);
		}else {
			am.update(indata, "id="+id);
		}
		return Help.success(0, "保存成功");
	}
	
	@RequestMapping("/admin/config/status")
	public String Status(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		ConfigModel am=new ConfigModel();
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
	
	@RequestMapping("/admin/config/recommend")
	public String recommend(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		ConfigModel am=new ConfigModel();
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
	
	@RequestMapping("/admin/config/delete")
	public String delete(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id	
	) {
		ConfigModel am=new ConfigModel();
		Map indata= new HashMap();
		indata.put("status", 11);
		am.update(indata, "id="+id);
		return Help.success(0, "删除成功");
	}
	
	
	
}

