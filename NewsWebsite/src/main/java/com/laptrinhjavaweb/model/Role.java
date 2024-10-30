package com.laptrinhjavaweb.model;


public class Role extends AbstractModel<Role> {
	private String code;
	private String name;
	
	public Role() {	
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
