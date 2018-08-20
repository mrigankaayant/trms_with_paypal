package com.ayantsoft.trms.dao;

import java.util.List;

import com.ayantsoft.trms.pojo.FollowUp;

public interface FollowupDao {
	
	void addFollowUp(FollowUp followUp);
	List<FollowUp> findFollowUpByCandidateId(String id);

}
