package com.jl.arky.jfinal.controller.admin;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jl.arky.jfinal.model.AdminLogModel;
import com.jl.arky.jfinal.model.AdminModel;
import com.jl.arky.jfinal.utils.CaptchaRender;
import com.jl.arky.jfinal.utils.IpKit;
import com.jl.arky.jfinal.utils.MD5utils;
import com.jl.arky.jfinal.utils.RegexUtils;

//@Before(LoginInterceptor.class)
public class AdminController extends Controller {

	public void index() {

		/*
		 * 此处将时间转回正确的格式
		 */
		List<Model> AdminModels = AdminModel.dao.find("select * from admin");
		for (Model AdminModel : AdminModels) {
			AdminModel.set("regtime", AdminModel.getLong("regtime") * 1000);
		}
		setAttr("userPage", AdminModels);
		// 返回结果给页面
		render("index.html");
	}

	/*
	 * 生成验证码
	 */
	@Clear
	public void veriCode() {
		// 生成验证码，并把验证码的MD5散列码放入session域
		CaptchaRender img = new CaptchaRender(4);
		setSessionAttr(CaptchaRender.DEFAULT_CAPTCHA_MD5_CODE_KEY, img.getMd5RandonCode());
		render(img);
	}

	/*
	 * 添加管理员
	 * 
	 */

	public void addAdmin() {
		// 获取要添加的管理员信息
		AdminModel adminModel = getModel(AdminModel.class);
		// 检验页面表单的数据是否正确
		String username = adminModel.get("username");
		String password = adminModel.get("password");
		String name = adminModel.get("name");

		// 判断表单username是否为空和数据的格式是否正确
		if (!checkUsername(username, password, name, "add.html", 0)) {
			return;
		}
		// 判断表单password是否为空和数据的格式是否正确
		if (!checkPassword(username, password, name, "add.html", 0)) {
			return;
		}
		// 判断表单name是否为空和数据的格式是否正确
		if (!checkName(username, password, name, "add.html", 0)) {
			return;
		}

		// 检验当前的用户是否已经注册
		if (AdminModel.dao.find("select * from admin where username=?", adminModel.get("username")).size() != 0) {
			setAttr("usernamemsg", "当前用户已经注册");
			render("add.html");
			return;
		}
		// 此处为密码password,进行加密处理
		String savepassword = MD5utils.md5Password(password);
		// 添加注册时间，登录时间
		long restime = System.currentTimeMillis() / 1000;
		adminModel.set("regtime", restime);
		adminModel.set("logintime", restime);
		adminModel.set("type", 1);
		adminModel.set("password", savepassword);
		adminModel.save();
		redirect("/Admin/Admin");

	}

	// 判断表单name是否为空和数据的格式是否正确
	private boolean checkName(String username, String password, String name, String URL, int id) {
		if (!StringUtils.isBlank(name)) {// 判断表单name是否为空
			if (RegexUtils.checkAdmin(name) != true) {
				setAttr("namemsg", "姓名格式错误");
				setAdminModel(username, password, name, id);
				render(URL);
				return false;
			}

		} else {
			setAttr("namemsg", "姓名不能为空");
			setAdminModel(username, password, name, id);
			render(URL);
			return false;
		}
		return true;

	}

	// 判断表单password是否为空和数据的格式是否正确
	private boolean checkPassword(String username, String password, String name, String URL, int id) {
		if (!StringUtils.isBlank(password)) { // 判断表单password是否为空
			// 判断表单password数据的格式是否正确
			if (RegexUtils.checkPassWord(password) != true) {
				System.out.println("密码格式错误");
				setAttr("passwordmsg", "密码格式错误");
				setAdminModel(username, password, name, id);
				render(URL);
				return false;
			}

		} else {
			setAttr("passwordmsg", "密码不能为空");
			setAdminModel(username, password, name, id);
			render(URL);
			return false;
		}
		return true;
	}

