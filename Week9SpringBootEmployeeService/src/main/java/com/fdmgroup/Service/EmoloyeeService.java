package com.fdmgroup.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.Exceptions.EmployeeNotFoundException;
import com.fdmgroup.Model.EmployeeClass;
import com.fdmgroup.Repo.EmployeeRepo;

@Service
public class EmoloyeeService {

	
	private EmployeeRepo employeeRepo;
	
    @Autowired
	public EmoloyeeService(EmployeeRepo employeeRepo) {
		super();
		this.employeeRepo = employeeRepo;
	}
    
    
    //methods
    
     //create an employee
    public EmployeeClass createEmployee(EmployeeClass employee) 
    {

    		this.employeeRepo.save(employee);
    		return employee;
    		
    }
    
    
     //get all employees
    public List<EmployeeClass> getAllEmployees()
    {
    	return this.employeeRepo.findAll();
    }
    
    //find employee by id 
    public EmployeeClass findEmployeeById(Long id) 
    {
    	Optional<EmployeeClass> employeeOpt = this.employeeRepo.findById(id);
    	if(!employeeOpt.isPresent()) 
    	{
    		throw new EmployeeNotFoundException("Employee with "+id+" is not found");
    	}
    	
    	return employeeOpt.get();
    }
	
	
	//update employee
    public EmployeeClass updateEmployee(Long id,EmployeeClass employee) 
    {
    	//update entire employee
    	//return this.employeeRepo.save(employee);
    	Optional<EmployeeClass> empUpdated =this.employeeRepo.findById(id);
    	if(!empUpdated.isPresent()) 
    	{
    		throw new EmployeeNotFoundException("Employee with "+id+" is not found");	
    	}
    	
    	return this.employeeRepo.save(employee);
    			
    			
    	
    }
    
    //delete
    public void deleteEmployee(Long id ) 
    {
    	EmployeeClass deleteEmp=findEmployeeById(id);
    	//check if the employee is exist
    	if(deleteEmp!=null) 
    	{
    	// if exist then delete
    	this.employeeRepo.deleteById(id);
    	}
    	else 
    	{
    	throw new EmployeeNotFoundException("Employee with "+id+" is not found");
    	}
    }
	
   
    //Find by fname and lname 
    public EmployeeClass findByfFirstnameAndLastname(String firstname,String lastname) 
    {
    	EmployeeClass emp = (EmployeeClass) this.employeeRepo.findByFirstnameAndLastname(firstname, lastname);
    	if(emp ==null) 
    	{
    		throw new EmployeeNotFoundException("Employee with "+firstname+" and "+lastname+" is not found");
    	}
    	return emp;
    }
	
}
