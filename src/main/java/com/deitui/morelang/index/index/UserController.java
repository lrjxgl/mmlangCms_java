package com.deitui.morelang.index.index;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.deitui.morelang.index.model.UserModel;
import com.deitui.morelang.index.model.UserPasswordModel;
import com.model.Model;
import com.model.AppConfig;
import com.model.Cache;
import com.model.Help;
import com.model.Login;
@RestController
@CrossOrigin("*")
public class UserController {
	@RequestMapping("/user/index")
	public String Index(@RequestParam(value="token",defaultValue="") String token) {

		int userid=Login.isLogin(token);
		if(userid==0) {
			return Login.unLogin();
		}
		UserModel userModel=new UserModel();
		Map<String,Object> user=userModel.get(userid,"");
		Map<String,Object> redata=new HashMap<String,Object>();
		redata.put("user", user);
		
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/user/set")
	public String Set(@RequestParam(value="token",defaultValue="") String token) {
		int userid=Login.isLogin(token);
		if(userid==0) {
			return Login.unLogin();
		}
		UserModel userModel=new UserModel();
		Map<String,Object> user=userModel.get(userid,"");
		Map<String,Object> redata=new HashMap<String,Object>();
		redata.put("user", user);
		
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/user/info")
	public String Info(@RequestParam(value="token",defaultValue="") String token) {
		int userid=Login.isLogin(token);
		if(userid==0) {
			return Login.unLogin();
		}
		UserModel userModel=new UserModel();
		Map<String,Object> user=userModel.get(userid,"userid,nickname,telephone,user_head,description,gender");
		Map<String,Object> redata=new HashMap<String,Object>();
		redata.put("user", user);
		
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	@RequestMapping("/user/save")
	public String Save(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="nickname",defaultValue="") String nickname,
			@RequestParam(value="description",defaultValue="") String description
	) {
		int userid=Login.isLogin(token);
		if(userid==0) {
			return Login.unLogin();
		}
		UserModel userModel=new UserModel();
		Map<String,Object> row=userModel.where("nickname= '"+nickname+"'").selectRow();
		if(row.size()==0) {
			return Help.error(1, "昵称已存在");
		}
		Map<String,Object> data=new HashMap();
		data.put("nickname", nickname);
		data.put("description", description);
		userModel.update(data, "userid="+userid);
		return Help.success(0, "保存成功");
	 
	}
	@RequestMapping("/user/head")
	public String Head(@RequestParam(value="token",defaultValue="") String token) {
		int userid=Login.isLogin(token);
		if(userid==0) {
			return Login.unLogin();
		}
		UserModel userModel=new UserModel();
		Map<String,Object> user=userModel.where("userid="+userid).selectRow();
		if(user.size()==0) {
			return Help.error(0, "用户不存在");
		}
		user.put("true_user_head", AppConfig.IMAGES_SITE+user.get("user_head")+"");
		Map<String,Object> redata=new HashMap<String,Object>();
		redata.put("user", user);
		
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	@RequestMapping("/user/headsave")
	public String HeadSave(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="user_head",defaultValue="") String user_head
	) {
		int userid=Login.isLogin(token);
		if(userid==0) {
			return Login.unLogin();
		}
		UserModel userModel=new UserModel();
		 
		Map<String,Object> data=new HashMap();
		System.out.print(user_head);
		data.put("user_head", user_head.replaceAll("\\\\", "\\\\\\\\"));

		userModel.update(data, "userid="+userid);
		return Help.success(0, "保存成功"+user_head);
	 
	}
	@RequestMapping("/user/passwordsave")
	public String PasswordSave(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="oldpassword",defaultValue="") String oldpassword,
			@RequestParam(value="password",defaultValue="") String password,
			@RequestParam(value="password2",defaultValue="") String password2
	) {
		int userid=Login.isLogin(token);
		if(userid==0) {
			return Login.unLogin();
		}
		if(!password.equals(password2)) {
			return Help.error(1, "两次密码不一致");
		}
		UserModel userModel=new UserModel();
		Map<String,Object> row=userModel.where("userid= "+userid).selectRow();
		if(row.size()==0) {
			return Help.error(1, "请先登录");
		}
		//验证旧密码
		UserPasswordModel userPassword=new UserPasswordModel();
		Map<String,Object> pwd=userPassword.where("userid= "+userid).selectRow();
		String upwd=pwd.get("password")+"";
		String md5pwd=oldpassword+pwd.get("salt")+"";
		md5pwd=DigestUtils.md5DigestAsHex(md5pwd.getBytes());
		md5pwd=DigestUtils.md5DigestAsHex(md5pwd.getBytes());
		if(!upwd.equals(md5pwd)) {
			return Help.error(1,"密码错误");
		}
		//更新密码
		Map<String,Object> data=new HashMap();
		int salt=(int)(Math.random()*10000);
		md5pwd=password+salt;
		md5pwd=DigestUtils.md5DigestAsHex(md5pwd.getBytes());
		md5pwd=DigestUtils.md5DigestAsHex(md5pwd.getBytes());
		data.put("password", md5pwd);
		data.put("salt", salt);
		userPassword.update(data, "userid= "+userid);
		//返回
		return Help.success(0, "保存成功");
	 
	}
	@RequestMapping("/user/sendsms")
	public String Sendsms(@RequestParam(value="token",defaultValue="") String token){
		int userid=Login.isLogin(token);
		if(userid==0) {
			return Login.unLogin();
		}
		UserModel userModel=new UserModel();
		Map<String,Object> user=userModel.get(userid,"");
		String telephone=user.get("telephone")+"";
		Map<String,Object> redata=new HashMap<String,Object>();
		int yzm=(int)(Math.random()*10000);
		redata.put("error", 0);
		redata.put("message", "success"+yzm);
		
		redata.put("yzm", yzm);
		String cacheKey="user-sendsms-"+telephone+yzm;
		Cache cache=new Cache();
		cache.set(cacheKey,yzm+"",300);

		
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/user/paypwdsave")
	public String paypwdSave(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="paypwd",defaultValue="") String paypwd
	) {
		int userid=Login.isLogin(token);
		if(userid==0) {
			return Login.unLogin();
		}
		UserPasswordModel userPassword=new UserPasswordModel();
		Map<String,Object> data=new HashMap();
		String md5pwd=DigestUtils.md5DigestAsHex(paypwd.getBytes());
		md5pwd=DigestUtils.md5DigestAsHex(md5pwd.getBytes());
		data.put("paypwd", md5pwd);
		userPassword.update(data, "userid= "+userid);
		return Help.success(0, "保存成功");
	}
	
}
