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
import com.deitui.morelang.index.model.NoticeModel;
import com.model.Help;
import com.model.Login;

@RestController
@CrossOrigin("*")
public class NoticeController {
	@RequestMapping("/notice/my")
	public String My(@RequestParam("token") String token) {
		int userid=Login.isLogin(token);
		if(userid==0) {
			return Login.unLogin();
		}
		NoticeModel gm=new NoticeModel();
		List list=gm.where("userid="+userid).select();
		int rscount=Integer.parseInt(gm.fields(" count(*) ").selectOne()) ;
		int len=list.size();
		String[] statusList=new String[3];
		statusList[0]="未读";
		statusList[1]="已读";
		statusList[2]="已读";
		if(len>0) {
			for(int i=0;i<len;i++) {
				JSONObject json=(JSONObject) new JSONObject().toJSON(list.get(i));
				json.put("timeago", Help.timeAgo(json.getIntValue("dateline")));
				json.put("status_name", statusList[json.getIntValue("status")]);
				list.set(i, json);
 
			}
		}
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("list", list);
        redata.put("rscount", rscount);
        return JSON.toJSONString(redata);
	}
}

