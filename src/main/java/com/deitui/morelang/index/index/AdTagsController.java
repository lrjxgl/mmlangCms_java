
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
import com.deitui.morelang.index.model.AdTagsModel;
import com.model.Help;

@RestController
@CrossOrigin("*")
public class AdTagsController {
	
	@RequestMapping("/ad_tags/index")
	public String Index() {
		AdTagsModel am=new AdTagsModel();
		String where=" 1 ";
		
		List list=am.where(where).Dselect(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("list", list);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/ad_tags/show")
	public String Show(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="tag_id",defaultValue="0") int tag_id
	) {
		AdTagsModel am=new AdTagsModel();
		Map data=am.where("tag_id="+tag_id).selectRow(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("data", data);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/ad_tags/add")
	public String Add(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="tag_id",defaultValue="0") int tag_id
	) {
		AdTagsModel am=new AdTagsModel();
		Map data=new HashMap();
		if(tag_id!=0) {
			data=am.where("tag_id="+tag_id).selectRow();
			data.put("trueimgurl", Help.image_site(data.get("imgurl")+""));
		}
		
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("data", data);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/ad_tags/save")
	public String Save(
		@RequestParam(value="token",defaultValue="") String token,
		@RequestParam(value="tag_id",defaultValue="0") int tag_id,
@RequestParam(value="title",defaultValue="") String title,
@RequestParam(value="orderindex",defaultValue="0") int orderindex,
@RequestParam(value="pid",defaultValue="0") int pid,
@RequestParam(value="status",defaultValue="0") int status,
@RequestParam(value="m",defaultValue="") String m,
@RequestParam(value="a",defaultValue="") String a,
@RequestParam(value="width",defaultValue="0") int width,
@RequestParam(value="height",defaultValue="0") int height,
@RequestParam(value="tagno",defaultValue="") String tagno
	) {
		Map indata= new HashMap();
		indata.put("title", title);
indata.put("orderindex", orderindex);
indata.put("pid", pid);
indata.put("status", status);
indata.put("m", m);
indata.put("a", a);
indata.put("width", width);
indata.put("height", height);
indata.put("tagno", tagno);

		AdTagsModel am=new AdTagsModel();
		if(tag_id==0) {
			am.insert(indata);
		}else {
			am.update(indata, "tag_id="+tag_id);
		}
		return Help.success(0, "保存成功");
	}
	
	@RequestMapping("/ad_tags/status")
	public String Status(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="tag_id",defaultValue="0") int tag_id
	) {
		AdTagsModel am=new AdTagsModel();
		Map row=am.where("tag_id="+tag_id).selectRow(); 
		JSONObject json=(JSONObject) new JSONObject().toJSON(row);
		int status=0;
		if(json.getIntValue("status")==1) {
			status=2;
		}else {
			status=1;
		}
		Map indata=new HashMap();
		indata.put("status", status);
		am.update(indata,"tag_id="+tag_id);
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("status", status);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/ad_tags/recommend")
	public String recommend(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="tag_id",defaultValue="0") int tag_id
	) {
		AdTagsModel am=new AdTagsModel();
		Map row=am.where("tag_id="+tag_id).selectRow(); 
		JSONObject json=(JSONObject) new JSONObject().toJSON(row);
		int status=0;
		if(json.getIntValue("is_recommend")==1) {
			status=0;
		}else {
			status=1;
		}
		Map indata=new HashMap();
		indata.put("is_recommend", status);
		am.update(indata,"tag_id="+tag_id);
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("is_recommend", status);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/ad_tags/delete")
	public String delete(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="tag_id",defaultValue="0") int tag_id	
	) {
		AdTagsModel am=new AdTagsModel();
		Map indata= new HashMap();
		indata.put("status", 11);
		am.update(indata, "tag_id="+tag_id);
		return Help.success(0, "删除成功");
	}
	
	
	
}

