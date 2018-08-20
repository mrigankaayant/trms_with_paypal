package com.ayantsoft.trms.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ayantsoft.trms.dao.RecruitmentServiceDao;
import com.ayantsoft.trms.pojo.RecruitmentService;
import com.ayantsoft.trms.service.RecruitmentServiceService;

@Service
public class RecruitmentServiceServiceImpl implements Serializable,RecruitmentServiceService {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = 8918028367261158975L;
	
	@Autowired
	private RecruitmentServiceDao recruitmentServiceDao;
	

	@Override
	public List<RecruitmentService> list() {
		return recruitmentServiceDao.list();
	}

}
