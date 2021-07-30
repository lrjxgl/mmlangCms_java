package com.deitui.morelang.forum.index;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.deitui.morelang.forum.model.ForumModel;
import com.deitui.morelang.index.model.UserModel;

@RestController
@CrossOrigin("*")
public class ForumPaihangController {
	@RequestMapping("/forum_paihang/index")
	public String Index() {
		ForumModel forumModel=new ForumModel();
		List wzList=forumModel.where("status=1").Dselect();
		UserModel u=new UserModel();
		List fsList=u.Dselect();
		Map<String,Object> redata=new HashMap<String,Object>();
	    redata.put("error",0);
	    redata.put("message","succcess");
	    redata.put("wzList",wzList);
	    redata.put("fsList", fsList);
	    return JSON.toJSONString(redata);
	}
}
