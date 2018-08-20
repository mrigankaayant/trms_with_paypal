package com.ayantsoft.trms.service.impl;

import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ayantsoft.trms.dao.EnrollmentFormNoDao;
import com.ayantsoft.trms.pojo.EnrollmentFormNo;
import com.ayantsoft.trms.service.EnrollmentFormService;

@Service
public class EnrollmentFormServiceImpl implements Serializable,EnrollmentFormService {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5254693133390331432L;

	@Autowired
	private EnrollmentFormNoDao enrollmentFormNoDao;

	@Override
	public EnrollmentFormNo getMaxFormNumber(String docName) {
		return enrollmentFormNoDao.getMaxFormNumber(docName);
	}

	@Override
	public void update(EnrollmentFormNo enrollmentFormNo) {
		enrollmentFormNoDao.update(enrollmentFormNo);
	}
}
