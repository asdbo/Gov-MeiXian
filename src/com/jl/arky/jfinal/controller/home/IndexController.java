package com.jl.arky.jfinal.controller.home;

import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jl.arky.jfinal.model.BusinessModel;
import com.jl.arky.jfinal.model.CarouselModel;
import com.jl.arky.jfinal.model.ChannelModel;
import com.jl.arky.jfinal.model.LetterModel;
import com.jl.arky.jfinal.model.Letter_Dept_Model;
import com.jl.arky.jfinal.model.Letter_Type_Model;
import com.jl.arky.jfinal.model.MailModel;
import com.jl.arky.jfinal.model.NavigationModel;
import com.jl.arky.jfinal.model.NewsModel;
import com.jl.arky.jfinal.utils.TimeUtil;

public class IndexController extends Controller {
	@ActionKey("/")
	public void home() {
		index();
	}
	
	public void anthor(){
		int pid=2;//新闻中心
		List<ChannelModel> newcms = this.getCMS(pid,5);
		setAttr("newcms", newcms);//栏目
		//获取相应的新闻
		List<List<NewsModel>> news = this.getNEWS(newcms, 5);
		setAttr("news", news);//新闻
		List<List<NewsModel>> news2 = new ArrayList<List<NewsModel>>();
		List<ChannelModel> newcms2 = new ArrayList<ChannelModel>();
		//政务要闻
		int id=28;
		ChannelModel zfcm = ChannelModel.dao.findById(id);
		List<NewsModel> zwnews = NewsModel.dao.paginate(1, 5,"select *", " from news where cid=?",id).getList();
		this.getTime(zwnews);
		newcms2.add(zfcm);
		news2.add(zwnews);
		//法规公文
		id=14;
		List<ChannelModel> fgcms = this.getCMS(id, 2);
		List<List<NewsModel>> fgnewss = this.getNEWS(fgcms, 5);
		newcms2.addAll(fgcms);
		news2.addAll(fgnewss);
		//常会会议
		id=23;
		ChannelModel cms = ChannelModel.dao.findById(id);
		 List<NewsModel> chnews = NewsModel.dao.paginate(1,5,"select *", " from news where cid=?",id).getList();	
		 newcms2.add(cms);
		news2.add(chnews);
		 //公示公告
		 id=17;
		 ChannelModel gsc = ChannelModel.dao.findById(id);
		 List<NewsModel> gsnews = NewsModel.dao.paginate(1, 5,"select *", " from news where cid=?",id).getList();
			this.getTime2(gsnews);
		newcms2.add(gsc);
		news2.add(gsnews);
		setAttr("newcms2", newcms2);
		setAttr("news2", news2);
		
		
		//..............................................
		List<ChannelModel> cmss = new ArrayList<ChannelModel>();
		id=52;
		ChannelModel ls = ChannelModel.dao.findById(id);
		List<ChannelModel> cms2 = this.getCMS(id, 6);
		ls.setScs(cms2);
		cmss.add(ls);
		id=58;
		ChannelModel fc = ChannelModel.dao.findById(id);
		cms2 = this.getCMS(id, 6);
		fc.setScs(cms2);
		cmss.add(fc);
		id=60;
		ChannelModel wl = ChannelModel.dao.findById(id);
		cms2 = this.getCMS(id, 6);
		wl.setScs(cms2);
		cmss.add(wl);
		setAttr("cmss", cmss);
		
		//..............................................
		List<ChannelModel> gkcmss=new ArrayList<ChannelModel>();
		List<List<NewsModel>> gknewss = new ArrayList<List<NewsModel>>();
		 id=4;
		List<ChannelModel> zfcms = this.getCMS(id,12);
		setAttr("zfcms", zfcms);//栏目
		//总结报告
		id=30;
		ChannelModel zjc = ChannelModel.dao.findById(id);
		 List<NewsModel> zjnews = NewsModel.dao.paginate(1, 5,"select *", " from news where cid=?",id).getList();
		this.getTime(zjnews);
		gkcmss.add(zfcm);
		gkcmss.addAll(fgcms);
		gkcmss.add(zjc);
		gknewss.add(zwnews);
		gknewss.addAll(fgnewss);
		gknewss.add(zjnews);
		setAttr("gkcmss", gkcmss);
		setAttr("gknewss", gknewss);
		
		render("2/index.html");
	}
	public void test2(){
		render("2/internet-company.html");
	}
	//显示梅县主页
	public void index(){
		int pid=2;//新闻中心
		List<ChannelModel> newcms = this.getCMS(pid,5);
		setAttr("newcms", newcms);//栏目
		//获取相应的新闻
		List<List<NewsModel>> news = this.getNEWS(newcms, 5);
		setAttr("news", news);//新闻
		
		//轮播
		List<Model> cs = CarouselModel.dao.paginate(1, 4, "select *", "from carousel").getList();
		setAttr("cs", cs);
		
		//政府信息公开
		int id=4;
		List<ChannelModel> zfcms = this.getCMS(id,12);
		setAttr("zfcms", zfcms);//栏目
		//重点区域信息公开
		id=55;
		List<ChannelModel> zdcms = this.getCMS(id, 4);
		setAttr("zdid", id);
		setAttr("zdcms", zdcms);
		//公示公告
		id=17;
		 List<NewsModel> gsnews = NewsModel.dao.paginate(1, 8,"select *", " from news where cid=?",id).getList();
			this.getTime2(gsnews);
			setAttr("gsid", id);
			setAttr("gsnews", gsnews);
		
//		
	
		render("index.html");
	}
	//魅力梅县
	public void intro(){
		//梅县概况新闻
		int newid=26;
		NewsModel mxgk = NewsModel.dao.findById(newid);
		//自然地理
		int zrid=35;
		//行政区域
		int xzid=36;
		
		setAttr("mxgk", mxgk);
		setAttr("zrid", zrid);
		setAttr("xzid", xzid);
		//美图美景
		int cid=57;
		List<NewsModel> mtnews = NewsModel.dao.paginate(1,8,"select * ","from news where cid=?",cid).getList();
		setAttr("mtid", cid);
		setAttr("mtnews", mtnews);
		
		//历史沿革
		cid=52;
		List<ChannelModel> lscms = ChannelModel.dao.paginate(1, 3,"select *" ,"from channel where pid=? order by id desc",cid).getList();
		setAttr("liid", cid);
		//历史沿革栏目下的新闻
		cid=53;
		NewsModel lsnew = NewsModel.dao.findFirst("select * from news where cid=?", cid);
		setAttr("lscms", lscms);
		setAttr("lsnew", lsnew);
		
		//今日风采
		cid =58;
		List<ChannelModel> fccms = ChannelModel.dao.paginate(1, 3,"select *" ,"from channel where pid=? order by id desc",cid).getList();
		setAttr("fcid", cid);	
		cid=59;
		NewsModel fcnew = NewsModel.dao.findFirst("select * from news where cid=?", cid);
		setAttr("fccms", fccms);
		setAttr("fcnew", fcnew);
		
		//未来发展
		cid=60;
		List<ChannelModel> wlcms = ChannelModel.dao.paginate(1, 3,"select *" ,"from channel where pid=? order by id desc ",cid).getList();
		setAttr("wlid", cid);
		cid=61;
		NewsModel wlnew = NewsModel.dao.findFirst("select * from news where cid=?", cid);
		setAttr("wlcms", wlcms);
		setAttr("wlnew", wlnew);
		
		render("intro.html");
	}
	//政务公开
	public void open(){
		//政务要闻
		int id=28;
		List<NewsModel> zwnews = NewsModel.dao.paginate(1, 8,"select *", " from news where cid=?",id).getList();
		this.getTime2(zwnews);
		setAttr("zwid", id);
		setAttr("zwnews", zwnews);
		//公示公告
		 id=17;
		 List<NewsModel> gsnews = NewsModel.dao.paginate(1, 8,"select *", " from news where cid=?",id).getList();
		this.getTime2(gsnews);
		setAttr("gsid", id);
		setAttr("gsnews", gsnews);
		
		//政府信息公开
		
		
		//常会会议
		id=23;
		 List<NewsModel> chnews = NewsModel.dao.paginate(1, 6,"select *", " from news where cid=?",id).getList();
		setAttr("chnews", chnews);
		setAttr("chid", id);
		//法规公文
		id=14;
		List<ChannelModel> fgcms = this.getCMS(id, 2);
		List<List<NewsModel>> fgnewss = this.getNEWS(fgcms, 6);
		setAttr("fgnewss", fgnewss);
		setAttr("fgid", id);
		setAttr("fgcms", fgcms);
		
		//政府采购
		id=29;
		List<ChannelModel> zfcms = this.getCMS(id, 2);
		List<List<NewsModel>> zfnewss = this.getNEWS(zfcms, 6);
		setAttr("zfnewss", zfnewss);
		setAttr("zfid", id);
		setAttr("zfcms", zfcms);
		
		//人事信息
		id=20;
		List<ChannelModel> rscms = this.getCMS(id, 3);
		List<List<NewsModel>> rsnewss = this.getNEWS(rscms, 6);
		setAttr("rsnewss", rsnewss);
		setAttr("rsid", id);
		setAttr("rscms", rscms);
		
		//统计信息
		id=21;
		List<ChannelModel> tjcms = this.getCMS(id, 4);
		List<List<NewsModel>> tjnewss = this.getNEWS(tjcms, 6);
		setAttr("tjnewss", tjnewss);
		setAttr("tjid", id);
		setAttr("tjcms", tjcms);
			
		//总结报告
		id=30;
		 List<NewsModel> zjnews = NewsModel.dao.paginate(1, 6,"select *", " from news where cid=?",id).getList();
		this.getTime(zjnews);
		setAttr("zjid", id);
		setAttr("zjnews", zjnews);
		//计划规划
		id=19;
		 List<NewsModel> jhnews = NewsModel.dao.paginate(1, 6,"select *", " from news where cid=?",id).getList();
		this.getTime(jhnews);
		setAttr("jhnews", jhnews);
		setAttr("jhid", id);
		render("open.html");
	}
	private void getTime2(List<NewsModel> news) {
		for (NewsModel nm : news) {
			long time = nm.get("TIME");
			String date = TimeUtil.MonthAndDay(time);
			nm.set("TIME", date);
		}
		
	}

