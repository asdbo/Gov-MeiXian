package com.jl.arky.jfinal.interceptor;


import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.i18n.I18n;
import com.jfinal.i18n.Res;

public class I18nInterceptor implements Interceptor{
	public static String I18nFilePath = "com.jl.arky.jfinal.config.txt";
	public static String Code_CN = "zh_CN";
	public static String Code_HK = "zh_HK";
	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		Res txt = I18n.use(I18nFilePath,Code_CN);
		
		inv.getController().setAttr("I18n", txt);
		inv.getController().setAttr("public", "/Public");
		inv.invoke();
	}

}
