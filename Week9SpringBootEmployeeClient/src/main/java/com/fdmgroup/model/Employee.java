package com.fdmgroup.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;

public class Employee {
	
	private Long id;
	@NotBlank(message = "first name can not be blank")
	private String firstname;
   @NotBlank (message = "last name can not be blank")
   private String lastname;
	private BigDecimal salary;
   @NotBlank (message = "state can not be blank")
	private String state;
   @NotBlank (message = "country can  not be blank")
	private String country;
	
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Employee(Long id, String firstname, String lastname,BigDecimal salary,String state,String country) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.salary = salary;
		this.state = state;
		this.country = country;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public BigDecimal getSalary() {
		return salary;
	}


	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", salary=" + salary
				+ ", state=" + state + ", country=" + country + "]";
	}
    
	
	
	
	
}
