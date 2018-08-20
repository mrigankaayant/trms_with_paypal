package com.ayantsoft.trms.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.ayantsoft.trms.dao.EnrollmentFormNoDao;
import com.ayantsoft.trms.pojo.EnrollmentFormNo;
import com.ayantsoft.trms.pojo.Folderpath;

@Repository
public class EnrollmentFormDaoImpl implements Serializable,EnrollmentFormNoDao {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = -7340057046612524966L;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public EnrollmentFormNo getMaxFormNumber(String docName) {
		EnrollmentFormNo enrollmentFormNo = null;
		try{
			Query query = new Query();
			query.addCriteria(Criteria.where("docName").is(docName));
			enrollmentFormNo = mongoTemplate.findOne(query,EnrollmentFormNo.class,"enrollment_form_no");
		}catch(Exception e){
			e.printStackTrace();
		}
		return enrollmentFormNo;
	}

	@Override
	public void update(EnrollmentFormNo enrollmentFormNo) {
		try{
			mongoTemplate.save(enrollmentFormNo);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
