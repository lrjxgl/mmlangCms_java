package com.deitui.morelang.forum.index;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.deitui.morelang.forum.model.ForumCategoryModel;
import com.deitui.morelang.forum.model.ForumGroupModel;
import com.deitui.morelang.forum.model.ForumModel;
import com.deitui.morelang.forum.model.ForumTagsModel;
import com.deitui.morelang.index.model.AdModel;
import com.deitui.morelang.index.model.FollowModel;
import com.deitui.morelang.index.model.UserModel;
import com.model.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model.AppConfig;
import com.model.Help;
import com.model.Login;
@RestController
@CrossOrigin("*")
public class ForumController {
    @RequestMapping("/forum/index")
    public String Index(){
        ForumTagsModel tags=new ForumTagsModel();
        List recList=tags.getForumByKey("index");
        AdModel ad=new AdModel();
        List navList= ad.listByNo("uniapp-forum-nav",100);
        List adList= ad.listByNo("uniapp-forum-ad",100);
        List flashList= ad.listByNo("uniapp-forum-index",100);
        //返回json设置
        Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("recList",recList);
        redata.put("navList",navList);
        redata.put("flashList",flashList);
        redata.put("adList",adList);
        return JSON.toJSONString(redata);
    }
    
    @RequestMapping("/forum/new")
    public String New(
    		@RequestParam(value="token",defaultValue="") String token,
    		@RequestParam(value="gid",defaultValue="0") int gid,
    		@RequestParam(value="catid",defaultValue="0") int catid
    ){
    	ForumGroupModel gp=new ForumGroupModel();
     
    	ForumModel forumModel=new ForumModel();
    	String where=" status in(0,1) ";
    	
    	List list=forumModel.where(where).order("id DESC").Dselect();
    	
    	Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("list", list);

        return JSON.toJSONString(redata);
    }
    
    @RequestMapping("/forum/list")
    public String List(
    		@RequestParam(value="token",defaultValue="") String token,
    		@RequestParam(value="gid",defaultValue="0") int gid,
    		@RequestParam(value="catid",defaultValue="0") int catid
    ){
    	ForumGroupModel gp=new ForumGroupModel();
    	Map group=gp.where("gid="+gid).selectRow();
    	ForumModel forumModel=new ForumModel();
    	String where=" gid="+gid;
    	if(catid!=0) {
    		where+=" AND catid="+catid;
    	}
    	List list=forumModel.where(where).Dselect();
    	ForumCategoryModel fc=new ForumCategoryModel();
    	List catList=fc.where("gid="+gid).select();
    	Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("list", list);
        redata.put("catList", catList);
        redata.put("group", group);
        return JSON.toJSONString(redata);
    }
    @RequestMapping("/forum/show")
    public String Show(
    		@RequestParam(value="token",defaultValue="") String token,
    		@RequestParam(value="id",defaultValue="") int id
    ){
    	int ssuserid=Login.isLogin(token);
    	ForumModel forumModel=new ForumModel();
    	Map data=forumModel.where("id="+id).selectRow();
    	UserModel u=new UserModel();
    	Map author=u.get(Integer.parseInt(data.get("userid")+""),"");
    	//判断是否关注
    	int userid= Integer.parseInt(data.get("userid")+"");
    	FollowModel fm=new FollowModel();
    	author.put("isFollow", fm.isFollow(ssuserid, userid));
    
    	Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("data",data);
        redata.put("author", author);
        return JSON.toJSONString(redata);
    }
    
    @RequestMapping("/forum/search")
    public String search(@RequestParam(value="keyword",defaultValue="") String keyword ) {
    	ForumModel forumModel=new ForumModel();
    	List list=new ArrayList();
    	String where="";
    	if(keyword!="") {
    		where=" status in(0,1) AND title like '%"+keyword+"%' ";
    		list=forumModel.where(where).Dselect();
    		
    	}
    	
    	
    	Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        redata.put("keyword", keyword);
        redata.put("list", list);
        return JSON.toJSONString(redata);
    }
    
