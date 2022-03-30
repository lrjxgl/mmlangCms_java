package com.deitui.morelang.forum.index;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.deitui.morelang.forum.model.ForumCommentModel;
import com.deitui.morelang.forum.model.ForumModel;
import com.model.Login;
import com.model.Model;
@RestController
@CrossOrigin("*")
public class ForumCommentController {
	@RequestMapping("/forum_comment/index")
	public String Index(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="objectid",defaultValue="") int objectid
	) {
		ForumCommentModel fc=new ForumCommentModel();
		List list=fc.where("objectid="+objectid+" AND status in(0,1) ").Dselect();
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("list", list);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/forum_comment/save")
	public String Save(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="content",defaultValue="") String content,
			@RequestParam(value="objectid",defaultValue="") int objectid
	) {
		int userid=Login.isLogin(token);
		if(userid==0) {
			return Login.unLogin();
		}
		ForumCommentModel fc=new ForumCommentModel();
		ForumModel f=new ForumModel();
		Map forum=f.where("id="+objectid).selectRow();
		Map<String,Object> indata=new HashMap<String,Object>();
		indata.put("content", content);
		indata.put("userid", userid);
		indata.put("objectid", objectid);
		indata.put("gid", forum.get("gid")+"");
		fc.insert(indata);
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
      
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/forum_comment/my")
	public String My(
			@RequestParam(value="token",defaultValue="") String token
	) {
		int userid=Login.isLogin(token);
		if(userid==0) {
			return Login.unLogin();
		}
		ForumCommentModel fc=new ForumCommentModel();
		List list=fc.where("userid="+userid+" AND status in(0,1) ").Dselect();
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("list", list);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	@RequestMapping("/forum_comment/delete")
	public String Delete(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		int userid=Login.isLogin(token);
		if(userid==0) {
			return Login.unLogin();
		}
		ForumCommentModel fc=new ForumCommentModel();
		Map indata=new HashMap();
		indata.put("status", 11);
		fc.update(indata, "id="+id);
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
      
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
}
