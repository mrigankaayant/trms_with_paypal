package com.ayantsoft.trms.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import com.ayantsoft.trms.dao.RecruitmentServiceDao;
import com.ayantsoft.trms.pojo.RecruitmentService;

@Repository
public class RecruitmentServiceDaoImpl implements Serializable,RecruitmentServiceDao {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = 3445998823003400401L;

	@Autowired
	private MongoTemplate mongoTemplate;


	@Override
	public List<RecruitmentService> list() {
		List<RecruitmentService> recruitmentServiceList = null;
		try{
			recruitmentServiceList = mongoTemplate.findAll(RecruitmentService.class,"recruitment_service");
		}catch(Exception e){
			e.printStackTrace();
		}
		return recruitmentServiceList;
	}

}
