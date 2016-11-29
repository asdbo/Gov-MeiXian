package com.jl.arky.jfinal.controller.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jl.arky.jfinal.interceptor.LoginInterceptor;
import com.jl.arky.jfinal.model.LetterModel;
import com.jl.arky.jfinal.model.Letter_Dept_Model;
import com.jl.arky.jfinal.model.Letter_Type_Model;


public class LetterController extends Controller {
	public void index() {
		List<Model> letterModel = LetterModel.dao.find(
				"select id,people_name,theme,content,letter_dept,receive_time,letter_type,state from letter order by receive_time desc");
		List<Model> letterTypes = Letter_Type_Model.dao.getLetterTypes();
		List<Model> letterDepts = Letter_Dept_Model.dao.getLetterDept();
		setAttr("letterTypes", letterTypes);
		setAttr("letterDepts", letterDepts);
		setAttr("letterModel", letterModel);
		
		render("index.html");
	}

	// 前台界面的访问
	public void showfirst() {
		List<Model> showFirst = LetterModel.dao.showFirst();
		setAttr("showFirst", showFirst);
		render("showFirst");
	}

	// 后台根据投诉查询
	public void showComplain() {
		List<Model> letterModel = LetterModel.dao.find(
				"SELECT id,people_name,theme,content,letter_dept,receive_time,letter_type,state FROM letter where letter_type = 2  ORDER BY receive_time DESC");
		setAttr("letterModel", letterModel);
		
		List<Model> letterTypes = Letter_Type_Model.dao.getLetterTypes();
		List<Model> letterDepts = Letter_Dept_Model.dao.getLetterDept();
		setAttr("letterTypes", letterTypes);
		setAttr("letterDepts", letterDepts);
		render("index.html");
	}

	// 后台根据咨询查询
	public void showConsult() {
		List<Model> letterModel = LetterModel.dao.find(
				"SELECT id,people_name,theme,content,letter_dept,receive_time,letter_type,state FROM letter where letter_type = 1  ORDER BY receive_time DESC");
		setAttr("letterModel", letterModel);
		
		List<Model> letterTypes = Letter_Type_Model.dao.getLetterTypes();
		List<Model> letterDepts = Letter_Dept_Model.dao.getLetterDept();
		setAttr("letterTypes", letterTypes);
		setAttr("letterDepts", letterDepts);
		render("index.html");
	}

	// 后台根据表扬查询
	public void showAdmire() {
		List<Model> letterModel = LetterModel.dao.find(
				"SELECT id,people_name,theme,content,letter_dept,receive_time,letter_type,state FROM letter where letter_type = 4  ORDER BY receive_time DESC");
		setAttr("letterModel", letterModel);
		
		List<Model> letterTypes = Letter_Type_Model.dao.getLetterTypes();
		List<Model> letterDepts = Letter_Dept_Model.dao.getLetterDept();
		setAttr("letterTypes", letterTypes);
		setAttr("letterDepts", letterDepts);
		render("index.html");
	}

	// 后台根据建议查询
	public void showSuggest() {
		List<Model> letterModel = LetterModel.dao.find(
				"SELECT id,people_name,theme,content,letter_dept,receive_time,letter_type,state FROM letter where letter_type = 3  ORDER BY receive_time DESC");
		setAttr("letterModel", letterModel);
		
		List<Model> letterTypes = Letter_Type_Model.dao.getLetterTypes();
		List<Model> letterDepts = Letter_Dept_Model.dao.getLetterDept();
		setAttr("letterTypes", letterTypes);
		setAttr("letterDepts", letterDepts);
		render("index.html");
	}

	// 后台根据区长
	public void showQuZhang() {
		List<Model> letterModel = LetterModel.dao.find(
				"SELECT id,people_name,theme,content,letter_dept,receive_time,letter_type,state FROM letter where letter_type = 5  ORDER BY receive_time DESC");
		setAttr("letterModel", letterModel);
		
		List<Model> letterTypes = Letter_Type_Model.dao.getLetterTypes();
		List<Model> letterDepts = Letter_Dept_Model.dao.getLetterDept();
		setAttr("letterTypes", letterTypes);
		setAttr("letterDepts", letterDepts);
		render("index.html");
	}

