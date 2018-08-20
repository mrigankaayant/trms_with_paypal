package com.ayantsoft.trms.pojo;

import java.io.Serializable;

public class CandidateInfo implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4946676014388776566L;
	
	
	private String candidateId;
	private String candidateName;
	private String email;
	private String workMobile;
	private String enrollmentStstus;
	private String recruiterName;
	
	
	public String getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWorkMobile() {
		return workMobile;
	}
	public void setWorkMobile(String workMobile) {
		this.workMobile = workMobile;
	}
	public String getEnrollmentStstus() {
		return enrollmentStstus;
	}
	public void setEnrollmentStstus(String enrollmentStstus) {
		this.enrollmentStstus = enrollmentStstus;
	}
	public String getRecruiterName() {
		return recruiterName;
	}
	public void setRecruiterName(String recruiterName) {
		this.recruiterName = recruiterName;
	}

}
