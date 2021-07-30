package com.deitui.morelang.index.index;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.deitui.morelang.index.model.UserModel;
import com.deitui.morelang.index.model.UserPasswordModel;
import com.model.Cache;
import com.model.Help;
import com.model.Login;
import com.model.Model;
@RestController
@CrossOrigin("*")
public class LoginController {
	@RequestMapping("/login/index")
	public String Index(){
		 Map<String,Object> redata=new HashMap<String,Object>();
		 redata.put("a","123");
		 return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/login/save")
	public String Save(
			@RequestParam(value="telephone",defaultValue="" ) String telephone, 
			@RequestParam(value="username",defaultValue="" ) String username,
			@RequestParam(value="password",defaultValue="") String password
	){
		UserModel user=new UserModel();
		 //Map<String,Object> row=user.where("username='"+username+"'").selectRow();
		 Map<String,Object> row=user.where("telephone='"+telephone+"'").selectRow();
		 if(row.size()==0) {
			 return Help.error(1,"用户不存在");
		 }
		 UserPasswordModel userPassword=new UserPasswordModel();
		 int userid= Integer.parseInt(row.get("userid")+"") ;
		 Map<String,Object> pwd=userPassword.where("userid='"+userid+"'").selectRow();
		 if(pwd.size()==0) {
			 return Help.error(1,"用户不存在");
		 }
		  
		 String upwd=pwd.get("password")+"";
		 String md5pwd=password+pwd.get("salt")+"";
		 md5pwd=DigestUtils.md5DigestAsHex(md5pwd.getBytes());
		 md5pwd=DigestUtils.md5DigestAsHex(md5pwd.getBytes());
		 if(!upwd.equals(md5pwd)) {
			 return Help.error(1,"密码错误"+upwd+","+md5pwd);
		 }
		
		//token加密方式
		Login login=new Login();
		JSONObject redata=login.getToken(userid, md5pwd);
		//输出json
		 
		redata.put("error", 0);
		redata.put("message", "登录成功");
		return JSON.toJSONString(redata);	
	}
	
	@RequestMapping("/login/refresh") 
	public String Refresh(
			@RequestParam("refreshToken") String refreshToken
	) {
		 Cache cache=new Cache();
		 String s=cache.get(refreshToken);
		 int userid=Integer.parseInt(s);
		 
		 Map<String,Object> redata=new HashMap<String,Object>();
		 return JSON.toJSONString(redata);
	}
}
