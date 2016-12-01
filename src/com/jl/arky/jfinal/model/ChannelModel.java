package com.jl.arky.jfinal.model;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class ChannelModel extends Model<ChannelModel>{
	public static final ChannelModel dao = new ChannelModel();
	private List<ChannelModel> children = new ArrayList<ChannelModel>(); 
	
	public List<ChannelModel> getChildren() {
		children=dao.find("select * from channel where pid = ?",this.get("id"));
		for(int i=0;i<children.size();i++){
			System.out.println(children.get(i).get("title"));
		}
		return children;
	}
	
	public Page<ChannelModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from channel order by id desc");
	}
	private List<ChannelModel> scs =new ArrayList<ChannelModel>();
	private List<NewsModel> news=new ArrayList<NewsModel>();
	public void setScs(List<ChannelModel> scs) {
		this.scs = scs;
	}
	
	public List<ChannelModel> getScs() {
		return scs;
	}
	public void setNews(List<NewsModel> news) {
		this.news = news;
	}
	public List<NewsModel> getNews() {
		return news;
	}

	
}
