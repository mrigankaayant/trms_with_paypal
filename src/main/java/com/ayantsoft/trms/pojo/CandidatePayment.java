package com.ayantsoft.trms.pojo;

public class CandidatePayment {
	
    private String coureseName;
    private Integer amount;
    private String candidateName;
    private String email;
    private String workMobile;
    private String description;
    private Integer pricePerItem;
    private Integer quantity;
    private String candidateId;
    private String returnUrl;
    

	public String getCoureseName() {
		return coureseName;
	}
	public void setCoureseName(String coureseName) {
		this.coureseName = coureseName;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getPricePerItem() {
		return pricePerItem;
	}
	public void setPricePerItem(Integer pricePerItem) {
		this.pricePerItem = pricePerItem;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getWorkMobile() {
		return workMobile;
	}
	public void setWorkMobile(String workMobile) {
		this.workMobile = workMobile;
	}
	
}
