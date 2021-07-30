
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
import com.deitui.morelang.index.model.RefundApplyModel;
import com.model.Help;

@RestController
@CrossOrigin("*")
public class RefundApplyController {
	
	@RequestMapping("/refund_apply/index")
	public String Index() {
		RefundApplyModel am=new RefundApplyModel();
		String where=" 1 ";
		
		List list=am.where(where).Dselect(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("list", list);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/refund_apply/show")
	public String Show(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		RefundApplyModel am=new RefundApplyModel();
		Map data=am.where("id="+id).selectRow(); 
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("data", data);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/refund_apply/add")
	public String Add(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		RefundApplyModel am=new RefundApplyModel();
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
	
	@RequestMapping("/refund_apply/save")
	public String Save(
		@RequestParam(value="token",defaultValue="") String token,
		@RequestParam(value="id",defaultValue="0") int id,
@RequestParam(value="shopid",defaultValue="0") int shopid,
@RequestParam(value="paytype",defaultValue="") String paytype,
@RequestParam(value="money",defaultValue="0") Double money,
@RequestParam(value="recharge_orderno",defaultValue="") String recharge_orderno,
@RequestParam(value="recharge_pay_orderno",defaultValue="") String recharge_pay_orderno,
@RequestParam(value="recharge_id",defaultValue="0") int recharge_id,
@RequestParam(value="content",defaultValue="") String content,
@RequestParam(value="odata",defaultValue="") String odata,
@RequestParam(value="status",defaultValue="0") int status
	) {
		Map indata= new HashMap();
		indata.put("shopid", shopid);
indata.put("paytype", paytype);
indata.put("money", money);
indata.put("recharge_orderno", recharge_orderno);
indata.put("recharge_pay_orderno", recharge_pay_orderno);
indata.put("recharge_id", recharge_id);
indata.put("content", content);
indata.put("odata", odata);
indata.put("status", status);

		RefundApplyModel am=new RefundApplyModel();
		if(id==0) {
			am.insert(indata);
		}else {
			am.update(indata, "id="+id);
		}
		return Help.success(0, "保存成功");
	}
	
	@RequestMapping("/refund_apply/status")
	public String Status(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		RefundApplyModel am=new RefundApplyModel();
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
	
	@RequestMapping("/refund_apply/recommend")
	public String recommend(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		RefundApplyModel am=new RefundApplyModel();
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
	
	@RequestMapping("/refund_apply/delete")
	public String delete(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id	
	) {
		RefundApplyModel am=new RefundApplyModel();
		Map indata= new HashMap();
		indata.put("status", 11);
		am.update(indata, "id="+id);
		return Help.success(0, "删除成功");
	}
	
	
	
}

