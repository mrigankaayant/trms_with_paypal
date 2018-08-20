package com.ayantsoft.trms.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.trms.dao.FollowupDao;
import com.ayantsoft.trms.pojo.FollowUp;
import com.ayantsoft.trms.service.FollowUpService;

@Service
public class FollowUpServiceImpl implements Serializable,FollowUpService {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = -45307400022852080L;
	
	@Autowired
	private FollowupDao followupDao;

	@Override
	public void addFollowUp(FollowUp followUp) {
		followupDao.addFollowUp(followUp);
		
	}

	@Override
	public List<FollowUp> findFollowUpByCandidateId(String id) {
		return followupDao.findFollowUpByCandidateId(id);
	}

}
