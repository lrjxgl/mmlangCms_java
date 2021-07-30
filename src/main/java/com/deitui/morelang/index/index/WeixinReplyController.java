
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
import com.deitui.morelang.index.model.WeixinReplyModel;
import com.model.Help;

@RestController
@CrossOrigin("*")
public class WeixinReplyController {
	
	@RequestMapping("/weixin_reply/index")
	public String Index() {
		WeixinReplyModel am=new WeixinReplyModel();
		String where=" 1 ";
		
		List list=am.where(where).Dselect(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("list", list);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/weixin_reply/show")
	public String Show(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		WeixinReplyModel am=new WeixinReplyModel();
		Map data=am.where("id="+id).selectRow(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("data", data);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/weixin_reply/add")
	public String Add(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		WeixinReplyModel am=new WeixinReplyModel();
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
	
	@RequestMapping("/weixin_reply/save")
	public String Save(
		@RequestParam(value="token",defaultValue="") String token,
		@RequestParam(value="id",defaultValue="0") int id,
@RequestParam(value="status",defaultValue="0") int status,
@RequestParam(value="openid",defaultValue="") String openid,
@RequestParam(value="msgtype",defaultValue="") String msgtype,
@RequestParam(value="content",defaultValue="") String content,
@RequestParam(value="msgid",defaultValue="") String msgid,
@RequestParam(value="picurl",defaultValue="") String picurl,
@RequestParam(value="mediaid",defaultValue="") String mediaid,
@RequestParam(value="thumbmediaid",defaultValue="") String thumbmediaid,
@RequestParam(value="format",defaultValue="") String format,
@RequestParam(value="location_x",defaultValue="") String location_x,
@RequestParam(value="location_y",defaultValue="") String location_y,
@RequestParam(value="scale",defaultValue="0") int scale,
@RequestParam(value="label",defaultValue="") String label,
@RequestParam(value="title",defaultValue="") String title,
@RequestParam(value="description",defaultValue="") String description,
@RequestParam(value="url",defaultValue="") String url,
@RequestParam(value="wid",defaultValue="0") int wid,
@RequestParam(value="shopid",defaultValue="0") int shopid,
@RequestParam(value="fromusername",defaultValue="") String fromusername,
@RequestParam(value="tousername",defaultValue="") String tousername
	) {
		Map indata= new HashMap();
		indata.put("status", status);
indata.put("openid", openid);
indata.put("msgtype", msgtype);
indata.put("content", content);
indata.put("msgid", msgid);
indata.put("picurl", picurl);
indata.put("mediaid", mediaid);
indata.put("thumbmediaid", thumbmediaid);
indata.put("format", format);
indata.put("location_x", location_x);
indata.put("location_y", location_y);
indata.put("scale", scale);
indata.put("label", label);
indata.put("title", title);
indata.put("description", description);
indata.put("url", url);
indata.put("wid", wid);
indata.put("shopid", shopid);
indata.put("fromusername", fromusername);
indata.put("tousername", tousername);

		WeixinReplyModel am=new WeixinReplyModel();
		if(id==0) {
			am.insert(indata);
		}else {
			am.update(indata, "id="+id);
		}
		return Help.success(0, "保存成功");
	}
	
	@RequestMapping("/weixin_reply/status")
	public String Status(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		WeixinReplyModel am=new WeixinReplyModel();
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
	
	@RequestMapping("/weixin_reply/recommend")
	public String recommend(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		WeixinReplyModel am=new WeixinReplyModel();
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
	
	@RequestMapping("/weixin_reply/delete")
	public String delete(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id	
	) {
		WeixinReplyModel am=new WeixinReplyModel();
		Map indata= new HashMap();
		indata.put("status", 11);
		am.update(indata, "id="+id);
		return Help.success(0, "删除成功");
	}
	
	
	
}

