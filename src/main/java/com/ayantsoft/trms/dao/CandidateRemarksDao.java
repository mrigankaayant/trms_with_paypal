package com.ayantsoft.trms.dao;

import java.util.List;

import com.ayantsoft.trms.pojo.CandidateRemarks;

public interface CandidateRemarksDao {
	
	List<CandidateRemarks> list();
	List<CandidateRemarks> findCandidateRemarks(String applicableFor);
	

}