	public void service(){
		render("service.html");
	}
	public void internet(){
		List<ChannelModel> cms = this.getCMS(159, 59);
		setAttr("cms", cms);
		render("internet.html");
	}
	//回应关切
	public void reply(){
		//新闻发布会
		int id=40;
		List<NewsModel> news = NewsModel.dao.paginate(1, 1,"select *", " from news where cid=?",id).getList();
		if(news.isEmpty()){
			setAttr("news1", null);
		}else{
			setAttr("news1", news.get(0));
		}
		
		
		//热点问题回应
		id=41;
		List<NewsModel> rdnews = NewsModel.dao.paginate(1, 7,"select *", " from news where cid=?",id).getList();
		setAttr("rdnews", rdnews);
		//政策解读
		id=42;
		List<ChannelModel> zccms = this.getCMS(id,2);
		List<List<NewsModel>> zcnewss = this.getNEWS(zccms, 7);
		setAttr("zcnewss", zcnewss);
		setAttr("zccms", zccms);
		
		//突发事件
		id=43;
		List<ChannelModel> tfcms = this.getCMS(id,2);
		List<List<NewsModel>> tfnewss = this.getNEWS(tfcms, 7);
		setAttr("tfnewss", tfnewss);
		setAttr("tfcms", tfcms);
		render("reply.html");
	}
	
	
	
