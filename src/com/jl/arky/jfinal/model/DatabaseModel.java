package com.jl.arky.jfinal.model;

import java.io.Serializable;

public class DatabaseModel implements Serializable {

	private String name;
	private String length;
	private String date;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
