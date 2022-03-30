package com.deitui.morelang.forum.index;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.deitui.morelang.forum.model.ForumCategoryModel;
import com.deitui.morelang.forum.model.ForumFavModel;
import com.deitui.morelang.forum.model.ForumModel;
import com.model.Help;
import com.model.Login;

@RestController
@CrossOrigin("*")
public class ForumSearchControl {
	@RequestMapping("/forum_search/index")
	public String Index(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="keyword",defaultValue="") String keyword
	) {
		String where=" status=1 ";
		ForumModel forumModel=new ForumModel();
		if(!keyword.equals("")) {
			where+=" AND title like ? ";
			 
			forumModel.param=new Object[] {
					"%"+keyword+"%"
			};
		}
		
		List list=forumModel.where(where).Dselect();
    	 
    	Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("list", list);
   
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);
	}
}
