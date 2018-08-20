package com.ayantsoft.trms.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.ayantsoft.trms.dao.RecruitmentSourceDao;
import com.ayantsoft.trms.pojo.RecruitmentSource;

@Repository
public class RecruitmentSourceDaoImpl implements Serializable,RecruitmentSourceDao {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = 8871751966775369630L;

	@Autowired
	private MongoTemplate mongoTemplate; 

	@Override
	public List<RecruitmentSource> list() {
		List<RecruitmentSource> sourceList = null;
		try{
			sourceList = mongoTemplate.findAll(RecruitmentSource.class,"recruitment_source");	
		}catch(Exception e){
			e.printStackTrace();
		}

		return sourceList;
	}
}
