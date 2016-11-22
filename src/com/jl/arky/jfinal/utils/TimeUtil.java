package com.jl.arky.jfinal.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	public static String timeStampToDate(long second ){
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(new Date(second*1000));
		return date;
	}
	public static String MonthAndDay(long second){
		SimpleDateFormat dateFormat=new SimpleDateFormat("MM-dd");
		String date = dateFormat.format(new Date(second*1000));
		return date;
	}
	public static String detailTime(long second){
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = dateFormat.format(new Date(second*1000));
		return date;
	}
}
