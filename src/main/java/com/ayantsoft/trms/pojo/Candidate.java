package com.ayantsoft.trms.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.ayantsoft.trms.pojo.PreferredLocation;

@Document(collection = "candidate")
public class Candidate implements Serializable {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = 8142745427355470426L;
	
	@Id
	private String id;
	private String candidateId;
	private SocialMedia socialMedia;
	private LoginCredential loginCredential;
	private String userMstId;
	private CreatedBy createdBy;
	private String currentLocation;
	private String payType;
	private String payRate;
	private String recruitmentSource;
	private Date createdDate;
	private String candidateName;
    private String enrollmentStstus;
    private Date enrollmentDate;
    private Date enrollmentFormSendDate;
    private Date enrollmentFormReceivedDate;
    private String uploadEnrollmentFormName;
    //private String uploadedEnrollmentPath;
    private Date registrationDate;
    private Date dateOfBirth;
    private String gender;
    private String country;
    private String street;
    private String paymentCounter;
    private String state;
    private String city;
    private String zip;
    private Double paymentDueAmount;
    private Date graduationDate;
    private Integer courseFee;
    private Date nextFollowupDate;
    private String generatedEnrollmentForm;
    private List<Phone> phones;
    private List<PreferredLocation> preferredLocations;
    private List<Immigration> immigrations;
    private String serviceName;
    private List<CandidateCourse> candidateCourses;
    private String employmentTypes;
    private String workAuthorization;
    private List<WorkExperience> workExperiences;
    private List<Skill> skills;
    private List<Education> educations;
    private List<Immigration> visas;
    
    
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
	public SocialMedia getSocialMedia() {
		return socialMedia;
	}
	public void setSocialMedia(SocialMedia socialMedia) {
		this.socialMedia = socialMedia;
	}
	public LoginCredential getLoginCredential() {
		return loginCredential;
	}
	public void setLoginCredential(LoginCredential loginCredential) {
		this.loginCredential = loginCredential;
	}
	public CreatedBy getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(CreatedBy createdBy) {
		this.createdBy = createdBy;
	}
	public String getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getPayRate() {
		return payRate;
	}
	public void setPayRate(String payRate) {
		this.payRate = payRate;
	}
	public String getRecruitmentSource() {
		return recruitmentSource;
	}
	public void setRecruitmentSource(String recruitmentSource) {
		this.recruitmentSource = recruitmentSource;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getEnrollmentStstus() {
		return enrollmentStstus;
	}
	public void setEnrollmentStstus(String enrollmentStstus) {
		this.enrollmentStstus = enrollmentStstus;
	}
	public Integer getCourseFee() {
		return courseFee;
	}
	public void setCourseFee(Integer courseFee) {
		this.courseFee = courseFee;
	}
	public List<Phone> getPhones() {
		return phones;
	}
	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}
	public List<PreferredLocation> getPreferredLocations() {
		return preferredLocations;
	}
	public void setPreferredLocations(List<PreferredLocation> preferredLocations) {
		this.preferredLocations = preferredLocations;
	}
	public List<Immigration> getImmigrations() {
		return immigrations;
	}
	public void setImmigrations(List<Immigration> immigrations) {
		this.immigrations = immigrations;
	}
	public List<CandidateCourse> getCandidateCourses() {
		return candidateCourses;
	}
	public void setCandidateCourses(List<CandidateCourse> candidateCourses) {
		this.candidateCourses = candidateCourses;
	}
	public Date getGraduationDate() {
		return graduationDate;
	}
	public void setGraduationDate(Date graduationDate) {
		this.graduationDate = graduationDate;
	}
	public Date getNextFollowupDate() {
		return nextFollowupDate;
	}
	public void setNextFollowupDate(Date nextFollowupDate) {
		this.nextFollowupDate = nextFollowupDate;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getUserMstId() {
		return userMstId;
	}
	public void setUserMstId(String userMstId) {
		this.userMstId = userMstId;
	}
	public Date getEnrollmentDate() {
		return enrollmentDate;
	}
	public void setEnrollmentDate(Date enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}
	public Date getEnrollmentFormSendDate() {
		return enrollmentFormSendDate;
	}
	public void setEnrollmentFormSendDate(Date enrollmentFormSendDate) {
		this.enrollmentFormSendDate = enrollmentFormSendDate;
	}
	public Date getEnrollmentFormReceivedDate() {
		return enrollmentFormReceivedDate;
	}
	public void setEnrollmentFormReceivedDate(Date enrollmentFormReceivedDate) {
		this.enrollmentFormReceivedDate = enrollmentFormReceivedDate;
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
	public List<Immigration> getVisas() {
		return visas;
	}
	public void setVisas(List<Immigration> visas) {
		this.visas = visas;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public Double getPaymentDueAmount() {
		return paymentDueAmount;
	}
	public void setPaymentDueAmount(Double paymentDueAmount) {
		this.paymentDueAmount = paymentDueAmount;
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
	public String getPaymentCounter() {
		return paymentCounter;
	}
	public void setPaymentCounter(String paymentCounter) {
		this.paymentCounter = paymentCounter;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getGeneratedEnrollmentForm() {
		return generatedEnrollmentForm;
	}
	public void setGeneratedEnrollmentForm(String generatedEnrollmentForm) {
		this.generatedEnrollmentForm = generatedEnrollmentForm;
	}
	public String getUploadEnrollmentFormName() {
		return uploadEnrollmentFormName;
	}
	public void setUploadEnrollmentFormName(String uploadEnrollmentFormName) {
		this.uploadEnrollmentFormName = uploadEnrollmentFormName;
	}
	/*public String getUploadedEnrollmentPath() {
		return uploadedEnrollmentPath;
	}
	public void setUploadedEnrollmentPath(String uploadedEnrollmentPath) {
		this.uploadedEnrollmentPath = uploadedEnrollmentPath;
	}*/
}
