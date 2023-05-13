package com.example.employeedemo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpForSpringDataJpa implements EmployeeService {
	
	EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeServiceImpForSpringDataJpa(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	@Override
	public List<Employee> listAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee listEmployeeById(int id) {
		Optional<Employee> op = employeeRepository.findById(id);
		Employee employee = null;
		if(op.isPresent()) {
			employee = op.get();
		}
		else {
			throw new RuntimeException("Employee not found with id " + id);
		}
		return employee;
	}

	@Override
	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);
		
	}

	@Override
	public void deleteEmployeeById(int id) {
		employeeRepository.deleteById(id);
		
	}

}
