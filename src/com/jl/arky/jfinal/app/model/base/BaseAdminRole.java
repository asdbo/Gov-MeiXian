package com.jl.arky.jfinal.app.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseAdminRole<M extends BaseAdminRole<M>> extends Model<M> implements IBean {

	public void setAdminid(java.lang.Integer adminid) {
		set("adminid", adminid);
	}

	public java.lang.Integer getAdminid() {
		return get("adminid");
	}

	public void setRoleid(java.lang.Integer roleid) {
		set("roleid", roleid);
	}

	public java.lang.Integer getRoleid() {
		return get("roleid");
	}

}
