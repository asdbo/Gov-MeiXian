package com.jl.arky.jfinal.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class Letter_Type_Model extends Model<Model>{
public static final Letter_Type_Model dao = new Letter_Type_Model();
	
	public Page<Model> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from Letter_Type_Model order by id desc");
	}
	
	public List<Model> getLetterTypes(){
		String sql = "select types from letter_type";
		return Letter_Type_Model.dao.find(sql);
	}
}
