package com.ayantsoft.trms.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.trms.dao.RecruitmentSourceDao;
import com.ayantsoft.trms.pojo.RecruitmentSource;
import com.ayantsoft.trms.service.RecruitmentSourceService;

@Service
public class RecruitmentSourceServiceImpl implements Serializable,RecruitmentSourceService {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = -5423230504055497578L;
	
	@Autowired
	private RecruitmentSourceDao recruitmentSourceDao;

	@Override
	public List<RecruitmentSource> recruitmentSourcelist() {
		return recruitmentSourceDao.list();
	}

}