	public void leader(){
		//领导之窗
		int id=13;
		String lid = getPara("id");
		if(lid!=null){
			id=Integer.parseInt(lid);
		}
		ChannelModel leader = ChannelModel.dao.findById(id);
		List<ChannelModel> cms1 = this.getCMS(13, 5);//领导之窗id
		List<ChannelModel> cms=new ArrayList<ChannelModel>();
		for(ChannelModel cm:cms1){
			if(id==13||id==cm.getInt("id")){
				List<ChannelModel> cms2 = this.getCMS(cm.getInt("id"), 3);
				for(ChannelModel c:cms2){
					List<NewsModel> ns = NewsModel.dao.find("select * from news where cid=?",c.get("id"));
					c.setNews(ns);
				}
				cm.setScs(cms2);
				cms.add(cm);
			}
			
		}
		setAttr("cms1", cms1);
		setAttr("cms", cms);
		setAttr("leader", leader);
		render("index-leader.html");
	}
	public void leaderDetail(){
		String id = getPara("id");
		NewsModel lnew = NewsModel.dao.findById(id);
		ChannelModel cm = ChannelModel.dao.findById(lnew.get("cid"));
		List<NewsModel> cnews = NewsModel.dao.find("select * from news where cid=?",cm.get("id"));
		setAttr("lnew", lnew);
		setAttr("cnews", cnews);
		setAttr("cm", cm);
		render("index-leader-detail.html");
	}
	
	
	public void page(){
		render("page.html");
	}
	public void page2(){
		render("page2.html");
	}
	public void page3(){
		render("page3.html");
	}
	
	public void detail(){
		render("detail.html");
	}
	public void test(){
		render("index-leader.html");
	}
	public void email(){
		render("email.html");
	}
	
