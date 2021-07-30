package com.deitui.morelang.forum.index;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.deitui.morelang.forum.model.ForumGroupModel;

@RestController
@CrossOrigin("*")
public class ForumGroupController {
	 @RequestMapping("/forum_group/index")
	 public String Index() {
		 ForumGroupModel forumGroupModel=new ForumGroupModel(); 
		 List list=forumGroupModel.where("status=1").Dselect();
		 Map<String,Object> redata=new HashMap<String,Object>();
		 redata.put("error",0);
		 redata.put("message","succcess");
		 redata.put("list", list);
		 return JSON.toJSONString(redata);
	 }
	 
}
