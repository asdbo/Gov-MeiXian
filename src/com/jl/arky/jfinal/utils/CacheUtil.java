package com.jl.arky.jfinal.utils;

import com.jfinal.plugin.ehcache.CacheKit;
/*
 * 缓存工具封装
 * 
 * */
public class CacheUtil {
	public static String defaultCacheName = "DefaultCache";
	public static String ConfigFileName = "src/com/jl/arky/jfinal/config/ehcache.xml";
	public static Object get(String key){
		return get(defaultCacheName, key);
	}
	public static Object get(String cacheName,String key){
		return CacheKit.get(cacheName, key);
	}
	public static void set(String key,Object value){
		set(defaultCacheName,key,value);
	}
	public static void set(String cacheName,String key,Object value){
		CacheKit.put(cacheName, key, value);
	}
}
