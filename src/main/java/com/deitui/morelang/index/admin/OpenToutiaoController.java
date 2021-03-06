
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
import com.deitui.morelang.index.model.OpenToutiaoModel;
import com.model.Help;

@RestController
@CrossOrigin("*")
public class OpenToutiaoController {
	
	@RequestMapping("/admin/open_toutiao/index")
	public String Index() {
		OpenToutiaoModel am=new OpenToutiaoModel();
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
	
	@RequestMapping("/admin/open_toutiao/show")
	public String Show(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		OpenToutiaoModel am=new OpenToutiaoModel();
		Map data=am.where("id="+id).selectRow(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("data", data);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/admin/open_toutiao/add")
	public String Add(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		OpenToutiaoModel am=new OpenToutiaoModel();
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
	
	@RequestMapping("/admin/open_toutiao/save")
	public String Save(
		@RequestParam(value="token",defaultValue="") String token,
		@RequestParam(value="id",defaultValue="0") int id,
@RequestParam(value="title",defaultValue="") String title,
@RequestParam(value="appid",defaultValue="") String appid,
@RequestParam(value="appkey",defaultValue="") String appkey,
@RequestParam(value="status",defaultValue="0") int status,
@RequestParam(value="merchant_private_key",defaultValue="") String merchant_private_key,
@RequestParam(value="alipay_public_key",defaultValue="") String alipay_public_key,
@RequestParam(value="notify_url",defaultValue="") String notify_url,
@RequestParam(value="return_url",defaultValue="") String return_url,
@RequestParam(value="openlogin",defaultValue="0") int openlogin
	) {
		Map indata= new HashMap();
		indata.put("title", title);
indata.put("appid", appid);
indata.put("appkey", appkey);
indata.put("status", status);
indata.put("merchant_private_key", merchant_private_key);
indata.put("alipay_public_key", alipay_public_key);
indata.put("notify_url", notify_url);
indata.put("return_url", return_url);
indata.put("openlogin", openlogin);

		OpenToutiaoModel am=new OpenToutiaoModel();
		if(id==0) {
			am.insert(indata);
		}else {
			am.update(indata, "id="+id);
		}
		return Help.success(0, "????????????");
	}
	
	@RequestMapping("/admin/open_toutiao/status")
	public String Status(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		OpenToutiaoModel am=new OpenToutiaoModel();
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
	
	@RequestMapping("/admin/open_toutiao/recommend")
	public String recommend(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		OpenToutiaoModel am=new OpenToutiaoModel();
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
	
	@RequestMapping("/admin/open_toutiao/delete")
	public String delete(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id	
	) {
		OpenToutiaoModel am=new OpenToutiaoModel();
		Map indata= new HashMap();
		indata.put("status", 11);
		am.update(indata, "id="+id);
		return Help.success(0, "????????????");
	}
	
	
	
}

