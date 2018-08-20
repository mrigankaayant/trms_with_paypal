package com.ayantsoft.trms.service;

import java.util.List;

import com.ayantsoft.trms.pojo.PhoneLogs;

public interface PhoneLogsService {
	
	List<PhoneLogs> getPhoneLogsByCandidateId(String candidateId);
	List<PhoneLogs> getPhoneLogsByEmployeeId(String employeeId,boolean isAdmin);

}
