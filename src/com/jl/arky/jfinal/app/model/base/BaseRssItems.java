package com.jl.arky.jfinal.app.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseRssItems<M extends BaseRssItems<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public void setTitle(java.lang.String title) {
		set("title", title);
	}

	public java.lang.String getTitle() {
		return get("title");
	}

	public void setLink(java.lang.String link) {
		set("link", link);
	}

	public java.lang.String getLink() {
		return get("link");
	}

	public void setDescription(java.lang.String description) {
		set("description", description);
	}

	public java.lang.String getDescription() {
		return get("description");
	}

	public void setLanguage(java.lang.String language) {
		set("language", language);
	}

	public java.lang.String getLanguage() {
		return get("language");
	}

	public void setAuthor(java.lang.String author) {
		set("author", author);
	}

	public java.lang.String getAuthor() {
		return get("author");
	}

	public void setPubdate(java.lang.String pubdate) {
		set("pubdate", pubdate);
	}

	public java.lang.String getPubdate() {
		return get("pubdate");
	}

	public void setTid(java.lang.Integer tid) {
		set("tid", tid);
	}

	public java.lang.Integer getTid() {
		return get("tid");
	}

	public void setCategory(java.lang.String category) {
		set("category", category);
	}

	public java.lang.String getCategory() {
		return get("category");
	}

}
