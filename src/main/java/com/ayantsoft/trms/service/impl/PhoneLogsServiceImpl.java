package com.ayantsoft.trms.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ayantsoft.trms.dao.PhoneLogsDao;
import com.ayantsoft.trms.pojo.PhoneLogs;
import com.ayantsoft.trms.service.PhoneLogsService;

@Service
public class PhoneLogsServiceImpl implements Serializable,PhoneLogsService {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6744839218260910476L;
	
	@Autowired
	private PhoneLogsDao phoneLogsDao;

	@Override
	public List<PhoneLogs> getPhoneLogsByCandidateId(String candidateId) {
		return phoneLogsDao.getPhoneLogsByCandidateId(candidateId);
	}

	@Override
	public List<PhoneLogs> getPhoneLogsByEmployeeId(String employeeId, boolean isAdmin) {
		return phoneLogsDao.getPhoneLogsByEmployeeId(employeeId, isAdmin);
	}

}
