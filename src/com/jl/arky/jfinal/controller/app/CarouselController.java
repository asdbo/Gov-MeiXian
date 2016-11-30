package com.jl.arky.jfinal.controller.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jl.arky.jfinal.app.interceptor.MainAppInterceptor;
import com.jl.arky.jfinal.app.model.Carousel;

@Clear
@Before(MainAppInterceptor.class)
public class CarouselController extends Controller {

	public void index(){
		String method = getRequest().getMethod();
		if("get".equalsIgnoreCase(method)){
			List<Carousel> list=Carousel.dao.find("select * from carousel where carouselType =  0");
			List<Map> lists=new ArrayList<Map>();
			for(Carousel carousel:list){
				Map<String,String> map=new HashMap<String, String>();
				map.put("link", carousel.getLink());
				map.put("title", carousel.getTitle());
				map.put("tolink", carousel.getTolink());
				lists.add(map);
			}
			renderJson(lists);
		}
	}
}