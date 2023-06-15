package com.fdmgroup.service;

import java.util.List;

import com.fdmgroup.model.Employee;

public interface EmployeeRESTAPIClient {
	
	//methods
	List<Employee> getAllEmployees();
    Employee create(Employee employee);
    Employee findById(Long id);
    Employee update(Long id,Employee employee);
    void deleteEmployee(Long id);
    Employee search(String fname,String lname);

}
