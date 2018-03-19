package com.deyi.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import net.sf.json.JSONSerializer;

public class ResponseWriteUtils {
	

	public static void returnAjax(HttpServletResponse response, String content) {
		
		try {
			response.setContentType("text/html; charset=utf-8");
			System.out.println(new Gson().toJson(content));
			response.getWriter().print(new Gson().toJson(content));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    public static void returnAjax(HttpServletResponse response,List list) {
		
		try {
			response.setContentType("text/html; charset=utf-8");
			//response.getWriter().write(JSONSerializer.toJSON(list).toString());
			response.getWriter().print(JSONSerializer.toJSON(list).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }
    
    public static void returnAjax(HttpServletResponse response,Object object) {
    	
		
  		try {
  			response.setContentType("text/html; charset=utf-8");
  			//response.getWriter().write(JSONSerializer.toJSON(list).toString());
  			System.out.println(new Gson().toJson(object));
  			response.getWriter().print(new Gson().toJson(object));
  		} catch (IOException e) {
  			e.printStackTrace();
  		}
  	 }
}
