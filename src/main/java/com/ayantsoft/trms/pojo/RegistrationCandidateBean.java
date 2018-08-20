package com.ayantsoft.trms.pojo;

import java.util.Date;
import java.util.List;

public class RegistrationCandidateBean {
	
	private String id;
	private String candidateId;
	private String candidateName;
	private Date dateOfBirth;
	private String gender;
	private String email;
	private String alternateEmail;
	private String workMobile;
	private String homeMobile;
	private String country;
	private String state;
	private String city;
	private String zip;
	private String street;
	private Date registrationDate;
	private String currentLocation;
	private String linkedinUrl;
	private String twitterUrl;
	private String facebookUrl;
	private String personalWebsite;
	private List<Immigration> visas;
	private List<PreferredLocation> preferredLocations;
	private String employmentTypes;
    private String workAuthorization;
    private List<WorkExperience> workExperiences;
    private List<Skill> skills;
    private List<Education> educations;
    private List<CandidateCourse> candidateCourses;
    private String paymentCounter;
	
	
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAlternateEmail() {
		return alternateEmail;
	}
	public void setAlternateEmail(String alternateEmail) {
		this.alternateEmail = alternateEmail;
	}
	public String getWorkMobile() {
		return workMobile;
	}
	public void setWorkMobile(String workMobile) {
		this.workMobile = workMobile;
	}
	public String getHomeMobile() {
		return homeMobile;
	}
	public void setHomeMobile(String homeMobile) {
		this.homeMobile = homeMobile;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	
	public List<Immigration> getVisas() {
		return visas;
	}
	public void setVisas(List<Immigration> visas) {
		this.visas = visas;
	}
	public List<PreferredLocation> getPreferredLocations() {
		return preferredLocations;
	}
	public void setPreferredLocations(List<PreferredLocation> preferredLocations) {
		this.preferredLocations = preferredLocations;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getEmploymentTypes() {
		return employmentTypes;
	}
	public void setEmploymentTypes(String employmentTypes) {
		this.employmentTypes = employmentTypes;
	}
	public String getWorkAuthorization() {
		return workAuthorization;
	}
	public void setWorkAuthorization(String workAuthorization) {
		this.workAuthorization = workAuthorization;
	}
	public List<WorkExperience> getWorkExperiences() {
		return workExperiences;
	}
	public void setWorkExperiences(List<WorkExperience> workExperiences) {
		this.workExperiences = workExperiences;
	}
	public List<Skill> getSkills() {
		return skills;
	}
	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}
	public List<Education> getEducations() {
		return educations;
	}
	public void setEducations(List<Education> educations) {
		this.educations = educations;
	}
	public String getLinkedinUrl() {
		return linkedinUrl;
	}
	public void setLinkedinUrl(String linkedinUrl) {
		this.linkedinUrl = linkedinUrl;
	}
	public String getTwitterUrl() {
		return twitterUrl;
	}
	public void setTwitterUrl(String twitterUrl) {
		this.twitterUrl = twitterUrl;
	}
	public String getFacebookUrl() {
		return facebookUrl;
	}
	public void setFacebookUrl(String facebookUrl) {
		this.facebookUrl = facebookUrl;
	}
	public String getPersonalWebsite() {
		return personalWebsite;
	}
	public void setPersonalWebsite(String personalWebsite) {
		this.personalWebsite = personalWebsite;
	}
	public String getPaymentCounter() {
		return paymentCounter;
	}
	public void setPaymentCounter(String paymentCounter) {
		this.paymentCounter = paymentCounter;
	}
	public List<CandidateCourse> getCandidateCourses() {
		return candidateCourses;
	}
	public void setCandidateCourses(List<CandidateCourse> candidateCourses) {
		this.candidateCourses = candidateCourses;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}	
}
