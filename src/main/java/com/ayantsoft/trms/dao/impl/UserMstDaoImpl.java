package com.ayantsoft.trms.dao.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ayantsoft.trms.dao.UserMstDao;
import com.ayantsoft.trms.dao.commonDao;
import com.ayantsoft.trms.pojo.Candidate;
import com.ayantsoft.trms.pojo.UserMst;

@Repository
public class UserMstDaoImpl implements Serializable,UserMstDao {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = 1453029047153007886L;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private commonDao commonDao;


	@Override
	public UserMst getUserMstByUsername(String userName) {
		UserMst userMst = null;
		try{
			Criteria criteria = new Criteria();
			criteria.andOperator(Criteria.where("username").is(userName),Criteria.where("active").is(true));
			Query query = new Query(criteria);
			userMst = mongoTemplate.findOne(query,UserMst.class,"userMst");
		}catch(Exception e){
			e.printStackTrace();
		}
		return userMst;
	}


	@Override
	public UserMst createUser(UserMst userMst) {
		try{
			userMst.setUserId(commonDao.getNextSequenceId("userMst"));
			if(!mongoTemplate.collectionExists(UserMst.class)){
				mongoTemplate.createCollection(UserMst.class);
			}
			mongoTemplate.save(userMst,"userMst"); 
		}catch(Exception e){
			e.printStackTrace();
		}

		return userMst;
	}


	@Override
	public void updateUserMast(UserMst userMst) {
		try{
			mongoTemplate.save(userMst);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
