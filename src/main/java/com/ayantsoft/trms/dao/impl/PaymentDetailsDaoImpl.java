package com.ayantsoft.trms.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ayantsoft.trms.dao.PaymentDetailsDao;
import com.ayantsoft.trms.dao.commonDao;
import com.ayantsoft.trms.pojo.PaymentDetails;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

@Repository
public class PaymentDetailsDaoImpl implements Serializable,PaymentDetailsDao {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = 2784797115180637743L;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private commonDao commonDao;



	@Override
	public void save(PaymentDetails paymentDetails) {
		try{
			paymentDetails.setPaymentDetailsId(commonDao.getNextSequenceId("payment_details"));
			if(!mongoTemplate.collectionExists(PaymentDetails.class)){
				mongoTemplate.createCollection(PaymentDetails.class);
			}
			mongoTemplate.save(paymentDetails,"payment_details"); 
		}catch(Exception e){
			e.printStackTrace();
		}
	}



	@Override
	public List<PaymentDetails> findPaymentDetailsByUsername(String userName) {
		List<PaymentDetails> paymentDetailsList = null;
		try{
			Query query = new Query();
			query.addCriteria(Criteria.where("username").is(userName));
			query.with(new Sort(new Order(Direction.DESC,"paymentDetailsId")));
			paymentDetailsList = mongoTemplate.find(query,PaymentDetails.class,"payment_details");
		}catch(Exception e){
			e.printStackTrace();
		}
		return paymentDetailsList;
	}


	@Override
	public void updatePayment(PaymentDetails paymentDetails) {
		try{
			mongoTemplate.save(paymentDetails,"payment_details");
		}catch(Exception e){
			e.printStackTrace();
		}
	}



	@Override
	public List<PaymentDetails> completedPaymentList(String userName) {
		List<PaymentDetails> paymentDetailsList = null;
		try{
			Criteria criteria = new Criteria();
			criteria.andOperator(Criteria.where("username").is(userName),Criteria.where("status").is("COMPLETED"));
			Query query = new Query(criteria);
			query.with(new Sort(new Order(Direction.ASC,"paymentDetailsId")));
			paymentDetailsList = mongoTemplate.find(query,PaymentDetails.class,"payment_details");
		}catch(Exception e){
			e.printStackTrace();
		}
		return paymentDetailsList;
	}



	@Override
	public long numberOfPayment(String userName) {
		long count = 0;
		try{
			Criteria criteria = new Criteria();
			criteria.andOperator(Criteria.where("username").is(userName),Criteria.where("status").is("COMPLETED"));
			Query query = new Query(criteria);
			count = mongoTemplate.count(query,"payment_details");
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}



	@Override
	public Integer findTotalAmount(String userName,String courseName) {
		int sum = 0;
		try{
			Criteria criteria = new Criteria();
			criteria.andOperator(Criteria.where("username").is(userName),Criteria.where("coureseName").is(courseName));

			Criteria crite = new Criteria();
			crite.andOperator(criteria,Criteria.where("status").is("COMPLETED"));

			Query query = new Query(crite);
			List<PaymentDetails> list = mongoTemplate.find(query,PaymentDetails.class,"payment_details");
			if(list != null){
				for(PaymentDetails p:list){
					sum = sum + p.getAmount();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return sum;
	}




	@Override
	public List<PaymentDetails> findPaymentDetailsByCandidateId(String candidateId) {
		List<PaymentDetails> paymentDetailsList = null;
		try{
			Query query = new Query();
			query.addCriteria(new Criteria().andOperator(Criteria.where("candidateId").is(candidateId),Criteria.where("status").is("COMPLETED")));
			query.with(new Sort(new Order(Direction.DESC,"paymentDetailsId")));
			paymentDetailsList = mongoTemplate.find(query,PaymentDetails.class,"payment_details");
		}catch(Exception e){
			e.printStackTrace();
		}
		return paymentDetailsList;
	}



	@Override
	public List<PaymentDetails> findOwnCandidatePayment(String employeeId,String month,String year) {
		List<PaymentDetails> paymentDetailsList = null;
		try{
			
			Criteria criteria = new Criteria();
			
			criteria.andOperator(new Criteria().andOperator(new Criteria().andOperator(Criteria.where("createdBy.employeeId").is(employeeId),Criteria.where("createdMonth").is(month)),Criteria.where("createdYear").is(year)),Criteria.where("status").is("COMPLETED"));

			Query query = new Query(criteria);
			paymentDetailsList = mongoTemplate.find(query,PaymentDetails.class,"payment_details");

		}catch(Exception e){
			e.printStackTrace();
		}
		return paymentDetailsList;
	}



	@Override
	public List<PaymentDetails> findTeamCandidatePayment(String employeeId, String month, String year) {
		List<PaymentDetails> paymentDetailsList = null;
		try{
			Criteria criteria = new Criteria();
			criteria.andOperator(new Criteria().andOperator(new Criteria().andOperator(Criteria.where("createdBy.supervisorId").is(employeeId),Criteria.where("createdMonth").is(month)),Criteria.where("createdYear").is(year)),Criteria.where("status").is("COMPLETED"));

			Query query = new Query(criteria);
			paymentDetailsList = mongoTemplate.find(query,PaymentDetails.class,"payment_details");
		}catch(Exception e){
			e.printStackTrace();
		}
		return paymentDetailsList;
	}


	@Override
	public List<PaymentDetails> findAllPaidCandidates() {
		List<PaymentDetails> paymentDetailsList = null;
		try{
			DBCollection colllection = mongoTemplate.getCollection("payment_details");
		    List<DBObject> pipeline = Arrays.<DBObject>asList(
		        new BasicDBObject("$match",new BasicDBObject("status","COMPLETED")),
		        new BasicDBObject("$group",
		            new BasicDBObject("_id",
		                new BasicDBObject("candidateId","$candidateId")
		                    .append("candidateName","$candidateName")
		                    .append("email","$email")
		                    .append("workMobile","$workMobile")
		            )
		        )
		    );

		    AggregationOutput output = colllection.aggregate(pipeline);
		    Iterable<DBObject> iterable =  output.results();
		    Iterator<DBObject> itr = iterable.iterator();
		    paymentDetailsList = new ArrayList<PaymentDetails>();
		    while(itr.hasNext()){
		    	PaymentDetails pd = new PaymentDetails();
		    	DBObject object= itr.next();
		    	BasicDBObject basicDBObject = (BasicDBObject) object.get("_id");
		    	
		    	pd.setCandidateId(basicDBObject.getString("candidateId"));
		    	pd.setCandidateName(basicDBObject.getString("candidateName"));
		    	pd.setEmail(basicDBObject.getString("email"));
		    	pd.setWorkMobile(basicDBObject.getString("workMobile"));
		    	
		    	paymentDetailsList.add(pd);
		    }
		    
		    if(paymentDetailsList.size() == 0){
		    	paymentDetailsList = null;
		    }
	
		}catch(Exception e){
			e.printStackTrace();
		}
		return paymentDetailsList;
	}

}
