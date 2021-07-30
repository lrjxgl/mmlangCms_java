package com.deitui.morelang.index.index;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.deitui.morelang.index.model.LoveModel;
import com.model.Login;

@RestController
@CrossOrigin("*")
public class LoveController {
	@RequestMapping("/love/index")
	public String Index() {
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/love/get")
	public String Get(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="tablename",defaultValue="article") String tablename,
			@RequestParam(value="objectid",defaultValue="0") int objectid
	) {
		int userid=Login.isLogin(token);
		if(userid==0) {
			return Login.unLogin();
		}
		LoveModel fm=new LoveModel();
		Map row= fm.where("userid="+userid+" AND tablename='"+tablename+"' AND objectid="+objectid).selectRow();
		String action="delete";
		if(row.size()>0) {
			action="love";
		}
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("action", action);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/love/toggle")
	public String Toggle(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="tablename",defaultValue="article") String tablename,
			@RequestParam(value="objectid",defaultValue="0") int objectid
	) {
		int userid=Login.isLogin(token);
		if(userid==0) {
			return Login.unLogin();
		}
		LoveModel fm=new LoveModel();
		Map row= fm.where("userid="+userid+" AND tablename='"+tablename+"' AND objectid="+objectid).selectRow();
		String action="delete";
		if(row.size()>0) {
			 fm.delete("userid="+userid+" AND tablename='"+tablename+"' AND objectid="+objectid);
		}else {
			Map inData=new HashMap();
			inData.put("userid",userid);
			inData.put("tablename",tablename);
			inData.put("objectid",objectid);
			fm.insert(inData);
			action="add";
		}
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("action", action);
        return JSON.toJSONString(redata);
	}
}