	//个人事项,//法人事项
	public void person1(){
		String pi= getPara("pid");
		int pid=113;
		if("133".equals(pi)){
			pid=133;
		}
		ChannelModel channel = ChannelModel.dao.findById(pid);
		setAttr("channel", channel);
		List<ChannelModel> pcms = this.getCMS(pid, 30);
		setAttr("pcms",pcms);
		String page = getPara("page");
		int nowpage=1;
		if(page!=null){
			nowpage=Integer.parseInt(page);
		}
		String id = getPara("id");
		if(id==null){
			if(pid==113){
				id="114";
			}else{
				id="134";
			}
		}
		setAttr("clickId", Integer.parseInt(id));
		List<NewsModel> list = NewsModel.dao.paginate(nowpage, 10, "select * ","from news where cid=? order by sort , id desc",id).getList();
		setAttr("news", list);
		
		NewsModel model = NewsModel.dao.findFirst("select count(*) num from news where cid=?",id);
		long count=model.get("num");//新闻总数
		long pageCount= count%10==0?count/10:(int)(count/10+1);//总页数
		if(pageCount==0){
			pageCount=1;
		}
		setAttr("nowPage", nowpage);
		setAttr("pageCount", pageCount);
		render("internet-person1.html");
	}
	
	//服务事项
	public void unit(){
		int pid=159;
		List<ChannelModel> pcms = this.getCMS(pid, 100);
		setAttr("pcms",pcms);
		String page = getPara("page");
		int nowpage=1;
		if(page!=null){
			nowpage=Integer.parseInt(page);
		}
		String id = getPara("id");
		if(id==null){
			id="160";
		}
		setAttr("clickId", Integer.parseInt(id));
		List<NewsModel> list = NewsModel.dao.paginate(nowpage, 10, "select * ","from news where cid=? order by sort , id desc",id).getList();
		setAttr("news", list);
		
		NewsModel model = NewsModel.dao.findFirst("select count(*) num from news where cid=?",id);
		long count=model.get("num");//新闻总数
		long pageCount= count%10==0?count/10:(int)(count/10+1);//总页数
		if(pageCount==0){
			pageCount=1;
		}
		setAttr("nowPage", nowpage);
		setAttr("pageCount", pageCount);
		render("internet-unit.html");
	}
	
	//企业开办
	public void qyfw_company(){
		//服务新闻
		int id=245;
		List<NewsModel> fwhnews = NewsModel.dao.paginate(1,6, "select * ","from news where cid=? order by sort,id desc",id).getList();
		this.getTime(fwhnews);
		setAttr("fwhnews", fwhnews);
		//服务公告
		id=246;
		List<NewsModel> ggnews = NewsModel.dao.paginate(1,6, "select * ","from news where cid=? order by sort,id desc",id).getList();
		this.getTime(ggnews);
		setAttr("ggnews", ggnews);
		//政策法规
		id=247;
		List<NewsModel> zcnews = NewsModel.dao.paginate(1,5, "select * ","from news where cid=? order by sort,id desc",id).getList();
		this.getTime(zcnews);
		setAttr("zcnews", zcnews);
		//
		render("internet-company.html");
	}
	//科技开办
	public void qyfw_technology(){
		//科技计划
		int id=249;
		List<NewsModel> kjnews = NewsModel.dao.paginate(1,6, "select * ","from news where cid=? order by sort,id desc",id).getList();
		this.getTime(kjnews);
		setAttr("kjnews", kjnews);
		//高新科技
		id=250;
		List<NewsModel> gxnews = NewsModel.dao.paginate(1,6, "select * ","from news where cid=? order by sort,id desc",id).getList();
		this.getTime(gxnews);
		setAttr("gxnews", gxnews);
		//优惠政策
		id=252;
		List<NewsModel> yhnews = NewsModel.dao.paginate(1,5, "select * ","from news where cid=? order by sort,id desc",id).getList();
		this.getTime(yhnews);
		setAttr("yhnews", yhnews);
		render("internet-technology.html");
	}
	//投资
	public void invest(){
		//招商新闻
		int id=257;
		List<NewsModel> zsnews = NewsModel.dao.paginate(1,6, "select * ","from news where cid=? order by sort,id desc",id).getList();
		this.getTime(zsnews);
		setAttr("zsnews", zsnews);
		//招商公告
		id=258;
		List<NewsModel> ggnews = NewsModel.dao.paginate(1,6, "select * ","from news where cid=? order by sort,id desc",id).getList();
		this.getTime(ggnews);
		setAttr("ggnews", ggnews);
		//重大战略
		id=260;
		List<NewsModel> zdnews = NewsModel.dao.paginate(1,5, "select * ","from news where cid=? order by sort,id desc",id).getList();
		this.getTime(zdnews);
		setAttr("zdnews", zdnews);
		//优惠政策
		id=261;
		List<NewsModel> yhnews = NewsModel.dao.paginate(1,5, "select * ","from news where cid=? order by sort,id desc",id).getList();
		this.getTime(yhnews);
		setAttr("yhnews", yhnews);
		//招商项目
		id=262;
		List<ChannelModel> cms = this.getCMS(id, 4);
		setAttr("cms", cms);
		List<List<NewsModel>> newss = this.getNEWS(cms, 5);
		setAttr("newss", newss);
		//企业风采
		id=263;
		List<NewsModel> qynews = NewsModel.dao.paginate(1,8, "select * ","from news where cid=? order by sort,id desc",id).getList();
		this.getTime(qynews);
		setAttr("qynews", qynews);
		render("internet-invest.html");
	}
	//就业
	public void employ(){
		//招聘会新闻
		int id=232;
		List<NewsModel> zphnews = NewsModel.dao.paginate(1,7, "select * ","from news where cid=? order by sort,id desc",id).getList();
		this.getTime(zphnews);
		setAttr("zphnews", zphnews);
		//自主创业
		id=233;
		List<NewsModel> zznews = NewsModel.dao.paginate(1,6, "select * ","from news where cid=? order by sort,id desc",id).getList();
		this.getTime(zznews);
		setAttr("zznews", zznews);
		//就业新闻
		id=234;
		List<NewsModel> jynews = NewsModel.dao.paginate(1,6, "select * ","from news where cid=? order by sort,id desc",id).getList();
		this.getTime(jynews);
		setAttr("jynews", jynews);
		//政策法规
		id=235;
		List<NewsModel> zcnews = NewsModel.dao.paginate(1,6, "select * ","from news where cid=? order by sort,id desc",id).getList();
		this.getTime(zcnews);
		setAttr("zcnews", zcnews);
		//公务员招考
		id=236;
		List<NewsModel> gwynews = NewsModel.dao.paginate(1,6, "select * ","from news where cid=? order by sort,id desc",id).getList();
		this.getTime(gwynews);
		setAttr("gwynews", gwynews);
		render("internet-employ.html");
	}
	
