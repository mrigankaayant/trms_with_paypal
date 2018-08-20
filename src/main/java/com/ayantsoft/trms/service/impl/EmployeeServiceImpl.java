package com.ayantsoft.trms.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.trms.dao.EmployeeDao;
import com.ayantsoft.trms.pojo.Employee;
import com.ayantsoft.trms.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService,Serializable {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = -4201462988524374336L;
	
	@Autowired
	private EmployeeDao employeeDao;

	/*@Override
	public List<Employee> employeeList() {
		return employeeDao.list();
	}*/

	@Override
	public Employee getEmployeeByUserId(String id) {
		return employeeDao.getEmployeeByUserId(id);
	}

	@Override
	public List<Employee> findEmployeeByRole(List<String> roles) {
		return employeeDao.findEmployeeByRole(roles);
	}

}
