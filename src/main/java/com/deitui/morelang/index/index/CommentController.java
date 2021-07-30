package com.deitui.morelang.index.index;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.model.Model;

@RestController
@CrossOrigin("*")
public class CommentController {
	@RequestMapping("/comment/index")
	public String Index() {
		Model md=new Model();
		md.preTable=md.table_pre+"_comment";
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        return JSON.toJSONString(redata);
	}
}
