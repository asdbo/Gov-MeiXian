package com.jl.arky.jfinal.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;
import com.jl.arky.jfinal.model.AdminModel;
import com.jl.arky.jfinal.model.ChannelModel;
import com.jl.arky.jfinal.model.MailModel;
import com.jl.arky.jfinal.model.NewsModel;
import com.jl.arky.jfinal.utils.TheadPool;
import com.jl.arky.jfinal.utils.ThreadMail;
import com.jl.arky.jfinal.utils.TimeUtil;

public class NewsController extends Controller {
	

	public void toAdd(){
		String sql="select * from channel where pid=?";
		List<ChannelModel> channels = ChannelModel.dao.find(sql,0);
		setAttr("channels", channels);
		render("add.html");
	}
	@Clear
	public void gethtml() throws IOException{
		String pid = getPara("pid");
		String src="<option value=-1>请选择...</option>";
		if(!pid.equals("-1")){
			String sql="select * from channel where pid=?";
			List<ChannelModel> channels = ChannelModel.dao.find(sql,pid);
			if(!channels.isEmpty()){
			for(ChannelModel cm:channels){
				src+="<option value="+cm.get("id")+">"+cm.get("title")+"</option>";
			}
			renderHtml(src);
			}
			else{
				renderHtml("");
			}
		}
	}
	public void toUpdate(){
		NewsModel newsModel = NewsModel.dao.findById(getPara("id"));
		String sql="select * from channel";
		List<ChannelModel> channels = ChannelModel.dao.find(sql);
		setAttr("channels", channels);
		setAttr("newsModel", newsModel);
		render("update.html");
	}
	//添加一条新闻
	public void addNew() throws Exception{
		
		NewsModel model = this.getModel();
		if(model!=null){
			model.save();
		}
		String cid = model.get("cid");
		System.out.println(cid);
		String newTitle = model.get("title");
		System.out.println(newTitle);
		//线程池5个
		TheadPool tp=new TheadPool(5);
		
		ChannelModel findById = ChannelModel.dao.findById(cid);
		String channeltitle = findById.get("title");
		
		//SELECT * FROM email
		List<MailModel> find = MailModel.dao.find("select * from email");
		for (MailModel mailModel : find) {
			String[] split = mailModel.getStr("subscribe").split(",");
			System.out.println(split[0]);
			for (int i = 0; i < split.length; i++) {
				if(cid.equals(split[i])){
					System.out.println(split[i]);
					String str = "<font color=\"" + "red\" size=\"" + "5px\">" + "您订阅的"+channeltitle+"已经更新</font><br>转到请点击："
							+ "http://www.meijiang.gov.cn/";
					
					tp.execute(new ThreadMail("13750528354@163.com", "13750528354@163.com", "313548323", mailModel.getStr("email"),
							newTitle, str));
				}
				
			}
		}
		redirect("/Admin/News/list");
		
	}
	//验证输入的数据是否为空
	public String verification(String src){
		if(src==null){
			return "";
		}
		return src;
	}
	//得到页面上的model
	public NewsModel getModel(){
		AdminModel user = getSessionAttr("AdminModel");
//		if(user!=null)
//		{
		String filePath="/attach/";
		UploadFile file = getFile("attach",filePath);
		String attchName = this.upload(file,filePath,"attach");//文件名
		filePath="/video/";
		UploadFile file2 = getFile("video",filePath);
		String videoName=this.upload(file2,filePath,"video");
		filePath="/images/";
		UploadFile file3 = getFile("newpic",filePath);
		String newpic=this.upload(file3,filePath,"pic");
		long time=System.currentTimeMillis()/1000;//时间戳(秒)
			NewsModel model = new NewsModel().set("attach",attchName ).set("video", videoName).set("TIME", time).set("aid",user.get("id")).set("looks", 0)
					.set("cid",verification(getPara("cid")) ).set("title", verification(getPara("title")))
					.set("writer", verification(getPara("writer"))).set("summary", verification(getPara("summary")))
					.set("sort", getPara("sort")==null?0:getPara("sort"))	.set("origin", verification(getPara("origin")))
					.set("newpic", newpic)
					.set("content", verification(getPara("content")));
			return model;
////		}
//		return null;
//		
		
	}
	//文件重命名
	public String upload(UploadFile file,String filePath,String type){
		if(file==null)
			{
				return "";
			}
		else{
			String substring = file.getFileName().substring(file.getFileName().lastIndexOf('.'));
			if("video".equals(type)){
				if(!substring.equals(".mov")&&!substring.equals(".mp4")&&!substring.equals(".avi")&&!substring.equals(".wmv")){
					return "";
				}
			}else if("attach".equals(type)){
				if(!substring.equals(".docx")&&!substring.equals(".pdf")&&!substring.equals(".xlsx")&&!substring.equals(".pptx")&&
					!substring.equals(".doc")&&!substring.equals(".xls")&&!substring.equals(".ppt")){
				return "";
				}
				
			}else if("pic".equals(type)){
				if(!substring.equals(".jpg")&&!substring.equals(".png")&&!substring.equals(".gif")&&!substring.equals(".PNG")&&
						!substring.equals(".JPG")&&!substring.equals(".GIF")){
					return "";
					}
			}
			
			else{
				return "";
			}
			
			String fileName="/Public/upload"+filePath+System.currentTimeMillis()+""+(int)(Math.random()*1000)+substring;
			File file2 = file.getFile();
			String contextPath = getRequest().getServletContext().getRealPath("/");
			 boolean renameTo = file2.renameTo(new File(contextPath+fileName));
			 System.out.println(renameTo);
			return fileName;
			
		}
	}
	//删除新闻
	public void deleteNew(){
		String id=getPara("id");
		if(id!=null){
			String[] ids = id.split(",");
			for(int i=0;i<ids.length;i++){
				NewsModel model = NewsModel.dao.findById(ids[i]);
				String videoName = model.get("video");
				String attachName = model.get("attach");
				String newpic=model.getStr("newpic");
				model.delete();
				this.deleteLoadfile(videoName);
				this.deleteLoadfile(attachName);
				this.deleteLoadfile(newpic);
			}
			
		}
		redirect("/Admin/News/list");
	}
	//删除上传的文件
	public void deleteLoadfile(String fileName){
		if(fileName!=""){
			String realPath = getRequest().getServletContext().getRealPath("/");
			File f=new File(realPath+fileName);
			if(f.exists()){
				f.delete();
			}
		}
	}
	//更新
	public void updateNew(){
		NewsModel model1 = this.getModel();
		if(model1!=null)
		{
			//获取以前上传的文件
			String id= getPara("id");
			NewsModel model = NewsModel.dao.findById(id);
			String attachUploadName = model.get("attach");
			String videoUploadName = model.get("video");
			//删除以前上传的文件
			this.deleteLoadfile(attachUploadName);
			this.deleteLoadfile(videoUploadName);
			
			//更新
			model1.set("id", id).update();
		}
		
				
		
		redirect("/Admin/News/list");
	}
	//获取数据
	public void list(){
		String sql="select news.id ,news.title, news.time,news.looks,news.newpic,channel.type,channel.title ct from news join channel on news.cid=channel.id";
		List<NewsModel> news=NewsModel.dao.find(sql);
		for(NewsModel nm:news){
			String date = TimeUtil.timeStampToDate((long)nm.get("time"));
			nm.set("TIME", date);
		}
		setAttr("news", news);
		render("index.html");
	}
	
	
}
