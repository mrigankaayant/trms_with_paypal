package com.ayantsoft.trms.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.trms.dao.CandidateRemarksDao;
import com.ayantsoft.trms.pojo.CandidateRemarks;
import com.ayantsoft.trms.service.CandidateRemarksService;

@Service
public class CandidateRemarksServiceImpl implements Serializable,CandidateRemarksService {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = -8472881866616849892L;
	
	
	@Autowired
	private CandidateRemarksDao candidateRemarksDao;


	@Override
	public List<CandidateRemarks> remarksList() {
		return candidateRemarksDao.list();
	}


	@Override
	public List<CandidateRemarks> findCandidateRemarks(String applicableFor) {
		return candidateRemarksDao.findCandidateRemarks(applicableFor);
	}


}
