package com.ayantsoft.trms.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ayantsoft.trms.dao.CandidateDao;
import com.ayantsoft.trms.pojo.Candidate;
import com.ayantsoft.trms.pojo.FreePoolCandidate;
import com.ayantsoft.trms.pojo.SearchCandidate;
import com.ayantsoft.trms.service.CandidateService;

@Service
public class CandidateServiceImpl implements CandidateService,Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8534751803540247308L;
	
	
	@Autowired
	private CandidateDao candidateDao;

	@Override
	public void addCandidate(Candidate candidate) {
		candidateDao.addCandidate(candidate);
	}

	@Override
	public Candidate findCandidateById(String candidateId) {
		return candidateDao.findCandidateById(candidateId);
	}

	@Override
	public void updateCandidate(Candidate candidate) {
		candidateDao.updateCandidate(candidate);
	}
	
	
	@Override
	public List<Candidate> candidatesList(boolean isAdmin,String employeeId) {
		return candidateDao.candidatesList(isAdmin,employeeId);
	}
    
	
	
	/*@Override
	public List<Candidate> candidatesList(int first, int rows, String sortField, int sortOrder,
			Map<String, Object> filters, boolean isAdmin, String employeeId) {
		
		return candidateDao.candidatesList(first,rows,sortField,sortOrder,filters,isAdmin,employeeId);
	}*/
	

	@Override
	public List<Candidate> findSearchingCandidate(SearchCandidate searchCandidate) {
		return candidateDao.findSearchingCandidate(searchCandidate);
	}

	@Override
	public Candidate checkEmail(String email, String id) {
		return candidateDao.checkEmail(email, id);
	}

	@Override
	public Candidate checkMobile(String mobile,String id) {
		return candidateDao.checkMobile(mobile,id);
	}

	@Override
	public List<FreePoolCandidate> findFreepoolCandidate(Date date) {
		return candidateDao.findFreepoolCandidate(date);
	}

	@Override
	public Candidate findCandidateByUserId(String userId) {
		return candidateDao.findCandidateByUserId(userId);
	}

	@Override
	public List<Candidate> findTraningEligableCandidateList() {
		
		return candidateDao.findTraningEligableCandidateList();
	}

}
