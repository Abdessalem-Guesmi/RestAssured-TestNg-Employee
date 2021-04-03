package com.restAssuredTestng.model;

public class Employee {

	private String nameEmploy;
	private String salaryEmploy;
	private String ageEmploy;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(String nameEmploy, String salaryEmploy, String ageEmploy) {
		super();
		this.nameEmploy = nameEmploy;
		this.salaryEmploy = salaryEmploy;
		this.ageEmploy = ageEmploy;
	}

	public String getNameEmploy() {
		return nameEmploy;
	}

	public void setNameEmploy(String nameEmploy) {
		this.nameEmploy = nameEmploy;
	}

	public String getSalaryEmploy() {
		return salaryEmploy;
	}

	public void setSalaryEmploy(String salaryEmploy) {
		this.salaryEmploy = salaryEmploy;
	}

	public String getAgeEmploy() {
		return ageEmploy;
	}

	public void setAgeEmploy(String ageEmploy) {
		this.ageEmploy = ageEmploy;
	}

}
