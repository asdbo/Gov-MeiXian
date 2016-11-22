package com.jl.arky.jfinal.controller.admin;







import java.util.Date;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jl.arky.jfinal.model.ChannelModel;
import com.jl.arky.jfinal.model.RssChannelModel;
import com.jl.arky.jfinal.model.RssModel;


/*
 *Rss控制类 
 */

public class RssController extends Controller{
	/*
	 * 默认RssController路由
	 * 
	 */
   public void index(){
	   /*
	    * 查看Rss记录
	    */
	  
	   RssModel rssModel=new RssModel();
	   List<RssModel> rsslist= rssModel.find("select * from rss_items");
	   setAttr("rsslist",rsslist);
	   render("index.html");
   }
   /*
    * 向数据库添加一条item
    */
   public void additem(){
	   RssModel rssModel=getModel(RssModel.class);
	   //此处获取
	  Date date=new Date();
	   System.out.println(date);
	   rssModel.set("pubdate", date.toString());
	  
	   rssModel.save();
	   
	  redirect("/Admin/Rss");
	   
	   
   }
   /*
    * 从数据库删除一条item
    */
   public void deleteitem(){
//	   int id=getParaToInt("id");
//	   RssModel rssModel=getModel(RssModel.class);
//	   rssModel.deleteById(id);
   }
   /*
    * 添加item页面的转换
    */
   public void addViewChange(){
	   List<ChannelModel> list=ChannelModel.dao.find("SELECT * FROM channel WHERE"
				+ " pid !=0 AND  id NOT IN (SELECT pid FROM channel)");
//		for(int i=0;i<list.size();i++)
//		{
//			System.out.println(list.get(i).get("title"));
//		}
	   //此处查询栏目个数
	   List<ChannelModel> channelList=ChannelModel.dao.find("select * from rss_channel");
	   setAttr("channelList",channelList);
	   render("edit.html");
   }
   /*
    * 删除item页面的转换
    */
   public void  deleteViewChange(){
	   
   }
   /*
    * 根据主题id返回频道
    */
   public void showRss(){
	    int tid=getParaToInt("tid");
	    RssModel rssModel=new RssModel();
	    List<RssModel> rsslist=rssModel.dao.find("select * from rss_items where tid=?",tid);
	    setAttr("rsslist",rsslist);
		   render("RssList.html");
          
        
   }
   /*
    * 控制Rss链接的跳转
    */
   public void toRss(){
//	   List<RssChannelModel> rsslist=RssChannelModel.dao.find("select * from rss_channel");
	   int pageNumber=0;
	   System.out.println(getParaToInt("pageNumber"));
	   if(getParaToInt("pageNumber")==null)
	   {
		   pageNumber=1;
	   }else{
		   pageNumber=getParaToInt("pageNumber");
	   }
	  Page<RssChannelModel> rsslist=RssChannelModel.dao.paginate(pageNumber, 20, "select *"," from rss_channel");
	   setAttr("rsslist",rsslist);
	   render("rss.html");
   }
   /*
    * 添加Rsschannel
    */
   public void addRssChannel(){
	  
		
		/*
		 * 获取要添加channel的信息
		 */
		
		RssChannelModel rssChannelModel=getModel(RssChannelModel.class);
		rssChannelModel.save();
		
		renderText("添加Channel");
   }
   /*
    * 添加channel页面的转换
    */
   public void toaAddRssChannel(){
	   /*
	    * 此处查找栏目中最后一级
	    */
	   
	   List<ChannelModel> list=ChannelModel.dao.find("SELECT * FROM channel WHERE"
				+ " pid !=0 AND  id NOT IN (SELECT pid FROM channel)");
//		for(int i=0;i<list.size();i++)
//		{
//			System.out.println(list.get(i).get("title"));
//			RssChannelModel rssChannelModel=new RssChannelModel();
//			rssChannelModel.set("url", "http://www.meijiang.gov.cn/Admin/Rss/showRss?tid="+(i+1));
//			rssChannelModel.set("theme",list.get(i).get("title"));
//			rssChannelModel.save();
//			
//		}
		//将最后一级的目录上传给页面
		setAttr("channellist",list);
		
		render("channeledit.html");
   }
   
   
}