	//教育
	public void  edu(){
		//教育新闻
		int id=217;
		List<NewsModel> jynews = NewsModel.dao.paginate(1,9, "select * ","from news where cid=? order by sort,id desc",id).getList();
		this.getTime(jynews);
		setAttr("jynews", jynews);
		//招生政策
		id=218;
		List<NewsModel> zsnews = NewsModel.dao.paginate(1,5, "select * ","from news where cid=? order by sort,id desc",id).getList();
		this.getTime(zsnews);
		setAttr("zsnews", zsnews);
		//教育公告
		id=219;
		List<NewsModel> ggnews = NewsModel.dao.paginate(1,5, "select * ","from news where cid=? order by sort,id desc",id).getList();
		this.getTime(ggnews);
		setAttr("ggnews", ggnews);
		
		render("internet-person.html");
	}
	//社保
	public void sec(){
		//社保资讯
		int id=221;
		List<NewsModel> sbnews = NewsModel.dao.paginate(1,6, "select * ","from news where cid=? order by sort,id desc",id).getList();
		this.getTime(sbnews);
		setAttr("sbnews", sbnews);
		//政策法规
		id=222;
		List<NewsModel> zcnews = NewsModel.dao.paginate(1,6, "select * ","from news where cid=? order by sort,id desc",id).getList();
		this.getTime(zcnews);
		setAttr("zcnews", zcnews);
		//社保公告
		id=223;
		List<NewsModel> ggnews = NewsModel.dao.paginate(1,6, "select * ","from news where cid=? order by sort,id desc",id).getList();
		this.getTime(ggnews);
		setAttr("ggnews", ggnews);
		//社保知识
		id=224;
		List<NewsModel> zsnews = NewsModel.dao.paginate(1,6, "select * ","from news where cid=? order by sort,id desc",id).getList();
		this.getTime(zsnews);
		setAttr("zsnews", zsnews);
		render("internet-security.html");
	}
	//个人服务的证件办理
	public void paper(){
		//新闻 资讯
		int id =226;
		List<NewsModel> zxnews = NewsModel.dao.paginate(1,6, "select * ","from news where cid=? order by sort,id desc",id).getList();
		this.getTime(zxnews);
		setAttr("zxnews", zxnews);
		//政策法规
		id =227;
		List<NewsModel> zcnews = NewsModel.dao.paginate(1,6, "select * ","from news where cid=? order by sort,id desc",id).getList();
		this.getTime(zcnews);
		setAttr("zcnews", zcnews);
		//办事指南
		id =228;
		List<NewsModel> bsnews = NewsModel.dao.paginate(1,5, "select * ","from news where cid=? order by sort,id desc",id).getList();
		this.getTime(bsnews);
		setAttr("bsnews", bsnews);
		//表格下载
		id =229;
		List<NewsModel> bgnews = NewsModel.dao.paginate(1,5, "select * ","from news where cid=? order by sort,id desc",id).getList();
		this.getTime(bgnews);
		setAttr("bgnews", bgnews);
		//常见问题
		id =230;
		List<NewsModel> wtnews = NewsModel.dao.paginate(1,5, "select * ","from news where cid=? order by sort,id desc",id).getList();
		this.getTime(wtnews);
		setAttr("wtnews", wtnews);
		//
		render("internet-paper.html");
	}
	//医疗服务
	public void health(){
		//医疗新闻
		int id=238;
		List<NewsModel> ylnews = NewsModel.dao.paginate(1,7, "select * ","from news where cid=? order by sort,id desc",id).getList();
		this.getTime(ylnews);
		setAttr("ylnews", ylnews);
		//政策文件
		id=239;
		List<NewsModel> zcnews = NewsModel.dao.paginate(1,5, "select * ","from news where cid=? order by sort,id desc",id).getList();
		this.getTime(zcnews);
		setAttr("zcnews", zcnews);
		//疾控管理
		id=240;
		List<NewsModel> jknews = NewsModel.dao.paginate(1,5, "select * ","from news where cid=? order by sort,id desc",id).getList();
		this.getTime(jknews);
		setAttr("jknews", jknews);
		//中医管理
		id=241;
		List<NewsModel> zynews = NewsModel.dao.paginate(1,5, "select * ","from news where cid=? order by sort,id desc",id).getList();
		this.getTime(zynews);
		setAttr("zynews", zynews);
		//健康教育
		id=242;
		List<NewsModel> hlnews = NewsModel.dao.paginate(1,5, "select * ","from news where cid=? order by sort,id desc",id).getList();
		this.getTime(hlnews);
		setAttr("hlnews", hlnews);
		render("internet-health.html");
	}
	
	
	//天气预报
	public void weather(){
		render("weather.html");
	}
	public void bssx_sy(){
		render("bssx_sy.html");
	}
	public void bssx_sy1(){
		render("bssx_sy(1).html");
	}
	public void myzj_index(){
		render("myzj_index.html");
	}
	public void shift(){
		render("index2.html");
	}
	//梅江主页
//	public void index() {
//
//		List<ChannelModel> cms = this.getCMS(3);// 梅江动态的子栏目
//		List<List<NewsModel>> lnews = this.getNEWS(cms, 9);// 梅江动态的子栏目的新闻
//		setAttr("cms", cms);
//		setAttr("lnews", lnews);
//
//		List<ChannelModel> cms1 = this.getCMS(7);// 政务公开的子栏目
//		List<List<NewsModel>> lnews1 = this.getNEWS(cms1, 6);// 政务公开的子栏目的新闻
//		setAttr("cms1", cms1);
//		setAttr("lnews1", lnews1);
//
//
//		List<NavigationModel> nas = this.getNavigation(0);// 网站导航栏目
//		List<List<NavigationModel>> naList = this.getNavigations();// 网站导航子栏目
//		setAttr("nas", nas);
//		setAttr("naList", naList);
//		
//		//轮播图片
//		List<Model> cs = CarouselModel.dao.paginate(1, 6, "select *", "from carousel").getList();
//		setAttr("cs", cs);
//		
//		//办事进度(最多显示30个)
//		List<BusinessModel> bms = BusinessModel.dao.paginate(1,30, "select *", "from schedule").getList();
//		for(BusinessModel bm:bms){
//			bm.set("time", TimeUtil.timeStampToDate(bm.getLong("time")));
//		}
//		setAttr("bms", bms);
//		render("index.html");
//	}

