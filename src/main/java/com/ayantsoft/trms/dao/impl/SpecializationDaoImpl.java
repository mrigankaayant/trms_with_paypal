package com.ayantsoft.trms.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.ayantsoft.trms.dao.SpecializationDao;
import com.ayantsoft.trms.pojo.Specialization;

@Repository
public class SpecializationDaoImpl implements Serializable,SpecializationDao {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8555743227568569299L;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Specialization> list() {
		List<Specialization> list = null;
		try{
			list = mongoTemplate.findAll(Specialization.class,"specialization");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;

	}

}
