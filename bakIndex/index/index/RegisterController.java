package com.deitui.morelang.index.index;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.util.DigestUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.deitui.morelang.index.model.UserModel;
import com.deitui.morelang.index.model.UserPasswordModel;
import com.model.Help;
import com.model.Login;
import com.model.Model;
import com.model.Cache;
@RestController
@CrossOrigin("*")
public class RegisterController {
	@RequestMapping("/register/index")
	public String Index() {
		
		//输出json
		Map<String,Object> redata=new HashMap<String,Object>();
		
		return JSON.toJSONString(redata);
	}
	@RequestMapping("/register/sendsms")
	public String Save(@RequestParam("telephone") String telephone){
		Map<String,Object> redata=new HashMap<String,Object>();
		int yzm=(int)(Math.random()*10000);
		redata.put("error", 0);
		redata.put("message", "success"+yzm);
		
		redata.put("yzm", yzm);
		String cacheKey="reg-sendsms-"+telephone+yzm;
		Cache cache=new Cache();
		cache.set(cacheKey,yzm+"",300);

		return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/register/save")
	public String Save(
			@RequestParam(value="nickname",defaultValue="") String username,
			@RequestParam(value="password",defaultValue="") String password,
			@RequestParam(value="telephone",defaultValue="") String telephone,
			@RequestParam(value="yzm",defaultValue="") String yzm
	){
		String cacheKey="reg-sendsms-"+telephone+yzm;
		
		Cache cache=new Cache();
		String c=cache.get(cacheKey);
		
		if(c=="") {
			return Help.error(1,"短信验证出错");
		}
		UserModel user=new UserModel();
		//查询是否重复
		Map row=user.where("telephone='"+telephone+"' ").selectRow();
		if(row.size()!=0) {
			return Help.error(1,"手机号码已存在");
		}
		row=user.where("username='"+username+"' ").selectRow();
		
		if(row.size()!=0) {
			
			return Help.error(1,"用户名已经已存在");
		}
		row=user.where("nickname='"+username+"' ").selectRow();
		if(row.size()!=0) {
			return Help.error(1,"昵称已经已存在");
		}
		Map<String,Object> data=new HashMap();
		data.put("username", username);
		//md5加密 两次加密 md5(md5(password.salt))
		int salt=(int)(Math.random()*10000);
		String md5pwd=password+salt;
		md5pwd=DigestUtils.md5DigestAsHex(md5pwd.getBytes());
		md5pwd=DigestUtils.md5DigestAsHex(md5pwd.getBytes());
		//存入数据
		data.put("username", username);
		data.put("nickname", username);
		data.put("telephone", telephone);
		int userid= user.insert(data);
		Map<String,Object> pdata=new HashMap();
		pdata.put("userid", userid);
		pdata.put("password", md5pwd);
		pdata.put("salt", salt);
		UserPasswordModel up=new UserPasswordModel();
		up.insert(pdata);
		
		//token加密方式
		Login login=new Login();
		JSONObject redata=login.getToken(userid, md5pwd);
		//输出json
		 
		redata.put("error", 0);
		redata.put("message", "注册成功");
		return JSON.toJSONString(redata);			 
	}
	
}
