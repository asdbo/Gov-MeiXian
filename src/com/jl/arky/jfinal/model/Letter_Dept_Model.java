package com.jl.arky.jfinal.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class Letter_Dept_Model extends Model<Model>{
public static final Letter_Dept_Model dao = new Letter_Dept_Model();
	
	public Page<Model> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from Letter_Dept_Model order by id desc");
	}
	public List<Model> getLetterDept(){
		String sql = "select depts from letter_dept";
		return Letter_Dept_Model.dao.find(sql);
	}
	
}
