package com.ayantsoft.trms.service;

import com.ayantsoft.trms.pojo.EnrollmentFormNo;

public interface EnrollmentFormService {
	
	public EnrollmentFormNo getMaxFormNumber(String docName);
	public void update(EnrollmentFormNo enrollmentFormNo);

}
