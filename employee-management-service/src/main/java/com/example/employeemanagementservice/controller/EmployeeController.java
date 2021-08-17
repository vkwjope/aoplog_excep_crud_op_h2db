package com.example.employeemanagementservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeemanagementservice.model.Employee;
import com.example.employeemanagementservice.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping
	public List<Employee> getEmployees() {
		List<Employee> employees = employeeService.getEmployees();
		return employees;
	}

	@PostMapping
	public Employee addEmployee(@RequestBody Employee employee) {
		Employee emp = employeeService.addEmployee(employee);
		return emp;
	}

	@GetMapping("/employee/{emp_id}")
	public Employee getEmployee(@PathVariable("emp_id") Long empId) {
		Employee employee = employeeService.getEmployee(empId);
		return employee;
	}

}
