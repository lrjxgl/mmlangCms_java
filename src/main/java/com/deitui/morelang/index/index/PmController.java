package com.deitui.morelang.index.index;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.deitui.morelang.index.model.PmIndexModel;
import com.deitui.morelang.index.model.PmModel;
import com.deitui.morelang.index.model.UserModel;
import com.model.Help;
import com.model.Login;

@RestController
@CrossOrigin("*")
public class PmController {
	@RequestMapping("/pm/index")
	public String Index(@RequestParam(value="token",defaultValue="") String token ) {
		int userid=Login.isLogin(token);
		if(userid==0) {
			return Help.error(1000, "请先登录");
		}
		PmIndexModel pi=new PmIndexModel();
		List msgList=pi.where("userid="+userid).Dselect();
		Map<String,Object> redata=new HashMap<String,Object>();
		redata.put("error", 0);
		redata.put("message", "success");
		redata.put("list", msgList);
		return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/pm/detail")
	public String Detail(
		@RequestParam(value="token",defaultValue="") String token,
		@RequestParam(value="t_userid",defaultValue="0") int t_userid
	) {
		int ssuserid=Login.isLogin(token);
		if(ssuserid==0) {
			return Help.error(1000, "请先登录");
		}
		UserModel u=new UserModel();
		Map ssuser=u.get(ssuserid, "userid,nickname,user_head");
		Map user=u.get(t_userid, "userid,nickname,user_head");
		PmModel pi=new PmModel();
		List msgList=pi.where("userid="+ssuserid+" AND t_userid="+t_userid).select();
		Map<String,Object> redata=new HashMap<String,Object>();
		redata.put("error", 0);
		redata.put("message", "success");
		redata.put("pmList", msgList); 
		redata.put("ssuser", ssuser);
		redata.put("user", user);
		return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/pm/save")
	public String save(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="t_userid",defaultValue="0") int t_userid,
			@RequestParam(value="content",defaultValue="") String content
	) {
		int ssuserid=Login.isLogin(token);
		if(ssuserid==0) {
			return Help.error(1000, "请先登录");
		}
		PmModel p=new PmModel();
		PmIndexModel pi=new PmIndexModel();
		Map pmData=new HashMap();
		Date date=new Date();
		SimpleDateFormat  sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createtime=sdf.format(date);
		
		//发送方
		pmData.put("userid",ssuserid);
		pmData.put("t_userid", t_userid);
		pmData.put("content", content);
		pmData.put("type_id", 1);	
		pmData.put("createtime", createtime);
		p.insert(pmData);
		//索引
		pmData=new HashMap();
		pmData.put("userid",ssuserid);
		pmData.put("t_userid", t_userid);
		pmData.put("content", content);
		pmData.put("type_id", 1);	
		pmData.put("createtime", createtime);
		pmData.put("status", 0);
		Map r1=pi.where("userid="+ssuserid+" AND t_userid="+t_userid).selectRow();
		if(r1.size()>0) {
			pi.update(pmData,"userid="+ssuserid+" AND t_userid="+t_userid );
		}else {
			pi.insert(pmData);
		}
		//接收方
		pmData=new HashMap();
		pmData.put("userid",t_userid);
		pmData.put("t_userid", ssuserid);
		pmData.put("content", content);
		pmData.put("type_id", 2);
		pmData.put("createtime", createtime);
		p.insert(pmData);
		//索引
		pmData=new HashMap();
		pmData.put("userid",t_userid);
		pmData.put("t_userid", ssuserid);
		pmData.put("content", content);
		pmData.put("type_id", 2);	
		pmData.put("createtime", createtime);
		pmData.put("status", 0);
		Map r2=pi.where("userid="+t_userid+" AND t_userid="+ssuserid).selectRow();
		if(r2.size()>0) {
			pi.update(pmData,"userid="+t_userid+" AND t_userid="+ssuserid );
		}else {
			pi.insert(pmData);
		}
		Map<String,Object> redata=new HashMap<String,Object>();
		redata.put("error", 0);
		redata.put("message", "success");
		return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/pm/getNew")
	public String getNew() {
		
		Map<String,Object> redata=new HashMap<String,Object>();
		redata.put("error", 0);
		redata.put("message", "success");
		return JSON.toJSONString(redata);
	}
	
	
	
}
