package com.jl.arky.jfinal.app.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseAdminLog<M extends BaseAdminLog<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public void setAdminid(java.lang.Long adminid) {
		set("adminid", adminid);
	}

	public java.lang.Long getAdminid() {
		return get("adminid");
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

	public void setOperationtype(java.lang.String operationtype) {
		set("operationtype", operationtype);
	}

	public java.lang.String getOperationtype() {
		return get("operationtype");
	}

	public void setOperationresult(java.lang.String operationresult) {
		set("operationresult", operationresult);
	}

	public java.lang.String getOperationresult() {
		return get("operationresult");
	}

	public void setIp(java.lang.String ip) {
		set("ip", ip);
	}

	public java.lang.String getIp() {
		return get("ip");
	}

}
