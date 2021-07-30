package com.deitui.morelang.index.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.deitui.morelang.index.model.AdModel;

@RestController
@CrossOrigin("*")
public class AdController {
	@RequestMapping("/admin/ad/index")
	public String Index() {
		AdModel am=new AdModel();
		String where=" status in(0,1,2) ";
		List list=am.where(where).Dselect();
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("list", list);
        return JSON.toJSONString(redata);
	}
}
