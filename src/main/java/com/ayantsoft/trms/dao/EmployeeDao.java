package com.ayantsoft.trms.dao;
import java.util.List;

import com.ayantsoft.trms.pojo.Employee;

public interface EmployeeDao {
	
	//List<Employee> list();
	public Employee getEmployeeByUserId(String id);
	public List<Employee> findEmployeeByRole(List<String> roles);

}
