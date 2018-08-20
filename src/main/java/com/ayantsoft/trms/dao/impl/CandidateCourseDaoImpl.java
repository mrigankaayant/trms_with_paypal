package com.ayantsoft.trms.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.ayantsoft.trms.dao.CandidateCourseDao;
import com.ayantsoft.trms.pojo.CandidateCourse;

@Repository
public class CandidateCourseDaoImpl implements Serializable,CandidateCourseDao {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = 5041928166565594540L;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<CandidateCourse> list() {
		List<CandidateCourse> candidateCourseList = null;
		try{
			candidateCourseList = mongoTemplate.findAll(CandidateCourse.class,"candidate_course");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return candidateCourseList; 
	}

}
