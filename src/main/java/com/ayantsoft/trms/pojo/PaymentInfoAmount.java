package com.ayantsoft.trms.pojo;

public class PaymentInfoAmount {
	
	private String currency;
    private String total;
    private PaymentInfoDetails details;
    
    
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public PaymentInfoDetails getDetails() {
		return details;
	}
	public void setDetails(PaymentInfoDetails details) {
		this.details = details;
	}
}
