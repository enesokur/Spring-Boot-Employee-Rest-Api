package com.example.employeedemo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeJpaImp implements EmployeeDAO {
	
	private EntityManager entityManager;
	
	@Autowired
	public EmployeeJpaImp(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Employee> listAllEmployees() {
		List<Employee> employees = entityManager.createQuery("FROM Employee",Employee.class).getResultList();
		return employees;
	}

	@Override
	public Employee listEmployeeById(int id) {
		Employee employee = entityManager.find(Employee.class, id);
		return employee;
	}

	@Override
	public void saveEmployee(Employee employee) {
		Employee employeeFromDb = entityManager.merge(employee);
		employee.setId(employeeFromDb.getId());
	}

	@Override
	public void deleteEmployeeById(int id) {
		Query query = entityManager.createQuery("DELETE FROM Employee e where e.id=:employeeId");
		query.setParameter("employeeId", id);
		query.executeUpdate();
	}

}
