package com.model;

import java.util.Date;

import org.springframework.util.DigestUtils;

import com.alibaba.fastjson.JSONObject;
import com.model.Cache;
public class Login {
	 
	 
	public static int isLogin(String token) {
		Cache cache=new Cache();
		String userid=cache.get(token);
		if(userid=="") {
			return 0;
		}else {
			return Integer.parseInt(userid);
		}
		 
	}
	public static String unLogin() {
		return "{\"error\":1000,\"message\":\"请先登录\"}";
	}
	public static JSONObject getToken(int userid,String password) {
		//cache
		Cache cache=new Cache();
		//token
		String md5pwd=DigestUtils.md5DigestAsHex(password.getBytes());
		String key= userid+"."+md5pwd.substring(0,16);
		int expire=3600*24*3;
		cache.set(key,userid+"",expire);
		int time=(int) (new Date().getTime()/1000);
		int token_expire=time+expire;
		//refresh_token
		password=password.substring(0,8);
		md5pwd=DigestUtils.md5DigestAsHex(password.getBytes());
		String refresh_token=userid+"."+md5pwd.substring(0,8);
		expire=3600*24*14;
		cache.set(refresh_token,userid+"",expire);
		time=(int) (new Date().getTime()/1000);
		int refresh_expire_time=time+expire;
		JSONObject obj=new JSONObject();
		obj.put("token",key);
		obj.put("token_expire", token_expire);
		obj.put("refresh_token", refresh_token);
		obj.put("refresh_token_expire", refresh_expire_time);
		return obj;
	}
	
	 
	
	public static String getUserByToken(String token) {
		
		return "";
	}
}
