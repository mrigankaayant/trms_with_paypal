package com.ayantsoft.trms.dao.impl;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.ayantsoft.trms.dao.EmploymentTypesDao;
import com.ayantsoft.trms.pojo.EmploymentTypes;

@Repository
public class EmploymentTypesDaoImpl implements Serializable,EmploymentTypesDao {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = 1388937568121913160L;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<EmploymentTypes> list() {
		List<EmploymentTypes> employmentTypes = null;
		try{
			employmentTypes = mongoTemplate.findAll(EmploymentTypes.class,"employment_types");
		}catch(Exception e){
			e.printStackTrace();
		}
		return employmentTypes; 
	}

}
