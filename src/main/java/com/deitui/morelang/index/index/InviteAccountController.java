
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
import com.deitui.morelang.index.model.InviteAccountModel;
import com.model.Help;

@RestController
@CrossOrigin("*")
public class InviteAccountController {
	
	@RequestMapping("/invite_account/index")
	public String Index() {
		InviteAccountModel am=new InviteAccountModel();
		String where=" 1 ";
		
		List list=am.where(where).Dselect(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("list", list);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/invite_account/show")
	public String Show(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="userid",defaultValue="0") int userid
	) {
		InviteAccountModel am=new InviteAccountModel();
		Map data=am.where("userid="+userid).selectRow(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("data", data);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/invite_account/add")
	public String Add(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="userid",defaultValue="0") int userid
	) {
		InviteAccountModel am=new InviteAccountModel();
		Map data=new HashMap();
		if(userid!=0) {
			data=am.where("userid="+userid).selectRow();
			data.put("trueimgurl", Help.image_site(data.get("imgurl")+""));
		}
		
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("data", data);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/invite_account/save")
	public String Save(
		@RequestParam(value="token",defaultValue="") String token,
		@RequestParam(value="userid",defaultValue="0") int userid,
@RequestParam(value="money",defaultValue="0") Double money,
@RequestParam(value="income",defaultValue="0") Double income
	) {
		Map indata= new HashMap();
		indata.put("money", money);
indata.put("income", income);

		InviteAccountModel am=new InviteAccountModel();
		if(userid==0) {
			am.insert(indata);
		}else {
			am.update(indata, "userid="+userid);
		}
		return Help.success(0, "保存成功");
	}
	
	@RequestMapping("/invite_account/status")
	public String Status(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="userid",defaultValue="0") int userid
	) {
		InviteAccountModel am=new InviteAccountModel();
		Map row=am.where("userid="+userid).selectRow(); 
		JSONObject json=(JSONObject) new JSONObject().toJSON(row);
		int status=0;
		if(json.getIntValue("status")==1) {
			status=2;
		}else {
			status=1;
		}
		Map indata=new HashMap();
		indata.put("status", status);
		am.update(indata,"userid="+userid);
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("status", status);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/invite_account/recommend")
	public String recommend(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="userid",defaultValue="0") int userid
	) {
		InviteAccountModel am=new InviteAccountModel();
		Map row=am.where("userid="+userid).selectRow(); 
		JSONObject json=(JSONObject) new JSONObject().toJSON(row);
		int status=0;
		if(json.getIntValue("is_recommend")==1) {
			status=0;
		}else {
			status=1;
		}
		Map indata=new HashMap();
		indata.put("is_recommend", status);
		am.update(indata,"userid="+userid);
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("is_recommend", status);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/invite_account/delete")
	public String delete(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="userid",defaultValue="0") int userid	
	) {
		InviteAccountModel am=new InviteAccountModel();
		Map indata= new HashMap();
		indata.put("status", 11);
		am.update(indata, "userid="+userid);
		return Help.success(0, "删除成功");
	}
	
	
	
}