	// 后台根据书记
	public void showShuJi() {
		List<Model> letterModel = LetterModel.dao.find(
				"SELECT id,people_name,theme,content,letter_dept,receive_time,letter_type,state FROM letter where letter_type = 6  ORDER BY receive_time DESC");
		setAttr("letterModel", letterModel);
		
		List<Model> letterTypes = Letter_Type_Model.dao.getLetterTypes();
		List<Model> letterDepts = Letter_Dept_Model.dao.getLetterDept();
		setAttr("letterTypes", letterTypes);
		setAttr("letterDepts", letterDepts);
		render("index.html");
	}

	// 前台查询信件所有信息
	public void showTable() {
		List<Model> letterModel = LetterModel.dao.find(
				"select id,letter_type,theme,people_name,receive_time,reply_time,letter_dept from letter where reply_time<>'null' order by reply_time desc");
		setAttr("letterModel", letterModel);
		render("index.html");
	}

	// 前台查询抱怨信件
	public void complain() {
		List<Model> letterModel = LetterModel.dao.find(
				"select id,letter_type,theme,people_name,receive_time,reply_time,letter_dept from letter where letter_type = '投诉' AND reply_time<>'null' order by reply_time desc");
		setAttr("letterModel", letterModel);
		// render("index.html");
	}

	// 前台查询咨询信件
	public void consult() {
		List<Model> letterModel = LetterModel.dao.find(
				"select id,letter_type,theme,people_name,receive_time,reply_time,letter_dept from letter where letter_type = '咨询' AND reply_time<>'null' order by reply_time desc");
		setAttr("letterModel", letterModel);
		// render("index.html");
	}

	// 前台查询表扬信件
	public void admire() {
		List<Model> letterModel = LetterModel.dao.find(
				"select id,letter_type,theme,people_name,receive_time,reply_time,letter_dept from letter where letter_type = '表扬' AND reply_time<>'null' order by reply_time desc");
		setAttr("letterModel", letterModel);
		// render("index.html");
	}

	// 前台查询建议信件
	public void suggest() {
		List<Model> letterModel = LetterModel.dao.find(
				"select id,letter_type,theme,people_name,receive_time,reply_time,letter_dept from letter where letter_type = '建议' AND reply_time<>'null' order by reply_time desc");
		setAttr("letterModel", letterModel);
		// render("index.html");
	}
	
	

	// 前后台都可以用，查看详情
	public void showDetail() {
		Model letterModel = LetterModel.dao.findById(getParaToInt());
		setAttr("letterModel", letterModel);
		
		List<Model> letterTypes = Letter_Type_Model.dao.getLetterTypes();
		List<Model> letterDepts = Letter_Dept_Model.dao.getLetterDept();
		setAttr("letterTypes", letterTypes);
		setAttr("letterDepts", letterDepts);
		render("showDetail.html");
	}

	// 前台用于保存用户的分数数据
	public void saveScore() {
		Db.update("update letter set score=? where id=?", getPara("scoreradio"), getParaToInt());
		redirect("/Admin/Letter");
	}

	// 后台管理员回复
	public void reply() {
		Model letterModel = LetterModel.dao.findById(getPara("id"));
		setAttr("letterModel", letterModel);
		render("reply.html");
	}

	// 后台保存管理员的回复
	public void saveReply() {
		String dateString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		Db.update("update letter set reply_time=?,state=?,reply_content=? where id=?", dateString, 1,
				getPara("content"), getPara("id"));
		redirect("/Admin/Letter");
	}

	// 前台的信件增加
	public void add() {
		List<Model> letterTypes = Letter_Type_Model.dao.getLetterTypes();
		List<Model> letterDepts = Letter_Dept_Model.dao.getLetterDept();
		setAttr("letterTypes", letterTypes);
		setAttr("letterDepts", letterDepts);
		render("add.html");
	}

	// 前台保存用户填写的数据
	public void save() {
		String dateString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		getModel(LetterModel.class).set("state", 0).set("receive_time", dateString).save();
		redirect("/Admin/Letter");
	}

	public void update() {
		getModel(LetterModel.class).update();
		redirect("/Admin/Letter");
	}

	public void delete() {
		

		String id=getPara("id");
		if(id!=null){
			String[] ids = id.split(",");
			for(int i=0;i<ids.length;i++){
				LetterModel model = (LetterModel) LetterModel.dao.findById(ids[i]);
				model.delete();

			}
			
		}
		
		redirect("/Admin/Letter");
	}




}
