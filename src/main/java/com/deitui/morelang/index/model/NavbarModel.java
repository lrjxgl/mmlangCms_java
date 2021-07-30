package com.deitui.morelang.index.model;

 
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model.Model;

public class NavbarModel extends Model {
	public NavbarModel() {
		this.preTable=this.table_pre+"navbar";
	}
	
	public List GroupList() {
		List list=new ArrayList();
		Map<String,Object> mp=new HashMap();
		mp.put("gid", 1);
		mp.put("title","后台顶部");
		list.add(mp);
		mp=new HashMap();
		mp.put("gid", 2);
		mp.put("title","后台左边");
		list.add(mp);
		return list; 
	}
}
