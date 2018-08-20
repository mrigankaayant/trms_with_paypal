package com.ayantsoft.trms.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AddCandidateBean implements Serializable {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = -7266513918713399842L;
	
	private String candidateId;
	private String candidateName;
	private String payType;
	private String email;
	private String payRate;
	private String alternateEmail;
	private Date graduationDate;
	private String workMobile;
	private String currentLocation;
	private String homeMobile;
	private String visa;
	private Date visaStartDate;
	private String recruitmentSource;
	private List<String> skill;
	private Integer courseFee;
	private String enrollmentStstus;
	private Date nextFollowUpDate;
	private String serviceName;
	private List<PreferredLocation> preferredLocations;
	private List<Immigration> immigrations;
	
	
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
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
	public String getAlternateEmail() {
		return alternateEmail;
	}
	public void setAlternateEmail(String alternateEmail) {
		this.alternateEmail = alternateEmail;
	}
	public Date getGraduationDate() {
		return graduationDate;
	}
	public void setGraduationDate(Date graduationDate) {
		this.graduationDate = graduationDate;
	}
	public String getWorkMobile() {
		return workMobile;
	}
	public void setWorkMobile(String workMobile) {
		this.workMobile = workMobile;
	}
	public String getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	public String getHomeMobile() {
		return homeMobile;
	}
	public void setHomeMobile(String homeMobile) {
		this.homeMobile = homeMobile;
	}
	public String getVisa() {
		return visa;
	}
	public void setVisa(String visa) {
		this.visa = visa;
	}
	public Date getVisaStartDate() {
		return visaStartDate;
	}
	public void setVisaStartDate(Date visaStartDate) {
		this.visaStartDate = visaStartDate;
	}
	public String getRecruitmentSource() {
		return recruitmentSource;
	}
	public void setRecruitmentSource(String recruitmentSource) {
		this.recruitmentSource = recruitmentSource;
	}
	
	public List<String> getSkill() {
		return skill;
	}
	public void setSkill(List<String> skill) {
		this.skill = skill;
	}
	public Integer getCourseFee() {
		return courseFee;
	}
	public void setCourseFee(Integer courseFee) {
		this.courseFee = courseFee;
	}
	public List<PreferredLocation> getPreferredLocations() {
		return preferredLocations;
	}
	public void setPreferredLocations(List<PreferredLocation> preferredLocations) {
		this.preferredLocations = preferredLocations;
	}
	public String getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}
	
	public Date getNextFollowUpDate() {
		return nextFollowUpDate;
	}
	public void setNextFollowUpDate(Date nextFollowUpDate) {
		this.nextFollowUpDate = nextFollowUpDate;
	}
	public String getEnrollmentStstus() {
		return enrollmentStstus;
	}
	public void setEnrollmentStstus(String enrollmentStstus) {
		this.enrollmentStstus = enrollmentStstus;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public List<Immigration> getImmigrations() {
		return immigrations;
	}
	public void setImmigrations(List<Immigration> immigrations) {
		this.immigrations = immigrations;
	}
}	