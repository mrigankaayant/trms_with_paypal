package com.ayantsoft.trms.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.trms.dao.CandidateVisaDao;
import com.ayantsoft.trms.pojo.CandidateVisa;
import com.ayantsoft.trms.service.CandidateVisaService;

@Service
public class CandidateVisaServiceImpl implements Serializable,CandidateVisaService {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = 7020355225985206318L;
	
	
	@Autowired
	private CandidateVisaDao candidateVisaDao;

	@Override
	public List<CandidateVisa> visaList() {
		return candidateVisaDao.list();
	}

}
