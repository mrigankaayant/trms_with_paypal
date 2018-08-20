package com.ayantsoft.trms.pojo;

import java.io.Serializable;
import java.util.Date;

public class FollowupCandidateInfo implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7846445823468985394L;
	
	private String candidateId;
	private String candidateName;
	private String email;
	private String workMobile;
	private String visa;
	private String skill;
	private String enrollmentStstus;
	private Date nextFollowupDate;
	
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
	public String getVisa() {
		return visa;
	}
	public void setVisa(String visa) {
		this.visa = visa;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public String getEnrollmentStstus() {
		return enrollmentStstus;
	}
	public void setEnrollmentStstus(String enrollmentStstus) {
		this.enrollmentStstus = enrollmentStstus;
	}
	public Date getNextFollowupDate() {
		return nextFollowupDate;
	}
	public void setNextFollowupDate(Date nextFollowupDate) {
		this.nextFollowupDate = nextFollowupDate;
	}
}
