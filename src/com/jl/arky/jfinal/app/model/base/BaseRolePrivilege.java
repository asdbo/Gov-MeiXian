package com.jl.arky.jfinal.app.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseRolePrivilege<M extends BaseRolePrivilege<M>> extends Model<M> implements IBean {

	public void setRoleid(java.lang.Integer roleid) {
		set("roleid", roleid);
	}

	public java.lang.Integer getRoleid() {
		return get("roleid");
	}

	public void setPrivilegeid(java.lang.Integer privilegeid) {
		set("privilegeid", privilegeid);
	}

	public java.lang.Integer getPrivilegeid() {
		return get("privilegeid");
	}

}
