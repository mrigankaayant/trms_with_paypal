package com.ayantsoft.trms.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.ayantsoft.trms.dao.MaxFollowupDao;
import com.ayantsoft.trms.pojo.MaxFollowup;

@Repository
public class MaxFollowupDaoImpl implements Serializable,MaxFollowupDao{

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = 3215537855570174360L;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public MaxFollowup findMaxFollowup(String candidateId) {
		MaxFollowup maxFollowup = null;
		try{	
			Query query = new Query();
			query.addCriteria(Criteria.where("candidateId").is(candidateId));
			maxFollowup = mongoTemplate.findOne(query,MaxFollowup.class,"max_followup");
		}catch(Exception e){
			e.printStackTrace();
		}
		return maxFollowup;
	}
	
	

	@Override
	public void save(MaxFollowup maxFollowup) {
		try{
			mongoTemplate.save(maxFollowup,"max_followup"); 
		}catch(Exception e){
			e.printStackTrace();
		}
	}



	@Override
	public void update(MaxFollowup maxFollowup) {
		try{
			mongoTemplate.save(maxFollowup,"max_followup");
		}catch(Exception e){
			e.printStackTrace();
		}
	}



	@Override
	public List<MaxFollowup> findFreePoolCandidates(Date dateBefore30Days) {
		List<MaxFollowup> maxFollowupList = null;
		try{
			
			Criteria criteria = new Criteria();	
			
			Criteria criteriaForC1 = new Criteria();
			
			Criteria c1 = criteriaForC1.andOperator(Criteria.where("followupDate").lt(dateBefore30Days),Criteria.where("enrollmentStstus").is("New Entry"));
			Criteria c2 = Criteria.where("enrollmentStstus").is("Not Interested");
			
			Criteria matchModeCriteria = criteria.orOperator(c1,c2);
			
			Query query = new Query();
			query.addCriteria(matchModeCriteria);
			
			maxFollowupList = mongoTemplate.find(query,MaxFollowup.class,"max_followup");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return maxFollowupList;
	}

}
