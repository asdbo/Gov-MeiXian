package com.jl.arky.jfinal.controller.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jl.arky.jfinal.model.ChannelModel;

public class ChannelController extends Controller{
	
	
	public void toAdd(){
		List<ChannelModel> find = ChannelModel.dao.find("select * from channel");
		setAttr("channelModel",find);
		render("add.html");
	}
	
	
	public void list(){
		List<ChannelModel> find = ChannelModel.dao.find("select * from channel order by id desc");
		setAttr("channelModel",find);
		List<ChannelModel> find1 = ChannelModel.dao.find("select id,title from channel order by id desc");
		setAttr("channelModel1",find1);
		render("index.html");
	}
	
	public void save() {
		getModel(ChannelModel.class).save();
		redirect("/Admin/Channel/list");
	}
	
	
	public void toedit() {
		setAttr("channelModel", ChannelModel.dao.findById(getPara("id")));
		List<ChannelModel> find = ChannelModel.dao.find("select * from channel where id <> ?",getPara("id"));
		setAttr("channelModel1",find);
		render("edit.html");
	}

	public void delete() {
		ChannelModel.dao.deleteById(getParaToInt());
		redirect("/Admin/Channel/list");
	}
	
	public void update() {
		getModel(ChannelModel.class).update();
		redirect("/Admin/Channel/list");
	}
	


}
