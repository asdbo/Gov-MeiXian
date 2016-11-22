package com.jl.arky.jfinal.interceptor;

import javax.servlet.http.HttpSession;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jl.arky.jfinal.model.AdminModel;

public class LoginInterceptor implements Interceptor {

	@Override  
    public void intercept(Invocation ai) {  
        // TODO Auto-generated method stub  
		String actionKey = ai.getActionKey();
		if(actionKey.indexOf("/Admin")!=-1){
			 HttpSession session = ai.getController().getSession();  
		        if(session == null){  
		            ai.getController().redirect("/Admin/Index/toLogin");  
		        }  
		        else{  
		        	AdminModel adminModel = (AdminModel) session.getAttribute("AdminModel");  
		            if(adminModel != null) {  
		            	//此处区别超级管理员和普通管理员
		            	if(ai.getActionKey().contains("/Admin")){
		            		if(adminModel.get("type").equals(0)){
		            		System.out.println("管理员类型"+adminModel.get("type"));
		            		ai.invoke();
		            		}else{
		            		ai.getController().renderText("你没有这个权限");	
		            		}
		            	}else{
		                ai.invoke();
		            	}
		            }  
		            else {  
		                ai.getController().redirect("/Admin/Index/toLogin");  
		            }  
		        }  
		}else{
			  //ai.getController().redirect("/Home/Index/toNewIndex");  
			ai.invoke();
		}
       
    } 

}
