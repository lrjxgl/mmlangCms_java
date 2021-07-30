package com.deitui.morelang.forum.index;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.deitui.morelang.forum.model.ForumModel;
import com.deitui.morelang.index.model.UserModel;

@RestController
@CrossOrigin("*")
public class forumHomeController {
	@RequestMapping("/forum_home/index")
	public String Index(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="userid",defaultValue="0") int userid
	) {
		
		ForumModel forumModel=new ForumModel();
    	String where=" userid="+userid+" AND status in(0,1) ";
    	
    	List list=forumModel.where(where).order("id DESC").Dselect();
    	UserModel u=new UserModel();
    	Map user=u.get(userid,"userid,user_head,nickname,description,follow_num,followed_num");
		Map<String,Object> redata=new HashMap<String,Object>();
	    redata.put("error",0);
	    redata.put("message","succcess");
	    redata.put("list",list);
	    redata.put("user",user);
	    return JSON.toJSONString(redata);
	}
}
