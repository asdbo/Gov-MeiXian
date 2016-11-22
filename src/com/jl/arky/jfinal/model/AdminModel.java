package com.jl.arky.jfinal.model;

import com.jfinal.plugin.activerecord.Model;

public class AdminModel extends Model<Model>{
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
}
