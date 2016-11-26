package com.jl.arky.jfinal.model;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class PrivilegeModel extends Model<Model>{
	
	
	public static final PrivilegeModel dao = new PrivilegeModel();
	
	private PrivilegeModel parent;
	private List<Model> children = new ArrayList<Model>(); 
	
//	public void install(){
//		PermissionModel permissionModel=new PermissionModel();
////		permissionModel.set(attr, value);
////		permissionModel.set(attr, value);
////		permissionModel.set(attr, value);
////		permissionModel.set(attr, value);
////		permissionModel.save();
//	}
	public PrivilegeModel getParent() {
		return parent;
	}

	public void setParent(PrivilegeModel parent) {
		this.parent = parent;
	}

	public List<Model> getChildren() {
		children=dao.find("select * from privilege where parentid = ? and type = 0",this.get("id"));
		return children;
	}
	public List<Model> getChildren1() {
		children=dao.find("select * from privilege where parentid = ?",this.get("id"));
		return children;
	}
//	public void setChildren(Set<PermissionModel> children) {
//		this.children = children;
//	}
}
