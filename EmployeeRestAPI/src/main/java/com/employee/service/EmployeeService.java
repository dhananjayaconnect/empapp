package com.employee.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;
import com.employee.util.CommonUtil;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployees() {
		List<Employee> employees =(List<Employee>)CommonUtil.makeCollection(employeeRepository.findAll());
		employeeRepository.findAll();
		return employees;
	}

	public Employee getEmployee(String employeeId) {
		return employeeRepository.findOne(employeeId);
	}

	public void addEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	public void updateEmployee(String employeeId, Employee employee) {
		employeeRepository.save(employee);
	}

	public void deleteEmployee(String employeeId) {
		employeeRepository.delete(employeeId);
	}

}
