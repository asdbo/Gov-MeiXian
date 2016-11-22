package com.jl.arky.jfinal.controller.admin;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.jfinal.core.Controller;
import com.jl.arky.jfinal.model.BusinessModel;
import com.jl.arky.jfinal.utils.TimeUtil;

public class BusinessController extends Controller{
	public void toAdd(){
		render("add.html");
	}
	//添加
	public void add(){
		String para = getPara("identifier");//编号名称
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
		String format = dateFormat.format(new Date());
		//获取当天办理的业务的数量
		//获取当天0时的时间戳
		Calendar cal = Calendar.getInstance(); 
		cal.set(Calendar.HOUR_OF_DAY, 0); 
		cal.set(Calendar.SECOND, 0); 
		cal.set(Calendar.MINUTE, 0); 
		cal.set(Calendar.MILLISECOND, 0); 
		long daySecond= cal.getTimeInMillis()/1000;
		String sql ="select count(id) num from schedule where time>=?";
		BusinessModel first = BusinessModel.dao.findFirst(sql,daySecond);
		Long count = first.getLong("num");
		DecimalFormat  df=new DecimalFormat("000");//格式化数字
		String string = df.format(count);
		//受理编号
		String identifier=para+"-"+format+string;
		
		//受理单位
		String company = getPara("company");
		//业务名称
		String title = getPara("title");
		//时间
		long time=System.currentTimeMillis()/1000;
		//状态
		int status=0;
		new BusinessModel().set("identifier", identifier).set("company", company).set("title", title).set("time", time).set("status", status).save();
		redirect("/Admin/Business/showList");
		
	}
	//删除
	public void delete(){
		String id = getPara("id");
		BusinessModel.dao.deleteById(id);
		redirect("/Admin/Business/showList");
	}
	//修改状态
	public void update(){
		String id = getPara("id");
		BusinessModel bm = BusinessModel.dao.findById(id);
		bm.set("status", 1).update();
		redirect("/Admin/Business/showList");
	}
	//显示
	public void showList(){
		String sql="select * from schedule ";
		List<BusinessModel> bus = BusinessModel.dao.find(sql);
		for(BusinessModel bm:bus){
			bm.set("time", TimeUtil.timeStampToDate(bm.getLong("time")));
		}
		setAttr("bus", bus);
		render("index.html");
	}
}
