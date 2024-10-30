package com.laptrinhjavaweb.utils;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;


public class HttpUtils {
	private String value;
	public HttpUtils (String v)
	{
		this.value=v;
	}
	
	public <T> T toModel(Class<T> tClass){
		try {
			return new ObjectMapper().readValue(this.value,tClass);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static HttpUtils of (BufferedReader reader)
	{
		StringBuilder  sb= new StringBuilder();
		String line;
		try {
			while( (line=reader.readLine()) != null) 
				sb.append(line);
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		return new HttpUtils(sb.toString());
	}
}
