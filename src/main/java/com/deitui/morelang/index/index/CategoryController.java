
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
import com.deitui.morelang.index.model.CategoryModel;
import com.model.Help;

@RestController
@CrossOrigin("*")
public class CategoryController {
	
	@RequestMapping("/category/index")
	public String Index() {
		CategoryModel am=new CategoryModel();
		String where=" 1 ";
		
		List list=am.where(where).Dselect(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("list", list);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/category/show")
	public String Show(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="catid",defaultValue="0") int catid
	) {
		CategoryModel am=new CategoryModel();
		Map data=am.where("catid="+catid).selectRow(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("data", data);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/category/add")
	public String Add(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="catid",defaultValue="0") int catid
	) {
		CategoryModel am=new CategoryModel();
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
	
	@RequestMapping("/category/save")
	public String Save(
		@RequestParam(value="token",defaultValue="") String token,
		@RequestParam(value="catid",defaultValue="0") int catid,
@RequestParam(value="tablename",defaultValue="") String tablename,
@RequestParam(value="pid",defaultValue="0") int pid,
@RequestParam(value="cname",defaultValue="") String cname,
@RequestParam(value="orderindex",defaultValue="0") int orderindex,
@RequestParam(value="type_id",defaultValue="0") int type_id,
@RequestParam(value="cat_tpl",defaultValue="") String cat_tpl,
@RequestParam(value="list_tpl",defaultValue="") String list_tpl,
@RequestParam(value="show_tpl",defaultValue="") String show_tpl,
@RequestParam(value="title",defaultValue="") String title,
@RequestParam(value="keywords",defaultValue="") String keywords,
@RequestParam(value="description",defaultValue="") String description,
@RequestParam(value="status",defaultValue="0") int status,
@RequestParam(value="level",defaultValue="0") int level,
@RequestParam(value="topic_num",defaultValue="0") int topic_num,
@RequestParam(value="last_post",defaultValue="") String last_post,
@RequestParam(value="logo",defaultValue="") String logo
	) {
		Map indata= new HashMap();
		indata.put("tablename", tablename);
indata.put("pid", pid);
indata.put("cname", cname);
indata.put("orderindex", orderindex);
indata.put("type_id", type_id);
indata.put("cat_tpl", cat_tpl);
indata.put("list_tpl", list_tpl);
indata.put("show_tpl", show_tpl);
indata.put("title", title);
indata.put("keywords", keywords);
indata.put("description", description);
indata.put("status", status);
indata.put("level", level);
indata.put("topic_num", topic_num);
indata.put("last_post", last_post);
indata.put("logo", logo);

		CategoryModel am=new CategoryModel();
		if(catid==0) {
			am.insert(indata);
		}else {
			am.update(indata, "catid="+catid);
		}
		return Help.success(0, "保存成功");
	}
	
	@RequestMapping("/category/status")
	public String Status(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="catid",defaultValue="0") int catid
	) {
		CategoryModel am=new CategoryModel();
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
	
	@RequestMapping("/category/recommend")
	public String recommend(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="catid",defaultValue="0") int catid
	) {
		CategoryModel am=new CategoryModel();
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
	
	@RequestMapping("/category/delete")
	public String delete(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="catid",defaultValue="0") int catid	
	) {
		CategoryModel am=new CategoryModel();
		Map indata= new HashMap();
		indata.put("status", 11);
		am.update(indata, "catid="+catid);
		return Help.success(0, "删除成功");
	}
	
	
	
}

