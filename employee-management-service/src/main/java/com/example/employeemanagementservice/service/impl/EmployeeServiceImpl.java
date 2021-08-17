package com.example.employeemanagementservice.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

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
		if (employee.getId() == null) {
			employee.setId(0l);
		}
		Employee emp = employeeRepo.save(employee);
		return emp;
	}

	@Override
	public String deleteEmployee(Long id) {
		Optional<Employee> emp = employeeRepo.findById(id);
		if (emp.isEmpty()) {
			throw new EmployeeNotFoundException("Delete Employee failed , employee is not present");
		} else {
			employeeRepo.deleteById(id);
		}
		return "Deleted employee with id:" + id;
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		Optional<Employee> emp = employeeRepo.findById(employee.getId());
		if (emp.isEmpty()) {
			throw new EmployeeNotFoundException("Update Employee failed , employee is not present");
		} else {
			Employee empl = employeeRepo.save(employee);
			return empl;
		}
	}

	@Override
	public Employee patchEmployee(Long empId, Map<Object, Object> updatedValues) {
		Optional<Employee> emp = employeeRepo.findById(empId);
		if (emp.isPresent()) {
			updatedValues.forEach((key, value) -> {
				Field field = ReflectionUtils.findField(Employee.class, (String) key);
				field.setAccessible(true);
				ReflectionUtils.setField(field, emp.get(), value);
			});
			Employee empl = employeeRepo.save(emp.get());
			return empl;
		} else {
			throw new EmployeeNotFoundException("Update failed, Employee not found");
		}
	}

}
