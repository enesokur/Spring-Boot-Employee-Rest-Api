package com.example.employeedemo;

import java.util.List;

public interface EmployeeService {
	public List<Employee> listAllEmployees();
	public Employee listEmployeeById(int id);
	public void saveEmployee(Employee employee);
	public void deleteEmployeeById(int id);
}