	private List<ChannelModel> getCMS(int pid,int pageSize) {
		List<ChannelModel> cms = ChannelModel.dao.paginate(1, pageSize, "select id ,title", " from channel where pid=?", pid)
				.getList();
		return cms;
	}

	private List<List<NewsModel>> getNEWS(List<ChannelModel> cms, int pageSize) {
		List<List<NewsModel>> lnews = new ArrayList<List<NewsModel>>();

		for (ChannelModel cm : cms) {
			List<NewsModel> news = null;
			List<ChannelModel> cms2 = this.getCMS(cm.getInt("id"),4);
			String sql1="from ((select t.*  from news t where t.cid=?)";
			ArrayList<Integer> ids=new ArrayList<Integer>();
			if (!cms2.isEmpty()) {
				ids.add(cms2.get(0).getInt("id"));
				for (int i=1;i<cms2.size();i++) {
						
						sql1+="union (select t"+i+".* from news t"+i+" where t"+i+".cid=?)";
						ids.add(cms2.get(i).getInt("id"));
				}
			} else{
				ids.add(cm.getInt("id"));
			}
			sql1+=") t0 order by sort,id desc";
			news = NewsModel.dao.paginate(1, pageSize, "select *", sql1,ids.toArray()).getList();
			getTime(news);
			lnews.add(news);
		}
		return lnews;
	}

