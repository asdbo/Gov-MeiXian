package com.jl.arky.jfinal.controller.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jl.arky.jfinal.app.interceptor.MainAppInterceptor;
import com.jl.arky.jfinal.app.model.News;
import com.jl.arky.jfinal.app.utils.HtmlUtils;
import com.jl.arky.jfinal.app.utils.TimeUtils;

@Clear
@Before(MainAppInterceptor.class)
public class NewsController extends Controller {
	
	public void index(){
		String method = getRequest().getMethod();
		if("get".equalsIgnoreCase(method)){
			
			String spage = getPara("page");
			
			String cid = getPara("cid");
			if(cid==null||!isNumeric(cid)){
				renderJson("[]");
			}
			int i=0;
			if(spage!=null&&isNumeric(spage)){
				i=Integer.valueOf(getPara("page"));
			}
			if(i==0){
				i=1;
			}
			Page<News> page=News.dao.paginate(i,10,"select *","from news where cid=?",cid);
			List<Map> lists=new ArrayList<Map>();
			for(News mnew:page.getList()){
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("title", mnew.getTitle());
				
				String summary=mnew.getSummary();
				if(summary==null||summary.length()==0){
					String content = HtmlUtils.delHTMLTag(mnew.getContent());
					int l=content.length();
					map.put("content", content.substring(0,l<150?l:150));
				}else{
					map.put("content", summary);
				}
				String time=TimeUtils.timeStamp2Date(String.valueOf(mnew.getTIME()), "yyyy-MM-dd HH:mm:ss");
				map.put("time",time);
				map.put("src", mnew.getNewpic());
				map.put("origin", mnew.getOrigin());
				map.put("looks", mnew.getLooks());
				map.put("id", mnew.getId());
				lists.add(map);
			}
			PageNews pageNews=new PageNews(lists,page.getTotalPage());
			renderJson(pageNews);
		}
	}
	
	public void onenew(){
		String method = getRequest().getMethod();
		if("get".equalsIgnoreCase(method)){
			String id = getPara("id");
			if(id==null||!isNumeric(id)){
				renderText("缺少id");
			}
			News news=News.dao.findById(id);
			if(news!=null){
				Map<String, Object> map=new HashMap<String, Object>();
				news.set("looks", news.getInt("looks")+1).update();
				map.put("id",news.getId());
				map.put("cid",news.getCid());
				map.put("content",news.getContent());
				map.put("time",TimeUtils.timeStamp2Date(String.valueOf(news.getTIME()), "yyyy-MM-dd HH:mm:ss"));
				map.put("attach",news.getAttach());
				map.put("aid",news.getAid());
				map.put("looks",news.getLooks());
				map.put("video",news.getVideo());
				map.put("writer",news.getWriter());
				map.put("origin",news.getOrigin());
				map.put("summary",news.getSummary());
				map.put("sort",news.getSort());
				map.put("newpic",news.getNewpic());
				map.put("title",news.getTitle());
				renderJson(map);
			}else{
				Map<String,String> map = new HashMap<String, String>();
				map.put("error", "400");
				renderJson(map);
			}
		}
	}
	
	public void search(){
		String src=getPara("Keywords");
		if(src==null||src.equals("")){
			Map<String,Object> map=new HashMap<String,Object>();
		 	map.put("totalPage", 0);
		 	map.put("list",new ArrayList());
		 	renderJson(map);
		 	return;
		}
		//创建索引库
		String sql="select * from news";
		List<News> news = News.dao.find(sql);//新闻数据
//		System.out.println(news.size());
		Analyzer analyzer = new IKAnalyzer(false);//词法分析器。
		Directory directory = new RAMDirectory();//内存存储(可以存到磁盘)
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40,analyzer);
		IndexWriter iwriter =null;
		try {
			iwriter = new IndexWriter(directory, config);
			for(News nm:news){
				Document doc = new Document();
				doc.add(new Field("id", nm.get("id").toString(),TextField.TYPE_STORED));
				doc.add(new Field("title", nm.get("title").toString(),TextField.TYPE_STORED));
				doc.add(new Field("time", nm.get("TIME").toString(),TextField.TYPE_STORED));
				doc.add(new Field("summary", nm.get("summary").toString(),TextField.TYPE_STORED));
				String content = HtmlUtils.delHTMLTag(nm.getContent());
				doc.add(new Field("content", content.substring(0,content.length()<150?content.length():150),TextField.TYPE_STORED));
				
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
		        	
		        List<Map> list = new ArrayList<Map>();
			    // Iterate through the results:
			 for (int i = 0; i < hits.length; i++) {
			      Document hitDoc = isearcher.doc(hits[i].doc);
			      String str = hl.getBestFragment(analyzer, "title",hitDoc.get("title"));
			      String summary = hl.getBestFragment(analyzer, "summary",hitDoc.get("summary"));
			      String content = hl.getBestFragment(analyzer, "content",hitDoc.get("content"));
			      Map<String, Object> map= new HashMap<String, Object>();
			      map.put("id", hitDoc.get("id"));
			      map.put("time", TimeUtils.timeStamp2Date(hitDoc.get("time"),null));
			      map.put("title", str);
			      if(summary==null||summary.length()==0){
			    	  if(content==null||content.equals("")){
			    		  map.put("content", hitDoc.get("content"));
			    	  }else{
			    		  map.put("content", content);
			    	  }
			      }else{
			    	  map.put("content", summary);
			      }
			      list.add(map);
			    }
			 //总页数
			 	int pageCount=count%pageSize==0?count/pageSize:(count/pageSize)+1;
			 	if(pageCount==0){
			 		pageCount=1;
			 	}
			 	Date dateAfter=new Date(System.currentTimeMillis());
			 	Map<String,Object> map=new HashMap<String,Object>();
			 	map.put("useTime", (dateAfter.getTime()-dateBefore.getTime())/1000);
			 	map.put("pageSize", pageSize);
			 	map.put("src", src);
			 	map.put("nowPage",page);
			 	map.put("count", count);
			 	map.put("totalPage", pageCount);
			 	map.put("list",list);
			 	renderJson(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Map<String,Object> map=new HashMap<String,Object>();
		 	map.put("totalPage", 0);
		 	map.put("list",new ArrayList());
		 	renderJson(map);
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
	}
	private ScoreDoc getlastSearch(int page, int pageSize, Query query, IndexSearcher isearcher) throws IOException {
		if(page==1)return null;//如果是第一页返回空
		int num = pageSize*(page-1);//获取上一页的数量
		TopDocs tds = isearcher.search(query, num);
		return tds.scoreDocs[num-1];
	}
	
	private boolean isNumeric(String str){ 
		   Pattern pattern = Pattern.compile("[0-9]*"); 
		   Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true; 
	}
	
	public class PageNews{
		private List<Map> list;
		private int totalPage;
		
		public PageNews(List<Map> list, int totalPage) {
			super();
			this.list = list;
			this.totalPage = totalPage;
		}
		
		public List<Map> getList() {
			return list;
		}
		
		public void setList(List<Map> list) {
			this.list = list;
		}
		
		public int getTotalPage() {
			return totalPage;
		}
		
		public void setTotalPage(int totalPage) {
			this.totalPage = totalPage;
		}
	}
}
