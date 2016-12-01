package com.jl.arky.jfinal.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jl.arky.jfinal.app.model._MappingKit;
import com.jl.arky.jfinal.controller.admin.AdminController;
import com.jl.arky.jfinal.controller.admin.BusinessController;
import com.jl.arky.jfinal.controller.admin.CarouselController;
import com.jl.arky.jfinal.controller.admin.ChannelController;
import com.jl.arky.jfinal.controller.admin.DatabaseController;
import com.jl.arky.jfinal.controller.admin.IndexController;
import com.jl.arky.jfinal.controller.admin.LetterController;
import com.jl.arky.jfinal.controller.admin.MailController;
import com.jl.arky.jfinal.controller.admin.NavigationController;
import com.jl.arky.jfinal.controller.admin.NewsController;
import com.jl.arky.jfinal.controller.admin.RoleController;
import com.jl.arky.jfinal.controller.admin.RssController;
import com.jl.arky.jfinal.controller.admin.UeditorController;
import com.jl.arky.jfinal.controller.home.ChannelContentController;
import com.jl.arky.jfinal.controller.home.NewContentController;
import com.jl.arky.jfinal.handler.MainHandler;
import com.jl.arky.jfinal.interceptor.I18nInterceptor;
import com.jl.arky.jfinal.interceptor.LoginInterceptor;
import com.jl.arky.jfinal.model.AdminLogModel;
import com.jl.arky.jfinal.model.AdminModel;
import com.jl.arky.jfinal.model.BusinessModel;
import com.jl.arky.jfinal.model.CarouselModel;
import com.jl.arky.jfinal.model.ChannelModel;
import com.jl.arky.jfinal.model.LetterModel;
import com.jl.arky.jfinal.model.Letter_Dept_Model;
import com.jl.arky.jfinal.model.Letter_Type_Model;
import com.jl.arky.jfinal.model.MailModel;
import com.jl.arky.jfinal.model.NavigationModel;
import com.jl.arky.jfinal.model.NewsModel;
import com.jl.arky.jfinal.model.PrivilegeModel;
import com.jl.arky.jfinal.model.R_P_Model;
import com.jl.arky.jfinal.model.RoleModel;
import com.jl.arky.jfinal.model.RssChannelModel;
import com.jl.arky.jfinal.model.RssModel;
import com.jl.arky.jfinal.model.U_R_Model;
import com.jl.arky.jfinal.utils.CacheUtil;

import freemarker.core._CoreAPI;

public class MainConfig extends JFinalConfig {
	public static final String DATABASE_USERNAME = "root";// 用户名
	public static final String DATABASE_PASSWORD = "root";// 密码
	public static final String DATABASE_NAME = "mxdata";// 数据库名
	public static final String DATABASE_HOST = "localhost";// 主机地址
	public static final String DATABASE_PORT = "3306";// 端口
	public static final String MYSQL_HOME = "";

	@Override
	public void configConstant(Constants me) {
		// TODO Auto-generated method stub
		me.setDevMode(true);
		me.setBaseViewPath("/WEB-INF/templates");
		me.setBaseUploadPath("Public/upload");// 文件上传路径
		me.setMaxPostSize(1024 * 1024 * 1024);// 文件大小
		me.setBaseDownloadPath("d:/");// 文件下载路径
	}

	@Override
	public void configRoute(Routes me) {
		// 添加AdminController类的路由
		me.add("/Admin/News", NewsController.class);
		me.add("/Admin/Channel", ChannelController.class);
		me.add("/Admin/Admin", AdminController.class);
		me.add("/Admin/Carousel", CarouselController.class);
		me.add("/Admin/Index", IndexController.class);
		me.add("/Admin/Database", DatabaseController.class);
		me.add("/Admin/Letter", LetterController.class);
		me.add("/Admin/Ueditor", UeditorController.class);
		me.add("/Admin/Mail", MailController.class);
		me.add("/Admin/Business",BusinessController.class);
		 //前端控制器
		me.add("/Home/Index",com.jl.arky.jfinal.controller.home.IndexController.class);
		me.add("/Home/NewContent",NewContentController.class);
		me.add("/Home/ChannelContent",ChannelContentController.class);

		me.add("/Admin/Rss", RssController.class);
		me.add("/Admin/Navigation", NavigationController.class);
		me.add("Admin/Role",RoleController.class);
		
		//app路由
		me.add("/v1/carousel", com.jl.arky.jfinal.controller.app.CarouselController.class);
		me.add("/v1/news", com.jl.arky.jfinal.controller.app.NewsController.class);
		me.add("/v1/channel", com.jl.arky.jfinal.controller.app.ChannelController.class);
	}

	@Override
	public void configPlugin(Plugins me) {
		/* cache plugin */
//		String fileName = CacheUtil.ConfigFileName;
//		me.add(new EhCachePlugin(fileName));
		C3p0Plugin cp = new C3p0Plugin("jdbc:mysql://" + DATABASE_HOST + ":" + DATABASE_PORT + "/" + DATABASE_NAME
				+ "?useUnicode=true&characterEncoding=UTF-8", DATABASE_USERNAME, DATABASE_PASSWORD);
		me.add(cp);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(cp);
		arp.setShowSql(true);
		me.add(arp);
		arp.addMapping("news", NewsModel.class);
		arp.addMapping("carousel", CarouselModel.class);
		arp.addMapping("letter", LetterModel.class);
		arp.addMapping("letter_dept", Letter_Dept_Model.class);
		arp.addMapping("letter_type", Letter_Type_Model.class);
		arp.addMapping("channel", ChannelModel.class);
		arp.addMapping("admin", AdminModel.class);
		arp.addMapping("admin_log", AdminLogModel.class);

		arp.addMapping("email", MailModel.class);
		arp.addMapping("rss_items", RssModel.class);
		arp.addMapping("rss_channel", RssChannelModel.class);
		arp.addMapping("navigation", NavigationModel.class);
		arp.addMapping("schedule", BusinessModel.class);
		arp.addMapping("privilege", PrivilegeModel.class);
		arp.addMapping("role", RoleModel.class);
		arp.addMapping("role_privilege", R_P_Model.class);
		arp.addMapping("admin_role", U_R_Model.class);
		
		_MappingKit.mapping(arp);
	}

	@Override
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub
		me.add(new I18nInterceptor());
//		me.add(new LoginInterceptor());
		me.add(new SessionInViewInterceptor(true));
	}

	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub
		me.add(new MainHandler());
	}

}
