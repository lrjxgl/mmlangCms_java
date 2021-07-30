
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
import com.deitui.morelang.index.model.ArticleModel;
import com.model.Help;

@RestController
@CrossOrigin("*")
public class ArticleController {
	
	@RequestMapping("/article/index")
	public String Index() {
		ArticleModel am=new ArticleModel();
		String where=" 1 ";
		
		List list=am.where(where).Dselect(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("list", list);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/article/show")
	public String Show(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		ArticleModel am=new ArticleModel();
		Map data=am.where("id="+id).selectRow(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("data", data);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/article/add")
	public String Add(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		ArticleModel am=new ArticleModel();
		Map data=new HashMap();
		if(id!=0) {
			data=am.where("id="+id).selectRow();
			data.put("trueimgurl", Help.image_site(data.get("imgurl")+""));
		}
		
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("data", data);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/article/save")
	public String Save(
		@RequestParam(value="token",defaultValue="") String token,
		@RequestParam(value="id",defaultValue="0") int id,
@RequestParam(value="title",defaultValue="") String title,
@RequestParam(value="catid",defaultValue="0") int catid,
@RequestParam(value="catid_top",defaultValue="0") int catid_top,
@RequestParam(value="catid_2nd",defaultValue="0") int catid_2nd,
@RequestParam(value="description",defaultValue="") String description,
@RequestParam(value="status",defaultValue="0") int status,
@RequestParam(value="imgurl",defaultValue="") String imgurl,
@RequestParam(value="tpl",defaultValue="") String tpl,
@RequestParam(value="author",defaultValue="") String author,
@RequestParam(value="price",defaultValue="0") Double price,
@RequestParam(value="market_price",defaultValue="0") Double market_price,
@RequestParam(value="total_num",defaultValue="0") int total_num,
@RequestParam(value="sold_num",defaultValue="0") int sold_num,
@RequestParam(value="grade",defaultValue="0") int grade,
@RequestParam(value="downurl",defaultValue="") String downurl,
@RequestParam(value="downsize",defaultValue="0") Double downsize,
@RequestParam(value="orderindex",defaultValue="0") int orderindex,
@RequestParam(value="videourl",defaultValue="") String videourl,
@RequestParam(value="tags",defaultValue="") String tags,
@RequestParam(value="imgsdata",defaultValue="") String imgsdata
	) {
		Map indata= new HashMap();
		indata.put("title", title);
indata.put("catid", catid);
indata.put("catid_top", catid_top);
indata.put("catid_2nd", catid_2nd);
indata.put("description", description);
indata.put("status", status);
indata.put("imgurl", imgurl);
indata.put("tpl", tpl);
indata.put("author", author);
indata.put("price", price);
indata.put("market_price", market_price);
indata.put("total_num", total_num);
indata.put("sold_num", sold_num);
indata.put("grade", grade);
indata.put("downurl", downurl);
indata.put("downsize", downsize);
indata.put("orderindex", orderindex);
indata.put("videourl", videourl);
indata.put("tags", tags);
indata.put("imgsdata", imgsdata);

		ArticleModel am=new ArticleModel();
		if(id==0) {
			am.insert(indata);
		}else {
			am.update(indata, "id="+id);
		}
		return Help.success(0, "保存成功");
	}
	
	@RequestMapping("/article/status")
	public String Status(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		ArticleModel am=new ArticleModel();
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
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("status", status);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/article/recommend")
	public String recommend(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		ArticleModel am=new ArticleModel();
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
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("is_recommend", status);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/article/delete")
	public String delete(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id	
	) {
		ArticleModel am=new ArticleModel();
		Map indata= new HashMap();
		indata.put("status", 11);
		am.update(indata, "id="+id);
		return Help.success(0, "删除成功");
	}
	
	
	
}

