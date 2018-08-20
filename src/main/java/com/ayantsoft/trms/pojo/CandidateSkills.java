package com.ayantsoft.trms.pojo;

import java.io.Serializable;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "candidate_skill")
public class CandidateSkills implements Serializable {
	
	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = -7317645307313084932L;
	
	@Id
	private String id;
	private String candidateId;
	private String expertiesLevel;
	private Date lastUsed;
	private String techSkill;
	private Integer yearsOfExp;
	
	
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
	public String getExpertiesLevel() {
		return expertiesLevel;
	}
	public void setExpertiesLevel(String expertiesLevel) {
		this.expertiesLevel = expertiesLevel;
	}
	public Date getLastUsed() {
		return lastUsed;
	}
	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}
	public String getTechSkill() {
		return techSkill;
	}
	public void setTechSkill(String techSkill) {
		this.techSkill = techSkill;
	}
	public Integer getYearsOfExp() {
		return yearsOfExp;
	}
	public void setYearsOfExp(Integer yearsOfExp) {
		this.yearsOfExp = yearsOfExp;
	}
}
