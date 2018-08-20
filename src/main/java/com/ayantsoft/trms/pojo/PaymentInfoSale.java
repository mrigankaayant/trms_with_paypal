package com.ayantsoft.trms.pojo;



public class PaymentInfoSale {
	
     private PaymentInfoAmount amount; 
     private String paymentMode;
     private String state;
     private String protectionEligibility;
     private String protectionEligibilityType;
     private PaymentInfoTransactionFee transactionFee;
     private String receiptId;
     private String parentPayment;
     private String createTime;
     private String updateTime;
     
     
	
	public PaymentInfoAmount getAmount() {
		return amount;
	}
	public void setAmount(PaymentInfoAmount amount) {
		this.amount = amount;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getProtectionEligibility() {
		return protectionEligibility;
	}
	public void setProtectionEligibility(String protectionEligibility) {
		this.protectionEligibility = protectionEligibility;
	}
	public String getProtectionEligibilityType() {
		return protectionEligibilityType;
	}
	public void setProtectionEligibilityType(String protectionEligibilityType) {
		this.protectionEligibilityType = protectionEligibilityType;
	}
	public PaymentInfoTransactionFee getTransactionFee() {
		return transactionFee;
	}
	public void setTransactionFee(PaymentInfoTransactionFee transactionFee) {
		this.transactionFee = transactionFee;
	}
	public String getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}
	public String getParentPayment() {
		return parentPayment;
	}
	public void setParentPayment(String parentPayment) {
		this.parentPayment = parentPayment;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
