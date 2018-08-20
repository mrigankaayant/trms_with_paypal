package com.ayantsoft.trms.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.ayantsoft.trms.dao.DesignationDao;
import com.ayantsoft.trms.pojo.Designation;

@Repository
public class DesignationDaoImpl implements Serializable,DesignationDao {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3272474660288314502L;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Designation> list() {
		List<Designation> designationList = null;
		try{
			designationList = mongoTemplate.findAll(Designation.class,"designation");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return designationList;
	}

}
