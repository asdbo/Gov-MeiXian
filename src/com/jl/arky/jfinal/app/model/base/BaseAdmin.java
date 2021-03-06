package com.jl.arky.jfinal.app.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseAdmin<M extends BaseAdmin<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public void setRegtime(java.lang.Long regtime) {
		set("regtime", regtime);
	}

	public java.lang.Long getRegtime() {
		return get("regtime");
	}

	public void setLogintime(java.lang.Long logintime) {
		set("logintime", logintime);
	}

	public java.lang.Long getLogintime() {
		return get("logintime");
	}

	public void setUsername(java.lang.String username) {
		set("username", username);
	}

	public java.lang.String getUsername() {
		return get("username");
	}

	public void setPassword(java.lang.String password) {
		set("password", password);
	}

	public java.lang.String getPassword() {
		return get("password");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}

	public java.lang.String getName() {
		return get("name");
	}

	public void setType(java.lang.Integer type) {
		set("type", type);
	}

	public java.lang.Integer getType() {
		return get("type");
	}

	public void setRoleid(java.lang.Integer roleid) {
		set("roleid", roleid);
	}

	public java.lang.Integer getRoleid() {
		return get("roleid");
	}

}
