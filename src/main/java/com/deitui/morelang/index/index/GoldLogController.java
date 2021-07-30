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
import com.deitui.morelang.index.model.GoldLogModel;
import com.model.Help;
import com.model.Login;

@RestController
@CrossOrigin("*")
public class GoldLogController {
	@RequestMapping("/gold_log/my")
	public String My(@RequestParam("token") String token) {
		int userid=Login.isLogin(token);
		if(userid==0) {
			return Login.unLogin();
		}
		GoldLogModel gm=new GoldLogModel();
		List list=gm.where("userid="+userid).order("id DESC").select();
		int len=list.size();
		if(len>0) {
			for(int i=0;i<len;i++) {
				JSONObject json=(JSONObject) new JSONObject().toJSON(list.get(i));
				json.put("timeago", Help.timeAgo(json.getIntValue("dateline")));
				 
				list.set(i, json);
 
			}
		}
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("list", list);
        return JSON.toJSONString(redata);
	}
}
