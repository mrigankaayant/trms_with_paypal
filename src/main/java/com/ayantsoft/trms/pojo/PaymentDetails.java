package com.ayantsoft.trms.pojo;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "payment_details")
public class PaymentDetails {
	
	@Id
	private String id;
	private String paymentDetailsId;
    private String coureseName;
    private Integer amount;
    private String candidateName;
    private String email;
    private String workMobile;
    private String description;
    private Integer pricePerItem;
    private Integer dueAmount;
    private Integer quantity;
    private String candidateId;
    private String returnUrl;
    private String status;
    
    private String username;
    private Date createdDate;
    private String createdYear;
    private String createdMonth;
    private String token;
    
    private CreatedBy createdBy;
  
    private PaymentInfo paymentInfo;
   

	public String getPaymentDetailsId() {
		return paymentDetailsId;
	}

	public void setPaymentDetailsId(String paymentDetailsId) {
		this.paymentDetailsId = paymentDetailsId;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
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

	public Integer getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(Integer dueAmount) {
		this.dueAmount = dueAmount;
	}

	public CreatedBy getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(CreatedBy createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedYear() {
		return createdYear;
	}

	public void setCreatedYear(String createdYear) {
		this.createdYear = createdYear;
	}

	public String getCreatedMonth() {
		return createdMonth;
	}

	public void setCreatedMonth(String createdMonth) {
		this.createdMonth = createdMonth;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
