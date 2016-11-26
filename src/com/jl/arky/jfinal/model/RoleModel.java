package com.jl.arky.jfinal.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

/**
 * @author zepeng
 *角色类
 */
public class RoleModel extends Model<Model>{
	public static final RoleModel dao = new RoleModel();
	
	
	public List<Model> getAllPrivilege(){
	List<Model> privilegeList= R_P_Model.dao.find("select * from role_privilege where roleid = ?",this.get("id"));
		
		return privilegeList;
		
	}


	
	
}
