package com.ayantsoft.trms.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ayantsoft.trms.dao.MaxFollowupDao;
import com.ayantsoft.trms.pojo.MaxFollowup;
import com.ayantsoft.trms.service.MaxFollowupService;

@Service
public class MaxFollowupServiceImpl implements Serializable,MaxFollowupService {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = 3650469153261723816L;
	
	@Autowired
	private MaxFollowupDao maxFollowupDao;

	@Override
	public MaxFollowup findMaxFollowup(String candidateId) {
		return maxFollowupDao.findMaxFollowup(candidateId);
	}

	@Override
	public void save(MaxFollowup maxFollowup) {
		maxFollowupDao.save(maxFollowup);
	}

	@Override
	public void update(MaxFollowup maxFollowup) {
		maxFollowupDao.update(maxFollowup);
	}

	@Override
	public List<MaxFollowup> findFreePoolCandidates(Date dateBefore30Days) {
		return maxFollowupDao.findFreePoolCandidates(dateBefore30Days);
	}
	
}
