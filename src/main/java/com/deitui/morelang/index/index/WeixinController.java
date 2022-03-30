
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
import com.deitui.morelang.index.model.WeixinModel;
import com.model.Help;

@RestController
@CrossOrigin("*")
public class WeixinController {
	
	@RequestMapping("/weixin/index")
	public String Index() {
		WeixinModel am=new WeixinModel();
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
	
	@RequestMapping("/weixin/show")
	public String Show(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		WeixinModel am=new WeixinModel();
		Map data=am.where("id="+id).selectRow(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("data", data);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/weixin/add")
	public String Add(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		WeixinModel am=new WeixinModel();
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
	
	@RequestMapping("/weixin/save")
	public String Save(
		@RequestParam(value="token",defaultValue="") String token,
		@RequestParam(value="id",defaultValue="0") int id,
 
@RequestParam(value="title",defaultValue="") String title,
@RequestParam(value="status",defaultValue="0") int status,
@RequestParam(value="domain",defaultValue="") String domain,
@RequestParam(value="catid",defaultValue="0") int catid,
@RequestParam(value="logo",defaultValue="") String logo,
@RequestParam(value="imgurl",defaultValue="") String imgurl,
@RequestParam(value="imgsdata",defaultValue="") String imgsdata,
@RequestParam(value="appid",defaultValue="") String appid,
@RequestParam(value="appkey",defaultValue="") String appkey,
@RequestParam(value="ysid",defaultValue="") String ysid,
@RequestParam(value="shopid",defaultValue="0") int shopid,
@RequestParam(value="wx_username",defaultValue="") String wx_username,
@RequestParam(value="wx_pwd",defaultValue="") String wx_pwd,
@RequestParam(value="enkey",defaultValue="") String enkey,
@RequestParam(value="mchid",defaultValue="") String mchid,
@RequestParam(value="mchkey",defaultValue="") String mchkey,
@RequestParam(value="sslcert_path",defaultValue="") String sslcert_path,
@RequestParam(value="sslkey_path",defaultValue="") String sslkey_path,
@RequestParam(value="openlogin",defaultValue="0") int openlogin
	) {
		Map indata= new HashMap();
		indata.put("token", token);
indata.put("title", title);
indata.put("status", status);
indata.put("domain", domain);
indata.put("catid", catid);
indata.put("logo", logo);
indata.put("imgurl", imgurl);
indata.put("imgsdata", imgsdata);
indata.put("appid", appid);
indata.put("appkey", appkey);
indata.put("ysid", ysid);
indata.put("shopid", shopid);
indata.put("wx_username", wx_username);
indata.put("wx_pwd", wx_pwd);
indata.put("enkey", enkey);
indata.put("mchid", mchid);
indata.put("mchkey", mchkey);
indata.put("sslcert_path", sslcert_path);
indata.put("sslkey_path", sslkey_path);
indata.put("openlogin", openlogin);

		WeixinModel am=new WeixinModel();
		if(id==0) {
			am.insert(indata);
		}else {
			am.update(indata, "id="+id);
		}
		return Help.success(0, "保存成功");
	}
	
	@RequestMapping("/weixin/status")
	public String Status(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		WeixinModel am=new WeixinModel();
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
	
	@RequestMapping("/weixin/recommend")
	public String recommend(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		WeixinModel am=new WeixinModel();
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
	
	@RequestMapping("/weixin/delete")
	public String delete(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id	
	) {
		WeixinModel am=new WeixinModel();
		Map indata= new HashMap();
		indata.put("status", 11);
		am.update(indata, "id="+id);
		return Help.success(0, "删除成功");
	}
	
	
	
}

