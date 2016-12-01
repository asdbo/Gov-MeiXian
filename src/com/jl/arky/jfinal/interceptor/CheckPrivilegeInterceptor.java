package com.jl.arky.jfinal.interceptor;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jl.arky.jfinal.model.AdminModel;

public class CheckPrivilegeInterceptor implements Interceptor {

	@Override  
    public void intercept(Invocation ai) {  
        // TODO Auto-generated method stub  
        HttpSession session = ai.getController().getSession();  
        //获取当前用户及其用户权限
        
        //检验当前用户有访问当前url的权限
        String url= ai.getActionKey();
        System.out.println(url);
      
        
        if(session == null){   
            ai.getController().redirect("/Admin/Index/toLogin");  
        }  
        else{  
        	AdminModel currentuser = (AdminModel) session.getAttribute("AdminModel");  
            if(currentuser != null) {
            	if(currentuser.hasPermission(url)){
            		ai.invoke();
            	}else if("/Admin/Index".equals(url)||"/Admin/Index/home".equals(url)){
            		ai.invoke();
            	}else{
            		ai.getController().renderText("你没有访问的权限");
            	}
            	
            	
            	
            	
            	
            }  
            else {  
            	//该用户没有登录，跑去登录
            	ai.getController().redirect("/Admin/Index/toLogin"); 
            }  
        }  
    } 

}
