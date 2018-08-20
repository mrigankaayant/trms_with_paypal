package com.ayantsoft.trms.pojo;

public class PaymentInfoPayer {
	
	private String paymentMethod;
    private String status;
    private PaymentPayerInfo payerInfo;
    
    
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public PaymentPayerInfo getPayerInfo() {
		return payerInfo;
	}
	public void setPayerInfo(PaymentPayerInfo payerInfo) {
		this.payerInfo = payerInfo;
	}
}
