package com.jl.arky.jfinal.controller.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jl.arky.jfinal.app.interceptor.MainAppInterceptor;
import com.jl.arky.jfinal.app.model.Channel;

@Clear
@Before(MainAppInterceptor.class)
public class ChannelController extends Controller{
	
	public void index(){
		List<Channel> channels=Channel.dao.find("select * from channel");
		List<Map> maps=new ArrayList<Map>();
		for(Channel channel:channels){
			if(!channel.hasChild()){
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("id", channel.getId());
				map.put("name", channel.getTitle());
				maps.add(map);
			}
		}
		renderJson(maps);
	}
	
	//单位事项栏目
	public void dwsx(){
		List<Channel> channels=Channel.dao.find("select * from channel where pid=159");
		List<Map> maps=new ArrayList<Map>();
		for(Channel channel:channels){
			if(!channel.hasChild()){
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("id", channel.getId());
				map.put("name", channel.getTitle());
				maps.add(map);
			}
		}
		renderJson(maps);
	}
	
}