	private List<NavigationModel> getNavigation(int pid) {
		List<NavigationModel> nas = NavigationModel.dao.find("select * from navigation where pid=?", pid);
		return nas;

	}


	private List<List<NavigationModel>> getNavigations() {
		List<List<NavigationModel>> naList = new ArrayList<List<NavigationModel>>();
		List<NavigationModel> nas = this.getNavigation(0);
		for (NavigationModel n : nas) {
			List<NavigationModel> sList = NavigationModel.dao
					.find("select * from navigation where pid = ? order by id asc", n.get("id"));
			naList.add(sList);
		}
		return naList;

	}

	
	private void getTime(List<NewsModel> news) {
		// 时间戳变为时间
		for (NewsModel nm : news) {
			long time = nm.get("TIME");
			String date = TimeUtil.timeStampToDate(time);
			nm.set("TIME", date);
		}
	}

	// 附件下载
	public void downfile() {
		String para = getPara("filename");
		String path = getRequest().getServletContext().getRealPath("/");
		renderFile(new File(path + para));
	}
	
	//全文检索
	public void search(){
		String src=getPara("Keywords");
		//创建索引库
		String sql="select * from news";
		List<NewsModel> news = NewsModel.dao.find(sql);//新闻数据
		Analyzer analyzer = new IKAnalyzer(true);//词法分析器。
		Directory directory = new RAMDirectory();//内存存储(可以存到磁盘)
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40,analyzer);
		IndexWriter iwriter =null;
		try {
			iwriter = new IndexWriter(directory, config);
			for(NewsModel nm:news){
				Document doc = new Document();
				doc.add(new Field("id", nm.get("id").toString(),TextField.TYPE_STORED));
				doc.add(new Field("title", nm.get("title").toString(),TextField.TYPE_STORED));
				doc.add(new Field("time", nm.get("TIME").toString(),TextField.TYPE_STORED));
				doc.add(new Field("summary", nm.get("summary").toString(),TextField.TYPE_STORED));
				iwriter.addDocument(doc);
			}
			iwriter.commit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (iwriter != null) {
				try {
					iwriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		
		//搜索
		
		Date dateBefore=new Date(System.currentTimeMillis());
		String para = getPara("page");
		int page=1;
		int pageSize=10;
		if(para!=null){
			page=Integer.parseInt(para);
		}
		 DirectoryReader ireader=null;
		  try {
			ireader = DirectoryReader.open(directory);//打开存储位置
			 IndexSearcher isearcher = new IndexSearcher(ireader);//创建搜索器
			 QueryParser parser = new QueryParser(Version.LUCENE_40, "title", analyzer);
			 Query query = parser.parse(src);
			  QueryScorer queryScorer = new QueryScorer(query);
			  //设置高亮标签
		       Formatter formatter = new SimpleHTMLFormatter("<span style='color:red;'>", "</span>");
		       //高亮分析器
		        Highlighter hl = new Highlighter(formatter, queryScorer);
		        Fragmenter fragmenter = new SimpleSpanFragmenter(queryScorer);
		        hl.setTextFragmenter(fragmenter); 
		        ScoreDoc lastScoreDoc = getlastSearch(page,pageSize,query,isearcher);
		        TopDocs topDocs = isearcher.searchAfter(lastScoreDoc,query,pageSize);
		        ScoreDoc[] hits = topDocs.scoreDocs;
		        //总条数
		        	int count = topDocs.totalHits;
			 ArrayList<Integer> ids=new ArrayList<Integer>();
			 ArrayList<String> strs=new ArrayList<String>();
			 ArrayList<String> summarys=new ArrayList<String>();
			 ArrayList<String> date=new ArrayList<String>();
			    // Iterate through the results:
			 for (int i = 0; i < hits.length; i++) {
			      Document hitDoc = isearcher.doc(hits[i].doc);
			      String str = hl.getBestFragment(analyzer, "title",hitDoc.get("title"));
			      String summary = hl.getBestFragment(analyzer, "summary",hitDoc.get("summary"));
			      ids.add(Integer.parseInt(hitDoc.get("id")));
			      date.add(TimeUtil.timeStampToDate(Long.parseLong(hitDoc.get("time"))));
			      strs.add(str);
			      if(summary==null){
			    	  summary="";
			      }
			      summarys.add(summary);
			    }
			 //总页数
			 	int pageCount=count%pageSize==0?count/pageSize:(count/pageSize)+1;
			 	if(pageCount==0){
			 		pageCount=1;
			 	}
			 	Date dateAfter=new Date(System.currentTimeMillis());
			 	setAttr("useTime", (dateAfter.getTime()-dateBefore.getTime())/1000);
			 	setAttr("pageSize", pageSize);
			 	setAttr("date", date);
			 	setAttr("summarys", summarys);
			    setAttr("ids", ids);
			    setAttr("src", src);
			    setAttr("strs", strs);
			    setAttr("nowPage",page);
			    setAttr("count", count);
			    setAttr("pageCount", pageCount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ireader != null) {
				try {
					ireader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				directory.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		  render("search.html");
		

	}
	private ScoreDoc getlastSearch(int page, int pageSize, Query query, IndexSearcher isearcher) throws IOException {
		if(page==1)return null;//如果是第一页返回空
		int num = pageSize*(page-1);//获取上一页的数量
		TopDocs tds = isearcher.search(query, num);
		return tds.scoreDocs[num-1];
	}

	//关于我们
	public void abus(){
		render("abus.html");
		}

	//网站声明
	public void webdec(){
		render("webdec.html");
	}
	//联系我们
	public void callus(){
		render("callus.html");
	}
	//网站地图
	public void webmap(){
		List<ChannelModel> pchannel = ChannelModel.dao.find("select * from channel where pid=?",0);
		HashMap<Integer, List<ChannelModel>> maps=new HashMap<Integer, List<ChannelModel>>();
		for(ChannelModel cm:pchannel){
			this.childChannel(cm.getInt("id"), maps);
		}
		setAttr("pcms", pchannel);
		setAttr("maps", maps);
		render("map.html");
	}
	//获取子栏目
	public void childChannel(int pid,Map<Integer,List<ChannelModel>> maps){
		List<ChannelModel> pchannel = ChannelModel.dao.find("select * from channel where pid=?",pid);
		if(!pchannel.isEmpty()){
			maps.put(pid, pchannel);
			for(ChannelModel cm:pchannel){
				this.childChannel(cm.getInt("id"), maps);
			}
		}
		
	}
	public void complaints() {
		List<Model> letterTypes = Letter_Type_Model.dao.getLetterTypes();
		List<Model> letterDepts = Letter_Dept_Model.dao.getLetterDept();
		setAttr("letterTypes", letterTypes);
		setAttr("letterDepts", letterDepts);
		render("complaints.html");
	}

	public void saveLetter() {
		String dateString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		getModel(LetterModel.class).set("state", "已接收").set("receive_time", dateString).save();
		redirect("/Home/Index/complaints");
	}

	public void mailAdd() {
		List<ChannelModel> channels = ChannelModel.dao
				.find("SELECT * FROM channel WHERE pid !=0 AND  id NOT IN (SELECT pid FROM channel)");
		setAttr("channels", channels);
		render("mailAdd.html");
	}

	public void mailSave() {
		MailModel mailModel = new MailModel();
		mailModel.set("email", getPara("email"));

		StringBuffer sb = new StringBuffer();
		String[] paraValues = getParaValues("after_select");

		for (int i = 0; i < paraValues.length; i++) {
			sb.append(paraValues[i]).append(",");
			System.out.println(paraValues[i]);
		}

		mailModel.set("subscribe", sb.substring(0, sb.length() - 1).toString()).save();
		render("subscribe_success.html");
	}

	public void mailDelete() {
		String mail = getPara("email");
		if (null == MailModel.dao.find("select * from email where email = ?", mail)) {
			System.out.println("找不到记录");
		} else {
			Db.update("delete from email where email = ?", mail);
		}
		render("delete_subscribe_success.html");
		// redirect("/Home/Index/mailAdd");
	}


}
