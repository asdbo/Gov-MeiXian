package com.jl.arky.jfinal.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class ChannelRightModel extends Model<Model>{
	public static final ChannelRightModel dao = new ChannelRightModel();
	
	/*
	 * 获取子栏目
	 */
	public List<ChannelRightModel> getChildren(){
		return null;
	}
	
	
}
