package com.ayantsoft.trms.dao;

import java.util.List;

import com.ayantsoft.trms.pojo.PhoneLogs;

public interface PhoneLogsDao {
	List<PhoneLogs> getPhoneLogsByCandidateId(String candidateId);
	List<PhoneLogs> getPhoneLogsByEmployeeId(String employeeId,boolean isAdmin);
}
