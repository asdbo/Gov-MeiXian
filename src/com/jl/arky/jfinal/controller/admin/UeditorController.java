package com.jl.arky.jfinal.controller.admin;
import com.baidu.ueditor.ActionEnter;
import com.jfinal.core.Controller;

public class UeditorController extends Controller{
	public void index(){
		String rootPath = getRequest().getServletContext().getRealPath( "/" );
		String configPath = rootPath+"/Public/tools/ueditor/config.json";
		String str = new ActionEnter( getRequest(), rootPath ,configPath).exec();
		renderText(str);
	}
	
}
