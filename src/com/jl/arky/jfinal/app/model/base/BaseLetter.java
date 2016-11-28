package com.jl.arky.jfinal.app.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseLetter<M extends BaseLetter<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public void setPeopleName(java.lang.String peopleName) {
		set("people_name", peopleName);
	}

	public java.lang.String getPeopleName() {
		return get("people_name");
	}

	public void setPhone(java.lang.String phone) {
		set("phone", phone);
	}

	public java.lang.String getPhone() {
		return get("phone");
	}

	public void setEmail(java.lang.String email) {
		set("email", email);
	}

	public java.lang.String getEmail() {
		return get("email");
	}

	public void setAddress(java.lang.String address) {
		set("address", address);
	}

	public java.lang.String getAddress() {
		return get("address");
	}

	public void setPostalcode(java.lang.String postalcode) {
		set("postalcode", postalcode);
	}

	public java.lang.String getPostalcode() {
		return get("postalcode");
	}

	public void setLettleType(java.lang.String lettleType) {
		set("lettle_type", lettleType);
	}

	public java.lang.String getLettleType() {
		return get("lettle_type");
	}

	public void setLetterDept(java.lang.String letterDept) {
		set("letter_dept", letterDept);
	}

	public java.lang.String getLetterDept() {
		return get("letter_dept");
	}

	public void setTheme(java.lang.String theme) {
		set("theme", theme);
	}

	public java.lang.String getTheme() {
		return get("theme");
	}

	public void setContent(java.lang.String content) {
		set("content", content);
	}

	public java.lang.String getContent() {
		return get("content");
	}

	public void setState(java.lang.Integer state) {
		set("state", state);
	}

	public java.lang.Integer getState() {
		return get("state");
	}

	public void setReceiveTime(java.lang.String receiveTime) {
		set("receive_time", receiveTime);
	}

	public java.lang.String getReceiveTime() {
		return get("receive_time");
	}

	public void setReplyTime(java.lang.String replyTime) {
		set("reply_time", replyTime);
	}

	public java.lang.String getReplyTime() {
		return get("reply_time");
	}

	public void setReplyContent(java.lang.String replyContent) {
		set("reply_content", replyContent);
	}

	public java.lang.String getReplyContent() {
		return get("reply_content");
	}

	public void setScore(java.lang.Integer score) {
		set("score", score);
	}

	public java.lang.Integer getScore() {
		return get("score");
	}

	public void setCid(java.lang.Integer cid) {
		set("cid", cid);
	}

	public java.lang.Integer getCid() {
		return get("cid");
	}

}
