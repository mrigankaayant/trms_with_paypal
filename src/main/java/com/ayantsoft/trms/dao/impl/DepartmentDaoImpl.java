package com.ayantsoft.trms.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.ayantsoft.trms.dao.DepartmentDao;
import com.ayantsoft.trms.pojo.Department;

@Repository
public class DepartmentDaoImpl implements Serializable,DepartmentDao {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = 688044485062853787L;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Department> list() {
		List<Department> departmentList = null;
		try{
			departmentList = mongoTemplate.findAll(Department.class,"department");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return departmentList; 
	}

}
