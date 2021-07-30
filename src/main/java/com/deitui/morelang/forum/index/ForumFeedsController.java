package com.deitui.morelang.forum.index;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.deitui.morelang.forum.model.ForumModel;

@RestController
@CrossOrigin("*")
public class ForumFeedsController {
	@RequestMapping("/forum_feeds/index")
	public String Index() {
		ForumModel forumModel=new ForumModel();
		String where=" status in(0,1) ";
		List list=forumModel.where(where).order("id DESC").Dselect();
		Map<String,Object> redata=new HashMap<String,Object>();
	    redata.put("error",0);
	    redata.put("message","succcess");
	    redata.put("list",list);
	    return JSON.toJSONString(redata);
	}
}
