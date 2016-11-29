package com.jl.arky.jfinal.controller.admin;

import java.util.List;


import com.jfinal.core.Controller;
import com.jl.arky.jfinal.model.NavigationModel;

public class NavigationController extends Controller {
	public void toAdd() {
		List<NavigationModel> find = NavigationModel.dao.find("select * from navigation where pid = ?", 0);
		setAttr("navigationModel", find);
		render("add.html");
	}

	public void list() {
		List<NavigationModel> find = NavigationModel.dao.find("select * from navigation order by id desc");
		setAttr("navigationModel", find);
		List<NavigationModel> find1 = NavigationModel.dao.find("select id,title from navigation order by id desc");
		setAttr("navigationModel1", find1);
		render("index.html");
	}

	public void save() {
		int pid = getParaToInt("navigationModel.pid");
		if (pid == 0)// 是否一级目录
		{
			getModel(NavigationModel.class).set("link", "").save();
		} else {
			getModel(NavigationModel.class).save();
		}
		redirect("/Admin/Navigation/list");
	}

	public void toedit() {
		setAttr("navigationModel", NavigationModel.dao.findById(getPara("id")));
		List<NavigationModel> find = NavigationModel.dao.find("select * from navigation where pid = ?", 0);
		setAttr("navigationModel1", find);
		render("edit.html");
	}

	public void delete() {
		String id = getPara("id");
		if (id != null) {
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				NavigationModel.dao.deleteById(ids[i]);
			}
		}
		redirect("/Admin/Navigation/list");
	}

	public void update() {
		int pid = getParaToInt("navigationModel.pid");
		String link = getPara("navigationModel.link");
		if (pid == 0)// 是否一级目录
		{
			getModel(NavigationModel.class).set("link", "").update();
		} else if (!"".equals(link)) {
			getModel(NavigationModel.class).update();
		}
		redirect("/Admin/Navigation/list");
	}
}
