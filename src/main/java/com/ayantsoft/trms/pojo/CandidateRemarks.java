package com.ayantsoft.trms.pojo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="candidate_remarks")
public class CandidateRemarks implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 853446996436845810L;
	
	@Id
	private String id;
    private String description;
    private Integer orderIndex;
    private String statusType;
    private List<String> applicableFor;
    
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getOrderIndex() {
		return orderIndex;
	}
	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}
	public String getStatusType() {
		return statusType;
	}
	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}
	public List<String> getApplicableFor() {
		return applicableFor;
	}
	public void setApplicableFor(List<String> applicableFor) {
		this.applicableFor = applicableFor;
	}
}
