package com.ayantsoft.trms.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.ayantsoft.trms.dao.CandidateDao;
import com.ayantsoft.trms.dao.commonDao;
import com.ayantsoft.trms.pojo.Candidate;
import com.ayantsoft.trms.pojo.FollowUp;
import com.ayantsoft.trms.pojo.FreePoolCandidate;
import com.ayantsoft.trms.pojo.SearchCandidate;

@Repository
public class CandidateDaoImpl implements CandidateDao,Serializable{

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = -3669624117146012165L;


	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private commonDao commonDao;

	private static final String COLLECTION_NAME="candidate";

	@Override
	public void addCandidate(Candidate candidate) {
		try{
			candidate.setCandidateId(commonDao.getNextSequenceId("candidate"));
			if(!mongoTemplate.collectionExists(Candidate.class)){
				mongoTemplate.createCollection(Candidate.class);
			}
			mongoTemplate.save(candidate,COLLECTION_NAME); 
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public Candidate findCandidateById(String candidateId) {
		Candidate candidate = null;
		try{	
			Query query = new Query();
			query.addCriteria(Criteria.where("candidateId").is(candidateId));
			candidate = mongoTemplate.findOne(query,Candidate.class,"candidate");
		}catch(Exception e){
			e.printStackTrace();
		}
		return candidate;
	}

	@Override
	public void updateCandidate(Candidate candidate) {
		try{
			mongoTemplate.save(candidate,"candidate");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public List<Candidate> findSearchingCandidate(SearchCandidate searchCandidate) {
		List<Candidate> candidates = null;
		try{	
			if(searchCandidate.getSearchValue().length() >0){
				Criteria criteria = new Criteria();
				criteria.orOperator(Criteria.where("candidateName").is(searchCandidate.getSearchValue().trim()),
						Criteria.where("socialMedia.email").is(searchCandidate.getSearchValue().trim()),
						Criteria.where("socialMedia.alternateEmail").is(searchCandidate.getSearchValue().trim()),
						Criteria.where("phones.number").is(searchCandidate.getSearchValue().trim()));
				Query query = new Query(criteria);
				candidates = mongoTemplate.find(query,Candidate.class,"candidate");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return candidates;
	}


	@Override
	public List<Candidate> candidatesList(boolean isAdmin,String employeeId) {
		List<Candidate> candidateList = null;
		try{
			if(isAdmin){
				candidateList = mongoTemplate.findAll(Candidate.class,"candidate");
			}else{
				Criteria criteria = new Criteria();
				criteria.orOperator(Criteria.where("createdBy.employeeId").is(employeeId),Criteria.where("createdBy.supervisorId").is(employeeId));
				Query query = new Query(criteria);
				candidateList = mongoTemplate.find(query,Candidate.class,"candidate");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return candidateList;
	}
	
	@Override
	public List<Candidate> findTraningEligableCandidateList() {
		List<Candidate> candidateList = null;
		try{
			
		Query query = new Query();
		//query.addCriteria(Criteria.where("uploadedEnrollmentPath").exists(true));
		//Chnage For ON Fly File Download//
		query.addCriteria(Criteria.where("uploadEnrollmentFormName").exists(true));
		candidateList = mongoTemplate.find(query,Candidate.class,"candidate");
		System.out.println("Candidate List Size"+candidateList.size());
		}catch(Exception e){
			e.printStackTrace();
		}
		return candidateList;
	}

	/*@Override
	public List<Candidate> candidatesList(int first, int rows, String sortField, int sortOrder,
			Map<String, Object> filters, boolean isAdmin, String employeeId) {

		System.out.println(first+"  "+rows+"   "+sortField+"   "+sortOrder+"    "+filters+"    "+isAdmin+"    "+employeeId);


		List<Candidate> candidateList = null;
		try{
			Criteria criteria = new Criteria();
			Query query = new Query(criteria);

			if(!isAdmin){
				criteria.orOperator(Criteria.where("createdBy.employeeId").is(employeeId),Criteria.where("createdBy.supervisorId").is(employeeId));
			}

			if (filters != null) {
				filters.forEach((k,v)->{
					     System.out.println("ttttttttt "+k+"    "+((LinkedHashMap<String,String>)v).get("value"));
					     criteria.where(k).regex(((LinkedHashMap<String,String>)v).get("value"));
				});
			}

			if(sortField != null){
				if(sortOrder == 1){
					query.with(new Sort(Sort.Direction.ASC,sortField));
				}else if(sortOrder == -1){
					query.with(new Sort(Sort.Direction.DESC,sortField));
				}
			}

			candidateList = mongoTemplate.find(query,Candidate.class,"candidate");

			System.out.println("SIZE OF CANDIDATE LIST : "+candidateList.size());

		}catch(Exception e){
			e.printStackTrace();
		}
		return candidateList;
	}*/


	@Override
	public Candidate checkEmail(String email, String id) {
		Candidate candidate = null;
		try{
			Criteria criteria = new Criteria();
			if("0".equals(id)){
				criteria.orOperator(Criteria.where("socialMedia.email").is(email.trim()),Criteria.where("socialMedia.alternateEmail").is(email.trim()));
				Query query = new Query(criteria);
				candidate = mongoTemplate.findOne(query,Candidate.class,"candidate");
			}else{
				Criteria c1 = criteria.orOperator(Criteria.where("socialMedia.email").is(email.trim()),Criteria.where("socialMedia.alternateEmail").is(email.trim()));
				Criteria c2 = Criteria.where("candidateId").ne(id);
				Criteria criteria2 = new Criteria();
				criteria2.andOperator(c1,c2);
				Query query = new Query(criteria2);
				candidate = mongoTemplate.findOne(query,Candidate.class,"candidate");
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return candidate;
	}


	@Override
	public Candidate checkMobile(String mobile,String id) {
		Candidate candidate = null;
		try{
			if("0".equals(id)){
				Query query = new Query();
				query.addCriteria(Criteria.where("phones.number").is(mobile.trim()));
				candidate = mongoTemplate.findOne(query,Candidate.class,"candidate");
			}else{
				Criteria criteria = new Criteria();
				Criteria c1 = Criteria.where("phones.number").is(mobile.trim());
				Criteria c2 = Criteria.where("candidateId").ne(id);
				criteria.andOperator(c1,c2);
				Query query = new Query(criteria);
				candidate = mongoTemplate.findOne(query,Candidate.class,"candidate");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return candidate;
	}



	@Override
	public List<FreePoolCandidate> findFreepoolCandidate(Date date) {

		List<FreePoolCandidate> freePoolCandidates = null;
		try{
			Criteria criteria = new Criteria();	

			GroupOperation groupOperation = Aggregation.group("candidateInfo.candidateId")
					.first("followupDate").as("followupDate") 
					.first("employeeId").as("employeeId")
					.first("candidateInfo.candidateId").as("candidateId")
					.first("candidateInfo.candidateName").as("candidateName")
					.first("candidateInfo.email").as("email")
					.first("candidateInfo.workMobile").as("workMobile")
					.first("candidateInfo.enrollmentStstus").as("enrollmentStstus")
					.first("candidateInfo.recruiterName").as("recruiterName")
					.max("followupDate").as("lastFollowup");

			SortOperation sortOperation = Aggregation.sort(new Sort(Direction.DESC, "followupDate"));

			Criteria criteriaForC1 = new Criteria();

			Criteria c1 = criteriaForC1.andOperator(Criteria.where("followupDate").lt(date),Criteria.where("candidateRemarks").is("New Entry"));
			Criteria c2 = Criteria.where("candidateRemarks").is("Not Interested");

			Criteria matchModeCriteria = criteria.orOperator(c1,c2);

			MatchOperation matchOperation = new MatchOperation(matchModeCriteria);

			ProjectionOperation projectionOperation = Aggregation.project("followupDate","employeeId","candidateId","candidateName","email","workMobile",
					"enrollmentStstus","recruiterName").and("candidateId").previousOperation();	



			freePoolCandidates = mongoTemplate.aggregate(Aggregation.newAggregation(sortOperation,groupOperation,matchOperation,projectionOperation),FollowUp.class,FreePoolCandidate.class).getMappedResults();

			for(FreePoolCandidate f:freePoolCandidates){
				System.out.println("Candidate Id: "+f.getCandidateId()+"    Follow up Date :"+f.getFollowupDate()+"     Status:  "+f.getEnrollmentStstus());
			}	

		}catch(Exception e){
			e.printStackTrace();
		}
		return freePoolCandidates;
	}

	@Override
	public Candidate findCandidateByUserId(String userId) {
		Candidate candidate = null;
		try{	
			Query query = new Query();
			query.addCriteria(Criteria.where("userMstId").is(userId));
			candidate = mongoTemplate.findOne(query,Candidate.class,"candidate");
		}catch(Exception e){
			e.printStackTrace();
		}
		return candidate;  
	}

	

}
