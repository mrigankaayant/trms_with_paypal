package com.ayantsoft.trms.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.trms.dao.SpecializationDao;
import com.ayantsoft.trms.pojo.Specialization;
import com.ayantsoft.trms.service.SpecializationService;

@Service
public class SpecializationServiceImpl implements Serializable,SpecializationService {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = -6711492636414621569L;
	
	@Autowired
	private SpecializationDao specializationDao;

	@Override
	public List<Specialization> list() {
		return specializationDao.list();
	}

}
