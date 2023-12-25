package com.peradone.library.persistence.dto;

public class Author {
	private int id;
	private String firstName;
	private String secondName;
	private String companyName;

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public String getCompanyName() {
		return companyName;
	}
	public void setId(int id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	@Override
	public String toString() {
		return "Author [id=" + id + ", first_name=" + firstName + ", second_name=" + secondName + ", company_name=" + companyName +"]";
	}


}
