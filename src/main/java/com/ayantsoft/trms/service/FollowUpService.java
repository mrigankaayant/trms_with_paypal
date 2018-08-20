package com.ayantsoft.trms.service;

import java.util.List;

import com.ayantsoft.trms.pojo.FollowUp;

public interface FollowUpService {
	
	void addFollowUp(FollowUp followUp);
	List<FollowUp> findFollowUpByCandidateId(String id);

}