	// 判断表单username是否为空和数据的格式是否正确
	private boolean checkUsername(String username, String password, String name, String URL, int id) {
		if (!StringUtils.isBlank(username)) {
			if (RegexUtils.checkAdmin(username) != true) {
				System.out.println("用户名格式错误");
				setAttr("usernamemsg", "用户名格式错误");
				setAdminModel(username, password, name, id);
				render(URL);
				return false;
			}
		} else {
			setAttr("usernamemsg", "用户名不能为空");
			setAdminModel(username, password, name, id);
			render(URL);
			return false;
		}
		return true;
	}

	/*
	 * 设置回显信息
	 */
	private void setAdminModel(String username, String password, String name, int id) {
		setAttr("username", username);
		setAttr("password", password);
		setAttr("name", name);
		setAttr("id", id);
	}

	/*
	 * 删除管理员
	 */
	public void deleteAdminById() {
		// 获取要删除的管理员信息
		int id = getParaToInt("id");
		AdminModel AdminModel = new AdminModel();
		// 根据id删除管理员
		AdminModel.deleteById(id);
		// 删除成功，返回列表页面
		redirect("/Admin/Admin");

	}

	/*
	 * 更新管理员信息
	 */
	public void updateAdminInfo() {
		// 获取要更新的管理员信息
		AdminModel adminModel = getModel(AdminModel.class);
		String password = adminModel.get("password");
		String name = adminModel.get("name");
		int id = adminModel.getInt("id");
		System.out.println(password);
		System.out.println(id);

		// 判断表单password是否为空和数据的格式是否正确
		if (!checkPassword("", password, name, "update.html", id)) {
			return;
		}
		// 判断表单name是否为空和数据的格式是否正确
		if (!checkName("", password, name, "update.html", id)) {
			return;
		}
		// 对密码进行md5加密

		Db.update("update admin set name=? ,password=? where id=?", adminModel.get("name"),
				MD5utils.md5Password(password), id);
		redirect("/Admin/Admin");

	}

	/*
	 * 查询管理员信息
	 */
	public void queryAdminInfo() {
		// 向数据库查询数据
		// 返回结果给页面
		List<Model> AdminModels = AdminModel.dao.find("select * from admin");
		for (Model AdminModel : AdminModels) {
			long now = AdminModel.getLong("regtime") * 1000;
			AdminModel.set("regtime", now);
		}
		setAttr("userPage", AdminModels);
		redirect("/Admin/Admin");
	}

	/*
	 * 管理员登录
	 */
	@Clear
	public void login() throws Exception {
		// 获取请求登录的管理者信息
		AdminModel adminModel = getModel(AdminModel.class);
		long logintime = System.currentTimeMillis() / 1000;
		String username = adminModel.getStr("username");
		// 通过请求登录的管理者信息，查询数据库获取密码
		List<Model> AdminModels = AdminModel.dao.find("select * from admin where username=?", username);
		// 获取验证码
		String captchaCode = getPara("captchaCode");
		Object objMd5RandomCode = this.getSessionAttr(CaptchaRender.DEFAULT_CAPTCHA_MD5_CODE_KEY);
		System.out.println("这里是session中" + objMd5RandomCode);
		System.out.println("这里是验证证中" + captchaCode);
		// 检验验证码是否正确
		if (CaptchaRender.encrypt(captchaCode).equals(objMd5RandomCode)
				|| CaptchaRender.encrypt(captchaCode.toUpperCase()).equals(objMd5RandomCode)) {
			System.out.println("验证码正确");
		} else {
			//此处添加管理员登录失败的记录
			/*
			 * 将管理员登陆的信息，添加入admin_log表
			 */
			saveAdminLog(AdminModels,logintime,"登录","登录失败,验证码错误");
			redirect("/Admin/Index/toLogin?error=1");
			return;
		}

		if (!AdminModels.isEmpty()) {
			String password = (String) AdminModels.get(0).get("password");
			String savepassword = MD5utils.md5Password(adminModel.getStr("password"));
			// 比对数据库的密码和用户输入的密码是否一致
			if (password.equals(savepassword)) {
				// 密码正确，修改当前用户的上次登录时间。
			
				AdminModels.get(0).set("logintime", logintime);
				AdminModels.get(0).update();
				/*
				 * 管理员登录完成，将管理员信息添加到session
				 */
				setSessionAttr("AdminModel", AdminModels.get(0));
				/*
				 * 将管理员登陆的信息，添加入admin_log表
				 */
				saveAdminLog(AdminModels,logintime,"登录","登录成功");
				
				redirect("/Admin/Index");

			} else {
				// setAttr("msg","密码错误");
				//此处添加登录失败的记录
				/*
				 * 将管理员登陆的信息，添加入admin_log表
				 */
				saveAdminLog(AdminModels,logintime,"登录","登录失败，密码错误");
				redirect("/Admin/Index/toLogin?error=2");
			}
		}else{
			redirect("/Admin/Index/toLogin?error=2");
		}

	}

