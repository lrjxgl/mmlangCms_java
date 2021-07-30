
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
import com.deitui.morelang.index.model.DbsessionModel;
import com.model.Help;

@RestController
@CrossOrigin("*")
public class DbsessionController {
	
	@RequestMapping("/dbsession/index")
	public String Index() {
		 
        return Help.success(0, "success");
	}
	 
	 
	
	
}

