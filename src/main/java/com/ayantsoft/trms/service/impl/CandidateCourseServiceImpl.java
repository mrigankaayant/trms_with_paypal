package com.ayantsoft.trms.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.trms.dao.CandidateCourseDao;
import com.ayantsoft.trms.pojo.CandidateCourse;
import com.ayantsoft.trms.service.CandidateCourseService;

@Service
public class CandidateCourseServiceImpl implements Serializable,CandidateCourseService {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = 9012770780165220223L;
	
	@Autowired
	private CandidateCourseDao candidateCourseDao;

	@Override
	public List<CandidateCourse> courseList() {
		return candidateCourseDao.list();
	}

}
