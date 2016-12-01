package com.jl.arky.jfinal.model;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

public class AdminModel extends Model<Model>{
	//当前拥有的权限列表
	List<String> urlList = new ArrayList<String>();
	
	//当前拥有的栏目权限列表
		List<Record> channelList= new ArrayList<Record>();
	
	public static final AdminModel dao = new AdminModel();
	public void add(){
		int age = (int)(Math.random()*100 +1);
		String name = "arky_" + age;
		int num = 123123;
		long time = System.currentTimeMillis()/1000;
		this.set("name", name)
			.set("age", age)
			.set("number", num)
			.set("time", time)
			.save();
	}
	public void mod(){
		AdminModel.dao.find("select * from admin where id<?", 12);
	}
	public static AdminModel find(int id){
		return (AdminModel) AdminModel.dao.findById(id);
	}
	public void del(){
		
	}
	/*
	 * 判断用户是否有url权限
	 */
	public boolean hasPermission(String url){
		//获取当前用户,并获取用户所拥有的权限
		int privilegeid;
		String url1;
		//可以考虑把urlList设置为缓存,减少对数据库的操作
		
		if(urlList.size()<1){
		int roleid = this.get("roleid");
		List<Record> roleList=Db.find("select * from role_privilege where roleid = ?",roleid);
		for(int i=0;i<roleList.size();i++){
			privilegeid = roleList.get(i).get("privilegeid");
		  url1 = Db.queryStr("select url from privilege where id = ?",privilegeid); 
		  urlList.add(url1);
		}
		}else{
		
		}
		
		
		
		if(urlList.contains(url)){
			return true;
		}else{
			return false;
		}
		
	
	}
	
	
	/*
	 * 判断用户是否有channel权限
	 */
	public boolean hasChannelPrivilege(int cid,String right){
		Record record;
		if(channelList.size()<1){
			int roleid=this.getInt("roleid");
			channelList=Db.find("select * from channel_right where rid = ?",roleid);
			System.out.println("这是栏目的个数"+channelList.size());
		}else{
			
		}
		
		
		for(int i=0;i<channelList.size();i++){
			 record=channelList.get(i);
//			 System.out.println(i);
//			 System.out.println(record.getInt("id"));
			 
			 
			if(record.get("cid").equals(cid)){
				System.out.println("<++++++++++++++++++++++++++++++++++++>");
				System.out.println(cid);
				System.out.println(record.get("cid"));
				System.out.println("拥有权限");
				System.out.println("<++++++++++++++++++++++++++++++++++++>");
			    return !record.getBoolean(right);
			}
			
		}
		
		return false;
		
		
		
	}
	
	/*
	 * 获取当前用户的所有权限
	 */
	public List<Model> getAllPermission(){
		List<Model> permissionList=R_P_Model.dao.find("select permissionid from role_permission where id = ?",this.get("roleid"));
		
		return permissionList;
		
	}
	/*
	 * 获取当前用户的所有的栏目权限
	 */
	public List<Model> getAllChannelPrivilege(){
		
		return null;
	}
	
	
}

	

