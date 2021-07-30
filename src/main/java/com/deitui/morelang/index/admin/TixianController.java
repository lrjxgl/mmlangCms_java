
package com.deitui.morelang.index.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.deitui.morelang.index.model.TixianModel;
import com.model.Help;

@RestController
@CrossOrigin("*")
public class TixianController {
	
	@RequestMapping("/admin/tixian/index")
	public String Index() {
		TixianModel am=new TixianModel();
		String where=" 1 ";
		
		List list=am.where(where).Dselect(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("list", list);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/admin/tixian/show")
	public String Show(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		TixianModel am=new TixianModel();
		Map data=am.where("id="+id).selectRow(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("data", data);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/admin/tixian/add")
	public String Add(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		TixianModel am=new TixianModel();
		Map data=new HashMap();
		if(id!=0) {
			data=am.where("id="+id).selectRow();
			data.put("trueimgurl", Help.image_site(data.get("imgurl")+""));
		}
		
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("data", data);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/admin/tixian/save")
	public String Save(
		@RequestParam(value="token",defaultValue="") String token,
		@RequestParam(value="id",defaultValue="0") int id,
@RequestParam(value="objectid",defaultValue="0") int objectid,
@RequestParam(value="k",defaultValue="") String k,
@RequestParam(value="money",defaultValue="0") Double money,
@RequestParam(value="status",defaultValue="0") int status,
@RequestParam(value="info",defaultValue="") String info,
@RequestParam(value="reply",defaultValue="") String reply,
@RequestParam(value="redateline",defaultValue="0") int redateline,
@RequestParam(value="siteid",defaultValue="0") int siteid,
@RequestParam(value="yhk_name",defaultValue="") String yhk_name,
@RequestParam(value="yhk_haoma",defaultValue="") String yhk_haoma,
@RequestParam(value="yhk_huming",defaultValue="") String yhk_huming,
@RequestParam(value="telephone",defaultValue="") String telephone,
@RequestParam(value="yhk_address",defaultValue="") String yhk_address,
@RequestParam(value="adminid",defaultValue="0") int adminid,
@RequestParam(value="paytype",defaultValue="") String paytype,
@RequestParam(value="bankid",defaultValue="0") int bankid
	) {
		Map indata= new HashMap();
		indata.put("objectid", objectid);
indata.put("k", k);
indata.put("money", money);
indata.put("status", status);
indata.put("info", info);
indata.put("reply", reply);
indata.put("redateline", redateline);
indata.put("siteid", siteid);
indata.put("yhk_name", yhk_name);
indata.put("yhk_haoma", yhk_haoma);
indata.put("yhk_huming", yhk_huming);
indata.put("telephone", telephone);
indata.put("yhk_address", yhk_address);
indata.put("adminid", adminid);
indata.put("paytype", paytype);
indata.put("bankid", bankid);

		TixianModel am=new TixianModel();
		if(id==0) {
			am.insert(indata);
		}else {
			am.update(indata, "id="+id);
		}
		return Help.success(0, "保存成功");
	}
	
	@RequestMapping("/admin/tixian/status")
	public String Status(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		TixianModel am=new TixianModel();
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
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("status", status);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/admin/tixian/recommend")
	public String recommend(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		TixianModel am=new TixianModel();
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
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("is_recommend", status);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/admin/tixian/delete")
	public String delete(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id	
	) {
		TixianModel am=new TixianModel();
		Map indata= new HashMap();
		indata.put("status", 11);
		am.update(indata, "id="+id);
		return Help.success(0, "删除成功");
	}
	
	
	
}

