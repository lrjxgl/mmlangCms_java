package com.deitui.morelang.index.index;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.deitui.morelang.index.model.FollowModel;
import com.deitui.morelang.index.model.FollowedModel;
import com.model.Login;

@RestController
@CrossOrigin("*")
public class FollowController {
	@RequestMapping("/follow/index")
	public String Index() {
		Map<String,Object> redata=new HashMap<String,Object>();
		redata.put("error", 0);
		redata.put("message", "success");
		 
		return JSON.toJSONString(redata);
	}
	@RequestMapping("/follow/toggle")
	public String Toggle(
		@RequestParam(value="token",defaultValue="") String token,
		@RequestParam(value="t_userid",defaultValue="0") int t_userid
	) {
		int ssuserid=Login.isLogin(token);
		if(ssuserid==0) {
			return Login.unLogin();
		}
		FollowModel fm=new FollowModel();
		FollowedModel fed=new FollowedModel();
		Map row=fm.where("userid="+ssuserid+" AND t_userid="+t_userid).selectRow();
		int followStatus=0;
		if(row.size()>0) {
			fm.delete("userid="+ssuserid+" AND t_userid="+t_userid);
			fed.delete("userid="+t_userid+" AND t_userid="+ssuserid);
		}else {
			Map indata=new HashMap();
			indata.put("userid",ssuserid);
			indata.put("t_userid",t_userid);
			
			fm.insert(indata);
			indata=new HashMap();
			indata.put("userid",t_userid);
			indata.put("t_userid",ssuserid);
			fed.insert(indata);
			followStatus=1;
		}
		Map<String,Object> redata=new HashMap<String,Object>();
		redata.put("error", 0);
		redata.put("message", "success");
		redata.put("status", followStatus); 
		return JSON.toJSONString(redata);
	}
}
