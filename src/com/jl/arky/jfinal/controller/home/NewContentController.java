package com.jl.arky.jfinal.controller.home;

import java.util.ArrayList;
import java.util.Collections;

import com.jfinal.core.Controller;
import com.jl.arky.jfinal.model.ChannelModel;
import com.jl.arky.jfinal.model.NewsModel;
import com.jl.arky.jfinal.utils.TimeUtil;

//管理新闻详情
public class NewContentController extends Controller{
	public void index(){
		String newid = getPara("newid");
		NewsModel model = NewsModel.dao.findById(newid);//获取该新闻
		model.set("looks", model.getInt("looks")+1).update();
		String date = TimeUtil.detailTime(model.getLong("TIME"));
		model.set("TIME", date);
		setAttr("new", model);
		//判断该新闻的属于哪个第一栏目
		int cid = model.get("cid");
		ArrayList<ChannelModel> cms=new ArrayList<ChannelModel>();
		ChannelModel cm = ChannelModel.dao.findById(cid);
		cms.add(cm);
		int pid=cm.getInt("pid");
		this.pchannel(pid, cms);
		Collections.reverse(cms);
		setAttr("cms", cms);
		render("detail.html");
	}
	private void pchannel(int cid,ArrayList<ChannelModel> cms){
		if(cid!=0){
			ChannelModel model = ChannelModel.dao.findById(cid);
			cms.add(model);
			int pid;
			if((pid=model.getInt("pid"))!=0){
				this.pchannel(pid, cms);
			}else{
				this.selected(model.getInt("id"));
			}
		}
		
	}
	private void selected(int id){
		switch(id){
		case 1:setAttr("selected", ".index");break;
		case 3:setAttr("selected", ".intro");break;
		case 4:setAttr("selected", ".open");break;
		case 112:setAttr("selected", ".internet");break;
		case 275:setAttr("selected", ".service");break;
		case 7:setAttr("selected", ".reply");break;
		default :setAttr("selected", ".no");break;
		}
		
		
	}
}