	private void saveAdminLog(List<Model> AdminModels,
			Long logintime,String type,String result) {
		if(!AdminModels.isEmpty()){
			AdminLogModel adminLogModel=new AdminLogModel();
			adminLogModel.set("adminid", AdminModels.get(0).get("id"));
			adminLogModel.set("logintime", logintime);
			adminLogModel.set("username",  AdminModels.get(0).get("username"));
			adminLogModel.set("operationtype", type);
			adminLogModel.set("operationresult", result);
			//此处获取登录管理员的ip
			adminLogModel.set("ip", getReadIp());
			adminLogModel.save();
		}
		
	}

	private String getReadIp() {
		
		return IpKit.getRealIp(this.getRequest());
	}

	/*
	 * 处理添加页面跳转
	 */
	public void addViewChange() {

		render("add.html");
	}

	/*
	 * 处理更新页面跳转
	 */
	// @ActionKey("/updateViewChange")
	public void updateViewChange() {
		int id = getParaToInt("id");

		AdminModel AdminModel = getModel(AdminModel.class);
		setAttr("AdminModel", AdminModel);

		setAttr("id", id);
		render("update.html");
	}
	// /*
	// * 查询单个管理员
	// */
	// public void querySingleAdminModel(){
	// String username=getPara("username");
	// List<AdminModelModel> singleAdminModel= AdminModelModel.dao.find("select
	// * from AdminModel where
	// username=?",username);
	// setAttr("singleAdminModel",singleAdminModel);
	// redirect("http://localhost/queryAdminModelInfo");
	// }

	/*
	 * 校验输入管理员是否注册（ajax）
	 */
	@Clear
	public void doGet() {
		List<Model> AdminModels = AdminModel.dao.find("select * from admin where username=?", getPara("username"));
		renderJson(AdminModels.size());
	}

	/*
	 * 添加一批管理员
	 */
	public void addAdmins() {

	}
	
   /*
    * 退出登录
    */
	public void exit(){
		setSessionAttr("AdminModel", null);
		redirect("/Admin/Index");
	}
	/*
	 * 查询管理员登录记录
	 */
	public void queryAdminLogInfo(){
		
		List<Model> adminLogModels=AdminLogModel.dao.find("select * from admin_log");
		//此处将时间转换格式
		for (Model adminLogModel : adminLogModels) {
			adminLogModel.set("logintime", adminLogModel.getLong("logintime") * 1000);
		}
		setAttr("adminLogModels",adminLogModels);

//		//此处区别超级管理员，和管理员权限
//		AdminModel adminModel =(AdminModel) this.getSession().getAttribute("AdminModel");
//		System.out.println(adminModel.get("username"));
//		System.out.println(adminModel.get("type"));
//        if(adminModel!=null)
//		if(adminModel.get("type").equals(0)){
//			setAttr("disabled","");
//		}else{
//			setAttr("disabled","disabled");
//		}
		render("loglist.html");
	}
	/*
	 * 通过id删除管理员登陆记录
	 */
	public void deleteLogInfoById(){
		// 获取要删除的管理员信息
		int id = getParaToInt("id");
		AdminLogModel.dao.deleteById(id);
		redirect("/Admin/Admin/queryAdminLogInfo");
	}
	

}
