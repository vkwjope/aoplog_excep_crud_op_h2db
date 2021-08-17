package com.example.employeemanagementservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.employeemanagementservice.model.Employee;

@Service
public interface EmployeeService {

	public Employee getEmployee(Long id);

	public List<Employee> getEmployees();

	public Employee addEmployee(Employee employee);

}
