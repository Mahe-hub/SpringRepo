package com.fdmgroup.Model;

import java.math.BigDecimal;


import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class EmployeeClass {

	@Id
	@GeneratedValue
	private Long id;

	@NotBlank(message = "fname can not be blank")
	private String firstname;
	@NotBlank(message = "lname can not be blank")
	private String lastname;

	private BigDecimal salary;
	@NotBlank(message = "state can not be blank")
	private String state;
	@NotBlank(message = "country can not be blank")
	private String country;
	
	
	public EmployeeClass() {
		super();
		// TODO Auto-generated constructor stub
	}


	public EmployeeClass(Long id, String firstname, String lastname, BigDecimal salary, String state, String country) {
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
		return "EmployeeClass [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", salary=" + salary
				+ ", state=" + state + ", country=" + country + "]";
	}
	
	
}
