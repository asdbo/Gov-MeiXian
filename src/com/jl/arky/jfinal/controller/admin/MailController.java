package com.jl.arky.jfinal.controller.admin;

import com.jfinal.core.Controller;
import com.jl.arky.jfinal.utils.TheadPool;
import com.jl.arky.jfinal.utils.ThreadMail;

public class MailController extends Controller {
	public void index() {
		render("index.html");
	}

	public void send() throws Exception {
		String[] arr = getPara("address").split("\n");
		String theme = getPara("theme");
		String content = getPara("content");
		// 线程池5个，发送邮件
		TheadPool tp = new TheadPool(5);

		for (String receiver : arr) {
			/*
			 * MailUtil.sendMail("13750528354@163.com", "13750528354@163.com",
			 * "313548323", receiver.trim(), theme, content);
			 */
			tp.execute(new ThreadMail("13750528354@163.com", "13750528354@163.com", "313548323", receiver.trim(), theme,
					content));

		}

		setAttr("success", "邮箱已成功发送");
		render("index.html");
	}
}
