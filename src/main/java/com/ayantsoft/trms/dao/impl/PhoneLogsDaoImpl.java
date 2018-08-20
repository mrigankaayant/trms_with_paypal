package com.ayantsoft.trms.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.ayantsoft.trms.dao.PhoneLogsDao;
import com.ayantsoft.trms.pojo.Candidate;
import com.ayantsoft.trms.pojo.PhoneLogs;

@Repository
public class PhoneLogsDaoImpl implements Serializable,PhoneLogsDao{

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = -7523626671180416550L;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<PhoneLogs> getPhoneLogsByCandidateId(String candidateId) {
		List<PhoneLogs> phoneLogs = null;
		try{
			Query query = new Query();
			query.addCriteria(Criteria.where("candidateId").is(candidateId));
			phoneLogs = mongoTemplate.find(query,PhoneLogs.class,"phone_logs");
		}catch(Exception e){
			e.printStackTrace();
		}
		return phoneLogs;
	}
	
	

	@Override
	public List<PhoneLogs> getPhoneLogsByEmployeeId(String employeeId, boolean isAdmin) {
		List<PhoneLogs> phoneLogs = null;
		try{
			if(isAdmin){
				phoneLogs = mongoTemplate.findAll(PhoneLogs.class,"phone_logs");
			}else{
				Criteria criteria = new Criteria();
				criteria.orOperator(Criteria.where("employeeId").is(employeeId),Criteria.where("supervisorId").is(employeeId));
				Query query = new Query(criteria);
				phoneLogs = mongoTemplate.find(query,PhoneLogs.class,"phone_logs");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return phoneLogs;
	}

}
