package com.ayantsoft.trms.pojo;

import java.io.Serializable;
import java.util.Date;

public class FreePoolCandidate implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1880939401443574319L;
	
	
	private Date followupDate;
	private String employeeId;
	private String candidateId;
	private String candidateName;
	private String email;
	private String workMobile;
	private String enrollmentStstus;
	private String recruiterName;
	private Date lastFollowup;
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
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
	public Date getFollowupDate() {
		return followupDate;
	}
	public void setFollowupDate(Date followupDate) {
		this.followupDate = followupDate;
	}
	public Date getLastFollowup() {
		return lastFollowup;
	}
	public void setLastFollowup(Date lastFollowup) {
		this.lastFollowup = lastFollowup;
	}
}
