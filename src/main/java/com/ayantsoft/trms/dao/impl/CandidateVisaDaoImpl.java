package com.ayantsoft.trms.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.ayantsoft.trms.dao.CandidateVisaDao;
import com.ayantsoft.trms.pojo.CandidateVisa;

@Repository
public class CandidateVisaDaoImpl implements Serializable,CandidateVisaDao {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = 5809692101925436448L;
	
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	
	@Override
	public List<CandidateVisa> list() {
		List<CandidateVisa> visaList = null;
		try{
			visaList = mongoTemplate.findAll(CandidateVisa.class,"candidate_visa");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return visaList;
	}

}
