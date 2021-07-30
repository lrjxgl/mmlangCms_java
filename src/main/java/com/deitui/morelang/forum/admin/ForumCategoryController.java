
package com.deitui.morelang.forum.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.deitui.morelang.forum.model.ForumCategoryModel;
import com.model.Help;

@RestController
@CrossOrigin("*")
public class ForumCategoryController {
	
	@RequestMapping("/admin/forum_category/index")
	public String Index() {
		ForumCategoryModel am=new ForumCategoryModel();
		String where=" 1 ";
		
		List list=am.where(where).Dselect(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("list", list);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/admin/forum_category/show")
	public String Show(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="catid",defaultValue="0") int catid
	) {
		ForumCategoryModel am=new ForumCategoryModel();
		Map data=am.where("catid="+catid).selectRow(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("data", data);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/admin/forum_category/add")
	public String Add(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="catid",defaultValue="0") int catid
	) {
		ForumCategoryModel am=new ForumCategoryModel();
		Map data=new HashMap();
		if(catid!=0) {
			data=am.where("catid="+catid).selectRow();
			data.put("trueimgurl", Help.image_site(data.get("imgurl")+""));
		}
		
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("data", data);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/admin/forum_category/save")
	public String Save(
		@RequestParam(value="token",defaultValue="") String token,
		@RequestParam(value="catid",defaultValue="0") int catid,
@RequestParam(value="gid",defaultValue="0") int gid,
@RequestParam(value="title",defaultValue="") String title,
@RequestParam(value="description",defaultValue="") String description,
@RequestParam(value="orderindex",defaultValue="0") int orderindex,
@RequestParam(value="status",defaultValue="0") int status,
@RequestParam(value="imgurl",defaultValue="") String imgurl
	) {
		Map indata= new HashMap();
		indata.put("gid", gid);
indata.put("title", title);
indata.put("description", description);
indata.put("orderindex", orderindex);
indata.put("status", status);
indata.put("imgurl", imgurl);

		ForumCategoryModel am=new ForumCategoryModel();
		if(catid==0) {
			am.insert(indata);
		}else {
			am.update(indata, "catid="+catid);
		}
		return Help.success(0, "保存成功");
	}
	
	@RequestMapping("/admin/forum_category/status")
	public String Status(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="catid",defaultValue="0") int catid
	) {
		ForumCategoryModel am=new ForumCategoryModel();
		Map row=am.where("catid="+catid).selectRow(); 
		JSONObject json=(JSONObject) new JSONObject().toJSON(row);
		int status=0;
		if(json.getIntValue("status")==1) {
			status=2;
		}else {
			status=1;
		}
		Map indata=new HashMap();
		indata.put("status", status);
		am.update(indata,"catid="+catid);
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("status", status);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/admin/forum_category/recommend")
	public String recommend(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="catid",defaultValue="0") int catid
	) {
		ForumCategoryModel am=new ForumCategoryModel();
		Map row=am.where("catid="+catid).selectRow(); 
		JSONObject json=(JSONObject) new JSONObject().toJSON(row);
		int status=0;
		if(json.getIntValue("is_recommend")==1) {
			status=0;
		}else {
			status=1;
		}
		Map indata=new HashMap();
		indata.put("is_recommend", status);
		am.update(indata,"catid="+catid);
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("is_recommend", status);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/admin/forum_category/delete")
	public String delete(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="catid",defaultValue="0") int catid	
	) {
		ForumCategoryModel am=new ForumCategoryModel();
		Map indata= new HashMap();
		indata.put("status", 11);
		am.update(indata, "catid="+catid);
		return Help.success(0, "删除成功");
	}
	
	
	
}

