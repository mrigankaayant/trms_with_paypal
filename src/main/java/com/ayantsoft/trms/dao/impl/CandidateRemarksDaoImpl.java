package com.ayantsoft.trms.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ayantsoft.trms.dao.CandidateRemarksDao;
import com.ayantsoft.trms.pojo.Candidate;
import com.ayantsoft.trms.pojo.CandidateRemarks;

@Repository
public class CandidateRemarksDaoImpl implements Serializable,CandidateRemarksDao {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3916993566537676855L;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<CandidateRemarks> list() {
		List<CandidateRemarks> candidateRemarks = null;
	     try{
	    	 
	    	 candidateRemarks = mongoTemplate.findAll(CandidateRemarks.class,"candidate_remarks");
	    	 
	     }catch(Exception e){
	    	 e.printStackTrace();
	     }
	     
	     return candidateRemarks;
	}

	@Override
	public List<CandidateRemarks> findCandidateRemarks(String applicableFor) {
		List<CandidateRemarks> candidateRemarks = null;
	     try{
	    	 Query query = new Query();
			 query.addCriteria(Criteria.where("applicableFor").is(applicableFor));
			 candidateRemarks = mongoTemplate.find(query,CandidateRemarks.class);
	     }catch(Exception e){
	    	 e.printStackTrace();
	     }
	     return candidateRemarks;
	}

}
