package com.ayantsoft.trms.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.trms.dao.DesignationDao;
import com.ayantsoft.trms.pojo.Designation;
import com.ayantsoft.trms.service.DesignationService;

@Service
public class DesignationServiceImpl implements Serializable,DesignationService {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = -1046252075344796789L;
	
	@Autowired
	private DesignationDao designationDao;

	@Override
	public List<Designation> designationList() {
		return designationDao.list();
	}

}
