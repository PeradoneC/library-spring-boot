package com.peradone.library.persistence.dto;

public class Publisher {

	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + "]";
	}
}
