package com.example.employeedemo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImp implements EmployeeService{
	
	private EmployeeDAO employeeDAO;
	
	@Autowired
	public EmployeeServiceImp(@Qualifier("employeeJpaImp") EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}
	
	@Override
	@Transactional
	public List<Employee> listAllEmployees(){
		return employeeDAO.listAllEmployees();
	}
	
	@Override
	@Transactional
	public Employee listEmployeeById(int id) {
		return employeeDAO.listEmployeeById(id);
	}
	
	@Override
	@Transactional
	public void saveEmployee(Employee employee) {
		employeeDAO.saveEmployee(employee);
	}
	
	@Override
	@Transactional
	public void deleteEmployeeById(int id) {
		employeeDAO.deleteEmployeeById(id);
	}
}
