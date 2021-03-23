package com.mgg;

/*
 * This class models an Item with a code a type and a name
 * 
 */
public abstract class Item {

	private String code;
	private String type;
	private String name;
	
	 
	public Item(String code, String type, String name) {
		this.code = code;
		this.type = type;
		this.name = name;
		
	}
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "Item [code=" + code + ", type=" + type + ", name=" + name + "]";
	}
	
	
	
}
