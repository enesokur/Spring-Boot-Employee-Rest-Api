package com.example.employeedemo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	EmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController(@Qualifier("employeeServiceImpForSpringDataJpa")EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	@GetMapping("/employees")
	public List<Employee> findAllEmployees(){
		return employeeService.listAllEmployees();
	}
	
	@GetMapping("/employees/{employeeId}")
	public Employee findEmployeeById(@PathVariable int employeeId) {
		return employeeService.listEmployeeById(employeeId);
	}
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee) {
		employee.setId(0);
		employeeService.saveEmployee(employee);
		return employee;
	}
	
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee employee) {
		employeeService.saveEmployee(employee);
		return employee;
	}
	
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		Employee employee = employeeService.listEmployeeById(employeeId);
		if(employee == null) {
			throw new RuntimeException("Employee id " + employeeId + " not found");
		}
		employeeService.deleteEmployeeById(employeeId);
		return "Employee with id " + employeeId + " deleted"; 
	}
	
	
}
