package com.ayantsoft.trms.pojo;

import java.io.Serializable;
import java.util.Date;

public class CandidateInfoWithFollowup implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5518213296504804690L;
	
	private String candidateName;
	private String alternateEmail;
	private String email;
	private String payRate;
	private String workMobile;
	private String skill;
	private String homeMobile;
	private String graduationDate;
	private String visa;
	private String enrollmentStatus;
	private String recruitmentSource;
	private String visaStartDate;
	private String currentLocation;
	private String prefferedLocation;
	private Integer courseFee;
	
	private Date followUpDate;
	private Date nextFollowUpDate;
	private String remarks;
	
	
	
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getAlternateEmail() {
		return alternateEmail;
	}
	public void setAlternateEmail(String alternateEmail) {
		this.alternateEmail = alternateEmail;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPayRate() {
		return payRate;
	}
	public void setPayRate(String payRate) {
		this.payRate = payRate;
	}
	public String getWorkMobile() {
		return workMobile;
	}
	public void setWorkMobile(String workMobile) {
		this.workMobile = workMobile;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public String getHomeMobile() {
		return homeMobile;
	}
	public void setHomeMobile(String homeMobile) {
		this.homeMobile = homeMobile;
	}
	public String getGraduationDate() {
		return graduationDate;
	}
	public void setGraduationDate(String graduationDate) {
		this.graduationDate = graduationDate;
	}
	public String getVisa() {
		return visa;
	}
	public void setVisa(String visa) {
		this.visa = visa;
	}
	public String getEnrollmentStatus() {
		return enrollmentStatus;
	}
	public void setEnrollmentStatus(String enrollmentStatus) {
		this.enrollmentStatus = enrollmentStatus;
	}
	public String getRecruitmentSource() {
		return recruitmentSource;
	}
	public void setRecruitmentSource(String recruitmentSource) {
		this.recruitmentSource = recruitmentSource;
	}
	public String getVisaStartDate() {
		return visaStartDate;
	}
	public void setVisaStartDate(String visaStartDate) {
		this.visaStartDate = visaStartDate;
	}
	public String getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	public String getPrefferedLocation() {
		return prefferedLocation;
	}
	public void setPrefferedLocation(String prefferedLocation) {
		this.prefferedLocation = prefferedLocation;
	}
	public Integer getCourseFee() {
		return courseFee;
	}
	public void setCourseFee(Integer courseFee) {
		this.courseFee = courseFee;
	}
	public Date getFollowUpDate() {
		return followUpDate;
	}
	public void setFollowUpDate(Date followUpDate) {
		this.followUpDate = followUpDate;
	}
	public Date getNextFollowUpDate() {
		return nextFollowUpDate;
	}
	public void setNextFollowUpDate(Date nextFollowUpDate) {
		this.nextFollowUpDate = nextFollowUpDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
