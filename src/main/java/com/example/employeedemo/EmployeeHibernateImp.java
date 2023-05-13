package com.example.employeedemo;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeHibernateImp implements EmployeeDAO {
	
	private EntityManager entityManager;
	
	@Autowired
	public EmployeeHibernateImp(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Employee> listAllEmployees(){
		Session session = entityManager.unwrap(Session.class);
		List<Employee> employees = session.createQuery("FROM Employee",Employee.class).getResultList();
		return employees;
	}
	
	@Override
	public Employee listEmployeeById(int id) {
		Session session = entityManager.unwrap(Session.class);
		Employee employee = session.get(Employee.class, id);
		return employee;
	}
	
	@Override
	public void saveEmployee(Employee employee) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(employee);
	}
	
	@Override
	public void deleteEmployeeById(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query<Employee> query = session.createQuery("DELETE FROM Employee e WHERE e.id=:employeeId");
		query.setParameter("employeeId",id);
		query.executeUpdate();
	}
	
	
}
