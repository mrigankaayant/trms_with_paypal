package com.ayantsoft.trms.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.ayantsoft.trms.dao.PayTypeDao;
import com.ayantsoft.trms.pojo.PayType;

@Repository
public class PayTypeDaoImpl implements Serializable,PayTypeDao {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = 7739845523850829210L;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<PayType> list() {
		List<PayType> payTypeList = null;
		try{
			
			payTypeList = mongoTemplate.findAll(PayType.class,"pay_type");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return payTypeList;
	}

}
