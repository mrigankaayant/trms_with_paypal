package com.ayantsoft.trms.dao;

import java.util.Date;
import java.util.List;

import com.ayantsoft.trms.pojo.MaxFollowup;

public interface MaxFollowupDao {
	
	public MaxFollowup findMaxFollowup(String candidateId);
	public void save(MaxFollowup maxFollowup);
	public void update(MaxFollowup maxFollowup);
	public List<MaxFollowup> findFreePoolCandidates(Date dateBefore30Days);

}
