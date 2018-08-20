package com.ayantsoft.trms.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "enrollment_form_no")
public class EnrollmentFormNo {
	
	@Id
	private String id;
	private String docName;
	private Integer enrollmentFormNumber;

	public int getEnrollmentFormNumber() {
		return enrollmentFormNumber;
	}

	public void setEnrollmentFormNumber(int enrollmentFormNumber) {
		this.enrollmentFormNumber = enrollmentFormNumber;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}
	
	
}
