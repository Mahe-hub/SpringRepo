package com.fdmgroup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.model.Employee;

@Service
public class EmployeeService {

	private EmployeeRESTAPIClient employeeRestApiClient;

	@Autowired
	public EmployeeService(EmployeeRESTAPIClient employeeRestApiClient) {
		super();
		this.employeeRestApiClient = employeeRestApiClient;
	}
	
	
	//get all employees
	public List<Employee> getAll()
	{
		return this.employeeRestApiClient.getAllEmployees();
	}
	
    //create employee
	public Employee create(Employee employee) 
	{
		return this.employeeRestApiClient.create(employee);
	}
	
	//get employee by id
	public Employee getEmployee(Long id)
	{
		return this.employeeRestApiClient.findById(id);
	}
	
	//update 
	public Employee updateEmployee(Long id,Employee employee)
	{
		return this.employeeRestApiClient.update(id,employee);
	}
	
	//delete 
	public void delete(Long id) 
	{
		this.employeeRestApiClient.deleteEmployee(id);
	}
	
	public Employee search(String fname,String lname) 
	{
		return this.employeeRestApiClient.search(fname, lname);
	}
}
