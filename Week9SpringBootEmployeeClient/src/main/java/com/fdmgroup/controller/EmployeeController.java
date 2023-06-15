package com.fdmgroup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fdmgroup.Exceptions.EmployeeNotFound;
import com.fdmgroup.model.Employee;
import com.fdmgroup.service.EmployeeService;

import jakarta.validation.Valid;

@Controller
public class EmployeeController {

	private EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	@GetMapping("/")
	public String goToIndex() 
	{

		return "index";
		
	}
	
	
	@GetMapping("/listEmployee")
	public String listEmployees(Model model) 
	{
		List<Employee> employees = this.employeeService.getAll();
		model.addAttribute("employees",employees);
		return "employees-list";
		
	}
	
	@GetMapping(value = "/createEmployee")
	public String goToCreateForm(Employee employee,Model model) 
	{
		model.addAttribute("employee",new Employee());
		return "create-employee";
	}
	
	@PostMapping("/process")
	public String createEmployee(@Valid Employee employee,BindingResult result,Model model) 
	{
		if(result.hasErrors()) 
		{
			return"create-employee";
		}
       Employee savedEmployee = employeeService.create(employee);
       model.addAttribute("newEmployee", savedEmployee);
		return "saved-employee";
	}
	
	@GetMapping("/update/{id}")
	public String goToUpdateForm(Employee employee,Model model,@PathVariable("id") Long id) 
	{
		Employee updateEmployee=this.employeeService.getEmployee(id);
		model.addAttribute("update",updateEmployee);
		return "update-employee";
		
	}
	
	
	@PostMapping("/processUpdate")
	public String updateEmployee(Employee employee,Model model) 
	{
		Employee Updateemployee = this.employeeService.updateEmployee(employee.getId(),employee);
		return "redirect:/listEmployee";
	}
	
	
	//@RequestMapping(value = "/delete/{id}",method= {RequestMethod.DELETE,RequestMethod.GET})
	@GetMapping("/delete/{id}")
	public String goToDelete(@PathVariable("id") Long id) 
	{
		
		this.employeeService.delete(id);
		return "redirect:/listEmployee";
	}
	
	
	@GetMapping("/searchEmployee")
	public String goToSearchForm(Employee emp,Model model) 
	{
		model.addAttribute("emp",new Employee());
		return "search-form";
		
	}
	
	@PostMapping("/processSearch")
	public String searchEmployee(Employee emp,Model model) 
	{
		Employee searchemp = this.employeeService.search(emp.getFirstname(),emp.getLastname());
		model.addAttribute("exist",searchemp);
		return "employee-exist";
	}
	
	@ExceptionHandler(EmployeeNotFound.class)
	public String handleException(EmployeeNotFound emf,RedirectAttributes re) 
	{
		re.addFlashAttribute("errormessage",emf.getMessage());
		return "redirect:/";
	}
	
}
