package com.ayantsoft.trms.pojo;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "incentive_structure")
public class IncentiveStructure implements Serializable{
	
	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = -6700685326774074885L;
	
	private Double targetLowerLimit;
	private Double targetHigherLimit;
	private Double incentiveInInr;
	private String typeFor;
	
	
	public Double getTargetLowerLimit() {
		return targetLowerLimit;
	}
	public void setTargetLowerLimit(Double targetLowerLimit) {
		this.targetLowerLimit = targetLowerLimit;
	}
	public Double getTargetHigherLimit() {
		return targetHigherLimit;
	}
	public void setTargetHigherLimit(Double targetHigherLimit) {
		this.targetHigherLimit = targetHigherLimit;
	}
	public Double getIncentiveInInr() {
		return incentiveInInr;
	}
	public void setIncentiveInInr(Double incentiveInInr) {
		this.incentiveInInr = incentiveInInr;
	}
	public String getTypeFor() {
		return typeFor;
	}
	public void setTypeFor(String typeFor) {
		this.typeFor = typeFor;
	}
}
