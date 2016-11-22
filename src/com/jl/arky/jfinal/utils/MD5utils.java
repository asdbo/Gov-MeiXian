package com.jl.arky.jfinal.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5utils {
	 public static String md5Password(String password) {
	        try { //得到一个信息摘要器
	            MessageDigest digest = MessageDigest.getInstance("md5");

	            byte[] result = digest.digest(password.getBytes());
	            StringBuffer buffer = new StringBuffer();
	            //把没一个byte做一个与运算0xff；
	            for (byte b : result) {
	                //与运算
	                int number = b & 0xff;
	                String str = Integer.toHexString(number);
	                if (str.length() == 1) {
	                    buffer.append("0");
	                }
	                buffer.append(str);
	            }
	            //标识的md5加密后的结果
	            System.out.println(buffer.toString());
	            return buffer.toString();

	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	            return "";
	        }
	    }
	 /*** 
	     * MD5加码 生成32位md5码 
	     */  
	    public static String string2MD5(String inStr){  
	        MessageDigest md5 = null;  
	        try{  
	            md5 = MessageDigest.getInstance("MD5");  
	        }catch (Exception e){  
	            System.out.println(e.toString());  
	            e.printStackTrace();  
	            return "";  
	        }  
	        char[] charArray = inStr.toCharArray();  
	        byte[] byteArray = new byte[charArray.length];  
	  
	        for (int i = 0; i < charArray.length; i++)  
	            byteArray[i] = (byte) charArray[i];  
	        byte[] md5Bytes = md5.digest(byteArray);  
	        StringBuffer hexValue = new StringBuffer();  
	        for (int i = 0; i < md5Bytes.length; i++){  
	            int val = ((int) md5Bytes[i]) & 0xff;  
	            if (val < 16)  
	                hexValue.append("0");  
	            hexValue.append(Integer.toHexString(val));  
	        }  
	        return hexValue.toString();  
	  
	    }  
	 public static String convertMD5(String inStr){  
		  
	        char[] a = inStr.toCharArray();  
	        for (int i = 0; i < a.length; i++){  
	            a[i] = (char) (a[i] ^ 't');  
	        }  
	        String s = new String(a);  
	        return s;  
	  
	    }  
	  
	    // 测试主函数  
	    public static void main(String args[]) {  
	        String s = new String("tangfuqiang");  
	        System.out.println("原始：" + s);  
	        System.out.println("原始：" + md5Password(s));  
	        System.out.println("MD5后：" + string2MD5(s));  
	        System.out.println("加密的：" + convertMD5(s));  
	        System.out.println("解密的：" + convertMD5(convertMD5(s)));  
	  
	    }  
}
