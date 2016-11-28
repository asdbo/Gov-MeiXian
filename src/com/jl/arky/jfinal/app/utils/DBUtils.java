package com.jl.arky.jfinal.app.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

public class DBUtils {

	public static String dbtable(String tablename){
		return "wu_"+tablename;
	}

//	public static User addUser(Controller controller,String s){
//		User user=controller.getModel(User.class,s);
//		user.set("createtime",System.currentTimeMillis())
//		.set("updatetime",System.currentTimeMillis());
//		boolean b=user.save();
//		if(b){
//			return user;
//		}else{
//			return null;
//		}
//	}

	public static <T extends Model<T>> T updateModels(Class<T> t,Map<String,Object> map1,Map<String,Object> map2){
		//T t2=t.newInstance();
		//		List<T> ts=DBUtils.find(t,map1);
		//		Boolean b;
		//		for(T t2:ts){
		//			b=t2._setAttrs(map2).save();
		//			if(b){
		//			}
		//		}
		return null;
	}

	public static <T extends Model<T>> T addModel(Class<T> t,Controller controller,String s){
		T t2=controller.getModel(t,s);
		boolean b=t2.save();
		if(b){
			return t2;
		}else{
			return null;
		}
	}

	public static <T extends Model<T>> T addModel(Class<T> t,String s,Map<String,Object> map){
		T t2=null;
		try {
			t2 = t.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		if(map!=null){
			for(Map.Entry<String,Object> entry:map.entrySet()){
				t2.set(entry.getKey(),entry.getValue());
			}
		}
		boolean b=t2.save();
		if(b){
			return t2;
		}else{
			return null;
		}
	}

	public static <T extends Model<T>> T addModel(Class<T> t,Controller controller,String s,Map<String,Object> map){
		T t2=controller.getModel(t,s);
		if(map!=null){
			for(Map.Entry<String,Object> entry:map.entrySet()){
				t2.set(entry.getKey(),entry.getValue());
			}
		}
		boolean b=t2.save();
		if(b){
			return t2;
		}else{
			return null;
		}
	}

//	public static User fingUserByNameAndPassword(String username,String password) throws Exception{
//		if(username==null||password==null){
//			throw new Exception("0");
//		}else{
//			User user=User.dao.findFirst("select * from "+DBUtils.dbtable("user")+" where username=? and password=?",username,password);
//			if(user==null){
//				throw new Exception("1");
//			}else{
//				return user;
//			}
//		}
//	}

	public static <T extends Model<T>> List<T> find(Class<T> t,Map<String, Object> map){
		String tname=t.getSimpleName();
		List<T> list=new ArrayList<T>();
		StringBuffer stringBuffer=new StringBuffer("select * from "+DBUtils.dbtable(tname));
		Object[] values = null;
		int i=0;
		if(map!=null&&map.size()>0){
			values=new Object[map.size()];
			stringBuffer.append(" where 1=1");
			for(Map.Entry<String, Object> entry:map.entrySet()){
				stringBuffer.append(" and ")
				.append(entry.getKey())
				.append("=?");
				values[i]=entry.getValue();
				i++;
			}
		}
		T t2=null;
		try {
			t2 = t.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
		if(values!=null){
			list=t2.find(stringBuffer.toString(), values);
		}else{
			list=t2.find(stringBuffer.toString());
		}
		return list;
	}

	public static <T extends Model<T>> T findOne(Class<T> t,Map<String, Object> map){
		String tname=t.getSimpleName();
		StringBuffer stringBuffer=new StringBuffer("select * from "+DBUtils.dbtable(tname));
		Object[] values = null;
		int i=0;
		if(map!=null&&map.size()>0){
			values=new Object[map.size()];
			stringBuffer.append(" where 1=1");
			for(Map.Entry<String, Object> entry:map.entrySet()){
				stringBuffer.append(" and ")
				.append(entry.getKey())
				.append("=?");
				values[i]=entry.getValue();
				i++;
			}
		}
		T t2=null;
		try {
			t2 = t.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		if(values!=null){
			return t2.findFirst(stringBuffer.toString(), values);
		}else{
			return t2.findFirst(stringBuffer.toString());
		}
	}
}
