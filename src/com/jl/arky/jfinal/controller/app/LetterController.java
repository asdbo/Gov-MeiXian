package com.jl.arky.jfinal.controller.app;

import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jl.arky.jfinal.app.interceptor.MainAppInterceptor;

@Clear
@Before(MainAppInterceptor.class)
public class LetterController extends Controller{
	
	public void index(){
		String method = getRequest().getMethod();
		if("post".equalsIgnoreCase(method)){
			Map<String, String[]> map = getParaMap();
			
			for(Map.Entry<String,String[]> entry:map.entrySet()){
				System.out.println("key:"+entry.getKey());
				for(String s:entry.getValue()){
					System.out.println("val:"+entry.getKey());
				}
				System.out.println("");
			}
			
			renderJson("{type:0}");
			
		}
	}
	
	public void type(){
		String method = getRequest().getMethod();
		if("post".equalsIgnoreCase(method)){
			
		}
	}
}
