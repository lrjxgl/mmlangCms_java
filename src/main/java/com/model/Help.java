package com.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class Help {
	public static String error(int errno,String errmsg) {
		return "{\"error\":"+errno+",\"message\":\""+errmsg+"\"}";
	}
	
	public static String success(int errno,String errmsg) {
		return "{\"error\":"+errno+",\"message\":\""+errmsg+"\"}";
	}
	 
	public static Boolean in_array(String str,String[] arr) {
		List<String> tempList = Arrays.asList(arr);
		if(tempList.contains(str))
		{
			return true;
		} else {
		   return false;
		}
	 
	}
	
	public static String _implode(ArrayList arr) {
		int len=arr.size();
		String str="";
		for(int i=0;i<len;i++) {
			if(i>0) {
				str+=",";
			}
			str+="'"+arr.get(i)+"'";
		}
		return str;
	}
	
	public static Object getObjectByKey(List list,String key,String value) {
		
		for(int i=0;i<list.size();i++){
			//System.out.println(list.get(i));
			JSONObject json=  (JSONObject) JSONObject.toJSON(list.get(i));
			String userid=json.getString(key);
		      if(userid.equals(value)){
		         return list.get(i);
		      }        
		 }
		return new Object();
	}
	
	public static String image_site(String url) {
		if(url!="") {
			return AppConfig.IMAGES_SITE+url;
		}else {
			return "";
		}
	}
	
	public static String timeAgo(int dateline) {
		String str="";
		int time=(int) (new Date().getTime()/1000);
		int tt=time-dateline;
		
		int h=0;
		int i=0;
		int s=0;
		if(tt>3600*24) {
			int d=(int) tt/(3600*24);
			str= d+"天前";
		}else {
			if(tt>3600) {
				h=(int) tt/3600;
				str+= h+"时前";
			}else if(tt>60) {
				i=(int) (tt-h*3600)/60;
				str+= i+"分前";
			}else{
				s=tt-h*3600-i*60;
				str+= s+"秒前";
			}
		}
		
		return str;
	}
	
	public static String createtime() {
		Date date=new Date();
		SimpleDateFormat  sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
}
