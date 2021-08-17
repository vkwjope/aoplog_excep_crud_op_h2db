package com.example.employeemanagementservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employeemanagementservice.exceptions.EmployeeAlreadyExistsException;
import com.example.employeemanagementservice.exceptions.EmployeeNotFoundException;
import com.example.employeemanagementservice.model.Employee;
import com.example.employeemanagementservice.repo.EmployeeRepo;
import com.example.employeemanagementservice.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

	@Override
	public Employee getEmployee(Long id) {
		Optional<Employee> emp;
		try {
			emp = employeeRepo.findById(id);
			if (emp.isEmpty()) {
				throw new EmployeeNotFoundException("Employee doesn't exist, please provide valid employee ID");
			}
		} catch (EmployeeNotFoundException e) {
			throw new EmployeeNotFoundException("Employee doesn't exist, please provide valid employee ID");
		}
		return emp.get();
	}

	@Override
	public List<Employee> getEmployees() {
		List<Employee> findAll = employeeRepo.findAll();
		return findAll;
	}

	@Override
	public Employee addEmployee(Employee employee) {
		Optional<Employee> empById = employeeRepo.findById(employee.getId());
		if (empById.isEmpty()) {
			Employee emp = employeeRepo.save(employee);
			return emp;
		} else {
			throw new EmployeeAlreadyExistsException("Employee exists with Employee id");
		}
	}

}
