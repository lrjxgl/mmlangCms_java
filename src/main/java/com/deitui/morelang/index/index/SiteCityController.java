
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
import com.deitui.morelang.index.model.SiteCityModel;
import com.model.Help;

@RestController
@CrossOrigin("*")
public class SiteCityController {
	
	@RequestMapping("/site_city/index")
	public String Index() {
		SiteCityModel am=new SiteCityModel();
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
	
	@RequestMapping("/site_city/show")
	public String Show(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="sc_id",defaultValue="0") int sc_id
	) {
		SiteCityModel am=new SiteCityModel();
		Map data=am.where("sc_id="+sc_id).selectRow(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("data", data);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/site_city/add")
	public String Add(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="sc_id",defaultValue="0") int sc_id
	) {
		SiteCityModel am=new SiteCityModel();
		Map data=new HashMap();
		if(sc_id!=0) {
			data=am.where("sc_id="+sc_id).selectRow();
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
	
	@RequestMapping("/site_city/save")
	public String Save(
		@RequestParam(value="token",defaultValue="") String token,
		@RequestParam(value="sc_id",defaultValue="0") int sc_id,
@RequestParam(value="title",defaultValue="") String title,
@RequestParam(value="cityid",defaultValue="0") int cityid,
@RequestParam(value="lat",defaultValue="0") Double lat,
@RequestParam(value="lng",defaultValue="0") Double lng,
@RequestParam(value="orderindex",defaultValue="0") int orderindex,
@RequestParam(value="status",defaultValue="0") int status,
@RequestParam(value="pid",defaultValue="0") int pid,
@RequestParam(value="siteid",defaultValue="0") int siteid
	) {
		Map indata= new HashMap();
		indata.put("title", title);
indata.put("cityid", cityid);
indata.put("lat", lat);
indata.put("lng", lng);
indata.put("orderindex", orderindex);
indata.put("status", status);
indata.put("pid", pid);
indata.put("siteid", siteid);

		SiteCityModel am=new SiteCityModel();
		if(sc_id==0) {
			am.insert(indata);
		}else {
			am.update(indata, "sc_id="+sc_id);
		}
		return Help.success(0, "保存成功");
	}
	
	@RequestMapping("/site_city/status")
	public String Status(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="sc_id",defaultValue="0") int sc_id
	) {
		SiteCityModel am=new SiteCityModel();
		Map row=am.where("sc_id="+sc_id).selectRow(); 
		JSONObject json=(JSONObject) new JSONObject().toJSON(row);
		int status=0;
		if(json.getIntValue("status")==1) {
			status=2;
		}else {
			status=1;
		}
		Map indata=new HashMap();
		indata.put("status", status);
		am.update(indata,"sc_id="+sc_id);
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("status", status);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/site_city/recommend")
	public String recommend(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="sc_id",defaultValue="0") int sc_id
	) {
		SiteCityModel am=new SiteCityModel();
		Map row=am.where("sc_id="+sc_id).selectRow(); 
		JSONObject json=(JSONObject) new JSONObject().toJSON(row);
		int status=0;
		if(json.getIntValue("is_recommend")==1) {
			status=0;
		}else {
			status=1;
		}
		Map indata=new HashMap();
		indata.put("is_recommend", status);
		am.update(indata,"sc_id="+sc_id);
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("is_recommend", status);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/site_city/delete")
	public String delete(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="sc_id",defaultValue="0") int sc_id	
	) {
		SiteCityModel am=new SiteCityModel();
		Map indata= new HashMap();
		indata.put("status", 11);
		am.update(indata, "sc_id="+sc_id);
		return Help.success(0, "删除成功");
	}
	
	
	
}

