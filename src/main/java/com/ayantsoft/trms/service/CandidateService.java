package com.ayantsoft.trms.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ayantsoft.trms.pojo.Candidate;
import com.ayantsoft.trms.pojo.FreePoolCandidate;
import com.ayantsoft.trms.pojo.SearchCandidate;

public interface CandidateService {
	
	public void addCandidate(Candidate candidate);
	public Candidate findCandidateById(String candidateId);
	public void updateCandidate(Candidate candidate);
	public List<Candidate> candidatesList(boolean isAdmin,String employeeId);
	public List<Candidate> findSearchingCandidate(SearchCandidate searchCandidate);
	public Candidate checkEmail(String email, String id);
	public Candidate checkMobile(String mobile,String id);
	public List<FreePoolCandidate> findFreepoolCandidate(Date date);
    public Candidate findCandidateByUserId(String userId);
    public List<Candidate>findTraningEligableCandidateList();
    
    //List<Candidate> candidatesList(int first,int rows,String sortField,int sortOrder,Map<String,Object> filters,boolean isAdmin,String employeeId);
}
