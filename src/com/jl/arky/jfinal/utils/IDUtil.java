package com.jl.arky.jfinal.utils;

import java.text.SimpleDateFormat;


import java.util.Date;
import java.util.Random;

/**
 * 各种id生成策略
 */
public class IDUtil {

	/**
	 * 图片,文件名生成
	 */
	public static String getImageName() {
		// 取当前时间的长整形值包含毫秒
		long millis = System.currentTimeMillis();
		Date nowTime = new Date(millis);
		SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		// 加上三位随机数
		Random random = new Random();
		int end3 = random.nextInt(999);
		// 如果不足三位前面补0
		String str = sdFormatter.format(nowTime) + String.format("%03d", end3);
		return str;
	}

	/**
	 * id生成
	 */
	public static long getId() {
		// 取当前时间的长整形值包含毫秒
		long millis = System.currentTimeMillis();
		// long millis = System.nanoTime();
		// 加上两位随机数
		Random random = new Random();
		int end2 = random.nextInt(999);
		// 如果不足两位前面补0
		String str = millis + String.format("%02d", end2);
		long id = new Long(str);
		return id;
	}

}
