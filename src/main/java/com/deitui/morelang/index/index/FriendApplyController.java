
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
import com.deitui.morelang.index.model.FriendApplyModel;
import com.model.Help;

@RestController
@CrossOrigin("*")
public class FriendApplyController {
	
	@RequestMapping("/friend_apply/index")
	public String Index() {
		FriendApplyModel am=new FriendApplyModel();
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
	
	@RequestMapping("/friend_apply/show")
	public String Show(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		FriendApplyModel am=new FriendApplyModel();
		Map data=am.where("id="+id).selectRow(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("data", data);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/friend_apply/add")
	public String Add(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		FriendApplyModel am=new FriendApplyModel();
		Map data=new HashMap();
		if(id!=0) {
			data=am.where("id="+id).selectRow();
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
	
	@RequestMapping("/friend_apply/save")
	public String Save(
		@RequestParam(value="token",defaultValue="") String token,
		@RequestParam(value="id",defaultValue="0") int id,
@RequestParam(value="touserid",defaultValue="0") int touserid,
@RequestParam(value="description",defaultValue="") String description
	) {
		Map indata= new HashMap();
		indata.put("touserid", touserid);
indata.put("description", description);

		FriendApplyModel am=new FriendApplyModel();
		if(id==0) {
			am.insert(indata);
		}else {
			am.update(indata, "id="+id);
		}
		return Help.success(0, "????????????");
	}
	
	@RequestMapping("/friend_apply/status")
	public String Status(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		FriendApplyModel am=new FriendApplyModel();
		Map row=am.where("id="+id).selectRow(); 
		JSONObject json=(JSONObject) new JSONObject().toJSON(row);
		int status=0;
		if(json.getIntValue("status")==1) {
			status=2;
		}else {
			status=1;
		}
		Map indata=new HashMap();
		indata.put("status", status);
		am.update(indata,"id="+id);
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("status", status);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/friend_apply/recommend")
	public String recommend(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		FriendApplyModel am=new FriendApplyModel();
		Map row=am.where("id="+id).selectRow(); 
		JSONObject json=(JSONObject) new JSONObject().toJSON(row);
		int status=0;
		if(json.getIntValue("is_recommend")==1) {
			status=0;
		}else {
			status=1;
		}
		Map indata=new HashMap();
		indata.put("is_recommend", status);
		am.update(indata,"id="+id);
		Map<String,Object> redata=new HashMap<String,Object>();
        
        
        redata.put("is_recommend", status);
        
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	@RequestMapping("/friend_apply/delete")
	public String delete(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id	
	) {
		FriendApplyModel am=new FriendApplyModel();
		Map indata= new HashMap();
		indata.put("status", 11);
		am.update(indata, "id="+id);
		return Help.success(0, "????????????");
	}
	
	
	
}

