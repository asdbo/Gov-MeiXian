package com.jl.arky.jfinal.app.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class MainAppInterceptor implements Interceptor {
	private Controller mController;
	@Override
	public void intercept(Invocation inv) {
		mController =inv.getController();
		mController.getResponse().setHeader("Access-Control-Allow-Origin", "*");
		mController.getResponse().setHeader("Access-Control-Allow-Headers", "X-Requested-With");
		inv.invoke();
	}
}
