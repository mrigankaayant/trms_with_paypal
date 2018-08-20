package com.ayantsoft.trms.service;

import java.util.List;

import com.ayantsoft.trms.pojo.CandidateRemarks;

public interface CandidateRemarksService {
	
	List<CandidateRemarks> remarksList();
	List<CandidateRemarks> findCandidateRemarks(String applicableFor);

}
