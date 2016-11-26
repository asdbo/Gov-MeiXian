package com.jl.arky.jfinal.controller.home;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.plaf.synth.SynthSpinnerUI;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Model;
import com.jl.arky.jfinal.model.CarouselModel;
import com.jl.arky.jfinal.model.ChannelModel;
import com.jl.arky.jfinal.model.LetterModel;
import com.jl.arky.jfinal.model.Letter_Dept_Model;
import com.jl.arky.jfinal.model.Letter_Type_Model;
import com.jl.arky.jfinal.model.NewsModel;
import com.jl.arky.jfinal.utils.TimeUtil;

//栏目选择的控制
public class ChannelContentController extends Controller{
	public void index(){
		String cid = getPara("cid");
		if("4".equals(cid)){
			redirect("/Home/Index/open");
			return;
		}
		if("13".equals(cid)){
			redirect("/Home/Index/leader");
			return;
		}
		if("15".equals(cid)){
			redirect("http://zwgk.gdmx.gov.cn/index/index?areacode=441421");
			return;
		}
		ChannelModel model = ChannelModel.dao.findById(cid);//获取该栏目
		
		//获取子栏目新闻标题
		List<ChannelModel> ids = ChannelModel.dao.find("select id,title from channel where pid=?",cid);
		if(ids.isEmpty()){
			//没有子栏目（则获取同级栏目）
			//该栏目的父栏目
			ChannelModel pcm = ChannelModel.dao.findById(model.get("pid"));
			List<ChannelModel> scms = ChannelModel.dao.find("select * from channel where pid=?",pcm.get("id"));
			this.schannel(scms);
			setAttr("schannels",scms );
			setAttr("channel", pcm);
			this.getNewTitle(Integer.parseInt(cid));
		}else{
			int []id=new int[ids.size()];
			ArrayList<ChannelModel> scms=new ArrayList<ChannelModel>();//子栏目
			for(int i=0;i<ids.size();i++){
				id[i]=ids.get(i).getInt("id");
				scms.add(ids.get(i));
			}
			this.schannel(scms);
			setAttr("schannels",scms );
			this.getNewTitle(id);
			setAttr("channel", model);//该栏目
		}
		//获取该栏目及父栏目
		ArrayList<ChannelModel> pcms=new ArrayList<ChannelModel>();
		pcms.add(model);
		this.pchannel(model.getInt("id"),model.getInt("pid"), pcms);
		Collections.reverse(pcms);//倒序
		setAttr("pchannels", pcms);
	
		render("news1.html");
	}
	//获取父栏目
	private void pchannel(int id,int cid,ArrayList<ChannelModel> cms){
		if(cid!=0){
			ChannelModel model = ChannelModel.dao.findById(cid);
			cms.add(model);
			this.pchannel(model.getInt("id"),model.getInt("pid"), cms);
			
		}
		else{
			this.selected(id);
		}
	}
	//获取子栏目
	public void schannel(List<ChannelModel> scms){
		for(ChannelModel cm:scms){
			List<ChannelModel> scs = ChannelModel.dao.find("select * from channel where pid=?",cm.get("id"));
			if(!scs.isEmpty()){
				cm.setScs(scs);
			}
		}
	}
	private void getNewTitle(int ...id){
		String sql1="from news where cid=? order by sort";
		String sql2="select count(*) num from news where cid=?";
		int page=1;
		String para = getPara("page");
		if(para!=null){
			page=Integer.parseInt(para);
		}
		int pageSzie=9;
		ArrayList<Integer> ids=new ArrayList<Integer>();
		ids.add(id[0]);
		if(id.length>1){
			sql1="from ((select t.*  from news t where t.cid=?)";
			for(int i=1;i<id.length;i++){
				ids.add(id[i]);
				sql1+="union (select t"+i+".* from news t"+i+" where t"+i+".cid=?)";
			}
			sql1+=") t0";
					
			sql2="select count(*) num "+sql1;
			sql1+=" order by sort";
		}
		NewsModel findFirst = NewsModel.dao.findFirst(sql2,ids.toArray());
		long count=findFirst.get("num");//新闻总数
		long pageCount= count%pageSzie==0?count/pageSzie:(int)(count/pageSzie+1);//总页数
		if(pageCount==0){
			pageCount=1;
		}
		
		List<NewsModel> list2 = NewsModel.dao.paginate(page, pageSzie, "select *", sql1,ids.toArray()).getList();
		//时间转换
		for(NewsModel nm:list2){
			nm.set("TIME", TimeUtil.MonthAndDay((long)nm.get("TIME")));
		}
		setAttr("nowPage", page);
		setAttr("pageCount", pageCount);
		setAttr("news", list2);
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
