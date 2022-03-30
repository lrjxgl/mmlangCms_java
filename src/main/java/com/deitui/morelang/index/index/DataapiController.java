
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
import com.deitui.morelang.index.model.DataapiModel;
import com.model.Help;

@RestController
@CrossOrigin("*")
public class DataapiController {
	
	@RequestMapping("/dataapi/aboutus")
	public String aboutus() {
		DataapiModel m=new DataapiModel();
		Map row=m.where("word='aboutus'").selectRow();
		Map<String,Object> redata=new HashMap<String,Object>();
		
		 
		redata.put("data", row);
	    
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);

	}
	
	
}

