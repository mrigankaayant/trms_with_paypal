package com.ayantsoft.trms.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ayantsoft.trms.dao.FollowupDao;
import com.ayantsoft.trms.dao.commonDao;
import com.ayantsoft.trms.pojo.FollowUp;

@Repository
public class FollowupDaoImpl implements Serializable,FollowupDao {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4247112042321205383L;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private commonDao commonDao;
	
	private static final String COLLECTION_NAME="follow_up";

	@Override
	public void addFollowUp(FollowUp followUp) {
		followUp.setFollowupId(commonDao.getNextSequenceId("follow_up"));
		if(!mongoTemplate.collectionExists(FollowUp.class)){
			mongoTemplate.createCollection(FollowUp.class);
		}
		mongoTemplate.save(followUp,COLLECTION_NAME); 
	}

	@Override
	public List<FollowUp> findFollowUpByCandidateId(String id) {
		List<FollowUp> followupList = null;
		try{
			Query query = new Query();
			query.addCriteria(Criteria.where("candidateInfo.candidateId").is(id));
			followupList = mongoTemplate.find(query,FollowUp.class,"follow_up");
		}catch(Exception e){
			e.printStackTrace();
		}
		return followupList;
	}

}
