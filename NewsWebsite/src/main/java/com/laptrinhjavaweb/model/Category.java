package com.laptrinhjavaweb.model;

public class Category extends AbstractModel<Category>{
	private String code;
	private String name;
	
	public Category()
	{
		
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
