
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
import com.deitui.morelang.index.model.SiteModel;
import com.model.Help;

@RestController
@CrossOrigin("*")
public class SiteController {
	
	@RequestMapping("/site/index")
	public String Index() {
		SiteModel am=new SiteModel();
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
	
	@RequestMapping("/site/show")
	public String Show(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="siteid",defaultValue="0") int siteid
	) {
		SiteModel am=new SiteModel();
		Map data=am.where("siteid="+siteid).selectRow(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("data", data);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/site/add")
	public String Add(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="siteid",defaultValue="0") int siteid
	) {
		SiteModel am=new SiteModel();
		Map data=new HashMap();
		if(siteid!=0) {
			data=am.where("siteid="+siteid).selectRow();
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
	
	@RequestMapping("/site/save")
	public String Save(
		@RequestParam(value="token",defaultValue="") String token,
		@RequestParam(value="siteid",defaultValue="0") int siteid,
@RequestParam(value="sitename",defaultValue="") String sitename,
@RequestParam(value="domain",defaultValue="") String domain,
@RequestParam(value="title",defaultValue="") String title,
@RequestParam(value="keywords",defaultValue="") String keywords,
@RequestParam(value="description",defaultValue="") String description,
@RequestParam(value="close_why",defaultValue="") String close_why,
@RequestParam(value="logo",defaultValue="") String logo,
@RequestParam(value="icp",defaultValue="") String icp,
@RequestParam(value="status",defaultValue="0") int status,
@RequestParam(value="template",defaultValue="") String template,
@RequestParam(value="statjs",defaultValue="") String statjs,
@RequestParam(value="index_template",defaultValue="") String index_template,
@RequestParam(value="wap_template",defaultValue="") String wap_template,
@RequestParam(value="wapbg",defaultValue="") String wapbg
	) {
		Map indata= new HashMap();
		indata.put("sitename", sitename);
indata.put("domain", domain);
indata.put("title", title);
indata.put("keywords", keywords);
indata.put("description", description);
indata.put("close_why", close_why);
indata.put("logo", logo);
indata.put("icp", icp);
indata.put("status", status);
indata.put("template", template);
indata.put("statjs", statjs);
indata.put("index_template", index_template);
indata.put("wap_template", wap_template);
indata.put("wapbg", wapbg);

		SiteModel am=new SiteModel();
		if(siteid==0) {
			am.insert(indata);
		}else {
			am.update(indata, "siteid="+siteid);
		}
		return Help.success(0, "保存成功");
	}
	
	@RequestMapping("/site/status")
	public String Status(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="siteid",defaultValue="0") int siteid
	) {
		SiteModel am=new SiteModel();
		Map row=am.where("siteid="+siteid).selectRow(); 
		JSONObject json=(JSONObject) new JSONObject().toJSON(row);
		int status=0;
		if(json.getIntValue("status")==1) {
			status=2;
		}else {
			status=1;
		}
		Map indata=new HashMap();
		indata.put("status", status);
		am.update(indata,"siteid="+siteid);
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("status", status);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/site/recommend")
	public String recommend(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="siteid",defaultValue="0") int siteid
	) {
		SiteModel am=new SiteModel();
		Map row=am.where("siteid="+siteid).selectRow(); 
		JSONObject json=(JSONObject) new JSONObject().toJSON(row);
		int status=0;
		if(json.getIntValue("is_recommend")==1) {
			status=0;
		}else {
			status=1;
		}
		Map indata=new HashMap();
		indata.put("is_recommend", status);
		am.update(indata,"siteid="+siteid);
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("is_recommend", status);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/site/delete")
	public String delete(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="siteid",defaultValue="0") int siteid	
	) {
		SiteModel am=new SiteModel();
		Map indata= new HashMap();
		indata.put("status", 11);
		am.update(indata, "siteid="+siteid);
		return Help.success(0, "删除成功");
	}
	
	
	
}

