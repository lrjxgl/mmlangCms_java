package com.deitui.morelang.index.index;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.deitui.morelang.index.model.KefuMsgModel;
import com.model.Help;
import com.model.Login;
import com.model.Model;

@RestController
@CrossOrigin("*")
public class KefuController {
	@RequestMapping("/kefu/index")
	public String Index(
		@RequestParam(value="token",defaultValue="") String token	
	) {
		int userid=Login.isLogin(token);
		if(userid==0) {
			return Login.unLogin();
		}
		KefuMsgModel km=new KefuMsgModel();
		List list=km.where("userid="+userid).select();
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("list", list);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/kefu/save")
	public String Save(
		@RequestParam(value="token",defaultValue="") String token,
		@RequestParam(value="content",defaultValue="") String content
	) {
		int userid=Login.isLogin(token);
		if(userid==0) {
			return Login.unLogin();
		}
		KefuMsgModel km=new KefuMsgModel();
		Map<String,Object> indata=new HashMap<String,Object>();
		indata.put("userid", userid);
		indata.put("content",content);
		indata.put("createtime", Help.createtime());
		km.insert(indata);
		//索引
		Model m=new Model();
		m.preTable=m.table_pre+"kefu_msgindex";
		Map row=m.where("userid="+userid).selectRow();
		indata=new HashMap<String,Object>();
		indata.put("userid", userid);
		indata.put("content",content);
		indata.put("createtime", Help.createtime());
		indata.put("isreply", 0);
		indata.put("isread", 0);
		if(row.size()>0) {
			
			m.update(indata, "userid="+userid);
		}else {
			m.insert(indata);
		}
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
  
        return JSON.toJSONString(redata);
	}
	
}
