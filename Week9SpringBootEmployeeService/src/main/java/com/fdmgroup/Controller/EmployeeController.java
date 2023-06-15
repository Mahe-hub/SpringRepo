package com.fdmgroup.Controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.fdmgroup.Model.EmployeeClass;
import com.fdmgroup.Service.EmoloyeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

	private EmoloyeeService employeeService;

	@Autowired
	public EmployeeController(EmoloyeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	
	//get all the employees
	@GetMapping
	@Operation(summary = "Get all employees")
	public ResponseEntity<List<EmployeeClass>> getAllEmployees()
	{
		//get the employees
		List<EmployeeClass>employees=this.employeeService.getAllEmployees();
		return ResponseEntity.ok(employees);
	}
	
	
	//get the employee by id
	@GetMapping("/{id}")
	@Operation(summary = "Get the employee by id")
	@ApiResponses(value = 
                         {
                            @ApiResponse(
                            		       responseCode = "200",
                            		       content = {
		                            		    		   @Content(mediaType=MediaType.APPLICATION_JSON_VALUE),
				    		    		    		       @Content(mediaType = MediaType.APPLICATION_XML_VALUE)
                            		                 },
                            		       description = "the employee with specfic id is exist"
                            		    ),
                            @ApiResponse(
                            		       responseCode = "404",
                            		       description = "the employee with specfic id is not exist"
                            		    )
                         }
	             )
	
	public ResponseEntity<EmployeeClass> getEmployeeById(@PathVariable("id") Long employeeId)
	{
		EmployeeClass employee = this.employeeService.findEmployeeById(employeeId);
		return ResponseEntity.ok().body(employee);
	}
	
	//create employee
	@PostMapping
	@Operation(summary = "Create employee")
	@ApiResponses(
			       value = {
			    		     @ApiResponse(
			    		    		       responseCode = "200",
			    		    		       content = {
			    		    		    		       @Content(mediaType=MediaType.APPLICATION_JSON_VALUE),
			    		    		    		       @Content(mediaType = MediaType.APPLICATION_XML_VALUE)
			    		    		    		     },
			    		    		       headers = {
			    		    		    		        @Header(
			    		    		    		        		 name = "employeeLocation",
			    		    		    		        		 description = "url to new employee"
			    		    		    		        		)
			    		    		                 },
			    		    		       description = "Create new employee"
			    		    		      )
			               }
			      )
	public ResponseEntity<EmployeeClass> createEmployee(@Valid @RequestBody EmployeeClass newEmployee)
	{
		EmployeeClass emp = this.employeeService.createEmployee(newEmployee);
		
		//create uri for new employee
		URI employeeLocation =ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				              .buildAndExpand(emp.getId()).toUri();
		
		//return the response 
		return ResponseEntity.created(employeeLocation).body(emp);
	}
	
	//update
	@PutMapping("/{id}")
	@Operation(summary = "Update employee information")
	@ApiResponses(value = {
			                 @ApiResponse(
			                		         responseCode = "200",
			                		         content= {
			                		        		   @Content(mediaType=MediaType.APPLICATION_JSON_VALUE),
			    		    		    		       @Content(mediaType = MediaType.APPLICATION_XML_VALUE)
			                		                  },
			                		         description = "Update employee information"
			                		     ),
			                 @ApiResponse(
			                		       responseCode = "404",
			                		       description = "update failed"
			                		     )
	                      }
	               )
	public  ResponseEntity<EmployeeClass> updateEmployee(@PathVariable("id") Long updateId,@RequestBody EmployeeClass updateEmployee )
	{
		EmployeeClass newEmp = this.employeeService.updateEmployee(updateId,updateEmployee);
		
		return ResponseEntity.ok().body(newEmp);
	}
	
	//delete 
	@DeleteMapping("/{id}")
	@Operation(summary = "Delete employee by id ")
	@ApiResponses(
			       value = {   
			    		    @ApiResponse(
			    		    		         responseCode = "200",
			    		    		         content = {
			    		    		        		      @Content(mediaType =MediaType.APPLICATION_JSON_VALUE ),
			    		    		        		      @Content(mediaType = MediaType.APPLICATION_XML_VALUE)
			    		    		                   },
			    		    		         description = "Delete the employee with specfic id"
			    		    		    ),
			    		    @ApiResponse(
			    		    		      responseCode = "404",
			    		    		      description = "the employee with specfic id is not found"
			    		    		    )
			               }
			      )
	public void deleteEmployee(@PathVariable("id") Long deleteId) 
	{
		this.employeeService.deleteEmployee(deleteId);
	}
	
	
	@GetMapping("/{firstname}/{lastname}")
	@Operation(summary = "search by firstName and LastName")
	@ApiResponses(
			       value= {
			    		     @ApiResponse(
			    		    		        responseCode = "200",
			    		    		        content = {
				    		    		        		      @Content(mediaType =MediaType.APPLICATION_JSON_VALUE ),
				    		    		        		      @Content(mediaType = MediaType.APPLICATION_XML_VALUE)
				    		    		              },
			    		    		        description="Employee exist"
			    		    		     ) ,
			    		     @ApiResponse(
			    		    		        responseCode = "404",
			    		    		        description = "employee not exist"
			    		    		      )
			              }
			     )
	public  ResponseEntity<EmployeeClass> search(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname)
	{
		EmployeeClass searchEmployee = this.employeeService.findByfFirstnameAndLastname(firstname, lastname);
		return ResponseEntity.ok().body(searchEmployee);
	}
	
}
