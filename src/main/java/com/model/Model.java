package com.model;
import com.alibaba.fastjson.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import  com.alibaba.fastjson.JSON;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.String.*;
import java.util.concurrent.ExecutionException;

public class Model {
    public  JdbcTemplate DB;
    public  Boolean conn=false;
    public String table="";
    public String table_pre="sky_";
    public String preTable="";
    public String where=" 1 ";
    public String fields="*";
    public String order="";
    public Integer start=0;
    public Integer limit=100;

    public  Model(){
        this.setHost();
    }

    public  Boolean  setHost(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///mmLang");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        try {
            this.DB = new JdbcTemplate(dataSource);
            this.conn=true;
        }catch (Exception e){
            this.conn=false;
            return false;
        }
        return true;

    }
    public  String pTable(String table){
        return this.table_pre+table;

    }
    public  Model M(String table){
        if(!this.conn){
            this.setHost();
        }
        this.table=this.table_pre+table;
        return this;
    }
    public Model MM(String module,String table){
        if(!this.conn){
            this.setHost();
        }
        this.table=this.table_pre+table;
        return this;
    }
    public String implode(List list){
        String str="";
        int len=list.size();
        for(int i=0;i<len;i++){
            if(i>0){
                str+=",";
            }
            str+="'"+list.get(i)+"'";
        }
        return str;
    }
    public List getAll(String sql,Object... value){
        sql=String.format(sql,value);
       // System.out.println(sql);
        return  this.DB.queryForList(sql);
    }
    public Map getRow(String sql,Object... value){
        sql=String.format(sql,value);
        try{
            return this.DB.queryForMap(sql);
        }catch (Exception e){
            return new HashMap();
        }

    }
    public Integer insert(Map<String,Object> map){
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 自增主键Holder
        String sql="insert into "+ this.preTable +" set ";
        Integer i=0;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if(i>0){
                sql=sql+",";
            }else{
                i=1;
            }
            sql =sql+" "+entry.getKey()+"= '"+entry.getValue().toString()+"' ";
        }
        int[] types = new int[]{};
        PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(sql,types);
        pscFactory.setReturnGeneratedKeys(true);
        Object[] objs = new Object[]{};
        PreparedStatementCreator psc = pscFactory.newPreparedStatementCreator(objs);
        this.DB.update(psc,keyHolder);
        if(keyHolder.getKey()==null) {
        	return 0;
        }else {
        	return keyHolder.getKey().intValue();
        }
       
    }
    public  Boolean update(Map<String,Object> map,String where){
        String sql="update  "+ this.preTable +" set ";
        Integer i=0;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if(i>0){
                sql=sql+",";
            }else{
                i=1;
            }
            sql =sql+" "+entry.getKey()+"= '"+entry.getValue().toString()+"' ";
        }
        sql+=" where "+where;
        System.out.print(sql);
        this.DB.update(sql);
        return true;
    }
    public   Boolean delete(String where){
        String sql="delete  from  "+ this.preTable +" where "+where;
        this.DB.update(sql);
        return true;
    }
    public Boolean initSql(){
        this.where=" 1 ";
        this.fields="*";
        this.order="";
        this.start=0;
        this.limit=100;

        return true;
    }
    public Model fields(String fields){
        this.fields=fields;
        return this;
    }
    public Model where(String where){
        this.where=where;
        return this;
    }
    public Model order(String order){
        this.order=order;
        return this;
    }
    public Model limit(Integer start,Integer limit){
        this.start=start;
        this.limit=limit;
        return this;
    }
    public List select(){

        String sql="SELECT "+this.fields+" from "+this.preTable
                +" where "+this.where;
        if(this.order!=""){
            sql+=" order by "+this.order;
        }
        sql+=" limit "+this.start+","+this.limit+ "";
        this.initSql();
        return this.getAll(sql);

    }
    public  Map selectRow(){
        String sql="SELECT "+this.fields+" from "+this.preTable
                +" where "+this.where;
        if(this.order!=""){
            sql+=" order by "+this.order;
        }
        sql+=" limit "+this.start+",1";
        //System.out.println(sql);
        this.initSql();
        try{
            Map row=this.getRow(sql);

            return row;
        }catch (Exception e){
            return new HashMap();
        }

    }
    public  String selectOne(){
        this.fields+=" AS ct ";
        String sql="SELECT "+this.fields+" from "+this.preTable
                +" where "+this.where;
        if(this.order!=""){
            sql+=" order by "+this.order;
        }
        sql+=" limit "+this.start+",1";
        this.initSql();
        try{
            Map<String, Object> row=this.getRow(sql);
            if(row.size()==0){
                return "";
            }
           
            return row.get("ct")+"";
        }catch (Exception e){
            return "";
        }


    }
    public List selectCols(){
        this.fields+=" AS ct ";
        String sql="SELECT "+this.fields+" from "+this.preTable
                +" where "+this.where;
        if(this.order!=""){
            sql+=" order by "+this.order;
        }
        sql+=" limit "+this.start+","+this.limit+ "";
        this.initSql();
        List res = this.DB.queryForList(sql);
        List map=new ArrayList();
        for(int i=0;i<res.size();i++){
            Object object= res.get(i);
            String jsonStr = JSONObject.toJSONString(object);
            JSONObject jsonObject = JSONObject.parseObject(jsonStr);
            map.add(jsonObject.get("ct"));
        }

        return map;

    }
    public List Dselect(){
        return this.select();
    }
    
    public Map postData() {
    	
    	return new HashMap();
    }
    
}
