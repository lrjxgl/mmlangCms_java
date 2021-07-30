package com.deitui.morelang.index.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.deitui.morelang.index.model.NavbarModel;
import com.model.Help;

@RestController
@CrossOrigin("*")
public class NavbarController {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	@RequestMapping("/admin/navbar/index")
	public String Index(
			@RequestParam(value="group_id",defaultValue="2") int group_id
	) {
		NavbarModel am=new NavbarModel();
		String where=" group_id="+group_id+" AND status in(0,1,2) ";
		List alist=am.where(where).Dselect(); 
		int len=alist.size();
		ArrayList list=new ArrayList();
		for(int i=0;i<len;i++) {
			JSONObject json=(JSONObject) JSONObject.toJSON(alist.get(i));
			if(json.getIntValue("pid")==0) {
				json.put("child", new ArrayList());
				list.add(json);
			}
		}
		int glen=list.size();
		ArrayList child=new ArrayList();
		ArrayList pList=new ArrayList();
		for(int j=0;j<glen;j++) {
			child=new ArrayList();
			JSONObject pjson=(JSONObject) JSONObject.toJSON(list.get(j));
			for(int i=0;i<len;i++) {
				JSONObject json=(JSONObject) JSONObject.toJSON(alist.get(i));
				if(json.getIntValue("pid")==pjson.getIntValue("id")) {
					child.add(json);
				}
			}
			pjson.put("child", child);
			pList.add(pjson); 
		}
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("list", pList);
        return JSON.toJSONString(redata);
	}
	@RequestMapping("/admin/navbar/add")
	public String Add(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id,
			@RequestParam(value="pid",defaultValue="0") int pid
	) {
		NavbarModel am=new NavbarModel();
		Map data=new HashMap();
		Map parent=new HashMap();
		int group_id=0;
		if(id!=0) {
			data=am.where("id="+id).selectRow();
			data.put("trueimgurl", Help.image_site(data.get("imgurl")+""));
			pid=Integer.parseInt(data.get("pid")+"") ;
			group_id=Integer.parseInt(data.get("group_id")+"") ;
		}else if(pid!=0) {
			parent=am.where("id="+pid).selectRow();
			group_id=Integer.parseInt(parent.get("group_id")+"") ;
		}
		List parentList=am.fields("id,title").where("status=1 AND pid=0").limit(0,1000).select();
		List groupList=am.GroupList();
		
		
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("data", data);
        redata.put("parentList", parentList);
        redata.put("pid", pid);
        redata.put("groupList", groupList);
        redata.put("group_id", group_id);
        return JSON.toJSONString(redata);
	}
	
	@RequestMapping("/admin/navbar/save")
	public String Save(
		@RequestParam(value="token",defaultValue="") String token,
		@RequestParam(value="id",defaultValue="0") int id,
		@RequestParam(value="title",defaultValue="") String title,
		@RequestParam(value="orderindex",defaultValue="0") int orderindex,
		@RequestParam(value="link_url",defaultValue="") String link_url,
		@RequestParam(value="target",defaultValue="") String target,
		@RequestParam(value="pid",defaultValue="0") int pid,
		@RequestParam(value="group_id",defaultValue="0") int group_id,
		@RequestParam(value="m",defaultValue="") String m,
		@RequestParam(value="a",defaultValue="") String a,
		@RequestParam(value="status",defaultValue="0") int status,
		@RequestParam(value="logo",defaultValue="") String logo,
		@RequestParam(value="icon",defaultValue="") String icon
	) {
		Map<String,Object> indata= new HashMap<String,Object>();
		indata.put("title", title);
		indata.put("orderindex", orderindex);
		indata.put("link_url", link_url);
		indata.put("target", target);
		indata.put("pid", pid);
		indata.put("group_id", group_id);
		indata.put("m", m);
		indata.put("a", a);
		indata.put("status", status);
		indata.put("logo", logo);
		indata.put("icon", icon);

		NavbarModel am=new NavbarModel();
		if(id==0) {
			am.insert(indata);
		}else {
			am.update(indata, "id="+id);
		}
		return Help.success(0, "保存成功");
	}
	@RequestMapping("/admin/navbar/status")
	public String Status(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id
	) {
		NavbarModel am=new NavbarModel();
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
	@RequestMapping("/admin/navbar/delete")
	public String delete(
			@RequestParam(value="token",defaultValue="") String token,
			@RequestParam(value="id",defaultValue="0") int id	
	) {
		NavbarModel am=new NavbarModel();
		Map indata= new HashMap();
		indata.put("status", 11);
		am.update(indata, "id="+id);
		return Help.success(0, "删除成功");
	}
	
	@RequestMapping("/admin/navbar/get")
	public String Get(@RequestParam(value="group_id",defaultValue="0") int group_id) {
		NavbarModel am=new NavbarModel();
		List list=am.where("group_id="+group_id+" AND status=1").order("orderindex ASC").select();
		//
		int len=list.size();
		ArrayList glist=new ArrayList();
		for(int i=0;i<len;i++) {
			JSONObject json=(JSONObject) JSONObject.toJSON(list.get(i));
			if(json.getIntValue("pid")==0) {
				json.put("child", new ArrayList());
				glist.add(json);
			}
		}
		int glen=glist.size();
		ArrayList child=new ArrayList();
		ArrayList pList=new ArrayList();
		for(int j=0;j<glen;j++) {
			child=new ArrayList();
			JSONObject pjson=(JSONObject) JSONObject.toJSON(glist.get(j));
			for(int i=0;i<len;i++) {
				JSONObject json=(JSONObject) JSONObject.toJSON(list.get(i));
				if(json.getIntValue("pid")==pjson.getIntValue("id")) {
					child.add(json);
				}
			}
			pjson.put("child", child);
			pList.add(pjson); 
		}
		
		Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("list", pList);
        return JSON.toJSONString(redata);
	}
}