package com.ayantsoft.trms.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.ayantsoft.trms.dao.IncentiveStructureDao;
import com.ayantsoft.trms.pojo.IncentiveStructure;

@Repository
public class IncentiveStructureDaoImpl implements Serializable,IncentiveStructureDao {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = -8805460318606056671L;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<IncentiveStructure> findIncentiveList(String typeFor) {
		List<IncentiveStructure> incentiveList = null;
		try{
			Query query = new Query();
			query.addCriteria(Criteria.where("typeFor").is(typeFor));
			incentiveList = mongoTemplate.find(query,IncentiveStructure.class,"incentive_structure");	
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return incentiveList;
	}

	@Override
	public double getIncentive(double amount,String typeFor) {
		double incentive = 0.0;
		try{
			Criteria criteria = new Criteria();
			criteria.andOperator(new Criteria().andOperator(Criteria.where("targetLowerLimit").lte(amount),Criteria.where("targetHigherLimit").gt(amount+1)),Criteria.where("typeFor").is(typeFor));
			
			Query query = new Query(criteria);
			IncentiveStructure incentiveStructure = mongoTemplate.findOne(query,IncentiveStructure.class,"incentive_structure");
			
			if(incentiveStructure != null){
				incentive = incentiveStructure.getIncentiveInInr();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return incentive;
	}

}
