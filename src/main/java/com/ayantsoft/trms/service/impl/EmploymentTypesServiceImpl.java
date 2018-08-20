package com.ayantsoft.trms.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.trms.dao.EmploymentTypesDao;
import com.ayantsoft.trms.pojo.EmploymentTypes;
import com.ayantsoft.trms.service.EmploymentTypesService;

@Service
public class EmploymentTypesServiceImpl implements Serializable,EmploymentTypesService {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = 3763903906089909232L;
	
	@Autowired
	private EmploymentTypesDao employmentTypesDao;

	@Override
	public List<EmploymentTypes> list() {
		return employmentTypesDao.list();
	}

}
