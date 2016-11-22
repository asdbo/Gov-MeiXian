package com.jl.arky.jfinal.controller.admin;

import java.io.File;

import javax.servlet.http.Cookie;


import com.jfinal.aop.Clear;
import com.jfinal.config.Routes;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.ext.handler.RoutesHandler;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jl.arky.jfinal.utils.CaptchaRender;

public class IndexController extends Controller{
	

	public void home(){
		render("home.html");
	}

	@Clear
	public void toLogin(){
		
		String msg = getPara("error");
		if(msg!=null){
			if(msg.equals("1"))
			setAttr("msg", "验证码错误！");
			else
			setAttr("msg", "账号或密码错误！");	
		}
		render("login.html");
	}
	@Clear
	public void captcha(){
		//验证码生成
		CaptchaRender captchaRender=new CaptchaRender();
		String md5RandonCode = captchaRender.getMd5RandonCode();
		setSessionAttr("_CAPTCHA_MD5_CODE_", md5RandonCode);
		render(captchaRender);
	}
	public void index(){
		render("index.html");
	}
	@ActionKey("/Admin")
	public void main(){
		redirect("/Admin/Index/index");
	}
	public void testDB(){
		/*ff 
		AdminModel admin = AdminModel.find(1);
		admin.set("name", "Arky1");
		admin.update();
		System.out.println(admin.get("name"));
		*/
//		new AdminModel().add();
		renderText("123");
	}
	public void testThread(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(5000);
					System.out.println("hello");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		renderText("thread");
	}
	public void testCache(){
		String item = "hello";
		String key = "word";
		String cacheName = "DefaultCache";
		Object value = CacheKit.get(cacheName, key);
		if(value!=null){
			System.out.println(value.toString());
		}else{
			System.out.println("value is null");
			CacheKit.put(cacheName, key, item);
		}
		renderText("render OK");
	}
	public void changeFile(){
		String who = getPara("who");
		String rootpath = getRequest().getServletContext().getRealPath("/WEB-INF/templates");
		File file = new File(rootpath+"/"+who);
		if(file.exists()){
			if(who.equals("mj")){
				new File(rootpath+"/Home").renameTo(new File(rootpath+"/mx"));
			}else if(who.equals("mx")){
				new File(rootpath+"/Home").renameTo(new File(rootpath+"/mj"));
			}
			file.renameTo(new File(rootpath+"/Home"));
		}
		renderNull();
	}
	public void whatFile(){
		String rootpath = getRequest().getServletContext().getRealPath("/WEB-INF/templates");
		File file = new File(rootpath+"/mx");
		if(file.exists()){
			renderText("梅县");
		}else{
			renderText("梅江");
		}
	}
	public void testI18N(){
//		HttpServletRequest request = getRequest();
//		String path = request.getSession().getServletContext().getRealPath("/");
//		System.out.println(path);
		
//		String path = this.getClass().getResource("/").getPath();
//		System.out.println(path);
		//x
		render("testI18N.html");
	}
	
}
