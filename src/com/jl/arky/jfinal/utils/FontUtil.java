package com.jl.arky.jfinal.utils;

import java.io.UnsupportedEncodingException;

public class FontUtil {
	/*
	 * 繁体转utf8
	 * */
	public static String big5ToChinese( String s )
	{
	    try{
	        if ( s == null || s.equals( "" ) )
	            return("");
	        String newstring = null;
	        newstring = new String( s.getBytes( "big5" ), "utf8" );
	        return(newstring);
	    }
	    catch ( UnsupportedEncodingException e )
	    {
	        return(s);
	    }
	}
	 
	 /*
	  * utf8转繁体
	  * */
	public static String ChineseTobig5( String s )
	{
	    try{
	        if ( s == null || s.equals( "" ) )
	            return("");
	        String newstring = null;
	        newstring = new String( s.getBytes( "utf8" ), "big5" );
	        return(newstring);
	    }
	    catch ( UnsupportedEncodingException e )
	    {
	        return(s);
	    }
	}
}
