package com.deitui.morelang.forum.model;

import com.model.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ForumTagsModel extends Model {
    public ForumTagsModel(){
        this.preTable=this.table_pre+"mod_forum_tags";
    }
    public List getForumByKey(String gkey){
        ForumModel forum=new ForumModel();
        Map tag=this.where("gkey='"+gkey+"' ").selectRow();
        if(tag.size()==0) {
        	return new ArrayList();
        }
        ForumTagsIndexModel tagIndex=new ForumTagsIndexModel();
        List ids= tagIndex.limit(0,10000).fields("objectid").where("tagid="+tag.get("tagid")).order("orderindex ASC").selectCols();
        String w=" id in("+this.implode(ids)+")";
        Integer limit= Integer.parseInt(tag.get("gnum").toString());
        List list=forum.where(w)
                .fields("id,title,userid,imgurl,imgsdata,description,createtime,view_num,love_num,comment_num")
                .limit(0,limit).Dselect();

        return list;
    }
}
