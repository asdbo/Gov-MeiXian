 package com.jl.arky.jfinal.interceptor;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/*
 * 用户输入检验类
 * 检测用户名、密码是否为空
 * 检测用户名、密码是否为字母、数字及下划线的组合，且用户名、密码长度为 8 到 20 个字符之间
 * 
 */
public class LoginValidator extends BaseValidator {
	
	
	protected void validate(Controller c) {
		validateRequiredString("admin.username", "nameMsg", "请输入用户名");
		validateRequiredString("admin.password", "passMsg", "请输入密码");
		//此处覆盖了用户名为空的情况
		validateUserNamePattern("admin.username","nameMsg","你输入的用户名格式错误");
		validateUserNamePattern("admin.password","passwordMsg","你输入的密码格式错误");
		validateRequiredString("captchaCode", "captchaCodeMsg", "请输入验证码");
	}

	protected void handleError(Controller c) {
//		if()
		c.renderFreeMarker("login.html");
	}

}
