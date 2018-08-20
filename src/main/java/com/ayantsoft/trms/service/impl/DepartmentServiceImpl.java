package com.ayantsoft.trms.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.trms.pojo.Department;
import com.ayantsoft.trms.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements Serializable,DepartmentService {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = 1387593656069030786L;
	
	@Autowired
	private DepartmentService departmentService;

	@Override
	public List<Department> departmentList() {
		return departmentService.departmentList();
	}

}
