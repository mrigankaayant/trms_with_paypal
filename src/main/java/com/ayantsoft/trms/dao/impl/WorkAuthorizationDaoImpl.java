package com.ayantsoft.trms.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import com.ayantsoft.trms.dao.WorkAuthorizationDao;
import com.ayantsoft.trms.pojo.WorkAuthorization;

@Repository
public class WorkAuthorizationDaoImpl implements Serializable,WorkAuthorizationDao {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3832006951238207950L;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	

	@Override
	public List<WorkAuthorization> list() {
		List<WorkAuthorization> workAuthorizations = null;
		try{
			workAuthorizations = mongoTemplate.findAll(WorkAuthorization.class,"work_authorization");
		}catch(Exception e){
			e.printStackTrace();
		}
		return workAuthorizations; 
	}

}
