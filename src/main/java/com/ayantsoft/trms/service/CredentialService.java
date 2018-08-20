package com.ayantsoft.trms.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.trms.dao.EmployeeDao;
import com.ayantsoft.trms.dao.UserMstDao;
import com.ayantsoft.trms.pojo.Employee;
import com.ayantsoft.trms.pojo.UserMst;

@Service
public class CredentialService implements Serializable {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = 2492855452460471379L;
	
	@Autowired
	private UserMstDao userMstDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	
	public Employee getEmployee(String userName){
		Employee employee = null;
		try{
			UserMst userMst = userMstDao.getUserMstByUsername(userName);
			employee = employeeDao.getEmployeeByUserId(userMst.getUserId());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return employee;
	}

}
