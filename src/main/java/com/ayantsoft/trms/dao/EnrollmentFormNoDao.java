package com.ayantsoft.trms.dao;

import com.ayantsoft.trms.pojo.EnrollmentFormNo;

public interface EnrollmentFormNoDao {
	
	public EnrollmentFormNo getMaxFormNumber(String docName);
	public void update(EnrollmentFormNo enrollmentFormNo);

}
