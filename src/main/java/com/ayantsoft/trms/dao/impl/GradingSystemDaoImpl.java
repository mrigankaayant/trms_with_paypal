package com.ayantsoft.trms.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.ayantsoft.trms.dao.GradingSystemDao;
import com.ayantsoft.trms.pojo.GradingSystem;

@Repository
public class GradingSystemDaoImpl implements Serializable,GradingSystemDao {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = 250916398229551000L;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<GradingSystem> list() {
		List<GradingSystem> list = null;
		try{
			list = mongoTemplate.findAll(GradingSystem.class,"grading_system");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

}
