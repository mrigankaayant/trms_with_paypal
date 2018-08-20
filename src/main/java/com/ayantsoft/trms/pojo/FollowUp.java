package com.ayantsoft.trms.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="follow_up")
public class FollowUp implements Serializable  {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = 4684520868401413526L;
	
	@Id
	private String id; 
	private String followupId;
	private Date followupDate;	
	private String followupRemarks;
	private String candidateRemarks;
	private String employeeId;
	private Date scheduledDate;
	private String followUpType;
	private CandidateInfo candidateInfo;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFollowupId() {
		return followupId;
	}
	public void setFollowupId(String followupId) {
		this.followupId = followupId;
	}
	public Date getFollowupDate() {
		return followupDate;
	}
	public void setFollowupDate(Date followupDate) {
		this.followupDate = followupDate;
	}
	public String getFollowupRemarks() {
		return followupRemarks;
	}
	public void setFollowupRemarks(String followupRemarks) {
		this.followupRemarks = followupRemarks;
	}
	public String getCandidateRemarks() {
		return candidateRemarks;
	}
	public void setCandidateRemarks(String candidateRemarks) {
		this.candidateRemarks = candidateRemarks;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public Date getScheduledDate() {
		return scheduledDate;
	}
	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}
	public String getFollowUpType() {
		return followUpType;
	}
	public void setFollowUpType(String followUpType) {
		this.followUpType = followUpType;
	}
	public CandidateInfo getCandidateInfo() {
		return candidateInfo;
	}
	public void setCandidateInfo(CandidateInfo candidateInfo) {
		this.candidateInfo = candidateInfo;
	}
}
