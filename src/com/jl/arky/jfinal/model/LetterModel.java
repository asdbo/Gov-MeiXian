package com.jl.arky.jfinal.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class LetterModel extends Model<Model>{
public static final LetterModel dao = new LetterModel();
	
	public Page<Model> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from letter order by id desc");
	}
	
	public List<Model> showFirst(){
		String sql = "SELECT id,people_name,theme,receive_time,lettle_type,state FROM letter ORDER BY receive_time DESC LIMIT 13";
		return dao.find(sql);
	}	
	
	public Page<Model>  adminShow(int pageNumber, int pageSize){
		//String sql = "SELECT id,people_name,theme,content,letter_dept,receive_time,lettle_type,state FROM letter ORDER BY receive_time DESC";
		//return dao.find(sql);
		
		return paginate(pageNumber, pageSize, "SELECT id,people_name,theme,content,letter_dept,receive_time,lettle_type,state ", "FROM letter ORDER BY receive_time DESC");
	}	
	
	
	public Page<Model> showSecond(int pageNumber, int pageSize){
		return paginate(pageNumber, pageSize, "select id,lettle_type,theme,people_name,receive_time,reply_time,letter_dept", "from letter where reply_time<>'null' order by reply_time desc");
	}	
	
	public Page<Model> showComplain(int pageNumber, int pageSize){
		return paginate(pageNumber, pageSize, "select id,lettle_type,theme,people_name,receive_time,reply_time,letter_dept", "from letter where lettle_type = '投诉' AND reply_time<>'null' order by reply_time desc");
	}	
	
	public Page<Model> showAdmire(int pageNumber, int pageSize){
		return paginate(pageNumber, pageSize, "select id,lettle_type,theme,people_name,receive_time,reply_time,letter_dept", "from letter where lettle_type = '表扬' AND reply_time<>'null' order by reply_time desc");
	}
	
	public Page<Model> showSuggest(int pageNumber, int pageSize){
		return paginate(pageNumber, pageSize, "select id,lettle_type,theme,people_name,receive_time,reply_time,letter_dept", "from letter where lettle_type = '建议' AND reply_time<>'null' order by reply_time desc");
	}
	
	public Page<Model>  showConsult(int pageNumber, int pageSize){
		return paginate(pageNumber, pageSize, "select id,lettle_type,theme,people_name,receive_time,reply_time,letter_dept", "from letter where lettle_type = '咨询' AND reply_time<>'null' order by reply_time desc");
	}
	
}
