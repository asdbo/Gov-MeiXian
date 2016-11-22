package com.jl.arky.jfinal.interceptor;

import com.jfinal.validate.Validator;

public abstract class BaseValidator extends Validator {
	// 校验电话号码正则表达式
	private static final String phonePattern = "\\b(0(\\d{2,3})-\\d{6,9})\\b";
	// 校验手机号码正则表达式
	private static final String mobilePattern = "\\b(1[3,5,7,8,9]\\d{9})\\b";
	// 校验电话手机号码正则表达式
	private static final String phoneMobilePattern = "\\b((1[3,5,7,8,9]\\d{9})|(0(\\d{2,3})-\\d{6,9}))\\b";
	// 校验用户名正则表达式
	private static final String userNamePatter = "^[^\\s]{5,16}$";
	// 校验用户名正则表达式
		private static final String userNamePatter1 = "^\\w{8}(,\\w{8})*$";
	/*
	 * 校验用户名
	 */
	protected void validateUserNamePattern(String field, String errorKey, String errorMsg) {
		validateRegex(field, userNamePatter, false, errorKey, errorMsg);
	}

	/**
	 * 校验电话号码
	 * 
	 * @param field
	 *            校验字段
	 * @param errorKey
	 * @param errorMsg
	 */
	protected void validatePhonePattern(String field, String errorKey, String errorMsg) {
		validateRegex(field, phonePattern, false, errorKey, errorMsg);
	}

	/**
	 * 验证手机号码
	 * 
	 * @param field
	 * @param errorKey
	 * @param errorMsg
	 */
	protected void validateMobilePattern(String field, String errorKey, String errorMsg) {
		validateRegex(field, mobilePattern, false, errorKey, errorMsg);
	}

	/**
	 * 验证电话手机号码
	 * 
	 * @param field
	 * @param errorKey
	 * @param errorMsg
	 */
	protected void validatePhoneMobilePattern(String field, String errorKey, String errorMsg) {
		validateRegex(field, phoneMobilePattern, false, errorKey, errorMsg);
	}

}