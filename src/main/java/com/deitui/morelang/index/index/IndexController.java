package com.deitui.morelang.index.index;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSON;
import com.model.Model;
@RestController
@CrossOrigin("*")
public class IndexController {
	@RequestMapping("/")
	public String Index() {
		Map<String,Object> redata=new HashMap<String,Object>();
		redata.put("error", 0);
		redata.put("message", "success");
		return JSON.toJSONString(redata);
	}
}
