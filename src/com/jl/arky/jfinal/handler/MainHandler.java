package com.jl.arky.jfinal.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;

public class MainHandler extends Handler{

	@Override
	public void handle(String target, HttpServletRequest request, HttpServletResponse response,
			boolean[] isHandled) {
		// TODO Auto-generated method stub
		
//		if(target.endsWith(".html")){
//			target = target.substring(0, target.length()-5);
//		}
		next.handle(target, request, response, isHandled);
	}
	
}