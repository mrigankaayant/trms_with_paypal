package com.ayantsoft.trms.service;
import java.util.List;

import com.ayantsoft.trms.pojo.Employee;

public interface EmployeeService {
	//List<Employee> employeeList();
	public Employee getEmployeeByUserId(String id);
	public List<Employee> findEmployeeByRole(List<String> roles);
}
