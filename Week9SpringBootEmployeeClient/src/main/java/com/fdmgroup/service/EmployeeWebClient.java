package com.fdmgroup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.fdmgroup.Exceptions.EmployeeNotFound;
import com.fdmgroup.model.Employee;

import reactor.core.publisher.Mono;

@Component
public class EmployeeWebClient implements EmployeeRESTAPIClient {

	//inject webclient
	private WebClient  employeeRestWebClient;
	
	
	@Autowired
	public EmployeeWebClient(WebClient createRestWebClient) {
		super();
		this.employeeRestWebClient = createRestWebClient;
	}



	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
      return this.employeeRestWebClient.get()
    		  .retrieve()
    		  .bodyToFlux(Employee.class)
    		  .collectList()
    		  .block();
	}


	
		
	public Employee create(Employee employee) 
	{
		return this.employeeRestWebClient.post()
				.bodyValue(employee)
				.retrieve()
				.bodyToMono(Employee.class)
				.block();
	}

	public Employee findById(Long id) 
	{
		return this.employeeRestWebClient.get()
				.uri("/"+id)
				.retrieve()
				.onStatus(status->status.value()==HttpStatus.NOT_FOUND.value(),
						  responose->Mono.error(new EmployeeNotFound("Employee with "+id+"Not Found")))
				.bodyToMono(Employee.class)
				.block();
	}
	
	public Employee update(Long id,Employee employee) 
	{
		return this.employeeRestWebClient.put()
				.uri("/"+id).
				 bodyValue(employee)
				.retrieve()
				.bodyToMono(Employee.class)
				.block();
	}
	
	public void deleteEmployee(Long id) 
	{
		        this.employeeRestWebClient.delete()
				   .uri("/"+id)
				   .retrieve()
				   .onStatus(status->status.value()==HttpStatus.NOT_FOUND.value(), 
						     response->Mono.error(new EmployeeNotFound("Employee with "+id+" Not Found to delete")))
				   .toBodilessEntity()
				   .block();
	}

	public Employee search(String fname,String lname) 
	{
		return this.employeeRestWebClient.get()
				   .uri("/"+fname+"/"+lname)
				   .retrieve()
				   .onStatus(status->status.value()==HttpStatus.NOT_FOUND.value(), 
						     response->Mono.error(new EmployeeNotFound("Employee with "+fname+" and "+lname+" not Found")))
				   .bodyToMono(Employee.class)
				   .block();
	}



}
