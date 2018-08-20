package com.ayantsoft.trms.service.impl;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ayantsoft.trms.dao.WorkAuthorizationDao;
import com.ayantsoft.trms.pojo.WorkAuthorization;
import com.ayantsoft.trms.service.WorkAuthorizationService;

@Service
public class WorkAuthorizationServiceImpl implements Serializable,WorkAuthorizationService {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = -7830118945634465090L;
	
	@Autowired
	private WorkAuthorizationDao workAuthorizationDao;
	

	@Override
	public List<WorkAuthorization> list() {
		return workAuthorizationDao.list();
	}

}
