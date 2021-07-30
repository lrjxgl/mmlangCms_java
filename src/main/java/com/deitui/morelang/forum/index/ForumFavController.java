package com.deitui.morelang.forum.index;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.deitui.morelang.forum.model.ForumFavModel;
import com.model.Help;
import com.model.Login;

@RestController
@CrossOrigin("*")
public class ForumFavController {
	@RequestMapping("/forum_fav/index")
	public String Index(
			@RequestParam(value="token",defaultValue="") String token
	) {
		int userid=Login.isLogin(token);
		if(userid==0) {
			return Help.error(1000, "请先登录");
		}
		ForumFavModel fv=new ForumFavModel();
		List list=fv.where("userid="+userid+" AND tablename='mod_forum' ").Dselect();
		Map<String,Object> redata=new HashMap<String,Object>();
		redata.put("error", 0);
		redata.put("message", "success");
		redata.put("list", list); 
		return JSON.toJSONString(redata);
	}
}
