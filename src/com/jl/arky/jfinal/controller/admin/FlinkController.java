package com.jl.arky.jfinal.controller.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jl.arky.jfinal.model.FlinkModel;

public class FlinkController extends Controller{
	
	
	public void toAdd(){
		render("add.html");
	}
	
	
	public void list(){
		List<FlinkModel> find = FlinkModel.dao.find("select * from flink order by id desc");
		setAttr("flinkModel",find);
		
		render("index.html");
	}
	
	public void save() {
		getModel(FlinkModel.class).save();
		redirect("/Admin/Flink/list");
	}
	
	
	public void toedit() {
		setAttr("flinkModel", FlinkModel.dao.findById(getPara("id")));
		
		render("edit.html");
	}

	public void delete() {
		String id=getPara("id");
		
		System.out.println(id+"---------------------"+getParaToInt());
		if(id!=null){
			String[] ids = id.split(",");
			for(int i=0;i<ids.length;i++){
				FlinkModel.dao.deleteById(ids[i]);
			}		
		}	
		redirect("/Admin/Flink/list");	
	}
	
	public void update() {
		getModel(FlinkModel.class).update();
		redirect("/Admin/Flink/list");
	}
	

}
