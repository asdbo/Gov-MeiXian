package com.jl.arky.jfinal.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jfinal.plugin.activerecord.Model;

/**
 * @author zepeng
 * 权限Model
 *
 */
public class PermissionModel extends Model<Model>{

	
	public static final PermissionModel dao = new PermissionModel();
	
	private PermissionModel parent;
	private List<Model> children = new ArrayList<Model>(); 
	
	public void install(){
		PermissionModel permissionModel=new PermissionModel();
//		permissionModel.set(attr, value);
//		permissionModel.set(attr, value);
//		permissionModel.set(attr, value);
//		permissionModel.set(attr, value);
//		permissionModel.save();
	}
	public PermissionModel getParent() {
		return parent;
	}

	public void setParent(PermissionModel parent) {
		this.parent = parent;
	}

	public List<Model> getChildren() {
		children=dao.find("select * from permission where parentid = ?",this.get("id"));
		return children;
	}

//	public void setChildren(Set<PermissionModel> children) {
//		this.children = children;
//	}
	
}
