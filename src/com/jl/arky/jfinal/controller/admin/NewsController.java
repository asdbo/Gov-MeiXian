package com.jl.arky.jfinal.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
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
	//审核中
	public void check(){
		String id = getPara("id");
		NewsModel newModel = NewsModel.dao.findById(id);
		setAttr("newsModel", newModel);
		render("checkNew.html");
	}
	//审核通过
	public void checkOK(){
		String id = getPara("id");
		NewsModel newModel = NewsModel.dao.findById(id);
		newModel.set("state", 1).update();
		this.list();
	}
	//审核不通过
		public void checkNO(){
			String id = getPara("id");
			NewsModel newModel = NewsModel.dao.findById(id);
			newModel.set("state", -1).update();
			this.list();
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
			this.addLunece(model);
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
	//摘要
	public String summaryStr(String src){
		if(src.equals("")){
			src=verification(getPara("content"));
			  String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
			  Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
		       Matcher m_html=p_html.matcher(src); 
		       src=m_html.replaceAll("").trim(); //过滤html标签 
		     return src.length()>150?src.substring(0, 150):src;   
		}
		return src;
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
		if("".equals(newpic)){
			newpic="/Public/upload/images/wu.jpg";
		}
		long time=System.currentTimeMillis()/1000;//时间戳(秒)
			NewsModel model = new NewsModel().set("attach",attchName ).set("video", videoName).set("TIME", time).set("aid",user.get("id")).set("looks", 0)
					.set("cid",verification(getPara("cid")) ).set("title", verification(getPara("title")))
					.set("writer", verification(getPara("writer"))).set("summary", summaryStr(verification(getPara("summary"))))
					.set("sort", getPara("sort")==null?0:getPara("sort"))	.set("origin", verification(getPara("origin")))
					.set("newpic", newpic)
					.set("content", verification(getPara("content"))).set("state", 0);
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
			this.deleteLucene(id);
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
			this.updateLucene(model1, id);
		}
		
				
		
		redirect("/Admin/News/list");
	}
	//获取数据
	public void list(){
		
		String sql="select cid from channel_right where rid=?";
		AdminModel admin = (AdminModel) this.getSession().getAttribute("AdminModel");
		
		System.out.println(admin);
		List<Record> find = Db.find(sql,admin.get("roleid"));
		System.out.println(find.size());
		List<NewsModel> news=new ArrayList<>();
		for (Record record : find) {
			sql="select news.id ,news.title,news.cid, news.time,news.looks,news.state,news.newpic,channel.type,channel.title ct from news join channel on news.cid="+record.getInt("cid");
			
			List<NewsModel> ns=NewsModel.dao.find("select * from news where cid = ?",record.getInt("cid"));
			news.addAll(ns);
		}
		
		for(NewsModel nm:news){
			String date = TimeUtil.timeStampToDate((long)nm.get("time"));
			nm.set("TIME", date);
		}
		System.out.println("欧阳泽鹏"+news.size());
		setAttr("news", news);
		render("index.html");
//		renderText("添加成功");
	}
	

	//添加一条索引
	public void addLunece(NewsModel nm){
		Analyzer analyzer = new IKAnalyzer(false);//词法分析器。
		Directory directory = new RAMDirectory();//内存存储(可以存到磁盘)
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40,analyzer);
		IndexWriter iwriter =null;
		try {
			iwriter = new IndexWriter(directory, config);
			Document doc = new Document();//要添加的数据
			doc.add(new Field("id", nm.get("id").toString(),TextField.TYPE_STORED));
			doc.add(new Field("title", nm.get("title").toString(),TextField.TYPE_STORED));
			doc.add(new Field("time", nm.get("TIME").toString(),TextField.TYPE_STORED));
			doc.add(new Field("summary", nm.get("summary").toString(),TextField.TYPE_STORED));
			iwriter.addDocument(doc);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				iwriter.commit();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				iwriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	//删除一条索引
	public void deleteLucene(String id){
		Analyzer analyzer = new IKAnalyzer(false);//词法分析器。
		Directory directory = new RAMDirectory();//内存存储(可以存到磁盘)
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40,analyzer);
		IndexWriter iwriter =null;
		try {
		iwriter = new IndexWriter(directory, config);
		//通过删除filename 为str的数据
		iwriter.deleteDocuments(new Term("id",id));  
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				iwriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	//更新一条索引
	//（Lucene没有真正的更新操作，通过某个fieldname，
	//可以更新这个域对应的索引，但是实质上，它是先删除索引，再重新建立的。）
	public void updateLucene(NewsModel nm,String id){
		Analyzer analyzer = new IKAnalyzer(false);//词法分析器。
		Directory directory = new RAMDirectory();//内存存储(可以存到磁盘)
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40,analyzer);
		IndexWriter iwriter =null;
		try {
		iwriter = new IndexWriter(directory, config);
		Document doc = new Document();//要更新的数据
			doc.add(new Field("id", nm.get("id").toString(),TextField.TYPE_STORED));
			doc.add(new Field("title", nm.get("title").toString(),TextField.TYPE_STORED));
			doc.add(new Field("time", nm.get("TIME").toString(),TextField.TYPE_STORED));
			doc.add(new Field("summary", nm.get("summary").toString(),TextField.TYPE_STORED));
		iwriter.updateDocument(new Term("id",id),doc);	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				iwriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}	
	

	
}