    @RequestMapping("/forum/addclick")
    public String addClick(
    		@RequestParam(value="token",defaultValue="") String token,
    		@RequestParam(value="id",defaultValue="") int id
    ) {
    	int userid=Login.isLogin(token);
    	ForumModel forumModel=new ForumModel();
    	Map forum=forumModel.where("id="+id).selectRow();
    	Map data=new HashMap();
    	data.put("view_num",  Integer.parseInt(forum.get("view_num")+"") +1);
    	forumModel.update(data, "id="+id);
    	Map<String,Object> redata=new HashMap<String,Object>();
        redata.put("error",0);
        redata.put("message","succcess");
        
        return JSON.toJSONString(redata);
    }
    @RequestMapping("/forum/add")
    public String Add(
    		@RequestParam(value="token",defaultValue="") String token,
    		@RequestParam(value="id",defaultValue="0") int id
    	){
    	int userid=Login.isLogin(token);
		if(userid==0) {
			return Login.unLogin();
		}
		Map data=new HashMap();
	 
		 ArrayList imgList=new ArrayList();
		if(id!=0) {
			ForumModel f=new ForumModel();
			data=f.where("id="+id).selectRow();
			data.put("true_videourl", Help.image_site(data.get("videourl")+""));
			String imgsdata=data.get("imgsdata")+"";
            String imgs[]=imgsdata.split(",");
           
            for(int ii=0;ii<imgs.length;ii++){
            	Map mp=new HashMap();
            	mp.put("imgurl", imgs[ii]);
            	mp.put("trueimgurl", Help.image_site(imgs[ii]));
                imgList.add(mp);
            }
            //内容
            Model m=new Model();
            m.preTable=m.table_pre+"mod_forum_data";
            String content= m.where("id="+id).fields(" content ").selectOne();
           
            data.put("content", content);
		}
		Map<String,Object> redata=new HashMap<String,Object>();
		//grouplist
		ForumGroupModel fgModel=new ForumGroupModel();
		List  grouplist=fgModel.where("status=1").select();
		ForumCategoryModel fcModel=new ForumCategoryModel();
		List catList=fcModel.where("status=1").select();
		ArrayList gcats=new ArrayList();
		
		ArrayList gList=new ArrayList();
		
		if(catList.size()>0) {
			for(Object g: grouplist) {
				JSONObject gg=JSONObject.parseObject(JSON.toJSONString(g));
				gcats=new ArrayList();
				for(Object c:catList) {
					JSONObject cc=JSONObject.parseObject(JSON.toJSONString(c));
					if(cc.get("gid").equals(gg.get("gid"))) {
						gcats.add(cc);
					}
					
				}
				gg.put("child", gcats);
				gList.add(gg);
			}
		}
		redata.put("groupList", gList);
		redata.put("data", data);
		redata.put("imgList", imgList); 
		return JSON.toJSONString(redata);
    }
    
    @RequestMapping("/forum/save")
    public String Save(
    		@RequestParam(value="token",defaultValue="") String token,
    		@RequestParam(value="id",defaultValue="0") int id,
    		@RequestParam(value="title",defaultValue="") String title,
    		@RequestParam(value="gid",defaultValue="0") int gid,
    		@RequestParam(value="catid",defaultValue="0") int catid,
    		@RequestParam(value="content",defaultValue="") String content,
    		@RequestParam(value="imgsdata",defaultValue="") String imgsdata,
    		@RequestParam(value="videourl",defaultValue="") String videourl
    		
    ) {
    	int userid=Login.isLogin(token);
		if(userid==0) {
			return Login.unLogin();
		}
		ForumModel forumModel=new ForumModel();
		Map data=new HashMap();
		data.put("title", title);
		
		data.put("gid", gid);
		data.put("catid", catid);
		 
		data.put("imgsdata", imgsdata);
		data.put("videourl", videourl);
		data.put("userid", userid);
		int time=(int) (new Date().getTime()/1000);
		data.put("dateline", time);
		 Model m=new Model();
         m.preTable=m.table_pre+"mod_forum_data";
         Map sdata=new HashMap();
         sdata.put("content", content);
			
		if(id==0) {
			id=forumModel.insert(data);
			
			sdata.put("id", id);
			sdata.put("dateline", time);
			forumModel.insert(sdata);
		}else {
			forumModel.update(data,"id="+id);
			m.update(sdata,"id="+id);
		}
		
		 
		
    	return Help.success(0, "保存成功");
    }
    @RequestMapping("/forum/my")
    public String My(
    	@RequestParam(value="token",defaultValue="") String token
    ) {
    	int userid=Login.isLogin(token);
		if(userid==0) {
			return Login.unLogin();
		}
		ForumModel f=new ForumModel();
		List list=f.where("userid="+userid+" AND status in(0,1,2) ").Dselect();
		Map<String,Object> redata=new HashMap<String,Object>();
		redata.put("error",0);
        redata.put("message","succcess");
        redata.put("list", list);
		return JSON.toJSONString(redata);
    }
    
    @RequestMapping("/forum/delete")
    public String Delete(
    		@RequestParam(value="token",defaultValue="") String token,
    		@RequestParam(value="id",defaultValue="0") int id
    ) {
    	int userid=Login.isLogin(token);
		if(userid==0) {
			return Login.unLogin();
		}
		ForumModel f=new ForumModel();
		Map data=new HashMap();
		data.put("status",12);
		f.update(data, "id="+id);
		return Help.success(0, "success");
    }
    
    
    @RequestMapping("/forum/user")
    public String User(@RequestParam(value="token",defaultValue="") String token) {
    	
    	int userid=Login.isLogin(token);
		if(userid==0) {
			return Login.unLogin();
		}
		UserModel userModel=new UserModel();
		Map<String,Object> user=userModel.get(userid,"");
		Map<String,Object> redata=new HashMap<String,Object>();
		redata.put("user", user);
		redata.put("topic_num", 1);
		redata.put("comment_num", 1);
		return JSON.toJSONString(redata);
    }
    
}