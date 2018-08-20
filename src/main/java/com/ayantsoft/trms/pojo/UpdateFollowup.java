package com.ayantsoft.trms.pojo;

import java.io.Serializable;
import java.util.Date;

public class UpdateFollowup implements Serializable {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = -7768970286969482780L;
	
	private String candidateId;
	private Date nextFollowUpDate;
	private String followupRemarks;
	
	
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
	public String getFollowupRemarks() {
		return followupRemarks;
	}
	public void setFollowupRemarks(String followupRemarks) {
		this.followupRemarks = followupRemarks;
	}
}
