package com.deitui.morelang.index.index;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.deitui.morelang.index.model.DataApiModel;

@RestController
@CrossOrigin("*")
public class datapiController {
	@RequestMapping("/dataapi/aboutus")
	public String aboutus() {
		DataApiModel m=new DataApiModel();
		Map row=m.where("word='aboutus'").selectRow();
		Map<String,Object> redata=new HashMap<String,Object>();
		redata.put("error",0);
		redata.put("message","succcess"); 
		redata.put("data", row);
	    return JSON.toJSONString(redata);
	}
}
